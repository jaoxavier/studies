# Vitual Threads

## Plataform Threads

- java.lang.Thread
- É um wrapper em cima das threads de SO
- Threads de SO suportam todas as linguagens (nivela por baixo)
- o Java precisa respeitar o SO
- Alto consumo de RAM
- A troca de tasks em execução é feita no nível do kernel
- Há subutilização de hardware
- Para aumentar o throughput é necessário investir em hardware
- <b>Plataform Threads são RECURSOS (como memória, cpu, disco, etc)</b>

## Porque gera limtação de throughput

- Little's law: the average number L of customers in a stationary system is equal to the average effectiver arrival rate y multiplied by the average time W what a acustomer spends in system
- L = YW (L - Concorrencia, Y - thoughput, W - latência)
- Modelo Thread-per-request
    - Modelo de execução Plataform Threads
    - Cada request consome uma thread durante toda sua execução
    - A thread fica bloqueada durante todo o tempo de execução do request
    - Quanto maior o número de threads possíveis se serem executadas, maior o throughput
    - L = #threads 

## Concorrência vs Paralelismo

- Concorrência: múltiplas tarefas independentes que competem pelos mesmos recursos disponíveis. Medida de performance: throughput
- Paralelismo: Dividir uma tarefa em tarefas menores que utilizam múltiplos recursos disponíveis. Medida de performance: latência

## Programação Assíncrona

- Solução até as virtuais threads
- Opção pra evitar desperdício de hardware
- Quando há espera por algum IO, ao invés de bloquear a thread, ela é "devolvida" ao pool e pode ser usada por outras requests
- O número de requests que podem ser atendidas é maior que o número de threads disponíveis
- O L (concorrência) não é limitada pelo números de threads
- Problemas:
    - Não é intuitivo
    - Requer um set específico de API
    - Grande impacto na observilidade e tunning,
- Por que? Porque toda a plataforma Java é organizada em Java
    - Exceptions: Contexto é fornecido através de uma sequência de chamada de threads
    - Debugger: passos a passo da execução de uma thread
    - Profiler (JFR): Agrupoa eventos em torno de threads
- Tudo isso é "perdido" quando utilizados um abordagem assincrona

## Versionamento

- JEP 425 (1º preview JDK 19)
- JEP 436 (2º preview JDK 20)
- JEP 425 (Final JDK 21)
- Visa permitir que desenvolvedores JAVA:
    - Escrevam aplicações dque façam o melhor uso do hardware disponível através de um código simples de ler e manter e compatível com as API e ferramentas já existentes

## Virtual Threads

- java.lang.Thread - mesma API
- Não são um wrapper em cima da threads de SO
- 100% implementadas e executadas na JVM
- O runtime do java implementa threads de modo mais efeiciente que as threads de SO, porque ele sabe exatamente como uma aplicação java funciona
- Por exemplo, as virtuais threads podem usar resizable stacks, antes não era possível nas threads do DO
- A JVM aloca pouquíssimas threads (carrier thread) para alocar um número gigantesco de virtual threads
- Ou seja:
    - Programação Java tradicional
    - Melhor utilização dos recursos disponíveis ("near-optimal hardware utilization")
- A Virtual Thread deixa de ser um RECURSO

## Como usar Virtual Threads?

- Thread.startVirtualThread(() -> {})
- Executors.newVirtualThreadPerTaskExecutor(() -> {})
- Executam qualquer código java

## 4 Passos para adotar uma Virtual Thread

1. Use as boas e velhas blocking API
2. Uso de threads pools = poucas threads / Uso de thread per task = um monte de thread
    - Transforme uma task em uma virtual thread, não uma SO thread por Virtual Thread
        - Task = unidade de trabalho
        - Podem ser instâncias de Runnable ou Callable, por exemplo
    - Não compartilhe uma VT entre várias tasks
    -  Virtual Threads não são recursos, são objetos de lógica de negócio 
3. Fuja do Pinning
    - Processo que leva muito tempo para ser concluindo, roda em modo Syncronized e é executado com muita frequência
    - Os 3 tem que estar presentes ao mesmo tempo, senão não é pinning
    - Essas combinações podem matar a escalabilidade das virtual threads
    - Resolvido no JAVA 21
4. Cuidado com casos de monopolização
    - Não use virtual threads para executar processos de longa duração
    - Esse tipo de workboard precisa aguardar o término de I/O, e por isso não pode fazer swaps rápidos entre threads
    - Ao fazer isso, ele bloqueia um core de cpu até a conclusão do I/O, ou seja, ele monopoliza a carrier thread
    - O que leva a criação de outras carrier threads, resultando não só em degradação de performance

## Futuro: Structured Concurrency

- Visa simplificar a programação concorrente em JAVA, trtando grupos de tasks que executam em diferentes threads como se fossem uma única unidade de trabalho
- Facilita o tratamento de erros e cancelamento de tasks filhas de modo coordenado
- Aumenta a prevesibilidade e o controle de execução de código
- Observabilidade

## Virtual Threds no Quarkus

- @RunOnVirtualThread