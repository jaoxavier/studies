## Synchronizers

## Objetivo

- Synchronizers: Orquestrando a colaboração entre threads
- Além do Synchronized: ferramentas para coordenação avançada
- Coordenando o inicio, o fim e o fluxo de trabalho de multiplas threads

## CountdownLatch: Esperando por Eventos

- Propósito: Permite que uma ou mais threads esperem até que um conjunto de N operações, executadas em outras threads, seja concluido
- Funcionamento:
    - Inicializado com uma contagem N
    - await() bloqueia até que a contagem chegue a zero.
    - countDown() decrementa a contagem
- Uso único: Não é reutilizável. Uma vez que a contagem chega a zero, await() retorna imediatamente
- Ex: Thread principal que precisa que diversos serviços estejam prontos para seguir as operações

## CyclicBarrier: Encontrando-se em uma barreira

- Propósito: Permite que um número fixo de threads espere umas pelas outras em um ponto de barreira comum
- Funcionamento:
    - Threads chamam await()
    - A última thread a chegar "quebra" a barreira liberando todas as outras
- Reutilizavel (ciclica): Após a quebra, a barreira é resetada automaticamente e pode ser usada novamente
- Ação Opcional: Pode executar um Runnable quando a barreira é quebrada

## Semaphore: Limitando o acesso a recursos

- Propósito: Controla o acesso a um recurso compartilhado atráves de um contador de permissões (permits)
- Funcionamento:
    - acquire() bloqueia até que uma permissão esteja disponível e a adquire
    - release() libera a permissão
- Casos de uso: limitar p número de conexões a um banco de dados, controlar o acesso a um pool de recursos caros
- Semáforo binário: Um semaforo com uma única permissão pode atuar como um lock (mutex)

## Phaser: A barreira dinâmica e multifásica

- A evolução dos sincronizadores: combina e expande as funcionalidades de CyclicBarrier and CountDownLatch
- Número dinâmico de participantes: Threads (parties) podem se registrar (register()) e desregistrar (arriveAndDeregister()) dinamicamente
- Fases (phases): Suporta múltiplos estágios de sincronização, onde o número de participantes pode mudar a cada fase