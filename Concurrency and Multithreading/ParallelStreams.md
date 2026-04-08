# Parallel Streams

## Objetivo

- Paralelismo Funcional e Simplificado
- O poder e o perigo de .parallel()
- Quando e como usar streams paralelos de forma segura e efeicaz

## A ligação com o Fork/Join

- Mecanismo Interno: Parallel Streams utilizam o ForkJoinPool.commonPool() por baixo dos panos
- Spliterator: A fonte de dados (ex: ArrayList) fornece um Spliterator que é responsável por dividir a fonte em pedaços para o processamento paralelo
- Processamento: O commonPool executa as operações do stream em cada pedaço, usando o algoritimo de work-stealing

## Quando usar Parallel Streams

- Tarefas CPU-Bound: A operação em cada elemento deve ser computacionalmente intensiva
- Grandes Volumes de Dados: Overhead do paralelismo só se paga em datasets grandes (Modelo NQ: N*Q>10000)
- Fontes de dados Eficientemente Divisíveis: ArrayList, int, long são ideais. HashSet e TreeSet são razoáveis. LinkedList é pessímo
- Operações Stateless e Associativas: As funções lambda não devem depender de estado mutável compartilhado. Operações de redeução devem ser associativas.

## Perigo de I/O bloqueante

- O Problema: O commonPool tem um número limitado de threads
- Saturação do Pool: Se uma tarefa no stream faz uma chamado de I/O bloqueante (rede, dsico), ela monopoliza uma thread do pool
- Conseuência: Se muitas tarefas bloquearem, todo o commonPool pode ser esgotado, paralisando toddas as tarefas Fork/Join e Parallel Streams na JVM

## Perigo de lambdas com estado (stateful lambdas)

- O Problema: Streams paralelos não garantem ordem de execução
- Condição de corrida: Se a funlão lambda modifica um estado compatilhado (ex. adicionar a uma lista externa, incrmentar um contador), ocorrerá uma condição de corrida
- Consequência: Resultados incorretos, incosistentes ou exceções em tempo de exeução. As operações devem ser stateless

## Conclusões e melhores práticas

- Padrão: Streams sequenciais
- Otimização: Considere apenas para operações CPI-Bound em grandes coleções eficentemente divisíveis
- Meça, não advinhe: Sempre faça um benchmark para confirmar o ganho de performance
- Evite I/O bloqueante, lambas com estado forte e fonte de dados inadequadas (LinkedList)