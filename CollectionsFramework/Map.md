# Map

## HashMap

- Node<K, V>[] table;
- Array de Buckets
- Cada posição pode ter uma lista encadeada ou árvore
- Necessida de hashCode bem feito e equals correto

## Inserção 

1. Calcula Hash
2. Encontra Bucket
3. Verifica
    - Bucket vazio -> insere
    - Bucket ocupado -> trata colisão virando uma lista, caso torne-se muito grandes, vira árvore

| Operação  | Custo      |
| --------- | ---------- |
| get       | O(1) médio |
| put       | O(1) médio |
| pior caso | O(n)       |


## HashSet

- HashMap<E, Object>
- Um Map disfarçado
- set.add("A"); vira map.put("A", PRESENT);

## TreeMap / TreeSet

- Mantém ordem automaticamente
- Usa Comparable ou Comparator
- Mas lento que HashMap, mas ordenado

| Operação | Custo    |
| -------- | -------- |
| get      | O(log n) |
| put      | O(log n) |

## LinkedHashMap

- HashMap + Lista Encadeada
- Mantém ordem de inserção ou de acesso

## PriorityQueue

- Heap (fila de prioridade)
- Implementada como array
- Menor elemento sempre no topo
- Reorganiza a cada inserção

| Operação | Custo    |
| -------- | -------- |
| add      | O(log n) |
| poll     | O(log n) |
| peek     | O(1)     |

## ArrayDeque

- Muito mais rápida que Srack
- Melhor que LinkedList para fila

