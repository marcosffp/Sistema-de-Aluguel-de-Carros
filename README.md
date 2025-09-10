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
| 01 | O cliente cadastra no sistema. | 2 |
| 02 | O cliente registra entidades empregadoras com seus respectivos rendimentos. | 3 |
| 03 | O cliente gerencia seus pedidos de aluguel. | 2 |
| 04 | O cliente cancela um pedido de aluguel que ainda não foi aprovado. | 2 |
| 05 | O cliente solicita um contrato de crédito associado ao aluguel. | 2 |
| 06 | O funcionário visualiza pedidos de aluguel atribuídos à sua empresa. | 1 |
| 07 | O funcionário edita pedidos de aluguel para correção de dados. | 2 |
| 08 | O funcionário encaminha pedidos de aluguel para análise de um agente bancário. | 1 |
| 09 | O funcionário avalia o pedido de aluguel. | 3 |
| 10 | O funcionário gera o contrato de aluguel. | 2 |
| 11 | O funcionário registra a transferência de propriedade do veículo para o banco quando um contrato de crédito é formalizado. | 3 |
| 12 | O agente bancário visualiza pedidos de aluguel encaminhados para análise. | 1 |
| 13 | O agente bancário aprova ou reprova um pedido de aluguel com base na análise financeira. | 3 |
| 14 | O agente bancário cria um contrato de crédito associado a um pedido aprovado. | 2 |
| 15 | O agente bancário registra a transferência de propriedade do veículo para o cliente após a quitação do contrato. | 3 |


### **Regras de Negócio (RN)**

| RN | Descrição | Complexidade |
|:---|:---|:---|
| 01 | O sistema só pode ser utilizado por usuários previamente cadastrados. | 1 |
| 02 | O cadastro de cliente requer os seguintes dados obrigatórios: RG, CPF, Nome, Endereço e Profissão. | 1 |
| 03 | Um cliente pode registrar até três entidades empregadoras com seus respectivos rendimentos. | 1 |
| 04 | Clientes podem criar, visualizar, editar e cancelar seus próprios pedidos de aluguel. | 2 |
| 05 | Um cliente pode ter múltiplos pedidos de aluguel simultâneos. | 1 |
| 06 | Todo pedido de aluguel deve passar por análise financeira por um agente bancário ou da empresa. | 3 |
| 07 | Apenas agentes autorizados podem modificar ou avaliar pedidos de aluguel. | 2 |
| 08 | Um pedido de aluguel pode estar associado a um contrato de crédito concedido por um banco agente. | 3 |
| 09 | Após a quitação do contrato de crédito, a propriedade do veículo deve ser transferida para o cliente. | 3 |
| 10 | A propriedade do veículo pode ser transferida entre cliente, empresa e banco conforme o estágio do contrato. | 3 |
| 11 | A transferência de propriedade para o banco ocorre automaticamente quando o contrato de crédito é formalizado. | 3 |
| 12 | Em contratos de aluguel sem crédito, a propriedade do veículo permanece com a empresa locadora. | 1 |
| 13 | Agentes (bancos/empresas) têm acesso apenas aos pedidos atribuídos especificamente para sua análise ou modificação. | 2 |
| 14 | Funcionários da empresa podem encaminhar pedidos para análise bancária e editar pedidos para correções de dados. | 2 |
| 15 | Após análise positiva, o pedido é aprovado e o contrato de aluguel é gerado automaticamente. | 2 |
| 16 | Pedidos reprovados na análise são automaticamente arquivados e não prosseguem para contratação. | 1 |
| 17 | Cada veículo deve ser registrado com os seguintes dados: matrícula, ano, marca, modelo e placa. | 1 |
| 18 | Um veículo só pode ser alugado se estiver disponível e não vinculado a um contrato ativo. | 2 |


## **Projeto**

### **Diagrama de Casos de Uso**

![UseCaseDiagram](/projeto/DiagramaDeCasosDeUso.drawio.svg)

### **Diagrama de Classes**

![UML](/projeto/DiagramaDeClasses.png)

### **Diagrama de Pacotes**

![PackageDiagram](/projeto/DiagramaDePacote.jpg)
