import jakarta.xml.ws.Service;
import servico.Calculator;
import servico.ServerUtil;

public class Main {
    public static void main(String[] args) {
        Service service = ServerUtil.getConnection();
        if (service != null) {
            Calculator calculator = service.getPort(Calculator.class);
            double a = 5, b = 4, result = 0;
            result = calculator.add(5, 3);
            System.out.println("O resultado de: A:" + a + " B:" + b);
            result = calculator.add(5, 3);
            System.out.println("Add: " + result);
            result = calculator.sub(5, 3);
            System.out.println("Sub: " + result);
            result = calculator.mult(5, 3);
            System.out.println("Mult: " + result);
            result = calculator.div(5, 3);
            System.out.println("Div: " + result);
        }

    }
}
