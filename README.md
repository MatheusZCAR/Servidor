## Servidor e Cliente Java

Este repositório contém um servidor e um cliente feito em Java que utiliza sockets e threads.

Este servidor recebe do cliente dois números e uma operação no seguinte formato: "numero1 operacao numero2", retornando para o cliente o resultado.

## Como executar

Para executar o servidor e testá-lo, basta compilar os dois arquivos java, iniciar primeiro o servidor e em outra janela de terminal, iniciar o cliente. No cliente, você poderá enviar as operações matemáticas.

## O que esperar de retornos (servidor)

Ao executar o servidor, se tudo estiver correto, o servidor retornará: "Servidor iniciado. Aguardando conexões..."   
Após um cliente se conectar, ele retornará "Cliente conectado!"   
Ao receber uma operação matemática de um cliente, como por exemplo "10 + 5", o servidor retornará "Mensagem recebida: 10 + 5".

## O que esperar de retornos (cliente)

Ao executar o cliente, caso o servidor tenha sido executado previamente e tudo estiver correto, o cliente poderá enviar uma operação matemática.    

Exemplo de operação e o retorno esperado:

Input: 10 + 5   
Retorno: "Servidor: Resultado: 15.0"
