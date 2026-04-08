# Fork/Join framework


## Objetivo

- Paralelismo com "Dividir para conquistar"
- Processando tarefas recursivas em múltiplos núcleos
- Introduzido no Java 7

## Arquitetura e o algoritimo work-stealing

- ForkJoinPool: o ExecutorService que gerencia as worker threads
- Deques (Double-Ended Queues): Cada Worker Thread possui sua própria fila de tarefas
- Work-Stealing: Quando uma thread fica ociosa, ela rouba trabalho da fila de outra thread, garantindo o balanceamento de carga e a máximo utilização da CPU
- Trbalha em LIFO pois normalmente há mais task para as ultimas tasks que entram na fila

## Tipos de tarefa: RecursiveTask e RecursiveAction

- ForkJoinTask<V>: A classe base abstrata para tarefas Fork/Join
- RecursiveTask<V>: Para tarefas que retornam um resultado (análogo Callable<V>)
- RecursiveAction: Para tarefas que não retornam um resultado (análogo a Runnable)

## Implementando o método compute()

```
if (problema é pequeno é suficiente){
    resolve diretamente; // caso base
} else {
    divide o problema em subproblemas; // fork
    inicia as subtarefas de forma assincrona;
    espera pelos resultados e os combina; // join
}
```

- fork(): Submete a tarefa para execução assíncrona
- join(): Aguarda o conclusão da tarefa e obtém o seu resultado

```
subtask1.fork(); // Envia a subtask1 para deque para possível roubo
subtask2.compute(); // Executa na thread atual
ResultType result1 = substask1.join(); // Espera pelo resultado de subtask1
```

## Usando o ForkJoinPool

- Criação do Pool: ForkJoinPool pool = new ForkJoinPool(); (usa o número de processadores disponíveis como nível de paralelismo)
- Runtime.getRuntime().availableProcessors()
- Pool Comum: ForkJoinPool.commonPool() é um pool estático e compartilhado, usado por padrão por Parallel Streams e CompletableFuture.
- Submissão da tarefa: pool.invoke(minhaTarefaPrincipal);