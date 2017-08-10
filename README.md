# Sample Java FX (Desktop Application)
#### Thiago Gomes

### Indice 

 * [Lógica de Transição de Telas](https://github.com/thiago123789/aula_javaFX/blob/master/README.md#1-lógica-das-transições-de-tela)
 * [Janela de Navegação principal](https://github.com/thiago123789/aula_javaFX/blob/master/README.md#2-janela-de-navegação-da-aplicação)


### 1. Lógica das transições de tela

O exemplo a seguir foi desenvolvido utilizando um conceito de transição de telas sobrescrevendo o objeto ``Stage``(Palco) da classe principal do sistema.
Para isso foi implementado o seguinte método:

```
public void changeStage(Stage stage){
  this.primaryStage = stage;
}
```

> Esse método servirá para enviar a classe que herda da classe ``Application``, classe Main, um novo objeto ``Stage`` modificado, fazendo assim com que a aplicação receba uma nova estrutura de ``Stage``.

Para que isso funcione é preciso que seja enviado para todos os controllers, que são carregados, a instancia da aplicação que iremos modificar.
Primeiramente, devemos criar uma variável estática que irá armazena essa instancia, dentro da classe que herda de ``Application``.

```
private static Sistema instance;
```
Também devemos criar um método ``get`` para essa variável, como se fosse um objeto ``singleton``.

```
public static Sistema getInstance(){
  if(instance == null){
    instane = new Sistema();
  }
  return instance;
}
```

> Vale salientar que como o JavaFX é responsável por criar essa instancia e invocar o metodo ``start`` quando utilizarmos em outra classe esse ``Sistema.getInstance()`` o instance será ``null``. Para resolver este problema precisamos adicionar a seguinte linha no metodo ``start()``.

```
...
public void start(){
  instance = this;
  ...
}
```

> Todo esse processo serve para sempre possuirmos a referencia para a classe que herda de ``Application``, ou seja, a primeira janela a ser exibida. Com isso não precisaremos, a toda nova funcionalidades que quisermos executar, abrir uma nova janela com a nova função. Apenas iremos modificar o conteúdo da primeira janela que foi exibida.

### 2. Janela de navegação da aplicação

![Janela Principal](/imgs/main.JPG)

