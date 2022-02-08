# API conectada a uma fila na AWS

 A aplicação contêm um endpoint que recebe a mensagem que deseja ser enviada para a fila SQS (Simple Queue Service),
 em no máximo de 5 minutos a aplicação irá buscar as mensagens que estão armazenadas na fila, logo após salvará
 todas as mensagens em um banco de dados MySql e apagará da fila.
 
 ***
 ## Configurações

 * application.yml
~~~
 server:
  port: 8080

 cloud:
  aws:
    region:
      static: us-east-1
      auto: false
    stack:
      auto: false
    credentials:
      access-key: your key
      secret-key: your key
    end-point:
      uri: your queue
~~~
  [Veja aqui](https://github.com/FernandesJr/sqs-aws/blob/main/src/main/resources/application.yml)


 * Banco de dados
 
  Você irá precisar de um servidor MySql rodando na sua máquina
  [Veja aqui as configurações](https://github.com/FernandesJr/sqs-aws/blob/main/src/main/resources/application.properties)

 ***
 ## EndPoint

 Verbo http **POST**
 http://localhost:8080/sqs

 * No corpo da requisição você deve passar a mensagem no formato **Json** (Veja um exemplo abaixo)
~~~
 {
    "titulo":"Enviar mensagem para a aws-sqs",
    "texto":"Este end-point envia uma mensagem para uma fila da aws-sqs, e em no máximo 5 minutos a aplicação salvará a mensagem no banco de dados."
 } 
~~~
***
