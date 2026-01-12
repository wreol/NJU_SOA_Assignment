package cn.edu.nju.schema;

public class AssignmentMain {
    public static void main(String[] args) {
        String xml1 = "xml_data/xml1.xml";
        String xml3 = "xml_data/xml3.xml";
        String xml4 = "xml_data/xml4.xml";
        String xml5 = "xml_data/xml5.xml";
        String xsl  = "xml_data/score_transform.xsl";

        System.out.println("========== 开始执行作业任务 ==========");

        DOMGenerator.generateXML3(xml1, xml3);

        XSLTTransformer.transform(xsl, xml3, xml4);

        SAXFilter.filterFailed(xml4, xml5);

        System.out.println("========== 全部任务执行完毕 ==========");
        System.out.println("请刷新项目目录查看 xml_data 文件夹下的生成文件。");
    }
}