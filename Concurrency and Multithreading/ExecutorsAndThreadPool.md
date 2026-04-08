# Executors e Thread Pools

## Objetivos

- Gerenciamento Moderno de Threads
- Abstraindo a execução de tarefas do gerenciamento de Threads
- Do controle manual à automação e eficiência

## Executor Franework: Componentes Fundamentais

- Interface Executor: O contrato mais simples: void execute(Runnable command). Desacopla a submissão da execução.
- Interface ExecutorService extends Executor. Adiciona gerenciamento de ciclo de vida (shutdown, awaitTermination), submissão de Callable e obtenção de resultados via Future
- Classe Executors: Classe factory para criar instâncias de ExecutorService com configurações pré-definidas e otimizadas 

## Tipos de Pools de Threads

- newFixedThreadPool(int n): Mantém um número fixo de threads
    - Ideal para cargas de trabalho com uso intensivo de CPU.
    - O números de threads é dimensionado de acordo com o núcleo do processador
    - Se todas threads estiverem cheias, entrará numa fila que pode ser tão grande que OutOfBound

- newCachedThreadPool(): Cria Threads conforme a necessidade e as reutiliza.
    - Adequado para um grande números de tarefas curtas e assíncronas
    - Depois de 60seg é fechada e o cache limpa
    - Por "não ter um limite" ele pode roubar recursos do sistema

- newSingleThreadExecutor(): Uma thread sequencial
    - Garante que as tarefas sejam executadas sequencialmente em uma única threas.
    - Útil para garantir ordem e evitar concorrência em tarefas de background.

- newScheduledThreadPool(int corePoolSize): permite agendar
    - Execução de tarefas após um atraso ou de forma periodica

## Dimensionando Pools de Threads: CPU-BOUND vs I/O-BOUND

- Tarefas CPU-Bound: O número de threads deve ser próximo ao número de núcleos de CPU
- Um pool de NCPUs ou NCPUs +1 threads maximiza a utilização sem excesso de troca de contexto.
- Ex de CPU-BOUND: Processamento de imagens, calculos, operações
- Tarefas I/O-BOund: As tarefas passam muito tempo bloqueadas (esperando por rede, disco)
- Um número maior de threasds é necessário para manter a CPU ocupada
- Uma boa parte do tempo de uso é na espera, fica muito ocioso.
- Por causa disso, precisa de um número maior de threads para cobrir a quantidade de threads bloqueadas.
- Formula de Dimensionamento (Brian Goetz)
- Threads = NCPUs * (1 + Tempo de Espera / Tempo de serviço)
- Ex de I/O-Bound: Consulta a banco de dados, chamadas API-REST, leitura e escrita de arquivos

## Ciclo de vida e desligamento

- shutdown(): Desligamento ordenado. Rejeita novas tarefas, mas permite que as submetidas terminem.
- shutdownNow(): Desligamento abrupto. Tenta parar todas as tarefas em execução (via Thread.interrupt) e retorna a lista de tarefas não iniciadas.
- awaitTermination(long timeout, TimeUnit unit): bloqueia a execução até que as tarefas expirem após um shutdown ou até que dê timeout
- Melhor prática: Sempre desligue seus ExecutorServices, preferencialmente em um bloco finally ou try-with-resources

## Project Loom: Virtual Threads

- Java 21+ Introduz o virtual threads gerenciadas pela JVM não pelo SO.
- Executors.newVirtualThreadsPerTaskExecutor(): Um novo tipo de ExecutorService que cria uma nova thread virtual para cada tarefa submetida
- Baixissimo custo de criação e bloqueio. Permite milhões de threads concorrentes, ideal pela tarefa I/O-bound