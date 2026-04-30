# Operações Intermediárias em detalhe

## filter

- Manter elementos dentro de uma condição

```java
List<Cliente> ativos = clientes.stream()
        .filter(cliente -> cliente.isAtivo())
        .toList();
```

## map

- Serve para transformar um elemento em outro

```java
List<String> nomes = clientes.stream()
        .map(cliente -> cliente.getNome())
        .toList();
```

## flatMap

- Transforma um elemento em vários elementos e depois achata tudo
- por exemplo:

```java
List<List<String>> grupos = List.of(
        List.of("Ana", "João"),
        List.of("Maria", "Pedro")
);
```

- Se usarmos MAP, vamos transformar a List<List<String>> em uma Stream<Stream<String>>

```java
List<Stream<String>> resultado = grupos.stream()
        .map(lista -> lista.stream())
        .toList();
```

- Já se usarmos o flatMap

```java
List<String> nomes = grupos.stream()
        .flatMap(Collection::stream)
        .toList();
```

- Obtemos um resultado em uma lista 

```
["Ana", "João", "Maria", "Pedro"]
```

- Exemplo final, se nossa classe pedido tem uma lista de pedidos dentro dela, ao inves de ter uma List<List<ItensPedidos>>, teria uma List<ItensPedidos>

## Distinct

- Remove duplicados de acordo com uma condição
- Em objetos utiliza como referencia equals e/ou hashCode
- equals e hashCode precisam ser bem implementados

```java
List<String> nomesUnicos = nomes.stream()
        .distinct()
        .toList();
```

## Sorted

- Ordena a Stream
- Para objetos precisa de um Comparator 
- Exemplo Objetos:

```java
List<Cliente> clientesOrdenados = clientes.stream()
        .sorted(Comparator.comparing(Cliente::getNome))
        .toList();
```

- Exemplo Objetos com multiplos critérios

```java
List<Cliente> clientesOrdenados = clientes.stream()
        .sorted(
            Comparator.comparing(Cliente::getCidade)
                      .thenComparing(Cliente::getNome)
        )
        .toList();
```

## limit e skip

- Muito úteis para paginação simples ou top N
- Limit limita a quantidade de resultados
- Skip pula elementos

```java
List<Integer> top3 = numeros.stream()
        .sorted(Comparator.reverseOrder())
        .skip(3)
        .limit(3)
        .toList();
```

## peek

- Serve para inspecionar a pipeline

```java
List<String> resultado = nomes.stream()
        .peek(nome -> System.out.println("Original: " + nome))
        .map(String::toUpperCase)
        .peek(nome -> System.out.println("Upper: " + nome))
        .toList();
```
