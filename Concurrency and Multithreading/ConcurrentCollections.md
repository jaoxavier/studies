# Concurrent Collections

## Objetivos

- Estruturas de dados para um mundo paralelo
- Por que ArrayList e HashMap não são suficientes?
- Alternativas seguras e performáticas para estado compartilhado

## As limitações das coleções sincronizadas

- Collections.synchronizedMap(new HashMap<>()): Cria um wrapper onde cada método (get, put, remove) é synchronized
- Mecanismo de Locking: Usa um único lock para a coleção inteira (locking de granularidade grossa), ou seja, bloquea-se a coleção inteira para realizar a operação
- Problema de performance: Apenas uma thread pode acessar a coleção por vez, criando um gargalo de contenção e limitando severamente a escalabilidade

## ConcurrentHashMap - A escolha padrão para mapas concorrentes

- Mecanismo de locking fino: Em vez de um lock global, bloqueia apenas partes do mapa (nós/buckets) durante as operações de escrita.
- Alta Concorrência: Permite que múltiplas threads leiam e escrevam simultaneamente em diferentes partes do mapa. Leituras são geralmente não-bloqueantes
- Iteradores Seguros: Os iteradores são "fracamente consistentes" e nuncam lançam "ConcurrentModificationException"
- Restrição: não permite chaves ou valores null

## CopyOnWriteArrayList - Otimizado para leituras

- Estratégia "Copiar ao Escrever": Toda operação de modificação (add, set, remove) cria uma nova cópia do array subjacente
- Leituras rápidas e seguras: As operações de leitura são muito rápida, pois não exigem locks e operam em uma cópia imutável
- Caso de uso: ideal para coleções que são lidas com muita frequência, mas modificadas raramente (ex: Listas de listerners/observadores)
- Custo: Modificações são caras (O(n)) devido a cópia do array.

## BlockingQueue - o padrão produtor-consumidor

- Interface BlockingQueue: Uma fila que bloqueia operações quando necessário
- put(e): Bloqueia se a fila estiver cheia, esperando por um espaço
- take(): Bloqueia se a fila estiver vazia, esperando por um item
- Fundação do padrão produtor-consumidor: Desacopla thrreads que produzem dados daquelas que os consume gerenciando o fluxo de forma segura