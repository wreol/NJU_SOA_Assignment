package cn.edu.nju.schema;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class XSLTTransformer {
    public static void transform(String xslPath, String xmlInput, String xmlOutput) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(new File(xslPath));
            Transformer transformer = factory.newTransformer(xslt);

            Source text = new StreamSource(new File(xmlInput));
            transformer.transform(text, new StreamResult(new File(xmlOutput)));

            System.out.println("XML4 (转换后) 生成成功: " + xmlOutput);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}