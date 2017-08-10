# Sample Java FX (Desktop Application)
### Thiago Gomes

__PS: Todos os codigos estão disponíveis nesse repositório__

### Indice 

 * [Lógica das transições de tela](https://github.com/thiago123789/aula_javaFX/blob/master/README.md#1-lógica-das-transições-de-tela)
 * [Janela de navegação da aplicação](https://github.com/thiago123789/aula_javaFX/blob/master/README.md#2-janela-de-navegação-da-aplicação)
 * [Eventos de click de teclado](https://github.com/thiago123789/aula_javaFX/blob/master/README.md#3-eventos-de-click-de-teclado)

### 1. Lógica das transições de tela

![Janela Application Login](/imgs/login.JPG)

O exemplo a seguir foi desenvolvido utilizando um conceito de transição de telas sobrescrevendo o objeto ``Stage``(Palco) da classe principal do sistema.
Para isso foi implementado o seguinte método:

```
public void changeStage(Stage stage){
  this.primaryStage = stage;
}
```

> Esse método servirá para enviar à classe que herda da classe ``Application``, classe Main, um novo objeto ``Stage`` modificado, fazendo com que a aplicação receba uma nova estrutura de ``Stage``.

Para que isso funcione é preciso que seja enviado para todos os controllers, que são carregados, a instancia da aplicação que iremos modificar.
Primeiramente, devemos criar uma variável estática que irá armazena essa instancia, dentro da classe que herda de ``Application``.

```
private static Sistema instance;
```
Também devemos criar um método ``getIntance()`` para essa variável, da mesma forma que fazemos na implementação do padrão ``Singleton``.

```
public static Sistema getInstance(){
  if(instance == null){
    instane = new Sistema();
  }
  return instance;
}
```

> Vale salientar que como o JavaFX é responsável por criar essa instância e invocar o metodo ``start`` quando utilizarmos em outra classe esse ``Sistema.getInstance()`` o instance será ``null``. Para resolver este problema precisamos adicionar a seguinte linha no metodo ``start()``.

```
...
public void start(){
  instance = this;
  ...
}
```

> Todo esse processo serve para sempre possuirmos a referência para a classe que herda de ``Application``, ou seja, a primeira janela a ser exibida. Com isso não precisaremos, a toda nova funcionalidades que quisermos executar, abrir uma nova janela com a nova função. Apenas iremos modificar o conteúdo da primeira janela que foi exibida.

### 2. Janela de navegação da aplicação

![Janela Principal](/imgs/main.JPG)

Nessa etapa nós iremos criar uma unica janela que ficará exibindo todos os outros templates.
Primeiro nós teremos que recuperar o template da janela acima, pois iremos edita-lo adicionando o conteudo a ser exibido sem remover a barra de menu, com o seguinte codigo:

```
Parent old = (Parent) FXMLLoader.load(getClass().getResource("/view/OverviewTemplate.fxml"));
```

> Lembrando que o layout da Janela Principal é um ``BorderPane``, então nós iremos adicionar o conteúdo no centro dele utilizando o método ``setCenter(Node node)``. 

Feito isso, deveremos carregar o template que desejamos apresentar na área onde está escrito __CONTEUDO__ em amarelo, iremos utilizar o seguinte código para isso:

```
Parent root = (Parent) FXMLLoader.load(getClass().getResource("/view/CadastrarContatoTemplate.fxml"));
```

> O comportamento será o mesmo independente do tipo de layout que está sendo utilizado nesse fxml(``AnchorPane``, ``Pane``, ``BorderPane``, etc)

Feito isso iremos então adiconar o ``CadastrarContatoTemplate`` dentro do ``OverviewTemplate`` da seguinte forma:

```
1 - ((BorderPane) old).setCenter(root);
2 - Scene scene = new Scene(old);
3 - stage = sis.getPrimaryStage();
4 - stage.setScene(scene);
5 - sis.changeStage(stage);
```

> Na primeira linha do código acima fizemos um cast, pois __TEMOS CERTEZA__ que a janela que estamos carregando é um ``BorderPane``, com isso adicionamos o ``CadastrarContatoTemplate`` no centro desse ``BorderPane``.

> Na segunda linha iremos criar um objeto do tipo ``Scene`` que receberá como paremetro o template da janela principal atualizado, com o conteúdo já carregado.3

> Na terceira linha iremos carregar o ``Stage`` da aplicação utilizando o método ``getPrimaryStage()`` que é estático e foi implementado na classe Sistema.

> Na quarta linha iremos modificar ``Stage`` inserindo o ``Scene``(Cenário) que foi instanciado na segunda linha.

> /Na quinta linha iremos passar o ``Stage`` atualizado, ou seja, com o conteúdo já adicionado, para a classe da ``Application``. 

### 3. Eventos de click de teclado

Durante a aula me perguntaram como fazer a aplicação reconhecer o click do enter... então segue o código aí...

Para reconhecer o click do botão esse click deve ser ligado a algum componente da tela, no meu caso eu liguei ele ao campo de senha (Que faz sentido), ficando da seguinte forma:

```
this.passwordField.setOnKeyPressed(e -> {
  if (e.getCode() == KeyCode.ENTER) {
    login();
  }
});
```

> utilizando [lambda](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html), estou criando um método que quando uma tecla for pressionada ele irá verificar o código dessa tecla, se for igual ao enter ele irá executar o método ``login()``. 


```
private void login(){
  Stage stage = null;
  Parent root = null;
  boolean loginOk = false;
  try{
  if(userField.getText().equals(user)){
    if(passwordField.getText().equals(pass)){
      stage = (Stage) loginButton.getScene().getWindow();
      root = (Parent) FXMLLoader.load(getClass().getResource("/view/OverviewTemplate.fxml"));
      loginOk = true;
    }else{
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Falha de Login");
      alert.setHeaderText("Informações inválidas");
      alert.setContentText("Senha incorreta");
      alert.showAndWait();
    }
  }else{
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Falha de Login");
    alert.setHeaderText("Informações inválidas");
    alert.setContentText("Usuário não encontrado");
    alert.showAndWait();
  }
  if(loginOk){
    Scene scene = new Scene(root);
    stage.setScene(scene);
    String tituloAtual = stage.getTitle();
    stage.setTitle(tituloAtual +" - "+ (""+user.charAt(0)).toUpperCase() + user.substring(1, user.length()));
    stage.setResizable(true);
    sis.changeStage(stage);
  }
  }catch(IOException e){
  e.printStackTrace();
  }
}
```

### Dúvidas ???

email-me: thiago123789@gmail.com
