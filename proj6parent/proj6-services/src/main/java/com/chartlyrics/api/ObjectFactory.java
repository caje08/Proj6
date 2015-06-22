
package com.chartlyrics.api;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.chartlyrics.api package. 
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

    private final static QName _String_QNAME = new QName("http://api.chartlyrics.com/", "string");
    private final static QName _GetLyricResult_QNAME = new QName("http://api.chartlyrics.com/", "GetLyricResult");
    private final static QName _ArrayOfSearchLyricResult_QNAME = new QName("http://api.chartlyrics.com/", "ArrayOfSearchLyricResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.chartlyrics.api
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddLyricResponse }
     * 
     */
    public AddLyricResponse createAddLyricResponse() {
        return new AddLyricResponse();
    }

    /**
     * Create an instance of {@link SearchLyricTextResponse }
     * 
     */
    public SearchLyricTextResponse createSearchLyricTextResponse() {
        return new SearchLyricTextResponse();
    }

    /**
     * Create an instance of {@link ArrayOfSearchLyricResult }
     * 
     */
    public ArrayOfSearchLyricResult createArrayOfSearchLyricResult() {
        return new ArrayOfSearchLyricResult();
    }

    /**
     * Create an instance of {@link SearchLyric }
     * 
     */
    public SearchLyric createSearchLyric() {
        return new SearchLyric();
    }

    /**
     * Create an instance of {@link GetLyric }
     * 
     */
    public GetLyric createGetLyric() {
        return new GetLyric();
    }

    /**
     * Create an instance of {@link GetLyricResponse }
     * 
     */
    public GetLyricResponse createGetLyricResponse() {
        return new GetLyricResponse();
    }

    /**
     * Create an instance of {@link GetLyricResult }
     * 
     */
    public GetLyricResult createGetLyricResult() {
        return new GetLyricResult();
    }

    /**
     * Create an instance of {@link SearchLyricResponse }
     * 
     */
    public SearchLyricResponse createSearchLyricResponse() {
        return new SearchLyricResponse();
    }

    /**
     * Create an instance of {@link AddLyric }
     * 
     */
    public AddLyric createAddLyric() {
        return new AddLyric();
    }

    /**
     * Create an instance of {@link SearchLyricDirectResponse }
     * 
     */
    public SearchLyricDirectResponse createSearchLyricDirectResponse() {
        return new SearchLyricDirectResponse();
    }

    /**
     * Create an instance of {@link SearchLyricText }
     * 
     */
    public SearchLyricText createSearchLyricText() {
        return new SearchLyricText();
    }

    /**
     * Create an instance of {@link SearchLyricDirect }
     * 
     */
    public SearchLyricDirect createSearchLyricDirect() {
        return new SearchLyricDirect();
    }

    /**
     * Create an instance of {@link SearchLyricResult }
     * 
     */
    public SearchLyricResult createSearchLyricResult() {
        return new SearchLyricResult();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.chartlyrics.com/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLyricResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.chartlyrics.com/", name = "GetLyricResult")
    public JAXBElement<GetLyricResult> createGetLyricResult(GetLyricResult value) {
        return new JAXBElement<GetLyricResult>(_GetLyricResult_QNAME, GetLyricResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSearchLyricResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.chartlyrics.com/", name = "ArrayOfSearchLyricResult")
    public JAXBElement<ArrayOfSearchLyricResult> createArrayOfSearchLyricResult(ArrayOfSearchLyricResult value) {
        return new JAXBElement<ArrayOfSearchLyricResult>(_ArrayOfSearchLyricResult_QNAME, ArrayOfSearchLyricResult.class, null, value);
    }

}
