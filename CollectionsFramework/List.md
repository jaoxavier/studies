# List Interface

## ArrayList

- Array que cresce
- Object[] e int size
- Se o array tiver cheio, cria um novo e copia os elementos
- Excelente para leitura, ruim para inserções no meio

| Operação    | Custo           |
| ----------- | --------------- |
| get(index)  | O(1)            |
| add (final) | O(1) amortizado |
| add (meio)  | O(n)            |
| remove      | O(n)            |

## LinkedList

- Lista Encadeada, cada elemento aponta para outros dois, o anterior e o próximo
- CPU cache ruim, mais objetos em memórias, mais GC

| Operação       | Custo |
| -------------- | ----- |
| get(index)     | O(n)  |
| add início/fim | O(1)  |
| add meio       | O(n)  |
