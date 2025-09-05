### **Requisitos do Sistema de Aluguel de Veículos**

#### **História de Usuários**

| Como | Desejo | Para |
|:-----|:-------|:-----|
| Usuário | Me cadastrar no sistema | Usar de todos os recursos disponíveis |
| Cliente | Introduzir, modificar, consultar e cancelar pedidos de aluguel | Poder alugar um carro |
| Agente | Modificar e avaliar pedidos de aluguel | Dar continuidade ao processo de aluguel de um carro |

---

### **Requisitos**

#### **Requisitos Funcionais (RF)**

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

#### **Regras de Negócio (RN)**

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
