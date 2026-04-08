# Completable Future

## Objetivo

- Programação assíncrona e reativa
- Superando limitações do future
- Compondo, combinando e tratando erros em operações assíncronas

## Criando um CompletableFuture

- runAsync(Runnable): executa uma tarefa assíncrona que não retorna valor. Retorna CompletableFuture<Void>
- supplyAsync(Supplier<T>): executa uma tarefa assíncrona que retorna um valor. Retorna CompletableFuture<T>
- Executor Padrão: por padrão, usa o ForkJoinPool.commonPool(). Uma sobrecarga permite fornecer um Executor customizado.

## Encadeamento e transformação de resultados

- thenApply(Function<T, U>): Transforma o resultado T em um resultado U de forma síncrona. Analogo ao map() do Stream.
- thenRun(Runnable): Executa uma ação após conclusão, sem usar o resultado.
- thenAccept(Consumer<T>): Consome o resultado T sem retornar nada. Uma ação terminal
- Variantes ...Async: executam o estágio em um pool de threads (geralmente o commonPool)

## thenApply vs. thenCompose

- thenApply: para mapeamento simlpes T>U
    - getUserById(id).thenApply(User::getEmail).
    - O método getEmail retorna uma String, não um CompletableFuture

- thenCompose: Para encadear operações que já são assíncronas.
    - T > CompletableFuture< U>
    - Ele achata o resultado para CompletableFuture< U>. Análogo ao flatMap() do stream

## Combinando múltiplos futures

- thenCombine(other, BiFunction): Combina os resultados de dois futuros independentes quando ambos estiverem concluídos
- allOf(futures...): Cria um CompletableFuturee< Void> que é concluído quando todos os futuros fornecidos são concluídos
    - Stream.of(f1, f2, f3).map(CompletableFuture::join).collect(Collectors.toList())
- anyOf(futures...): Cria um CompletableFuture< Object> que é concluído quando qualquer um dos futuros fornecidos é concluído

## Tratamento de erros robusto

- exceptionally(Function< Throwable, T>): fornece um valor de fallback caso o pipeline falhe. Análogo a um bloco catch
- handle(BiFunction< T, Throwable, U>): Processa tanto o resultado de sucesso (T), quando a exceção (Throwable), análogo a um bloco finally que pode retornar um valor