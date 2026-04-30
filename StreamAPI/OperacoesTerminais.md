# Operações Terminais em detalhe

## toList

- Retorna uma lista imutável
- Stream.toList()

```java
List<String> nomes = clientes.stream()
        .map(Cliente::getNome)
        .toList();

nomes.add("Novo"); // UnsupportedOperationException
```

- Se é necessário um ArrayList modificável, pode usar o collect


```java
List<String> nomes = clientes.stream()
        .map(Cliente::getNome)
        .collect(Collectors.toCollection(ArrayList::new));
```

- Ou retornar a stream para um arrayList

```java
List<String> nomes = new ArrayList<>(
        clientes.stream()
                .map(Cliente::getNome)
                .toList()
);
```

## collect

- Ele pode transformar o stream em diversos tipos diferentes de Interfaces
- List, Map, Set

```java
List<String> lista = stream.collect(Collectors.toList());
Set<String> set = stream.collect(Collectors.toSet());
Map<Long, Cliente> porId = clientes.stream()
        .collect(Collectors.toMap(
            Cliente::getId,
            cliente -> cliente
        ));
```

## count

- conta elementos

```java
long quantidade = clientes.stream()
        .filter(Cliente::isAtivo)
        .count();
```

## findFirst

- Retorna o primeiro elemento encontrado
- Retorna optional porque pode não encontrar nada

```java
Optional<Cliente> cliente = clientes.stream()
        .filter(c -> c.getId().equals(10L))
        .findFirst();
```

## findAny

- Parecido como findFirst, mas não garante necessariamente o primeiro em strams paralelas
- Qualquer um que satisfaça

```java
Optional<Cliente> cliente = clientes.stream()
        .filter(Cliente::isAtivo)
        .findAny();
```

## anyMatch

- Verifica se algum elemento atende à condição
- Short-circuit, para antes de percorrer tudo

```java
boolean existeInadimplente = clientes.stream()
        .anyMatch(Cliente::isInadimplente);
```

## allMatch

- Verifica se todos atendem

```java
boolean todosAtivos = clientes.stream()
        .allMatch(Cliente::isAtivo);
```

## noneMatch

- Verifica se nenhum atendem

```java
boolean nenhumBloqueado = clientes.stream()
        .noneMatch(Cliente::isBloqueado);
```

## Reduce

- Serve para reduzir vários elementos em um único resultado

```java
Integer soma = numeros.stream()
        .reduce(0, Integer::sum);

BigDecimal total = produtos.stream()
        .map(Produto::getPreco)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
```