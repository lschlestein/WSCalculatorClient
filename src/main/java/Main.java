import jakarta.xml.ws.Service;
import servico.Calculator;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8085/servico/calculator?wsdl");
        QName qname = new QName("http://servico.webservices.academia.com/", "CalculatorImplService");
        Service service = Service.create(url, qname);

        Calculator calculator = service.getPort(Calculator.class);
        double a=5, b=4, result=0;
        result = calculator.add(5, 3);
        System.out.println("O resultado de: A:"+a+" B:"+b);
        result = calculator.add(5, 3);
        System.out.println("Add: "+result);
        result = calculator.sub(5, 3);
        System.out.println("Sub: "+result);
        result = calculator.mult(5, 3);
        System.out.println("Mult: "+result);
        result = calculator.div(5, 3);
        System.out.println("Div: "+result);

    }
}
