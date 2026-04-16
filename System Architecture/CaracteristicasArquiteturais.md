# Características Arquiteturais

- São atributos de qualidade do software que fogem do negócio, mas definem como o sistema se comporta ou quão bem realiza suas funções
- Também chamados de requisitos não funcionais
- Fora do negócio
- O atributo requer decisões arquiteturais específicas
- Crítico para o sucesso da aplicação

## Requisito x Caracteristica

- Requisitos são funcionalidades do negócio, o que o software deve fornecer para atender as necessidades do dominio
- Caracteristicas são aspectos gerais de qualidade e operação do sistema. O que deve atender em termos de desempenho, segurança, escalabilidade, etc
- A responsabilidade do arquiteto é identificar e endereçar esses requisitos de arquitetura

## Características implícitas e explícitas

- explícitas: são aquelas declaradas claramente em documentos de requisitos, contratos ou pelos stakeholders
- implícitas: são qualidades não mencionadas firetamente, mas que são naturalmente esperadas
- Descoberta proativa: Cabe ao arquiteto levantar essas caracteristicas implícitas

## Operacionais

- Desempenho: Capacidade do sistema de responder rapidamente e processar a carga de trabalho. Inclui tempos de respostas, throughput e uso eficiente de recursos computacionais
- Disponibilidade: Quanto tempo o sistema fica disponível para uso (uptime)
- Escalabilidade: Crescer em capacidade conforme aumenta a demanda sem perda de desempenho
- Confiabilidade: Frequência e impacto de falhas no sistema. Um sistema confiável opera de forma consistente, sem erros frequentes e seguro contra perda de dados ou corrupção
- Continuidade de Negócio: Capacidade de recuperação diante de desastres ou interrupções severas, minimizando downtime
- Robustez: Habilidade de lidar com condiçõs de erro e situaões inesperadas em tempo de execução. Um sistema robusto continua operando mesmo com falha

## Estruturais

- Modularidade & Manutenbilidade: Capacidade de evoluir e dar manutenção no software de forma fácil e segura
- Extensibilidade: Facilidade de adicionar novas funcionalidades ou variar comportamentos existentes sem mudanças drásticas na base de código
- Reutilização de componentes: Partes do sistema podem ser reutilizadas em outros contextos ou produtos
- Portabilidade: Habilidade do sistema operar em diferentes plataformas tecnológicas com pouca ou nenhuma modifição, seja AWS ou AZURE
- Configurabilidade: Facilidade de ajustar o comportamento do software através de configurações externas, sem necessidade de alterar código fonte
- Suportabilidade: Nivel de facilidade para suportar, monitorar e depurar o sistema em produção

## Transversais

- Segurança: Proteger o sistema e os dados contra acessos não autorizados e ataques
- Privacidade: Proteção de dados pessoais e sensíveis, garantindo que apenas quem deva tenha acesso as informações
- Compliance Legal: Adequação de leis e regulamentações externas que impactam o sistema
- Acessibilidade: Pessoas com deficiência conseguirem utilizar o sistema
- Usabilidade: Quão facil e intuitivo é utilizar seus objetivos no sistema
- Arquivamento e Retenção: Necessidade de armazenar, arquivar, ou descartar dados antigos de acordo com regras de negócio ou legais

## Trade-off

- Interdependência: As caracteristicas competem entre si. Melhorar uma, perde outra, lençol curto
- Complexidade Acumulada: Cada caracteristica extra suportada torna o design mais complexo
- Balanço e compromisso: Equilibrio apropriado com as prioridades de negócio 
