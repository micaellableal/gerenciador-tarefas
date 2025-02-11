# 📝 Gestão de Tarefas API
Uma API REST para gerenciamento de tarefas, construída com Spring Boot, MySQL e Hibernate.

📌 Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA (Hibernate)**
- **Spring Web**
- **Spring Validation**
- **MySQL**
- **Maven**

## 🚀 Como Rodar o Projeto
### 🔹 1. Pré-requisitos
Antes de iniciar, certifique-se de ter instalado:
- **Java 17+**
- **Maven**
- **MySQL Server**
- **Postman (para testar API - opcional)**

### 🔹 2. Configurações Banco de dados
Acesse o MySQL via terminal ou MySQL Workbench.
1. Acesse o MySQL via terminal ou MySQL Workbench.
2. Crie o banco de dados:
   ```sql CREATE DATABASE todo_list;```
4. Atualize o arquivo `src/main/resources/application.properties:`
  ```
   spring.datasource.url=jdbc:mysql://localhost:3306/todo_list
   spring.datasource.username=root
   spring.datasource.password=suasenha

   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
   spring.jpa.hibernate.ddl-auto=update
  ```
### 🔹 3. Clonar o Projeto e Rodar
**Clone este repositório:**
  	```
    git clone https://github.com/seu-usuario/todo-list-springboot.git
    cd todo-list-springboot
    ```
**Compile e execute:**
   `mvn spring-boot:run`    
**A API estará disponível em:**
    `http://localhost:8080`


## 📌 Endpoints da API
✅ **Tarefas**
| Método HTTP | Endpoint                 | Descrição                              | Parâmetros                   | Resposta Esperada        |
|-------------|--------------------------|----------------------------------------|------------------------------|--------------------------|
| GET         | `/api/tarefas`            | Retorna a lista de todas as tarefas    | Nenhum                       | Lista de tarefas         |
| GET         | `/api/tarefas/{id}`       | Retorna uma tarefa específica pelo ID  | `id` (Long)                  | Tarefa                  |
| POST        | `/api/tarefas`            | Cria uma nova tarefa                   | `title`, `description`, `status` | Tarefa criada           |
| PUT         | `/api/tarefas/{id}`       | Atualiza os dados de uma tarefa        | `id` (Long), `title`, `description`, `status` | Tarefa atualizada |
| DELETE      | `/api/tarefas/{id}`       | Deleta uma tarefa                      | `id` (Long)                  | Tarefa deletada         |

## 📌 Exemplo de Requisição e Resposta
### Criar uma tarefa (POST /tarefas)
📌**Request Body**
```
  {
    "titulo": "Ir ao mercado",
    "descricao": "Comprar Leite e farinha",
    "status": "PENDENTE"
  }
```
📌**Resposta**
```
  {
    "id":1
    "titulo": "Ir ao mercado",
    "descricao": "Comprar Leite e farinha",
    "status": "PENDENTE"
  }
```
## 📌Estrutura do Projeto

📂 gerenciador-tarefas \
├── 📂 src\
│   ├── 📂 main\
│   │   ├── 📂 java\
│   │   │   ├── 📂 org.example\
│   │   │   │   ├── 📂 controller       **(Controllers REST)**\
│   │   │   │   ├── 📂 entity           **(Entidades JPA)**\
│   │   │   │   ├── 📂 service          **(Lógica de negócio)**\
│   │   │   │   ├── 📂 repository       **(Repositórios (Spring Data JPA))**\
│   │   │   │   ├── Application.java\
│   │   ├── 📂 resources\
│   │   │   ├── application.properties\
├── 📂 pom.xml

## 📌Autor
👩‍💻 **_Micaella Leal_**\
📧 **_Email: micaellableal@gmail.com_** \
🔗 **_GitHub: https://github.com/micaellableal_** \
🔗 **_LinkedIn: https://www.linkedin.com/in/micaella-leal/_**
