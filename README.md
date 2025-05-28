# Blackbelt: Uma ferramenta de Gestão de vulnerabilidades

## 1. Sobre o projeto:

O Seg BlackBelt é uma solução voltada para a gestão eficiente de vulnerabilidades por empresa, centralizando dados em um sistema integrado.

Com suporte ao upload e validação de planilhas, o sistema processa informações de vulnerabilidades, como criticidade, cves e descrição. Gestores têm acesso restrito aos dados de suas unidades, enquanto a equipe central monitora todas as vulnerabilidades, promovendo organização, segurança e tomada de decisão estratégica.

## 2. Tecnologias usadas:

O projeto foi desenvolvido com as seguintes tecnologias:

- **Backend**:

  - `Java 21 (JDK 21)`: Linguagem de programação utilizada para construir a aplicação backend.
  - `Spring Boot`: Framework usado para simplificar o desenvolvimento da aplicação.
  - `Spring Boot Web`: Para construção de APIs e integração com o frontend.
  - `Spring Security`: Para implementação de autenticação e autorização.
  - `JPA/Hibernate`: Facilita o mapeamento objeto-relacional (ORM) e interações com o banco de dados.
  - `Java Mail`: É usada para enviar e receber e-mails usando Java. Ela permite a aplicação se conecte a servidores SMTP, POP3 ou IMAP para manipular mensagens de e-mail de forma programada.

- **Frontend**:
  - `Thymeleaf`: Motor de template utilizado para renderizar dados do backend no frontend.
  - `HTML, CSS e JavaScript`: Para construir e estilizar a interface do usuário.
  - `Power BI`: Biblioteca para criação de gráficos dinâmicos exibindo dados do sistema.
- **Banco de Dados**:
  - `PostgreSQL`: Sistema de gerenciamento de banco de dados relacional.
  - `PostgreSQL Driver`: Para conexão entre a aplicação e o banco de dados.
- **Infraestrutura**:
  - `Railway`: Plataforma utilizada para o deploy da aplicação

## 3. Pré-requisitos

Antes de iniciar, certifique-se de ter os seguintes requisitos configurados no seu ambiente:

- **Java Development Kit (JDK) 21**:

  - O projeto utiliza a versão 21 do JDK.
  - Certifique-se de que o Java está configurado corretamente no PATH do sistema.
  - Para verificar se o Java está instalado:

  ```bash
  java -version
  ```

- **PostgreSQL**:

  - Certifique-se de que o PostgreSQL está instalado e funcionando. Caso não, [Clique Aqui](https://www.postgresql.org/download/) para instalar.
  - Configure um banco de dados para a aplicação com User e Password e o nome do Banco, para mais detalhes vá para o item 5.
  - Não se esqueça o User e password do banco de dados do seu PC, você configura ao baixar o banco.

- **Uma IDE com suporte a Java**:

  Recomendado:

  - [IntelliJ IDEA](https://www.jetbrains.com/idea/) (preferencialmente a versão Ultimate, que possui suporte completo ao Spring Boot).
  - [Eclipse](https://eclipseide.org/) ou [VS Code](https://code.visualstudio.com/download) com extensões Java.

  A IDE deve ser capaz de executar projetos Maven e trabalhar com Spring Boot.

- **Railway**: (opcional para deploy)
  - Caso vá testar o deploy na mesma plataforma utilizada pelo desenvolvedor.
  - Configure uma conta no [Railway](https://railway.app/).

## 4. Configuração do Projeto

Antes de configurar o ambiente, é necessário obter o código-fonte do projeto e prepará-lo na sua máquina local. Siga as etapas abaixo para garantir que o projeto esteja pronto para ajustes e execução:

- **1. Clonar o Repositório:**

  Faça o clone do repositório do projeto para a sua máquina local:

  ```bash
  https://github.com/Poopstoop1/Take-2025-BlackBelt.git
  ```

- **2. Importar o Projeto na IDE**

  - Abra sua IDE preferida (recomenda-se IntelliJ IDEA, Eclipse ou VS Code com extensões Java).

  - Se estiver usando Intellij ou Eclipse importe o projeto como um projeto Maven:

    - **No IntelliJ IDEA**:
      1. Clique em File > Open.
      2. Navegue até o diretório do projeto e selecione o arquivo `pom.xml`.
      3. Clique em OK e aguarde o `Maven` importar as dependências.
    - **No Eclipse**:
      1. Clique em File > Import > Existing `Maven` Projects.
      2. Selecione o diretório do projeto e clique em Finish.
    - **No Visual Studio Code**:

      1. **Instalar Extensões Necessárias**: Antes de abrir o projeto, certifique-se de que o VS Code tem as extensões necessárias para trabalhar com `Java` e `Maven`:
         - Instale o Extension Pack for Java (fornecido pela Microsoft):
           1. Abra o VS Code.
           2. Vá na aba Extensions (ícone de quadradinho do lado esquerdo ou `Ctrl+Shift+X`).
           3. Procure por **Extension Pack for Java** e clique em **Install**.

      - Instale a Extensão Java também no VScode.

      2. **Abrir o Projeto**:

         1. Clique em File > Open Folder... ou use o atalho `Ctrl+K, Ctrl+O`.
         2. Navegue até o diretório onde o projeto foi clonado e selecione a pasta do projeto.

      3. **Configurar o Maven**:

         1. Certifique-se de que o VS Code reconheceu o projeto como um projeto `Maven`.
         2. Na barra lateral, você verá uma seção chamada `Maven`:
         3. Clique nela para expandir e visualizar as dependências do projeto.
         4. Caso as dependências não sejam carregadas automaticamente, pressione `Ctrl+Shift+P` para abrir a paleta de comandos e procure por:
            ```bash
            Java: Import Java Projects
            ```
            O VS Code fará o download das dependências necessárias.

## 5. Configuração do Ambiente

- **Configurar o Banco de Dados PostgreSQL**

  - **Criar um banco de dados**:
    - Abra o PostgreSQL (usando o terminal ou uma ferramenta como pgAdmin4).
    - Crie um banco de dados que será utilizado pelo projeto:
    ```sql
    CREATE DATABASE nome_do_banco;
    ```
  - **Atualizar o arquivo application.properties**:

    - Localize o arquivo `application.properties` na pasta `src/main/resources`.
    - Edite as seguintes linhas para configurar o acesso ao banco de dados:

    ```´bash
    spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_banco
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    spring.mail.username=seu_email
    spring.mail.password=seu_password
    ```

    - Substitua:

      - `nome_do_banco`: pelo nome do banco de dados que você criou.
      - `seu_usuario:` pelo nome do usuário do PostgreSQL.
      - `sua_senha`: pela senha do usuário.
      - `seu_email`: por um email para enviar email de recuperação de senha
      - `sua_senha`: senha gerada pelo google apenas para enviar email

    - **Como criar uma senha de aplicativo no Gmail apenas para enviar email**

    1. **Acesse as configurações da sua Conta do Google**: Vá para https://myaccount.google.com/ e faça login na sua conta.

    2. **Vá para a seção de Segurança**: No menu à esquerda, clique em "Segurança".
    3. **Encontre a opção de Verificação em duas etapas**: Role para baixo e procure por "Como você faz login no Google" e clique em "Verificação em duas etapas".
    4. **Ative a verificação em duas etapas**: Se não estiver ativada, siga as instruções para ativá-la (geralmente exige que você insira um número de telefone para receber um código SMS).
    5. **Crie a senha de aplicativo**: Após ativar a verificação em duas etapas, você verá a opção de criar uma senha de aplicativo. Clique nela.
    6. **Escolha um nome para a senha**: Você pode dar um nome descritivo à senha para facilitar a sua identificação, como "Gmail para aplicativo".
    7. **Gerar a senha**: A Google irá gerar uma senha aleatória e você poderá copiá-la para uso posterior.
    8. **Armazene a senha em um local seguro**: Guarde a senha de aplicativo em um local seguro, pois ela será necessária para autenticar com o Gmail em outros aplicativos.

  - **Verificar a configuração do driver**:

    - Certifique-se de que o driver do PostgreSQL está declarado no arquivo pom.xml (isso geralmente já está configurado, mas é bom verificar):

      ```xlm
      <dependency>
      <groupId>org.postgresql</groupId>
      <artifactId>postgresql</artifactId>
      <version>42.6.0</version>
      </dependency>
      ```

- **Outras Configurações**:
  - **Porta do Servidor**:
    Se estiver rodando outro projeto e precisar trocar a porta:
    O projeto Spring Boot utiliza por padrão a porta 8080. Para mudar, adicione no `application.properties`:
    ```bash
    server.port=porta_desejada
    ```

## 6. Rodando o Projeto

Após concluir a configuração do projeto e do ambiente, siga os passos abaixo para rodar a aplicação localmente:

1. **Abra o arquivo principal da aplicação:**

   - Navegue até o arquivo `BlackBeltApplication.java` localizado em `src/main/java/.....`.
   - Esse é o arquivo principal que inicia a aplicação.

2. **Execute a aplicação:**

   - **Na IDE:**

     - Clique no botão **Run** (geralmente ao lado do método `main`) ou pressione o atalho `Ctrl+Shift+F10` (no IntelliJ IDEA).
     - Em outras IDEs, como Eclipse ou VS Code, busque a opção "Run" no menu de execução.

   - **Pelo terminal:**
     - Navegue até a pasta raiz do projeto.
     - Execute o comando Maven para rodar a aplicação:
       ```bash
       mvn spring-boot:run
       ```

3. **Verifique a saída no console:**

   - Se a configuração estiver correta, você verá uma mensagem no console indicando que a aplicação está rodando, como:
     ```bash
     2025-04-13T15:29:17.156-03:00  INFO 9640 --- [Blackbelt] [  restartedMain] c.p.Blackbelt.BlackbeltApplication       : Started BlackbeltApplication in 10.796 seconds
     ```

4. **Acesse a aplicação no navegador:**
   - Abra seu navegador e acesse:
     ```
     http://localhost:8080
     ```
     Caso tenha alterado a porta no arquivo `application.properties`, use a porta configurada:
     ```
     http://localhost:<sua porta>
     ```

> **⚠️ Atenção:**  
> Se o servidor não iniciar corretamente, verifique os logs de erro no console para identificar problemas de configuração (como credenciais de banco de dados ou problemas com o Google Sheets API).

### Acesso à Aplicação

Para acessar a aplicação, é necessário fazer login em uma conta. Ao iniciar o aplicativo, são criados os seguintes perfis:

- **Administrador**:

  O **Administrador** tem acesso aos dados de todas as vulnerabilidades. Ele pode adicionar, editar e excluir empresas, além de gerenciar usuários. Ou seja, é o administrador quem adiciona, edita e exclui os usuários do sistema.

  ```bash
  login: AirtonRibeiro@hotmail.com
  senha: 1234
  ```

- **Gestores**:

  Os Gestores têm acesso apenas aos dados de suas respectivas filiais. Eles podem visualizar as informações e adicionar correções as vulnerabilidades.

  ```bash
  login: GabrielLima@gmail.com
  Senha: 1234

  ```

## Testes Recomendados

Para testar a aplicação, recomendamos que você tente realizar algumas alterações como **Administrador**, como:

- Adicionar um usuário
- Adicionar uma empresa
- Editar um usuário
- Editar uma empresa
- Excluir um usuário
- Excluir uma empresa

> **⚠️ Atenção:**  
> **Não exclua os usuários já criados**, pois eles são necessários para que a aplicação funcione corretamente.

## 7. Contribuição ou Problemas

Se você deseja contribuir com o projeto ou encontrou algum problema, siga as etapas abaixo:

### Contribuindo

1. **Fork o repositório**: Clique no botão "Fork" no canto superior direito do repositório para criar uma cópia do projeto em sua conta.
2. **Clone o repositório**: Clone o repositório forkado em sua máquina local:

   ```bash
   git clone https://github.com/seu-usuario/nome-do-projeto.git

   ```

3. **Crie uma branch: Antes de fazer suas alterações, crie uma nova branch:**
   ```bash
   git checkout -b nome-da-sua-branch
   ```
4. **Faça suas alterações:** Realize as modificações desejadas no código.
5. **Comite suas mudanças:**
   ```bash
   git add .
   git commit -m "Descrição das mudanças"
   ```
6. **Envie suas mudanças:**
   ```bash
   git push origin nome-da-sua-branch
   ```
7. **Crie um Pull Request:** Acesse o repositório original e crie um Pull Request para que suas alterações sejam revisadas e possivelmente mescladas ao projeto principal.

### Problemas

Caso você tenha encontrado um problema, por favor, siga as etapas abaixo para reportá-lo:

1. **Verifique se o problema já foi reportado**: Consulte as issues abertas no repositório para verificar se alguém já relatou o mesmo problema.
2. **Crie uma nova issue**: Caso o problema ainda não tenha sido reportado, abra uma nova issue detalhando o problema. Seja claro sobre os passos para reproduzir o erro e forneça detalhes como:

   - O que você estava tentando fazer.
   - O erro ou comportamento inesperado.
   - Mensagens de erro, se houver.

## 🧱 Estrutura do Projeto

```txt
blackbelt/
├── .gitignore
├── README.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── project/
│       │           └── blackbelt/
│       │               ├── BlackbeltApplication.java
│       │               ├── controller/
│       │               │   ├── DashboardController.java
│       │               │   ├── EmpresaController.java
│       │               │   ├── LoginController.java
│       │               │   ├── RecuperacaoController.java
│       │               │   └── UsuarioController.java
│       │               ├── model/
│       │               │   ├── Documento.java
│       │               │   ├── Empresa.java
│       │               │   └── Users.java
│       │               ├── repository/
│       │               │   ├── DocumentoRepository.java
│       │               │   ├── EmpresaRepository.java
│       │               │   └── UserRepository.java
│       │               ├── security/
│       │               │   ├── WebSecurityConfig.java
│       │               │   └── SecurityDataBaseService.java
│       │               └── service/
│       │                   ├── EmailService.java
│       │                   └── RecuperacaosenhaService.java
│       └── resources/
│           ├── static/
│           │   ├── img/
│           │   │   └── blackbeltlogo.png
│           │   ├── js/
│           │   │   ├── dashboard.js
│           │   │   ├── gestaodeempresas.js
│           │   │   └── gestaodeusuarios.js
│           │   ├── dashboard.css
│           │   ├── gestaodeempresas.css
│           │   ├── gestaodeusuarios.css
│           │   ├── login.css
│           │   └── recuperacao.css
│           ├── templates/
│           │   ├── paginas/
│           │   │    ├── gestaodeempresas.html
│           │   │    ├── gestaodeusuarios.html
│           │   │    ├── login.html
│           │   │    ├── recuperar-senha.html
│           │   │    └── redefinir-senha.html
│           │   └── dashboard.html
│           └── application.properties
└── src/
    └── test/
        └── java/
            └── com/
                └── project/
                    └── blackbelt/
                        └── BlackbeltApplicationTests.java

```

## 8. Agradecimentos

Gostaríamos de agradecer a todos os colaboradores e desenvolvedores que contribuíram para este projeto. Se você tem interesse em contribuir ou se deseja ajudar a melhorar o projeto, fique à vontade para enviar um pull request!
