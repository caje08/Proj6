
package com.chartlyrics.api;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * Chartlyrics API version 1.2
 * 
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "apiv1", targetNamespace = "http://api.chartlyrics.com/", wsdlLocation = "http://api.chartlyrics.com/apiv1.asmx?WSDL")
public class Apiv1
    extends Service
{

    private final static URL APIV1_WSDL_LOCATION;
    private final static WebServiceException APIV1_EXCEPTION;
    private final static QName APIV1_QNAME = new QName("http://api.chartlyrics.com/", "apiv1");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://api.chartlyrics.com/apiv1.asmx?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        APIV1_WSDL_LOCATION = url;
        APIV1_EXCEPTION = e;
    }

    public Apiv1() {
        super(__getWsdlLocation(), APIV1_QNAME);
    }

    public Apiv1(WebServiceFeature... features) {
        super(__getWsdlLocation(), APIV1_QNAME, features);
    }

    public Apiv1(URL wsdlLocation) {
        super(wsdlLocation, APIV1_QNAME);
    }

    public Apiv1(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, APIV1_QNAME, features);
    }

    public Apiv1(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Apiv1(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Apiv1Soap
     */
    @WebEndpoint(name = "apiv1Soap")
    public Apiv1Soap getApiv1Soap() {
        return super.getPort(new QName("http://api.chartlyrics.com/", "apiv1Soap"), Apiv1Soap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Apiv1Soap
     */
    @WebEndpoint(name = "apiv1Soap")
    public Apiv1Soap getApiv1Soap(WebServiceFeature... features) {
        return super.getPort(new QName("http://api.chartlyrics.com/", "apiv1Soap"), Apiv1Soap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (APIV1_EXCEPTION!= null) {
            throw APIV1_EXCEPTION;
        }
        return APIV1_WSDL_LOCATION;
    }

}
