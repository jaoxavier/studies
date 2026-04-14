# Pensamento Arquitetural

## Objetivo

- Arquitetura vs Design
- Amplitude vs Profundidade Técnica
- Análise de trade-offs
- Drivers de Negócio
- Arquiteto "mão na massa"

## O que é o pensamento arquitetural

- Olhar de Arquiteto: Ver o sistema de forma ampla, focando no desenvolvimento em longo prazo, focando em padrões gerais
- Enfoque Sistêmico: Enxergar não somente um código, mas sim um sistema que cumpre requisitos técnicos e negociais.
- Postura colaborativa: Fazer parte do time de desenvolvimento, comuncando decisões e ouvindo feedback

## Arquitetura vs Design

- Architect: characteristics, style and component structure
- Developer: class desgin, user interface and source code
- Mundo colaborativo moderno: arquiteto e dev trabalham em um mesmo ciclo, trocando informações continuamente
- Arquiteto como lider técnico e mentor, arquitetura e design evoluem juntos

## Amplitude vs Profundidade de conhecimento técnico

- Perfil do desenvolvedor: Precisa ter um conhecimento profundo em linguagem, frameworks e ferramentas para realizar um desenvolvimento no curto prazo com um conhecimento sólido
- Perfil do arquiteto: Precisa ter um conhecimento amplo de diversas técnologias do mercado, padrões de projetos diferentes, estilos aruiteturais, entre outros, mas não necessariamente de forma especifica, e sim sabendo suas vantagens e desvantagens 
- Nada é estático, precisa de um estudo constante, desenvolvimento tanto em conteudo especifico, como sua linguagem, como em tecnologias novas no mercado, pelo menos saber que existe e alguns detalhes do mesmo

## Análise de trade-offs

- Saber medir entre benefícios e custos, identificar essas trocar e avaliar qual opção equilibra melhor com a prioridade do projeto
- O famoso "depende" serve para reconhecer que a melhor decisão depende do contexto: requisitos, ambiente, prazos, equipe, restrições de negócio
- Precisa de experiência e julgamento para tomar decisões

## Business drivers

- Objetivos e necessidades de negócio que o software deve atender ou suportar
- Metas para atender a N usuários, cumprir certos regulamentos
- Permitir expansão para novos mercados, reduzir custos de infra, prazo de lançamento, etc
- Convertê-los em requisitos aruiteturais concretos
- Exemplos de ilities:
    - Desempenho: resposta rápida, baixa latência, chamadas locais, cache em memória
    - Disponibilidade: para 24/7 redundância, failover, deploy sem downtime
    - Segurança: requisitos de criptografia, controle de acesso rigoroso, auditoria
    - Escalabilidade: Arq. em microsserviços, ou baseada em auto-escalável pode ser o foco

## Arquiteto Mão na Massa

- Fundamental que o arquiteto mantenha envolvimento com o código, saber o que está vendo e poder avaliar se está bom
- Não ser responsável para uma parte essencial do sistema sozinho, mas estar envolvido
- PoCs - Prova de Conceito - Medir desempenho em pequenos protótipos, ver quao complexo é implementar
- Tarefas Técnicas e Correções:Refatorar, corrigir bugs mais complicados