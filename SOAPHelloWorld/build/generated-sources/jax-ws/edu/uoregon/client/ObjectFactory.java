
package edu.uoregon.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the edu.uoregon.client package. 
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

    private final static QName _GetLocationResponse_QNAME = new QName("http://cs.uoregon.edu/", "getLocationResponse");
    private final static QName _HelloResponse_QNAME = new QName("http://cs.uoregon.edu/", "helloResponse");
    private final static QName _GetLocation_QNAME = new QName("http://cs.uoregon.edu/", "getLocation");
    private final static QName _Hello_QNAME = new QName("http://cs.uoregon.edu/", "hello");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: edu.uoregon.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link GetLocation }
     * 
     */
    public GetLocation createGetLocation() {
        return new GetLocation();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link GetLocationResponse }
     * 
     */
    public GetLocationResponse createGetLocationResponse() {
        return new GetLocationResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLocationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "getLocationResponse")
    public JAXBElement<GetLocationResponse> createGetLocationResponse(GetLocationResponse value) {
        return new JAXBElement<GetLocationResponse>(_GetLocationResponse_QNAME, GetLocationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLocation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "getLocation")
    public JAXBElement<GetLocation> createGetLocation(GetLocation value) {
        return new JAXBElement<GetLocation>(_GetLocation_QNAME, GetLocation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

}
