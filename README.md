# **Sistema de Aluguel de Veículos**

## **História de Usuários**

Aqui está a estrutura das histórias de usuário conforme o formato solicitado:

| Como            | Desejo                                                                     | Para                                                              |
| --------------- | -------------------------------------------------------------------------- | ----------------------------------------------------------------- |
| Cliente         | Fazer um pedido de aluguel de automóvel                                    | Poder alugar um veículo                                           |
| Cliente         | Fazer múltiplos pedidos de aluguel de automóveis                           | Conseguir alugar mais de um veículo                               |
| Cliente         | Visualizar meus pedidos de aluguel                                         | Acompanhar o andamento de cada pedido                             |
| Cliente         | Editar meus pedidos de aluguel                                             | Atualizar as informações de um pedido existente                   |
| Cliente         | Cancelar meu pedido de aluguel                                             | Impedir que o pedido prossiga                                     |
| Cliente         | Contratar um crédito para um automóvel                                     | Adquirir a propriedade do veículo após o pagamento integral       |
| Funcionário     | Acessar os pedidos de aluguel                                              | Avaliar as solicitações de aluguel dos clientes                   |
| Funcionário     | Editar os pedidos de aluguel                                               | Corrigir ou atualizar informações de um pedido existente          |
| Funcionário     | Encaminhar um pedido de aluguel para o agente bancário                     | Verificar a análise de crédito e a situação financeira do cliente |
| Funcionário     | Transferir a propriedade do carro para o banco                             | Transferir a responsabilidade do contrato e garantia para o banco |
| Agente Bancário | Analisar os pedidos de aluguel                                             | Criar um contrato de crédito para o cliente                       |
| Agente Bancário | Criar um contrato de crédito para o cliente                                | Formalizar o processo de financiamento do automóvel               |
| Agente Bancário | Transferir a propriedade do carro para o cliente após o pagamento integral | Concluir a transação de compra do veículo                         |
| Agente Bancário | Editar os pedidos de aluguel                                               | Corrigir ou atualizar informações de um pedido existente          |
---

## **Requisitos**

### **Requisitos Funcionais (RF)**

| RF | Descrição | Complexidade |
|:---|:---|:---|
| 01 | O sistema deve permitir o **cadastro de novos usuários** (clientes e agentes). | 2 |
| 02 | O sistema deve permitir o **login de usuários** previamente cadastrados. | 1 |
| 03 | O Cliente deve poder **introduzir um novo pedido** de aluguel. | 2 |
| 04 | O Cliente deve poder **modificar um pedido** de aluguel. | 2 |
| 05 | O Cliente deve poder **consultar seus pedidos** de aluguel. | 1 |
| 06 | O Cliente deve poder **cancelar um pedido** de aluguel. | 2 |
| 07 | O Agente deve poder **modifica um pedido** de aluguel. | 2 |
| 08 | O Agente deve poder **avaliar um pedido** de aluguel (análise financeira). | 3 |
| 09 | O sistema deve permitir o **registro de contratos** de aluguel. | 2 |
| 10 | O sistema deve permitir o **registro de automóveis**. | 1 |

### **Regras de Negócio (RN)**

| RN | Descrição | Complexidade |
|:---|:---|:---|
| 01 | O **cadastro de clientes** só é permitido após a inserção dos dados de identificação (RG, CPF, Nome, Endereço), profissão, entidades empregadoras e rendimentos. | 2 |
| 02 | Um cliente pode ter até **três rendimentos** registrados. | 1 |
| 03 | Um pedido de aluguel deve ser **analisado financeiramente** por um agente antes de ser aprovado. | 3 |
| 04 | Um pedido de aluguel **só pode ser modificado ou cancelado pelo cliente** se seu status for "Em Análise". | 2 |
| 05 | Um pedido de aluguel só pode ser **modificado por um agente** se seu status for "Em Análise". | 2 |
| 06 | Um pedido de aluguel com parecer financeiro **positivo** é colocado sob consideração dos agentes para a execução do contrato. | 2 |
| 07 | Um contrato de aluguel pode ser **associado a um contrato de crédito**, concedido por um banco agente. | 3 |
| 08 | Os automóveis podem ser registrados como **propriedade de clientes, empresas ou bancos**. | 1 |
| 09 | Os automóveis são compostos pelos seguintes dados: matrícula, ano, marca, modelo e placa.| 1 |
| 10 | Um usuário individual é classificado como **`Cliente`**, enquanto empresas e bancos são classificados como **`Agentes`**. | 1 |

## **Projeto**

### **Diagrama de Casos de Uso**

![UseCaseDiagram](/projeto/DiagramaDeCasosDeUso.drawio.svg)

### **Diagrama de Classes**

![UML](/projeto/DiagramaDeClasses.jpg)

### **Diagrama de Pacotes**

![PackageDiagram](/projeto/DiagramaDePacote.jpg)
