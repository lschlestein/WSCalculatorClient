
package servico;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Action;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 3.0.0
 * Generated source version: 3.0
 * 
 */
@WebService(name = "Calculator", targetNamespace = "http://servico.webservices.academia.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Calculator {


    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns double
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servico.webservices.academia.com/Calculator/addRequest", output = "http://servico.webservices.academia.com/Calculator/addResponse")
    public double add(
        @WebParam(name = "arg0", partName = "arg0")
        double arg0,
        @WebParam(name = "arg1", partName = "arg1")
        double arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns double
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servico.webservices.academia.com/Calculator/subRequest", output = "http://servico.webservices.academia.com/Calculator/subResponse")
    public double sub(
        @WebParam(name = "arg0", partName = "arg0")
        double arg0,
        @WebParam(name = "arg1", partName = "arg1")
        double arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns double
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servico.webservices.academia.com/Calculator/multRequest", output = "http://servico.webservices.academia.com/Calculator/multResponse")
    public double mult(
        @WebParam(name = "arg0", partName = "arg0")
        double arg0,
        @WebParam(name = "arg1", partName = "arg1")
        double arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns double
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servico.webservices.academia.com/Calculator/divRequest", output = "http://servico.webservices.academia.com/Calculator/divResponse")
    public double div(
        @WebParam(name = "arg0", partName = "arg0")
        double arg0,
        @WebParam(name = "arg1", partName = "arg1")
        double arg1);

}
