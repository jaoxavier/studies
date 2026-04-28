# Componente-Based

- Manifestação física do modulo, um pacote de código pronto para implantar
- .jar no Java, .dll no .NET

## Escopo

- Biblioteca (Libary): Componente mais simples, empacota código reutilizavel que roda no mesmo processo da aplicação.
- Subsistema/Camada: Compoenente que agrupa várias classes/módulos em um nível mais alto, podendo definir uma layer ou serviço interno do sistema.
- Serviço (Service): Componente autônome executado em seu próprio processo ou servidor, comunicando-se via rede.

## Papéis na definição de componentes

- Arquiteto: Define e é responsável pelos componentes do sistema em alto nível, colaborando com analistas de negócio, especialistas e desenvolvedores. Atende requisitos funcionais e não funcionais
- Desenvolvedor: Refina os componentes em estrutura de códigos (classes, funções) e implementa detalhes internos. Desenvolvedores e líderes técnicos participam do design de componentes junto com o arquiteto

## Partição dos componentes

- Divisão em componentes: Decidir como particionar a arquitetura em componentes de alto nível. Toda decisão envolve trade-off. Diferentes estilos de particionamento gera impactos
- Estilos de particionamento: Dois estilos comuns de organização global do sistema são a partição técnica em camadas e a partição por domínios de negócio

## Domain Partitioning

- Foco no Domínio: A arquitetura é dividida em componentes autônomos alinhadas a workflow ou contextos de negócio específicos. Essa estratégia aproxcima o software a vida real
- Vantagens: Maior coesão com negócio, cada componente implementa um conjunto de casos de uso do domínio, reduzindo dependências entre domínios. O design fica mais próximo de um monólito modular ou arquitetura de microservices, com fluxo de mensagens alinhado a funcionalidades reais do problema.
- Desafio: Possível duplicação de lógica ou código em multiplos componentes 

## Particionamento em Camadas

- Foco técnico: A arquitetura é organizada por capacidades técnicas ou camadas lógicas, separando responsabilidades como apresentação, lógica de negócio, serviços e persistência.
- Vantagens: Promove separação de preocupações técnicas, código de apresentação isoldado do de negócio, que por sua vez é isolado do de dados. Facilita reutilizar componentes técnicos comuns e segue um modelo familiar de arquitetura em camadas.
- Desvantagens: Acoplamente global elevado, se partes comuns e locais estão formente ligadas, mudanças em um componente comum ou local podem impactar todos os outros.

## Fluxo de identificação de componentes

- Identificar componentes iniciais: o arquiteto propõe um conjunto inicial de componentes que cubra as principais funcionalidades de acordo com o estilo de partição escolhido. Essa primeira divisão é hipotética e dificilmente perfeita
- Alocar requisitos funcionais e não funcionais para os componentes, nessa fase surgem novos componentes, fusões ou divisões de componentes
- Analisar papéis e responsabilidades: Cada componente tem que ter um conjutno coeso de responsabilidades condizentes com os atores e caso de uso levantados
- Considerar caracterpisticas arquiteturais: Verifica-se como requisitos não funcionais afetam a divisão de componentes
- Componentes que enfrentam exigencias arquiteturais diferentes devem ser separados.
- Refinar e Iterar: O conjunto de componentes é refinado continuamente com feedback do desenvolvimento

## Granularidade

- Fine-Grained vs Coarse-Grained: Encontrar o tamanho ideal dos componentes é um dos desafios do arquiteto. Se definirmos componentes muitos pequenos e especificos o sistema sofre com comunicações excessivas entre eles para cumprir funcionalidades, por outro lado, muito grande concentram muitas responsabilidades internamente, gerando alto acoplamento interno e dificultando testes e implementação

## Anti-Pattern: Entity Trap

- Problema: O Arquiteto identifica componentes apenas espelhando as entidades do banco de dados, criando por exemplo, componentes Manager para cada entidade. O resultado é uma arquitetura que na verdade não adiciona abstração significativa além do que um ORM já forneceria
- Essa abordagem ignora os fluxos de trabalhos reais do sistema, os relacionamentos do banco raramente correspondem 1:1 aos processos de negócio. Assim, componentes definidos apenas pelas entidades tendem a ser excessivamente genéricos e granulares demais

## Monílito vs Distribuído

- Monólito: É implantada como uma única unidade, um único processo contendo todos os comopnentes geralmente conectada a um banco de dados unificados. Oferece simplicidade de implantação e comunicação interna direta entre componentes, sendo vantajosa se o sistema puder ser satsfeito com um único conjunto de características arquiteturais uniforme.
- Distribuiída (Microservices): múltiplos serviços/componentes implantados separadamente, cada um rodando em seu prórprio processo e comunicando-se via rede. Adequada quando diferentes compoenentes do sistema possuem demandas arquiteturais bem distintas.
- Crtério de escolha: deve ser guiada pelas caracteristicas arquiteturais identificadas no design. Se todos os componentes compatilham dos mesmos requisitos não funcionais, um monolito simplifica o sistema. Mas se surgem componentes com necessidades incompatíveis é um indicativo de arquitetura distribuida