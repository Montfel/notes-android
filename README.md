<h1 align="center">Notes</h1>

## 🚀 Como executar

### Pré-requisitos

- JDK versão 17 ou maior
- Android SDK, API level 26 ou maior
  - Instalar Android Studio ou o Android command-line tools.
- Um dispositivo virtual Android (emulador)

### Passo a passo

- Baixe o repositório
- Configure o servidor
  - Acesse a pasta `server` do projeto
  - Execute o comando `java -jar pushnotificationsfcm.jar -port=8080` no terminal para subir o servidor
- Abra o emulador Android
- Execute o app no emulador

## 🛠 Tech Stack

As seguintes ferramentas foram usadas na construção do projeto:

#### Linguagem

- **Kotlin**
  - Escolhi Kotlin como linguagem de programação por ser a linguagem oficial do Android, com recursos modernos como null safety e coroutines, que facilitam a escrita de código conciso e seguro.

#### Arquitetura

- **Clean Architecture**
  - A adoção de Clean Architecture se deve à necessidade de criar uma aplicação escalável e fácil de manter. Dividindo o projeto em camadas (Domain, Data, Presentation), fica mais simples isolar responsabilidades e facilitar testes.
- **MVVM**
  - MVVM é uma arquitetura que facilita a separação de responsabilidades entre a interface do usuário (UI) e a lógica de negócios. Utilizei ViewModel para gerenciar o estado da UI de forma eficiente.

#### Libraries

- **LiveData**
  -  LiveData foi utilizado para observar e gerenciar o estado da UI de forma reativa. Ele permite que os componentes da interface do usuário observem mudanças nos dados e atualizem a UI automaticamente, garantindo que a interface seja sempre consistente com os dados subjacentes.
-  **Flow**
   -  Eu também criei uma branch separada ([using-coroutines-flow](https://github.com/Montfel/notes-android/tree/using-coroutines-flow)) onde usei Flow em vez de LiveData, pois o Flow oferece um gerenciamento de estado mais eficiente e flexível, como também lida melhor com operações assíncronas complexas, integra-se melhor com coroutines e com o Compose, e permite um controle maior sobre a emissão e manipulação dos dados.
- **Hilt**
  - Hilt foi escolhido como o framework de injeção de dependências para simplificar a criação de componentes dependentes e melhorar a modularidade do código, facilitando a troca de implementações, especialmente em testes.
- **Retrofit**
  - Usei Retrofit, uma biblioteca cliente HTTP, para fazer requisições ao backend local, que processa e dispara notificações para o Firebase Cloud Messaging (FCM). Retrofit simplifica a comunicação com APIs RESTful, tornando o código mais limpo e fácil de manter.
- **MockK**
  - MockK foi escolhido para facilitar a criação de mocks durante os testes unitários. Ele permite testar as interações entre componentes sem precisar de implementações reais, o que acelera o processo de teste.
- **Jetpack Compose**
  - Escolhi Jetpack Compose para a construção da interface do usuário, por ser a toolkit moderna de UI do Android, que facilita a criação de layouts declarativos e reativos, tornando o código mais limpo e de fácil manutenção.
- **Room**
  - Room foi utilizado para persistir os dados localmente, garantindo que as notas fossem armazenadas de forma segura e eficiente no dispositivo do usuário.
- **Firebase Cloud Messaging**
  - Firebase Cloud Messaging foi integrado tanto ao aplicativo, como ao back-end local para gerenciar o envio de notificações push. Utilizei FCM para garantir que os usuários sejam alertados às 9 horas da manhã do dia anterior ao vencimento da nota. Quando o back-end local recebe a requisição do dispositivo, ele aciona uma notificação via FCM, que é então entregue ao dispositivo do usuário.
- **Alarm Manager**
  - O AlarmManager foi utilizado para agendar um alarme no dispositivo do usuário, que será acionado às 9 horas da manhã do dia anterior ao vencimento da nota, sempre que uma nota for criada ou editada. Com isso, ao disparar o AlarmManager, é capturado o evento no Broadcast Receiver e em seguida é feita a requisição para o back-end local acionar o Firebase Cloud Messaging.
 
#### Outros

- **Back-end local usando Ktor**
  - Desenvolvi um back-end local utilizando Ktor para poder adequar ao Firebase Cloud Messaging HTTP v1, pois ele é necessário para guardar a chave da API do Firebase e servir como intermediário para solicitar as notificações para o FCM.

## 🦸 Autor

Feito por Luís Felipe Monteiro.

[![Linkedin Badge](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/luis-felipe-monteiro/)
[![Gmail Badge](https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:felipemonteirose@gmail.com)
