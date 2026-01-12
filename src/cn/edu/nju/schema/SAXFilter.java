package cn.edu.nju.schema;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileWriter;
import java.io.IOException;

public class SAXFilter {

    public static void filterFailed(String inputXml, String outputXml) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            FailedHandler handler = new FailedHandler(outputXml);
            saxParser.parse(inputXml, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class FailedHandler extends DefaultHandler {
        private String outputFile;
        private StringBuilder xmlContent = new StringBuilder();
        private StringBuilder currentCourseBuffer = new StringBuilder();
        private StringBuilder currentStudentBuffer = new StringBuilder();

        private boolean isFailingStudent = false;
        private boolean insideStudentScore = false;
        private boolean insideScoreTotal = false;
        private String currentCourseId = "";

        public FailedHandler(String path) {
            this.outputFile = path;
            xmlContent.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<FailedList>\n");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equals("Course")) {
                currentCourseId = attributes.getValue("id");
            } else if (qName.equals("StudentScore")) {
                insideStudentScore = true;
                isFailingStudent = false;
                currentStudentBuffer.setLength(0);
                currentStudentBuffer.append("    <StudentScore studentId=\"" + attributes.getValue("studentId") + "\" courseId=\"" + currentCourseId + "\">\n");
            } else if (insideStudentScore) {
                currentStudentBuffer.append("      <" + qName + ">");
                if (qName.endsWith("scoreTotal")) insideScoreTotal = true;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            if (insideStudentScore) {
                String content = new String(ch, start, length);
                currentStudentBuffer.append(content);
                if (insideScoreTotal) {
                    try {
                        int score = Integer.parseInt(content.trim());
                        if (score < 60) isFailingStudent = true;
                    } catch (NumberFormatException e) {}
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if (qName.equals("StudentScore")) {
                currentStudentBuffer.append("    </StudentScore>\n");
                if (isFailingStudent) {
                    xmlContent.append(currentStudentBuffer.toString());
                }
                insideStudentScore = false;
            } else if (insideStudentScore) {
                currentStudentBuffer.append("</" + qName + ">\n");
                if (qName.endsWith("scoreTotal")) insideScoreTotal = false;
            }
        }

        @Override
        public void endDocument() {
            xmlContent.append("</FailedList>");
            try (FileWriter fw = new FileWriter(outputFile)) {
                fw.write(xmlContent.toString());
                System.out.println("XML5 (不及格名单) 生成成功: " + outputFile);
            } catch (IOException e) { e.printStackTrace(); }
        }
    }
}