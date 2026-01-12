
package cn.edu.nju.soap.biz;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="studentId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="authEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "studentId",
    "authEmail"
})
@XmlRootElement(name = "getStudentRequest")
public class GetStudentRequest {

    @XmlElement(required = true)
    protected String studentId;
    @XmlElement(required = true)
    protected String authEmail;

    /**
     * 获取studentId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * 设置studentId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudentId(String value) {
        this.studentId = value;
    }

    /**
     * 获取authEmail属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthEmail() {
        return authEmail;
    }

    /**
     * 设置authEmail属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthEmail(String value) {
        this.authEmail = value;
    }

}
