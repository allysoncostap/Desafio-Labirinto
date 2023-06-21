## Desafio-Labirinto

#Desafio do labirinto
Será dada uma entrada em arquivo texto, onde na primeira linha contém as dimensões do labirinto 
(Linhas Colunas) e nas demais linhas o labirinto em si, em que:
  - 1 indica uma parede (isto é, não pode seguir neste ponto da matriz)
  - 0 indica um caminho possível de se trafegar
  - X é o ponto de partida (não necessariamente é um canto do mapa)

O objetivo é encontrar a única saída, sem "andar pelas paredes" e seguindo a seguinte ordem de 
prioridade (quando puder se deslocar):
  1) Ir para cima (C)
  2) Ir para a esquerda (E)
  3) Ir para a direita (D)
  4) Ir para baixo (B)
Caso se alcance um ponto em que não é possível se movimentar e/ou não tenham mais posições 
que ainda não foram visitadas, deve-se retornar usando o mesmo caminho utilizado até este ponto 
“sem-saída” até o último ponto onde teve mais de uma posição possível de movimento. A ordem 
de movimento só é utilizada quando há mais de uma posição possível de movimento para posições 
ainda não visitadas.<br />

O desafio é elaborar um código-fonte dentro do projeto Template dentro da função 
CodigoAtividade() (se a prova for feita em C#) ou dentro da própria função main (se a prova for 
feita em Java) que seja capaz de:<br />
  1) ler o arquivo texto de entrada<br />
  2) identificar a dimensão da matriz do labirinto, em que o primeiro número indica o número de 
  linhas e o segundo número indica o número de colunas (é separado por espaço)<br />
  3) identificar a posição de origem (ponto O localizado dentro da matriz). A posição “aumenta 
  de valor” lendo de cima para baixo e/ou da esquerda para a direita. A posição na 
  extremidade superior esquerda é a [1, 1] (linha 1 coluna 1) e a posição na extremidade 
  inferior direita é a que representa o número de linhas e o número de colunas [L, C] (exemplo, 
  se tem 4 linhas e 5 colunas, esta extremidade em questão é a [4, 5])<br />
  4) a partir do ponto de origem se deslocar (seguindo a ordem de prioridade de deslocamento) 
  e encontrar a única saída (que se encontra no ponto 0 localizado em uma extremidade da 
  matriz)<br />
  5) ao encontrar a saída gerar um arquivo texto de saída (na mesma pasta onde está o arquivo 
  de entrada, só que com outro nome de arquivo. ex: entrada.txt é arquivo de entrada então 
  o arquivo de saída pode ser saída-entrada.txt) contendo cada passo do trajeto, onde cada 
  linha indica a direção e posição destinada. A primeira linha do arquivo de saída deve estar 
  com O (origem) seguido da posição inicial<br />
##Exemplo 1:<br />
#Conteúdo do arquivo de entrada<br />
5 8<br/>
1 1 1 1 1 1 1 1<br/>
1 1 0 1 0 1 1 1<br />
1 1 0 0 0 1 1 1<br />
X 0 0 1 0 0 0 0<br />
1 1 1 1 1 1 1 1<br />
#Conteúdo correto do arquivo saída<br />
O [4, 1]<br />
D [4, 2]<br />
D [4, 3]<br />
C [3, 3]<br />
C [2, 3]<br />
B [3, 3]<br />
D [3, 4]<br />
D [3, 5]<br />
C [2, 5]<br />
B [3, 5]<br />
B [4, 5]<br />
D [4, 6]<br />
D [4, 7]<br />
D [4, 8]<br />
##Exemplo 2:<br />
#Conteúdo do arquivo de entrada<br />
8 8<br />
1 1 1 1 1 1 1 1<br />
1 0 0 1 0 0 0 1<br />
1 1 0 0 0 1 1 1<br />
1 0 1 X 0 1 0 1<br />
1 0 0 0 0 0 0 1<br />
1 1 1 1 1 1 0 1<br />
1 0 0 0 0 0 0 0<br />
1 1 1 1 1 1 1 1<br />

Conteúdo correto do arquivo saída<br />
O [4, 4]<br />
C [3, 4]<br />
E [3, 3]<br />
C [2, 3]<br />
E [2, 2]<br />
D [2, 3]<br />
B [3, 3]<br />
D [3, 4]<br />
D [3, 5]<br />
C [2, 5]<br />
D [2, 6]<br />
D [2, 7]<br />
E [2, 6]<br />
E [2, 5]<br />
B [3, 5]<br />
B [4, 5]<br />
B [5, 5]<br />
E [5, 4]<br />
E [5, 3]<br />
E [5, 2]<br />
C [4, 2]<br />
B [5, 2]<br />
D [5, 3]<br />
D [5, 4]<br />
D [5, 5]<br />
D [5, 6]<br />
D [5, 7]<br />
C [4, 7]<br />
B [5, 7]<br />
B [6, 7]<br />
B [7, 7]<br />
E [7, 6]<br />
E [7, 5]<br />
E [7, 4]<br />
E [7, 3]<br />
E [7, 2]<br />
D [7, 3]<br />
D [7, 4]<br />
D [7, 5]<br />
D [7, 6]<br />
D [7, 7]<br />
D [7, 8]<br />

##Exemplo 3:<br />
Conteúdo do arquivo de entrada<br />
5 8<br />
1 1 1 1 1 1 1 1<br />
1 1 0 0 0 1 1 1<br />
1 1 0 0 0 1 1 1<br />
X 0 0 0 0 0 0 1<br />
1 1 0 1 1 1 1 1<br />
Conteúdo correto do arquivo saída<br />
O [4, 1]<br />
D [4, 2]<br />
D [4, 3]<br />
C [3, 3]<br />
C [2, 3]<br />
D [2, 4]<br />
D [2, 5]<br />
B [3, 5]<br />
E [3, 4]<br />
B [4, 4]<br />
D [4, 5]<br />
D [4, 6]<br />
D [4, 7]<br />
E [4, 6]<br />
E [4, 5]<br />
E [4, 4]<br />
C [3, 4]<br />
D [3, 5]<br />
C [2, 5]<br />
E [2, 4]<br />
E [2, 3]<br />
B [3, 3]<br />
B [4, 3]<br />
B [5, 3]<br />

