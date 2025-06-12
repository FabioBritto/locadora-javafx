# Sistema de Locadora JavaFX

## Visão Geral
Este é um sistema de gerenciamento de locadora de veículos desenvolvido em Java utilizando JavaFX para a interface gráfica. O projeto segue uma arquitetura MVC (Model-View-Controller) e implementa diversos padrões de projeto para melhor organização e manutenibilidade do código.

## Tecnologias Utilizadas
- Java 11
- JavaFX 13 (Interface gráfica)
- MySQL (Banco de dados principal)
- H2 Database (Banco de dados para testes)
- Maven (Gerenciamento de dependências)
- GSON (Manipulação de JSON)

## Arquitetura e Padrões de Projeto

### 1. Padrão MVC (Model-View-Controller)
O projeto está estruturado seguindo o padrão MVC:
- **Model**: Localizado em `model/entities/` contendo as classes de domínio
- **View**: Arquivos FXML (não visíveis na estrutura, mas referenciados nos controllers)
- **Controller**: Classes com sufixo `Controller` que gerenciam a lógica da interface

### 2. Padrão DAO (Data Access Object)
- Implementado no diretório `model/dao/`
- Separa a lógica de acesso a dados das regras de negócio
- Permite a troca de fonte de dados sem impactar o resto do sistema

### 3. Padrão DTO (Data Transfer Object)
- Localizado em `model/dto/`
- Utilizado para transferência de dados entre camadas
- Otimiza a comunicação evitando transferência de dados desnecessários

### 4. Enums
- Organizados em `model/enums/`
- Utilizados para representar estados e tipos fixos no sistema
- Melhora a type safety e legibilidade do código

## Principais Componentes

### 1. Entidades Principais
- **Cliente.java**: Gerenciamento de clientes com validações e regras de negócio
- **Veiculo.java**: Representação e gerenciamento de veículos
- **PedidoLocacao.java**: Controle de locações com regras de negócio complexas
- **Usuario.java**: Gerenciamento de usuários do sistema
- **TipoSeguro.java**: Configuração e gerenciamento de seguros
- **CategoriaVeiculo.java**: Categorização de veículos

### 2. Controllers Principais
- **NovaLocacaoController**: Gerencia o processo de locação
- **PagamentoController**: Controla operações de pagamento
- **SegurosController**: Administração de seguros
- **UsuariosController**: Gerenciamento de usuários
- **TelaClientesController**: Interface de gerenciamento de clientes

## Implementações Interessantes

### 1. Sistema de Locação
O processo de locação (em `NovaLocacaoController.java`) implementa:
- Validações complexas de disponibilidade
- Cálculo de valores
- Integração com sistema de seguros
- Gestão de estado da locação

### 2. Gestão de Pagamentos
O `PagamentoController.java` implementa:
- Diferentes formas de pagamento
- Cálculos de descontos e taxas
- Validações financeiras
- Registro de transações

### 3. Segurança e Autenticação
Implementado através do `LoginController.java`:
- Sistema de autenticação de usuários
- Controle de acesso baseado em perfis
- Proteção de rotas e funcionalidades

## Banco de Dados
- Utiliza MySQL como banco principal
- Scripts de criação disponíveis em:
  - `query-para-criação-DB.sql`
  - `query-popular-banco.sql`
- Implementa H2 Database para testes

## Testes
- Configurado com H2 Database para testes unitários
- Ambiente de teste isolado do ambiente de produção

## Boas Práticas Implementadas
1. Separação clara de responsabilidades
2. Uso de padrões de projeto para melhor organização
3. Tratamento de exceções personalizado
4. Validações em diferentes camadas
5. Uso de DTOs para transferência segura de dados
6. Implementação de interfaces para baixo acoplamento

## Pontos de Extensão
O sistema foi projetado para ser facilmente extensível em:
1. Novos tipos de veículos
2. Novas formas de pagamento
3. Tipos adicionais de seguros
4. Regras de negócio customizadas
5. Integrações com outros sistemas

## Como Executar o Projeto

### Pré-requisitos
- Java 11 ou superior
- Maven instalado
- MySQL Server

### Passos para Execução
1. Clone o repositório
2. Execute os scripts SQL para criar e popular o banco de dados
3. Configure as credenciais do banco no arquivo de configuração
4. Execute o comando: `mvn clean javafx:run`

## Contribuição
Sinta-se à vontade para contribuir com o projeto através de Pull Requests. Para mudanças significativas, abra uma issue primeiro para discutir o que você gostaria de mudar.

## Licença
Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

# Como contribuir

Faça um fork no repositório, crie uma branch com o nome da feature ou fix que voce vai trabalhar e use essa branch. 

Use os seguintes padrões de commit para cada tipo antes da mensagem:

***

**feat** - Commits do tipo feat indicam que seu trecho de código está incluindo um novo recurso (se relaciona com o MINOR do versionamento semântico).

**fix** - Commits do tipo fix indicam que seu trecho de código commitado está solucionando um problema (bug fix), (se relaciona com o PATCH do versionamento semântico).

**docs** - Commits do tipo docs indicam que houveram mudanças na documentação, como por exemplo no Readme do seu repositório. (Não inclui alterações em código).

**test** - Commits do tipo test são utilizados quando são realizadas alterações em testes, seja criando, alterando ou excluindo testes unitários. (Não inclui alterações em código)

**build** - Commits do tipo build são utilizados quando são realizadas modificações em arquivos de build e dependências.

**perf** - Commits do tipo perf servem para identificar quaisquer alterações de código que estejam relacionadas a performance.

**style** - Commits do tipo style indicam que houveram alterações referentes a formatações de código, semicolons, trailing spaces, lint... (Não inclui alterações em código).

**refactor** - Commits do tipo refactor referem-se a mudanças devido a refatorações que não alterem sua funcionalidade, como por exemplo, uma alteração no formato como é processada determinada parte da tela, mas que manteve a mesma funcionalidade, ou melhorias de performance devido a um code review.

**chore** - Commits do tipo chore indicam atualizações de tarefas de build, configurações de administrador, pacotes... como por exemplo adicionar um pacote no gitignore. (Não inclui alterações em código)

**ci** - Commits do tipo ci indicam mudanças relacionadas a integração contínua (continuous integration).

**raw** - Commits do tipo raw indicam mudanças relacionadas a arquivos de configurações, dados, features, parâmetros.

**cleanup** - Commits do tipo cleanup são utilizados para remover código comentado, trechos desnecessários ou qualquer outra forma de limpeza do código-fonte, visando aprimorar sua legibilidade e manutenibilidade.

**remove** - Commits do tipo remove indicam a exclusão de arquivos, diretórios ou funcionalidades obsoletas ou não utilizadas, reduzindo o tamanho e a complexidade do projeto e mantendo-o mais organizado.

***

Além disso, caso seu commit referencie uma issue utilize # + número da issue na sua mensagem de commit.

**Exemplos** -> "feat: nova funcionalidade #23", "fix: refatoração de um método #77".

Após esse processo, faça um Pull Request com a branch nova criada, assim que sua PR for aprovada, o merge na branch main será feito. Após o merge, você pode apagar a sua branch criada, atualizar a branch main, e criar uma nova branch, reiniciando o processo para uma nova tarefa.

