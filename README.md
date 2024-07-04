# Web Service (Client)
Repositório onde é demonstrado como consumir um Web Service Básico com JAX-WS.
O lado oposto a essa implementação (Server) pode ser encontrado aqui: [Server](https://github.com/lschlestein/WSCalculatorServer.git)
Para implementar esse WS (Web Service) as dependencias básicas necessárias são as seguintes:

``` xml
       <dependencies>
        <dependency>
            <groupId>com.sun.xml.ws</groupId>
            <artifactId>jaxws-ri</artifactId>
            <version>3.0.0</version>
            <type>pom</type>
        </dependency>
    </dependencies>
```

Configuradas nossas depedências, loga em seguida, faremos uso da ferramenta WsImport, a qual implementará as classes necessárias para consumirmos os serviços oferecidos pelo nosso Web Service.
É importante frisar, que para que a importação ocorra, o server já deve ter sido configurado, e deve estar rodando no momento da compilação do projeto. Certifique-se que ele esteja rodando.

``` xml
  <build>
        <plugins>
            <plugin>
                <groupId>com.sun.xml.ws</groupId>
                <artifactId>jaxws-maven-plugin</artifactId>
                <version>3.0.0</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>wsimport</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <wsdlUrls>
                        <wsdlUrl>http://localhost:8085/servico/calculator?wsdl</wsdlUrl>
                    </wsdlUrls>
                    <keep>true</keep>
                    <packageName>com.service.client</packageName>
                    <sourceDestDir>src/main/java</sourceDestDir>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

Esse plugin buscará as informações em nosso Web Service, via URL configurada no próprio plugin:
``` xml
<wsdlUrls>
     <wsdlUrl>http://localhost:8085/servico/calculator?wsdl</wsdlUrl>
</wsdlUrls>
```

Logo em seguida, precisaremos definir qual será o nome do pacote em nosso projeto, onde o WsImport criará as classes necessárias para acessarmos os serviços oferecidos pelo nosso Web Service

```xml
 <packageName>com.service.client</packageName>
```

Também é necessário configurar onde está o nosso projeto.
``` xml
  <sourceDestDir>src/main/java</sourceDestDir>
```
O pacote com.service.client é gerado pelo WsImport, bem como a interface Calculator e a classe CalculatorImplService.

![image](https://github.com/lschlestein/WSCalculatorClient/assets/103784532/dda7b73c-c79a-46d2-92bb-e0af6d27c1ff)

Em seguida precisamos criar uma classe cujo a qual fará a conexão com nosso Web Service:

``` java

import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebServiceException;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;

public class ServerUtil {
    public static Service getConnection() {
        Service service = null;
        try {
            URL url = new URL("http://localhost:8085/servico/calculator?wsdl");
            QName qname = new QName("http://servico.webservices.academia.com/", "CalculatorImplService");
            service = Service.create(url, qname);
        } catch (MalformedURLException e) {
            System.out.println("Verifique se a URL informada está correta " + e.getMessage());
            e.printStackTrace();
        } catch (WebServiceException e) {
            System.out.println("Não foi Possível Conectar ao Web Service" + e.getMessage());
            e.printStackTrace();
        }
        return service;
    }
}
```
A URL é o endereço, onde nosso Web Service foi hospedado. Esse endereço bem como sua porta, são configurados no servidor, conforme configurado anteriormente:
``` java
URL url = new URL("http://localhost:8085/servico/calculator?wsdl");
```
A Qname (qualified name de nosso XML) também foi configurada em nosso servidor anteriormente. Com o servidor rodando, esse endereço pode ser obtido acessando nosso server via browser e localizando a informação *targetNamespace* :

 ``` xml
<!--  Published by JAX-WS RI (https://github.com/eclipse-ee4j/metro-jax-ws). RI's version is JAX-WS RI 3.0.0 git-revision#af8101a.  -->
<!--  Generated by JAX-WS RI (https://github.com/eclipse-ee4j/metro-jax-ws). RI's version is JAX-WS RI 3.0.0 git-revision#af8101a.  -->
<definitions xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd" xmlns:wsp="http://www.w3.org/ns/ws-policy" xmlns:wsp1_2="http://schemas.xmlsoap.org/ws/2004/09/policy" xmlns:wsam="http://www.w3.org/2007/05/addressing/metadata" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://servico.webservices.academia.com/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="http://schemas.xmlsoap.org/wsdl/" targetNamespace="http://servico.webservices.academia.com/" name="CalculatorImplService">
```
``` java
QName qname = new QName("http://servico.webservices.academia.com/", "CalculatorImplService");
```

Em seguinda devemos criar nosso service, que acessará o servidor para interação cliente x servidor.
```java
service = Service.create(url, qname);
```

Como nossa classe de conexão ao Web Service devidamente configurada podemos agora consumir os serviçõs de nosso servidor:

``` java
import com.service.client.Calculator;
import jakarta.xml.ws.Service;

public class Main {
    public static void main(String[] args) {
        Service service = com.service.client.ServerUtil.getConnection();
        if (service != null) {
            Calculator calculator = service.getPort(Calculator.class);
            double a = 5, b = 4, result = 0;
            result = calculator.add(5, 3);
            System.out.println("O resultado de: A:" + a + " B:" + b);
 	        result = calculator.add(5, 3);
            System.out.println("Add: " + result);
	    .
	    .
        }
    }
}

```
Aqui é feita a conexão ao servidor
```java
Service service = com.service.client.ServerUtil.getConnection();
```
Aqui, dizemos que a implementação da interface Calculator deve ser feita pelo nosso Web Service, hospedado em nosso servidor.
``` java
Calculator calculator = service.getPort(Calculator.class);
```

Logo em seguida passamos por parâmetro a interface, os valores o quais desejamos lançar ao nosso servidor.
``` java
 result = calculator.add(5, 3);
```
