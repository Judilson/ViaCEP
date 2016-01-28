# ViaCEP
Projeto em Java para obter CEP do webservice ViaCEP
http://viacep.com.br/

![alt tag](https://cloud.githubusercontent.com/assets/15053271/10401225/fcf30d8c-6e93-11e5-8a5d-ca8dac30d93b.png)

## Novidade
Agora o projeto conta com uma interface para eventos (ViaCEPEvents), assim você pode melhorar o seu projeto implementando *onCEPSuccess* e *onCEPError*, veja esse exemplo:

```java
import java.util.Scanner;
import viacep.ViaCEP;
import viacep.ViaCEPEvents;
import viacep.ViaCEPException;

/**
 * Testa a classe ViaCEP
 * @author Pablo Alexander da Rocha Gonçalves
 */
public class ViaCEPTest implements ViaCEPEvents {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ViaCEPTest().run();
    }

    public void run() {
        ViaCEP viaCEP = new ViaCEP(this);
        String cep;
        Scanner scan = new Scanner(System.in);

        System.out.println(ViaCEPTest.class.getName() + " - digite sair para fechar o teste!");

        do {
            System.out.print("Digite um cep: ");
            cep = scan.next();
            // consulta
            if (!cep.equals("sair")) {
                try {
                    viaCEP.buscar(cep);
                } catch (ViaCEPException ex) {
                    System.err.println("Ocorreu um erro na classe " + ex.getClasse() + ": " + ex.getMessage());
                }
            }
        } while (!cep.equals("sair"));
    }

    @Override
    public void onCEPSuccess(ViaCEP cep) {
        System.out.println();
        System.out.println("CEP " + cep.getCep() + " encontrado!");
        System.out.println("Logradouro: " + cep.getLogradouro());
        System.out.println("Complemento: " + cep.getComplemento());
        System.out.println("Bairro: " + cep.getBairro());
        System.out.println("Localidade: " + cep.getLocalidade());
        System.out.println("UF: " + cep.getUf());
        System.out.println("Gia: " + cep.getGia());
        System.out.println("Ibge: " + cep.getIbge());
        System.out.println();
    }

    @Override
    public void onCEPError(String cep) {
        System.out.println();
        System.out.println("Não foi possível encontrar o CEP " + cep + "!");
        System.out.println();
    }

}
```

## Alternativa
Existe o projeto https://github.com/gilberto-torrezan/viacep do Gilberto que é excelente e tem suporte a GWT.

## Teste Benchmark
Pessoal foi adicionado um teste Benchmark como sugestão do Gilberto em https://github.com/parg-programador/ViaCEP/issues/1, mas recomendo executado o teste em uma conexão de IP dinâmico já que ele provavelmente vai exceder o limite de requisições no ViaCEP e bloquear o seu IP por tem indeterminado.

Eu modifiquei o código do Gilberto e testei no https://github.com/parg-programador/ViaCEP, fiquem a vontade para executar os seus teste e favor enviar um feedback.

```java
/*
 * TesteBenchmark
 * https://github.com/gilberto-torrezan
 */
package viacep;

/**
 * Teste do ViaCEP basedo em https://github.com/parg-programador/ViaCEP/issues/1
 * @author Gilberto Torrezan Filho
 */
public class TesteBenchmark {

    public static void main(String[] args) {
        System.out.println("Starting BenchmarkViaCEPClient...");

        ViaCEP client = new ViaCEP();

        String[] ceps = new String[]{"20930-040", "01311-000", "30140-010", "40026-040", "90010-273", "50030-000", "65010-970", "69900-094", "70050-000", "80010-150"};

        int numSteps = 1000;
        long sleep = 100;

        for (int step = 0; step < numSteps; step++) {
            for (int i = 0; i < ceps.length; i++) {
                String cep = ceps[i];
                try {
                    client.buscar(cep);
                    System.out.println(client.getLocalidade());

                    Thread.sleep(sleep);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(-1);
                }
            }
        }

        System.out.println("BenchmarkViaCEPClient finished.");
    }
    
}
```

## O que é?
Este é um projeto de Java feito no **NetBeans IDE 8.0.2** usando a **JDK 8**, mas que eu acredito que pode ser adaptado para qualquer JDK ou versão do Java.

<<<<<<< HEAD
O projeto serve somente como teste para ver como funciona o procedimento para solictar um CEP e interpletar o JSON de retorno do **ViaCEP**, sendo que a classe principal do projeto é a *ViaCEP* (**ViaCEP.java**) que tem como dependência as classes da biblioteca **org.json** https://github.com/douglascrockford/JSON-java de @douglascrockford.
=======
O projeto é uma biblioteca jar que tem como teste o projeto ViaCEP-teste(https://github.com/parg-programador/ViaCEP-teste) para ver como funciona o procedimento para solictar um CEP e interpletar o JSON de retorno do **ViaCEP**, sendo que a classe principal do projeto é a *ViaCEP* (**ViaCEP.java**) que tem como dependência as classes da biblioteca **org.json** https://github.com/douglascrockford/JSON-java de @douglascrockford.
>>>>>>> biblioteca

## Onde?
Você pode estar se perguntando onde você pode usar essa classe e a resposta é em qualquer lugar que o Java permite, em minha experiência pessoal já usei em *Java Web JSP*, *Java/Swing*, *Android*.

## Dica!
O **ViaCEP** é um serviço gratuito que serve como exemplo para muitos, então não vamos sobrecarregar o serviço com consultas repetitivas, pode usar uma estrutura de consultas internas/externas, onde em nossa base de dados consultamos a tabela CEP e caso não exista consultamos o ViaCEP, depois que a consulta do ViaCEP retornar (caso o CEP exista) cadastramos ele e nossa própria tabela de CEP.

Pode-se até criar um TIMESTAMP para registrar data que o CEP foi criado/atualizado para que a cada um determinado tempo o mesmo seja atualizado novamente através do ViaCEP.

Se todos fizerem o melhor pensando no próximo vamos ter um excelente serviço rápido e estável.