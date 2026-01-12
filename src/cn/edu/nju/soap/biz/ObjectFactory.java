
package cn.edu.nju.soap.biz;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cn.edu.nju.soap.biz package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ServiceFault_QNAME = new QName("http://jw.nju.edu.cn/wsdl", "ServiceFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cn.edu.nju.soap.biz
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetStudentResponse }
     * 
     */
    public GetStudentResponse createGetStudentResponse() {
        return new GetStudentResponse();
    }

    /**
     * Create an instance of {@link StudentType }
     * 
     */
    public StudentType createStudentType() {
        return new StudentType();
    }

    /**
     * Create an instance of {@link GetStudentRequest }
     * 
     */
    public GetStudentRequest createGetStudentRequest() {
        return new GetStudentRequest();
    }

    /**
     * Create an instance of {@link CheckInRequest }
     * 
     */
    public CheckInRequest createCheckInRequest() {
        return new CheckInRequest();
    }

    /**
     * Create an instance of {@link CheckInResponse }
     * 
     */
    public CheckInResponse createCheckInResponse() {
        return new CheckInResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jw.nju.edu.cn/wsdl", name = "ServiceFault")
    public JAXBElement<String> createServiceFault(String value) {
        return new JAXBElement<String>(_ServiceFault_QNAME, String.class, null, value);
    }

}
