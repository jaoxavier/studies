# Stream API

- Pipeline de processamento de dados
- Transforma os dados de uma fonte (collection, array) e aplica operações encadeadas
- Não armazena dados, ela contém uma sequência de operações a serem executadas sobre uma fonte
- Só pode ser consumida uma vez

```java
list.stream()
    .filter(x -> x > 10)
    .map(x -> x * 2)
    .forEach(System.out::println);
```

Fonte dos dados        Operação intermediária     Operação intermediária     Operação terminal
list.stream()  ->   filter(...)           ->    map(...)              ->   forEach()

## Conceito 

- Toda Stream normalmente tem:

1. Fonte, de onde os dados vem
    - Clientes.stream()

2. Operações intermediárias, são operações que retornam outra Stream e são chamadas de intermediárias porque permitem continuar a pipeline
    - Ex.: filter(), map(), flatMap(), distinct(), sorted(), limit(), skip(), peek()

3. Operação Terminal, executa a Stream e fecha o fluxo
    - Ex.: toList(), collect(), forEach(), count(), findFirst(), anyMatch(), allMatch(), noneMatch(), reduce(), min(), max()

# Stream Primitivas

- É muito comum usar os maps abaixo para os números
- mapToInt(), mapToLong(), mapToDouble()
- Stream<Integer> trabalha com objetos Integer
- IntStream trabalha com primitivos, int, evitando boxing/unboxing

## Lazy Evaluation

- Stream são preguiçosas, ou seja, operações interemediárias não executam imediatamente
- Aguardam a operação terminal para executar a pipeline

## Optional

- Muitas operações podem retonar optional
- findFirst(), findAny(), min(), max(), reduce() sem valor inicial

## Short-circuit