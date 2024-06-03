
package servico;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 3.0.0
 * Generated source version: 3.0
 * 
 */
@WebServiceClient(name = "CalculatorImplService", targetNamespace = "http://servico.webservices.academia.com/", wsdlLocation = "http://localhost:8085/servico/calculator?wsdl")
public class CalculatorImplService
    extends Service
{

    private final static URL CALCULATORIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException CALCULATORIMPLSERVICE_EXCEPTION;
    private final static QName CALCULATORIMPLSERVICE_QNAME = new QName("http://servico.webservices.academia.com/", "CalculatorImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8085/servico/calculator?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CALCULATORIMPLSERVICE_WSDL_LOCATION = url;
        CALCULATORIMPLSERVICE_EXCEPTION = e;
    }

    public CalculatorImplService() {
        super(__getWsdlLocation(), CALCULATORIMPLSERVICE_QNAME);
    }

    public CalculatorImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), CALCULATORIMPLSERVICE_QNAME, features);
    }

    public CalculatorImplService(URL wsdlLocation) {
        super(wsdlLocation, CALCULATORIMPLSERVICE_QNAME);
    }

    public CalculatorImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CALCULATORIMPLSERVICE_QNAME, features);
    }

    public CalculatorImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CalculatorImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Calculator
     */
    @WebEndpoint(name = "CalculatorImplPort")
    public Calculator getCalculatorImplPort() {
        return super.getPort(new QName("http://servico.webservices.academia.com/", "CalculatorImplPort"), Calculator.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link jakarta.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Calculator
     */
    @WebEndpoint(name = "CalculatorImplPort")
    public Calculator getCalculatorImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://servico.webservices.academia.com/", "CalculatorImplPort"), Calculator.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CALCULATORIMPLSERVICE_EXCEPTION!= null) {
            throw CALCULATORIMPLSERVICE_EXCEPTION;
        }
        return CALCULATORIMPLSERVICE_WSDL_LOCATION;
    }

}
