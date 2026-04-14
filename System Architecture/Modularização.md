# Modularização

- Unidade independente que faz parte de um sistema maior
- Agrupamento lógico de código relacionado
- Modularidade: Princípio de organização arquitetural em que partes relacionadas são agrupadas em módulos coesos e interfaces bem definidas
- Favorece reutilizazção de código

## Palavras chaves

- Coesão: Quão bem os componentes de um módulo estão relacionados
- Acoplamento: Mede o grau de dependência entre diferentes módulos, ou seja, quão forte são os vinculos. Modulos com baixo acoplamento são desejavéis
- Connascence: QUalifica as dependências entre módulos. 

## Coesão

- O grau em que os elementos dentro de um módulo estão relacionados e focados em única responsabilidade ou funcionalidade
- Agrupa tudo que é essencial para desempenhar sua função especifica 
- Alta coesão é desejavél: Todas as partes pertencem aquela unidade de software, facilitando a compreensão e reutilizando
- Um módulo tende a ser autocontido: remover ou separar suas partes quebraria sua função principal

## Tipos de coesão

- Coesão funcional: Todos os elementos do módulo contibuem diretamente para uma única função bem definida
- Coesão sequencial: Ocorre quando a saída de um módulo é a entrada de outro
- Coesão comunicacional: Trabalham em cima dos mesmos dados ou contribuem juntos para uma mesma saída, mesmo com tarefas diferentes
- Coesão procedural: As partes do módulo devem ser executadas em ordem específica para funcionar corretamente
- Coesão temporal: Estão agrupadas porque ocorrem próximos no tempo, por exigência de inicialização ou agendamento
- Coesão lógica: Partes dos módulos estão agrupadas mais por categoria ou semelhança lógica.
- Coesão coincidentes: é a pior forma, elementos de módulos não possuem relação significativa
- Métricas de coesão - LCOM - Lack of Cohesion of Methods

## Acoplamento

- Grau de dependência entre módulos de um sistema. Se alterações em um módulo afetam fortemente outro, eles estão fortemente acoplados
- Acoplamento Aferente (Ca): Métrica que conta quantos componentes externos dependem de um dado módulo
- Acoplamento Eferente (Ce): Conta quantos componentes externos aquele módulo utiliza
- Baixo vs Alto acoplamento: Baixo acoplamento signficia Ca e Ce pequenos, o módulo não depende demais de outros.

## Métricas de acoplamento

- Abstractness  (A) - grau de abstração: Razão entre elementos abstratos (interfaces, classes abstratas) e elementos concretos em um módulo. A varia de 0 (nenhuma) a 1 (somente abstrações)
- Instability (I): Definida como a razão entre dependências de saída e o total de dependências (saída + entrada) do módulo formalmente I = Ce / (Ce + Ca)
- I varia de 0 (ninguem depende de outros, só dependem dele) até 1 (depende de muitos outros e ninguém depende dele)


## Connascence

- "Dois componentes são connascentes se uma mudança em uma requer uma mudança correspondente na outra para manter a correção do sistema"

## Tipos de Connascence

- Connascence estático: visível no código fonte
    - Connascence of Name: múltiplos componentes dependem de um mesmo nome, um método publico usado por diversos módulos
    - Connascence of Type: Dependência de tipos de dados. Ocorre quando funções/módulos devem concordar sobre o tipo de uma entidade trocada entre eles
    - Connascence of Meaning/Convention: Dependência de significados ou convenção de valores. Ex. Status 0, 1, 2
- Connascence dinâmico: manifesta-se em tempo de execução
    - Connascence of Execution: Ordem de execução
    - Connascence of Timing: Momento ou temporazição de execução importa. Duas threads acessando um recurso
    - Connascence of Values: Múltiplois componentes dependem de valores correlacionados que devem mudar em conjunto
    - Connascence of Identity: Diversas partes devem referenciar a mesma entidade ou recurso

## Propriedades da Connascence

- Força: Quão dificil é alterar ou refatorar uma forma de connascence
- Localidade: Refere-se à proximidade dos módulos connascentes
- Grau: Indica quantos componentes estão envolvidos na mesma connascence

## Reduzindo Connascence

1. Minimize a connascence global, dividindo o sistema em partes encapsuladas onde for possível
2. Minimize connascence que atravessa fronteiras do encapsulamento. Se duas funcionalidades estão fortemente dependentes, considere colocar no mesmo módulo
3. Maxime a connascence dentro de cada encapsulamento, ou seja, apenas intramódulo