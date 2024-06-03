# Web Service (Client)
Dependências básicas

``` xml
        <dependency>
            <groupId>jakarta.xml.ws</groupId>
            <artifactId>jakarta.xml.ws-api</artifactId>
            <version>3.0.0</version>
        </dependency>

        <dependency>
            <groupId>com.sun.xml.ws</groupId>
            <artifactId>jaxws-rt</artifactId>
            <version>3.0.0</version>
        </dependency>

        <dependency>
            <groupId>com.sun.xml.ws</groupId>
            <artifactId>jaxws-ri</artifactId>
            <version>3.0.0</version>
            <type>pom</type>
        </dependency>

```
Além das dependências, precisamos desse plugin, que de forma automática criará as classes para que possamos consumir um serviço SOAP.

``` xml
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
                    <packageName>servico</packageName>
                    <sourceDestDir>src/main/java</sourceDestDir>
                </configuration>
            </plugin>
 ```

Configurar a url onde está publicado o serviço, e certificar-se que o mesmo está rodando:
``` xml
                    <wsdlUrls>
                        <wsdlUrl>http://localhost:8085/servico/calculator?wsdl</wsdlUrl>
                    </wsdlUrls>
```

Indicar o pacote, em que as classes e interface serão criadas dentro do projeto:
``` xml
                   <packageName>servico</packageName>
```

Indicar o caminho onde está nosso projeto:
``` xml
                   <sourceDestDir>src/main/java</sourceDestDir>
```
Executar a build do maven (clean install) e verificar se foi criado o pacote, classes e interfaces baseados em nosso serviço anteriormente publicado.

Em seguida, podemos consumir os serviços através de nossa aplicação java:

``` java
public class Main {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8085/servico/calculator?wsdl");
        QName qname = new QName("http://servico.webservices.academia.com/", "CalculatorImplService");
        Service service = Service.create(url, qname);

        Calculator calculator = service.getPort(Calculator.class);
        double a=5, b=4, result=0;
        result = calculator.add(5, 3);
        .
        .
        .
```

A URL obtemos de nosso server, ou seja, aonde o WS está sendo publicado. Nas primeiras linhas do WSDL.
a Qname (Qualified name) obtemos de nosso WSDL:

http://localhost:8085/servico/calculator?wsdl
``` xml
<definitions
xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd"
xmlns:wsp="http://www.w3.org/ns/ws-policy" xmlns:wsp1_2="http://schemas.xmlsoap.org/ws/2004/09/policy"
xmlns:wsam="http://www.w3.org/2007/05/addressing/metadata" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
xmlns:tns="http://servico.webservices.academia.com/" xmlns:xsd="http://www.w3.org/2001/XMLSchema"
xmlns="http://schemas.xmlsoap.org/wsdl/" targetNamespace="http://servico.webservices.academia.com/" name="CalculatorImplService">
```

Após, é só rodarmos a aplicação e conferir o resultado enviado pelo nosso WS.

``` shell
O resultado de: A:5.0 B:4.0
Add: 8.0
Sub: 2.0
Mult: 15.0
Div: 1.6666666666666667
```




