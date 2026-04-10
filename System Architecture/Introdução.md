# Arquitetura de Software

- "A arquitetura é sobre as coisas importantes... seja lá o que for isso" - Raplh Johnson
- Não tem uma definição unânime
- Escoplo amplo e em expansão: O papel do arquiteto evoluiu além de aspectos puramentes técnicos (componentes, padrões)
- Novos estilos e paradigmas (como microserviços) ampliaram responsabilidades para incluir preocupações operacionais, de negócio e soft skils
- Arquitetura moderna é dinâmica e evolutiva, não algo estático
- Devem ser adaptadas ao contexto atual

## Definição

- Estrutura de sistema: Organização dos componentes e módulos conforme um ou mais estilos arquiteturais adotados
- Caracteristicas arquiteturais: Atributos de qualidades que o sistema deve atender. Desempenhos, escalabilidade, segurança, disponibilidade, manutenibilidade, etc. Foje das regras de negócio do sistema.
- Decisões arquiteturais: Escolhas e regras de alto nível feitas pelo arquiteto para guiar a construção do sistema. Definem padrões, restrições e diretrizes sobre o que é ou não permitido na solução
- Princípios de design: Diretrizes gerais de projeto que orientam as decisões de desgin de baixo nível. Representam boas práticas recomendadas para manter consistência e qualidade na implementação

## Estrutura - Estilo Arquitetural

- Padrão macro de organização do sistema
- Ex. Em camada (layered), cliente-servidor, microserviços, orientadas a eventos, microkernel, etc
- Descrição parcial: Indica somente o estilo usado, ou seja, não descreve a arquitetura completa. É preciso combinar estrutura + decisões, características e princípios para entender o sistema por inteiro

## Caracteristicas Arquiteturais

- Qualidades: Também chamadas de requisitos não funcionais, ou seja, Desempenho, escalabilidade, segurança, disponibilidade, manutenibilidade
- Critérios de sucesso: Caracteristicas definem quão bem o sistema atende as expectativas de qualidade. São ortogonais as funcionalidades, ou seja, não dependenm do que o sistema faz, mas quão bem ele faz

## Decisões arquiteturais 

- Regras e restrições: Definem regras que direcionam a implementação. São escolhas tomadas para impor padrões e limites, guiam as equipes sobre o que é permitido ou preferido na construção do sistema
- Exemplo (camadas): Pode-se decidir que somente as camadas de negócio/serviços acessam diretamente o banco de dados. A camada de apresentação fica proibida de acessá-lo, devendo passar pela camada abaixo. Isso garante isolamente entre UI e dados. facilitando manutenções e evitando violações de consistência
- Exceções controladas: Decisões arquiteturais devem ser documentadas e comunicadas. Se uma regra precisa ser quebradas em determinado caso, o arquiteto pode conceder uma exceção, tipicamente através de um processo formal de revisão arquitetural, para avaliação de impacto antes de aprovar a quebra de regra

## Principios de Design

- Boas práticas: São orientações gerais para o desenho de componentes e códigos em vez de regras rigidas. 
- Ex. Baixo acoplamento e alta coesão entre classes, preferir composição em vez de herança, programar voltado a interfaces, aplicar padrões de projeto onde apropiado
- Ex. Em microsserviços, adotar como principio preferir comunicação assincrona entre serviços para melhor desempenho e escalabilidade, contudo, os desenvolvedores tem liberdade para usar chamadas REST/gRPC sincronas se um caso especifico exigir
- O principio orienta a escolha preferencial, mas não proibe alternativas quando justificadas

## O papel do arquiteto

- Varia conforme contexto, varia de um tech lead até alguém definindo estratégia de tecnológia de toda empresa
- Foco em expectativas: Pensar nas responsabilidades e expectativas centrais colocadas sobre um arquiteto. Certas competências são esperadas de arquitetos em geral, indepoendente do titulo ou descrião de cargo

## Expectativas do arquiteto

- Definir deciões de arquitetura
- Analisar a arquitetura continuamente
- Manter-se atualizado
- Garantir conformidades
- Experiência diversificada
- Conhecimento do domínio de negócio
- Habilidade interpessoais
- Navegação política

## Arquitetura e práticas de engenharia

- Processo vs Prática: Antigamente, arquitetura era vista como independente do processo de desenvolvimento
- No entanto, práticas modernas de engenharia influenciam diretamente a viabilidade de certas arquiteturas
- Evolução nas práticas: Extreme Porgramming focaram em práticas de qualidade independentemente do processo gerencial. Isso evoluiu para Continuous Delivery e posteriormente para a cultura DevOps que integra desenvolvimente e operações

## Arquitetura e Operações (DevOps)

- Desenvolvimento e operações trabalham em conjunto. Arquiteturas modernas como microsserviços transferem muitas preocupações (escalabilidade elástica, tolerência a falhas, monitoramento) para a infraestrutura automatizada (nuvem, orquestração de contêiners). Isso simplifica o design das aplicações, mas exige colaboração: arquitwetos entendem das capacidades de Ops/Cloud e profissionais de Ops usam práticas de software

## Arquitetura e Dados

- Decisões de dados: Escolhas como usar um bando de dados relacional ou NoSQL, chema centralizado ou distribuido, cache, estratégias de replicação
- O arquiteto precisa determinar como os dados serão armazenados, acessados e integrados entre componentes
- Volume, variedade e veracidade: Com o crescimento do volume de dados e diversidade de fontes, devem considerar tecnologias especilizadas
- Consistência vs disponivilidade 
- Governança de dados tornam-se partes das decisões arquiteturais
