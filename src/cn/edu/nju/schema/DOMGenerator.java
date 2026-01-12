package cn.edu.nju.schema;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Random;

public class DOMGenerator {
    private static final String NS_JW = "http://jw.nju.edu.cn/schema";

    public static void generateXML3(String inputPath, String outputPath) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            Element root = doc.createElementNS(NS_JW, "StudentList");
            root.setAttribute("xmlns", NS_JW);
            doc.appendChild(root);

            Random rand = new Random();
            boolean atLeastOneFail = false; // 是否已出现不及格

            for (int i = 0; i < 10; i++) {
                Element student = doc.createElementNS(NS_JW, "Student");

                Element id = doc.createElementNS(NS_JW, "id");
                id.setTextContent("STU" + (1000 + i));
                student.appendChild(id);

                Element name = doc.createElementNS(NS_JW, "name");
                name.setTextContent("Student_" + i);
                student.appendChild(name);

                Element gender = doc.createElementNS(NS_JW, "gender");
                gender.setTextContent(rand.nextBoolean() ? "Male" : "Female");
                student.appendChild(gender);

                for (int c = 1; c <= 5; c++) {
                    Element course = doc.createElementNS(NS_JW, "Course");
                    course.setAttribute("courseId", "C0" + c);

                    int daily = 60 + rand.nextInt(41);
                    int finalS = 60 + rand.nextInt(41);

                    // 强制逻辑：如果是最后一个学生且还没不及格，强制不及格

                    if ((i == 9 && !atLeastOneFail) || rand.nextInt(100) < 15) {
                        finalS = rand.nextInt(60);
                        atLeastOneFail = true;
                    }

                    int total = (int)(daily * 0.3 + finalS * 0.7);

                    Element eDaily = doc.createElementNS(NS_JW, "scoreDaily");
                    eDaily.setTextContent(String.valueOf(daily));
                    Element eFinal = doc.createElementNS(NS_JW, "scoreFinal");
                    eFinal.setTextContent(String.valueOf(finalS));
                    Element eTotal = doc.createElementNS(NS_JW, "scoreTotal");
                    eTotal.setTextContent(String.valueOf(total));

                    course.appendChild(eDaily);
                    course.appendChild(eFinal);
                    course.appendChild(eTotal);
                    student.appendChild(course);
                }
                root.appendChild(student);
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(new DOMSource(doc), new StreamResult(new File(outputPath)));
            System.out.println("XML3 生成成功: " + outputPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}