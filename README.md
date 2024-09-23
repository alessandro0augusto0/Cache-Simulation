# Simulador de Memória Cache

## Instituto Federal do Sul de Minas Gerais

### Organização e Arquitetura de Computadores

**Trabalho Prático**

**Professor:** Douglas Braz

---

## Descrição do Projeto

Este projeto consiste no desenvolvimento de um simulador de acesso à memória cache em Java, com interface gráfica intuitiva. O simulador implementa três tipos de mapeamento de memória cache:

1. **Mapeamento Direto**
2. **Mapeamento Associativo**
3. **Mapeamento Associativo em Conjuntos**

Os mapeamentos associativo e associativo em conjuntos utilizam algoritmos de substituição, como **Random**, **FIFO**, **LFU**, e **LRU**. No entanto, o mapeamento associativo LFU ainda não foi implementado corretamente. Se alguém puder contribuir corrigindo esse código, toda ajuda será muito bem-vinda!

---

### Funcionalidades do Simulador

- **Configuração de Cache**: Através de um arquivo `config.txt`, o usuário pode definir as especificações da memória principal e da memória cache.
- **Execução de Testes**: O usuário pode realizar testes de acesso à memória utilizando diferentes arquivos de teste (`teste_1.txt`, `teste_2.txt`).
- **Simulação de Mapeamentos**: O simulador permite a escolha entre os três tipos de mapeamento e, quando necessário, a seleção de um algoritmo de substituição para a execução dos testes.

---

### Interface Gráfica

#### Tela Inicial

<div align="center">
    <img src="https://github.com/user-attachments/assets/5fb020ce-4d41-4d26-85ad-4b413fd2e83b" alt="Tela Inicial">
</div>

Clique no botão **Iniciar** para acessar a tela de simulação.

#### Segunda Tela

<div align="center">
    <img src="https://github.com/user-attachments/assets/98942454-2926-4030-a96a-29309823f861" alt="Segunda Tela">
</div>

Nesta tela, o usuário deve selecionar os arquivos de configuração (`config.txt`) e de teste (`memory`), escolher o tipo de mapeamento, selecionar um algoritmo de substituição (se necessário), e clicar em **Run** para executar a simulação.

#### Resultados

- **Exemplo de Mapeamento Direto**:

<div align="center">
    <img src="https://github.com/user-attachments/assets/5636cb77-48f7-4c23-9934-83397f828b0a" alt="Mapeamento Direto">
</div>

Resultado da simulação utilizando o mapeamento direto.

- **Exemplo de Mapeamento Associativo em Conjuntos (Random)**:

<div align="center">
    <img src="https://github.com/user-attachments/assets/f1dee545-60cb-4b25-880f-451b12e09241" alt="Mapeamento Associativo em Conjuntos">
</div>

Resultado da simulação utilizando o mapeamento associativo em conjuntos com o algoritmo de substituição **Random**.

---

## Protótipo: CacheSimulatorLOTR

Antes da versão final do projeto, um protótipo foi desenvolvido para validar a implementação das funcionalidades principais. Este protótipo, chamado **CacheSimulatorLOTR**, segue a mesma lógica de operação do simulador final, mas com algumas diferenças na interface e estrutura.

---

### Funcionalidades do Protótipo

- **Mapeamentos**: Implementa os mesmos três tipos de mapeamento (direto, associativo e associativo em conjunto).
- **Algoritmos de Substituição**: Inclui a implementação dos algoritmos de substituição necessários para os mapeamentos associativo e associativo em conjunto.

---

### Interface Gráfica do Protótipo

#### Tela Inicial

<div align="center">
    <img src="https://github.com/user-attachments/assets/e7b8e183-e48b-4ebb-bd39-5e884488084c" alt="Tela Inicial Protótipo">
</div>

Clique no botão **Iniciar** para acessar a tela de simulação.

#### Segunda Tela

<div align="center">
    <img src="https://github.com/user-attachments/assets/efd5a68b-49ae-4ff7-9d7f-77439f26e044" alt="Segunda Tela Protótipo">
</div>

Tela para seleção de arquivos de configuração e teste, escolha do mapeamento e execução da simulação.

#### Resultados

<div align="center">
    <img src="https://github.com/user-attachments/assets/9b4d97f4-5db7-40c8-9832-d15e561e3966" alt="Resultados Protótipo">
</div>

Exemplo dos resultados gerados após a simulação.

---

## Como Executar o Projeto

1. **Clone o Repositório**:

   ```bash
   git clone https://github.com/alessandro0augusto0/Cache-Simulation.git

   
2. **Importe os Arquivos `.jar`**

   É necessário adicionar os arquivos `.jar` à biblioteca do projeto. Esses arquivos estão disponíveis na pasta `libs` do repositório. Siga as instruções da sua IDE (Eclipse, IntelliJ, etc.) para incluir as bibliotecas externas. Além disso, uma pasta chamada `jar` foi incluída no repositório para facilitar a adição dos arquivos necessários, dependendo da IDE utilizada. Também foi criada uma pasta `Testes` para organizar separadamente os arquivos de teste e de configuração.


3. **Abra o Projeto na IDE de Sua Preferência**

4. **Configuração do Visual Studio Code**

   Se estiver utilizando o **Visual Studio Code**, configure o arquivo `launch.json` corretamente. Aqui está um exemplo:

   ```json
   {
       "type": "java",
       "name": "Main",
       "request": "launch",
       "mainClass": "interfaces.Main",
       "projectName": "CacheSimulation_dc67194b",
       "vmArgs": "--module-path \"C:/java-libs/javafx-sdk/lib\" --add-modules javafx.controls,javafx.fxml,javafx.media"
   }

Ajuste os caminhos conforme necessário para a estrutura do seu projeto.

5. **Compile e Execute o Programa**

6. **Utilize a Interface Gráfica**

   Selecione os arquivos de configuração (`config.txt`, `configteste.txt`) e de teste (`teste_1.txt`, `teste_2.txt`, `avaliacao.txt`) na pasta `testes`.

   Escolha o tipo de mapeamento e, se necessário, o algoritmo de substituição.

   Clique em **Run** para visualizar os resultados.

   ---

   ## Estrutura do Repositório

- **/libs**: Contém os arquivos `.jar` necessários para a execução do projeto.
- **/testes**: Contém os arquivos `.txt` utilizados para os testes no simulador.
- **config.txt**: Arquivo de configuração da memória principal e cache.
- **configteste.txt**: Arquivo de configuração adicional para testes.
- **teste_1.txt**, **teste_2.txt**: Arquivos de teste que simulam o acesso à memória principal.

---

## Considerações Finais

Este projeto foi desenvolvido para atender aos requisitos do **1º Trabalho Prático** do curso de **Organização e Arquitetura de Computadores**. O simulador oferece uma ferramenta visual e interativa para compreender o funcionamento dos diferentes mapeamentos de memória cache e os impactos dos algoritmos de substituição.
