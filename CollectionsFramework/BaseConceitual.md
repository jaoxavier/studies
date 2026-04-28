# Iterable

- Todas as sub classes que herdam essa classe, tornam-se interativas, ou seja, podem ser percorridas
- Pode ser usado em um for each

# Collection

- Uma collection herda iterable
- Define operações básicas: add, remove, contains, size, isEmpty
- Mas a Collection em si não define:
    - Ordem
    - Duplicidade
    - Acesso por Índice

# List extends Collection<E>

- Ordernada e Indexada
- Mantém ordem
- Permite duplicados
- Acesso por índice

# Set extends Collection<E>

- Conjunto Matemático
- Não permite duplicados
- Pode ou não ter ordem
- Aqui contém equals e hashCode

# Queue extends Collection<E>

- Fila FIFO - First in First out
- Ordem de processamento
- Foco em inserção e remoção

# Deque extends Queue<E>

- Fila dupla, insere/remove no inicio e fim
- pode ser stack ou queue

# Map NÃO É COLLECTION

- Não é um grupo de elementos simples
- Associa uma chave ao valor
- ```Map<String, Integer> idades;```
- Trabalha com pares Entry<K, V>
- Usa chave como identificador único

# RESUMO

-```List<String> lista = new ArrayList<>();```
- Programa para a interface List e escolhe a<b> Estratégia </b> ArrayList

1. Preciso de Ordem? 
2. Preciso de Indíce?
3. Posso ter Duplicados?
4. Preciso de busca rápida?
5. Preciso de Ordenação automática?
6. Preciso de uma chave para achar o valor?

# Compração geral

| Estrutura     | Base           | Melhor uso     |
| ------------- | -------------- | -------------- |
| ArrayList     | array          | leitura rápida |
| LinkedList    | nós            | raramente útil |
| HashMap       | hash           | acesso rápido  |
| TreeMap       | árvore         | ordenação      |
| HashSet       | hash           | unicidade      |
| PriorityQueue | heap           | prioridade     |
| ArrayDeque    | array circular | fila/stack     |
