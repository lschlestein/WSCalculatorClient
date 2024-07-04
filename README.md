# Web Service (Client)
Repositório onde é demonstrado um Web Service Básico com JAX-WS.
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
                    <packageName>servico</packageName>
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


É necessário anotar essa interface com @Webservice.
Já a anotação @SOAPBinding, configura o retorno de nosso serviço. 
Deixo uma fonte para maiores informações:
https://developer.ibm.com/articles/ws-whichwsdl/

Também é necessário anotar cada um de nossos métodos (serviços) que serão disponiblizados posteriormente.


Já na implementação é necessário também utilizar a anotação @WebService, porém, apontando para a nossa interface (Calculator)

@WebService(endpointInterface = "com.academia.webservices.servico.Calculator")

(CalculatorImpl.java) - Service Implementation Bean (SIB)
```java
@WebService(endpointInterface = "com.academia.webservices.servico.Calculator")
public class CalculatorImpl implements Calculator {

	@Override
	public double add(double a, double b) {
		return a+b;
	}
.
.
.
```
Após, devemos rodar nosso WS, em uma classe principal:
Criamos uma instância de nosso CalculatorImpl e em seguida publicamos o serviço:
Nesse caso estamos utilizando a porta 8085.
``` java
public class Main {
    public static void main(String[] args) {
        CalculatorImpl calculator = new CalculatorImpl();
        Endpoint.publish("http://localhost:8085/servico/calculator", calculator);
        System.out.println("Serviço publicado com sucesso");
    }
}
```

Para empacotar a aplicação é necessário configurar arquivo pom.xml, para copiar as depedências para dentro da pasta /libs conforme segue:
```xml
<build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-dependency-plugin</artifactId>
                <executions>
                    <execution>
                        <id>copy-dependencies</id>
                        <phase>prepare-package</phase>
                        <goals>
                            <goal>copy-dependencies</goal>
                        </goals>
                        <configuration>
                            <outputDirectory>
                                ${project.build.directory}/libs
                            </outputDirectory>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.4.1</version>
                <configuration>
                    <archive>
                        <manifest>
                            <addClasspath>true</addClasspath>
                            <classpathPrefix>libs/</classpathPrefix>
                            <mainClass>
                                Main
                            </mainClass>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

Após, empacotar a aplicação (maven clean package), localizar o diretório onde está o .jar criado, e rodar a aplicação.
A mensagem Serviço publicado com sucesso deverá ser mostrada.
``` shell
C:\Users\Lucas\eclipse-workspace\WSCalculatorServer\target>java -jar WSCalculator-1.0-SNAPSHOT.jar
Serviço publicado com sucesso
```

Para confirmar, acessar no navegador o endereço:
http://localhost:8085/servico/calculator
![image](https://github.com/lschlestein/WSCalculatorServer/assets/103784532/b586eb5d-53a6-4920-b0b0-71498edcb1de)

Se tudo estiver ocorrido como o esperado, o WS estará rodando.
É possível testa-lo com o SoapUI no link:
http://localhost:8085/servico/calculator?wsdl
