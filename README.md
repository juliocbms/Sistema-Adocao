
# Sistema de Gestão de Adoções (ONG)

Sistema em **Java (CLI)** desenvolvido para auxiliar ONGs e protetores independentes no gerenciamento de animais, adotantes e no controle dos processos de adoção.  
O projeto enfatiza o uso correto dos **pilares da Programação Orientada a Objetos (POO)** e a **persistência de dados em ficheiros CSV**.


## Sobre o Projeto

O sistema permite:

- Cadastro de animais (Cães e Gatos)
- Cadastro de adotantes
- Realização de adoções com regras de negócio validadas
- Relatórios detalhados
- Persistência automática dos dados em arquivos CSV

As interações são realizadas via **terminal (CLI)**, e os menus são carregados dinamicamente a partir de arquivos `.txt`.


## Funcionalidades Principais

### Gestão de Animais (CRUD Completo)
- Cadastrar cães e gatos  
- Listar todos os animais  
- Editar informações  
- Excluir registros  
- **Polimorfismo:**  
  - Sons personalizados por espécie  
  - Vacinação e cuidados específicos

### Gestão de Adotantes (CRUD Completo)
- Cadastrar  
- Listar  
- Editar  
- Excluir  
- Controle automático do número de animais já adotados

### Processo de Adoção
- Vincula um animal disponível a um adotante  
- Valida automaticamente:  
  - Limite máximo de adoções por pessoa (3)  
  - Disponibilidade do animal  
- Atualiza o status para **ADOTADO**

### Relatórios Avançados
- Histórico completo de adoções  
- Filtros por:  
  - Nome do adotante  
  - Intervalo de datas (via Java Streams)

### Persistência de Dados
- Arquivos CSV:
  - `animais.csv`
  - `adotantes.csv`
  - `adocoes.csv`
- Menus em arquivos `.txt` dentro de `src/data`


## Tecnologias e Conceitos de POO

Este projeto utiliza exclusivamente **Java**, com forte foco em arquitetura e POO:

### Encapsulamento
- Uso de atributos privados e getters/setters

### Herança
- `Cachorro` e `Gato` herdam de `Animal`

### Classe Abstrata
- `Animal` define estrutura base e exige implementação de `emitirSom()`

### Interface
- `CuidadosEspeciais` define métodos como `vacinar()` e `vermifugar()`

### Polimorfismo
- Listagem e manipulação genérica de animais  
- Sons e comportamentos distintos conforme a espécie

### Exceptions Personalizadas
- `LimiteAdocoesException`  
- `AnimalIndisponivelException`

### Arquitetura em Camadas (MVC+Service+Repository)

Model → Repository → Service → UI


## Regras de Negócio Implementadas

### Limite de Adoções
Um adotante só pode ter **até 3 animais** simultaneamente.

### Disponibilidade
Somente animais com status **DISPONIVEL** podem ser adotados.  
Após a adoção → status muda automaticamente para **ADOTADO**.


## Estrutura do Projeto
```markdown
src/
└── br/com/projeto/
├── exception/      # Exceções personalizadas
├── main/           # Classe Main (ponto de entrada)
├── model/          # Domínio: Animal, Adotante, Adocao, Enums
├── repository/     # Persistência em CSV e leitura de menus
├── service/        # Regras de negócio e validações
└── service/ui      # Interface de texto (menus e inputs)
```

## Como Executar

### Pré-requisitos
- **Java JDK 21**  
- IDE  (Intellij)

### Passos
1. Clone o repositório ou faça download dos arquivos  
2. Abra o projeto na IDE  
3. Localize `src/br/com/projeto/main/Main.java`  
4. Execute o método `main`  

 *Na primeira execução*, a pasta `src/data` e os arquivos `.csv` serão criados automaticamente.


## Guia de Testes (Sugestão)

### Cadastrar Adotante
Menu Principal → **1 (Pessoas)** → **1 (Cadastrar)**

### Cadastrar Animais
Menu Principal → **2 (Pets)**  
Cadastre um Cachorro e um Gato

### Testar Polimorfismo
Menu Principal → **10 (Administrar Cuidados)**  
Selecione um animal e verifique:
- Som emitido
- Comportamento especial por espécie

### Realizar Adoção
Menu Principal → **4 (Realizar Adoção)**  
Tente realizar uma adoção válida

### Testar Regras de Negócio
- Tentar adotar o mesmo animal novamente (erro)  
- Tentar exceder o limite de 3 adoções (erro)

### Relatórios
Menu Principal → **7 (Adoções)**  
- Filtro por datas  
- Filtro por nome
