# ViaCEP
Projeto em Java para obter CEP do webservice ViaCEP
http://viacep.com.br/

![alt tag](http://www.parg.com.br/imagens.parg.com.br/viacep.png)

## O que é?
Este é um projeto de Java/Swing feito no **NetBeans IDE 8.0.2** usando a **JDK 8**, mas que eu acredito que pode ser adaptado para qualquer JDK ou versão do Java.

O projeto serve somente como teste para ver como funciona o procedimento para solictar um CEP e interpletar o JSON de retorno do **ViaCEP**, sendo que a classe principal do projeto é a *ViaCEP* (**ViaCEP.java**) que tem como dependência as classes da biblioteca **org.json** "http://www.json.org/json-pt.html".

## Onde?
Você pode estar se perguntando onde você pode usar essa classe e a resposta é em qualquer lugar que o Java permite, em minha experiência pessoal já usei em *Java Web JSP*, *Java/Swing*, *Android*.

## Dica!
O **ViaCEP** é um serviço gratuito que serve como exemplo para muitos, então não vamos sobrecarregar o serviço com consultas repetitivas, pode usar uma estrutura de consultas internas/externas, onde em nossa base de dados consultamos a tabela CEP e caso não exista consultamos o ViaCEP, depois que a consulta do ViaCEP retornar (caso o CEP exista) cadastramos ele e nossa própria tabela de CEP.

Pode-se até criar um TIMESTAMP para registrar data que o CEP foi criado/atualizado para que a cada um determinado tempo o mesmo seja atualizado novamente através do ViaCEP.

Se todos fizerem o melhor pensando no próximo vamos ter um excelente serviço rápido e estável.
