# Collectors

## groupingBy

- Agrupa elementos por algum critério

```java
Map<String, List<Cliente>> clientesPorCidade = clientes.stream()
        .collect(Collectors.groupingBy(Cliente::getCidade));
```

- Agrupa com contagem

```java
Map<String, Long> quantidadePorCidade = clientes.stream()
        .collect(Collectors.groupingBy(
            Cliente::getCidade,
            Collectors.counting()
        ));
```

- Agrupa com soma

- Inteiro:

```java
Map<String, Integer> idadeTotalPorCidade = clientes.stream()
        .collect(Collectors.groupingBy(
            Cliente::getCidade,
            Collectors.summingInt(Cliente::getIdade)
        ));
```

- BigDecimal

```java
Map<String, BigDecimal> totalPorCategoria = produtos.stream()
        .collect(Collectors.groupingBy(
            Produto::getCategoria,
            Collectors.reducing(
                BigDecimal.ZERO,
                Produto::getPreco,
                BigDecimal::add
            )
        ));
```

# toMap

- Tranforma um list em map

```java
Map<Long, Cliente> clientePorId = clientes.stream()
        .collect(Collectors.toMap(
            Cliente::getId,
            Function.identity()
        ));

// se duas chaves forem iguais, dá exception
// para resolver, passa uma função merge

Map<Long, Cliente> clientePorId = clientes.stream()
        .collect(Collectors.toMap(
            Cliente::getId,
            Function.identity(),
            (anitgo, novo) -> antigo // aqui escolhe qual vai querer
        ));

// se quiser preservar ordem de inserção

Map<Long, Cliente> clientePorId = clientes.stream()
        .collect(Collectors.toMap(
            Cliente::getId,
            Function.identity(),
            (antigo, novo) -> antigo,
            LinkedHashMap::new
        ));
```

## partitioningBy

- Separa em dois grupos, true e false
- Sempre agrupa por boolean

```java
Map<Boolean, List<Cliente>> particionados = clientes.stream()
        .collect(Collectors.partitioningBy(Cliente::isAtivo));
```

## joining

- Muito útil para montar strings

```java
String nomes = clientes.stream()
        .map(Cliente::getNome)
        .collect(Collectors.joining(", ", "[", "]"));
```


