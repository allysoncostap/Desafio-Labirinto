
package com.atos;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // LE O ARQUIVO
        String filePath = JOptionPane.showInputDialog("Informe o caminho completo do arquivo de entrada do labirinto:");

        if (filePath == null || filePath.trim().equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Caminho do arquivo deve ser informado",
                    "Alerta",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        File f = new File(filePath);
        if (!f.exists() || f.isDirectory()) {
            JOptionPane.showMessageDialog(null,
                    "Caminho do arquivo informado é inválido",
                    "Alerta",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<String> lines = new ArrayList<>();
        try {
            FileInputStream fstream = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine;
            while ((strLine = br.readLine()) != null)
                lines.add(strLine);

            fstream.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Não foi possível ler o arquivo de entrada",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] dimensoes = lines.get(0).split(" ");
        int linhas = Integer.parseInt(dimensoes[0]);
        int colunas = Integer.parseInt(dimensoes[1]);

        // Preenche matriz do labirinto
        String[][] matriz = new String[linhas][colunas];
        int linhaAtual = -1; // Posição inicial: linha
        int colunaAtual = -1; // Posição inicial: coluna
        int linhaSaida = -1; // Saída: linha
        int colunaSaida = -1; // Saída: coluna

        // percorre toda a matriz (a partir da segunda linha do arquivo texto) para identificar a posição inicial e a saída
        for (int l = 1; l < lines.size(); l++) {
            String[] line = lines.get(l).split(" ");
            for (int c = 0; c < line.length; c++) {
                String ll = line[c];
                matriz[l - 1][c] = ll;

                if (ll.equals("X")) {
                    // Posição inicial
                    linhaAtual = l - 1;
                    colunaAtual = c;
                } else if (ll.equals("0") && (l == 1 || c == 0 || l == lines.size() - 1 || c == line.length - 1)) {
                    // Saída
                    linhaSaida = l - 1;
                    colunaSaida = c;
                }
            }
        }

        // Posição máxima de linha e coluna que pode ser movida (borda)
        int extremidadeLinha = linhas - 1;
        int extremidadeColuna = colunas - 1;

        // Guarda o trajeto em uma list de string e já inicia com a posição de origem
        List<Integer[]> trajetoLinha = new ArrayList<>();
Stack<Integer[]>jogadas= new Stack<>();

        List<String> resultado = new ArrayList<>();
        resultado.add("O [" + (linhaAtual + 1) + ", " + (colunaAtual + 1) + "]");

        // Percorre a matriz (labirinto) até encontrar a saída, usando as regras de prioridade e posições não visitadas, e vai armazenando o trajeto na list resultado
        boolean achouSaida = linhaAtual == linhaSaida && colunaAtual == colunaSaida;

        while (!achouSaida) {

            Integer[] subir = new Integer[]{linhaAtual - 1, colunaAtual};
            Integer[] esqueda = new Integer[]{linhaAtual, colunaAtual - 1};
            Integer[] direita = new Integer[]{linhaAtual, colunaAtual + 1};
            Integer[] baixo = new Integer[]{linhaAtual + 1, colunaAtual};

            //subir
            if (linhaAtual > 0 && matriz[linhaAtual - 1][colunaAtual].equals("0") && noContain(trajetoLinha, subir)) {

                linhaAtual--;
                trajetoLinha.add(new Integer[]{linhaAtual, colunaAtual});
                jogadas.add(new Integer[]{linhaAtual, colunaAtual});
                resultado.add("C [" + (linhaAtual + 1) + ", " + (colunaAtual + 1) + "]");
            }
            //esqueda
            else if (colunaAtual > 0 && matriz[linhaAtual][colunaAtual - 1].equals("0") && noContain(trajetoLinha, esqueda)) {

                colunaAtual--;
                trajetoLinha.add(new Integer[]{linhaAtual, colunaAtual});
                jogadas.add(new Integer[]{linhaAtual, colunaAtual});
                resultado.add("E [" + (linhaAtual + 1) + ", " + (colunaAtual + 1) + "]");
                //direita
            } else if (colunaAtual < extremidadeColuna && matriz[linhaAtual][colunaAtual + 1].equals("0") && noContain(trajetoLinha, direita)) {

                colunaAtual++;
                trajetoLinha.add(new Integer[]{linhaAtual, colunaAtual});
                jogadas.add(new Integer[]{linhaAtual, colunaAtual});
                resultado.add("D [" + (linhaAtual + 1) + ", " + (colunaAtual + 1) + "]");
                //baixo
            } else if (linhaAtual < extremidadeLinha && matriz[(linhaAtual + 1)][colunaAtual].equals("0") && noContain(trajetoLinha, baixo)) {

                linhaAtual++;
                trajetoLinha.add(new Integer[]{linhaAtual, colunaAtual});
                jogadas.add(new Integer[]{linhaAtual, colunaAtual});
                resultado.add("B [" + (linhaAtual + 1) + ", " + (colunaAtual + 1) + "]");
            }else  {
int linhaAnterior = linhaAtual;
 int colunaAnterior= colunaAtual;

                Integer[]jogadaCont = jogadas.pop();
                linhaAtual= jogadaCont[0];
                colunaAtual= jogadaCont[1];

                trajetoLinha.add(new Integer[]{linhaAtual, colunaAtual});
if(linhaAnterior>linhaAtual && colunaAnterior==colunaAtual ){
    resultado.add("C [" + (linhaAtual + 1) + ", " + (colunaAtual + 1) + "]");
}else if(linhaAnterior<linhaAtual && colunaAnterior==colunaAtual){
    resultado.add("B [" + (linhaAtual + 1) + ", " + (colunaAtual + 1) + "]");
                }
else if(linhaAnterior==linhaAtual && colunaAnterior<colunaAtual){
    resultado.add("D [" + (linhaAtual + 1) + ", " + (colunaAtual + 1) + "]");
}else if(linhaAnterior==linhaAtual && colunaAnterior<colunaAtual){
    resultado.add("E [" + (linhaAtual + 1) + ", " + (colunaAtual + 1) + "]");
}


            }

                if (linhaAtual + 1 == 0 || linhaAtual == extremidadeLinha || colunaAtual + 1 == 0 || colunaAtual == extremidadeColuna) {
                    achouSaida = true;
                }

        }

        // Escreve no arquivo texto a saída
        String folderPath = f.getParent();
        String fileName = f.getName();
        String outputPath = folderPath + "\\saida-" + fileName;

        try {
            File fout = new File(outputPath);
            FileOutputStream fos = new FileOutputStream(fout);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            for (int i = 0; i < resultado.size(); i++) {
                bw.write(resultado.get(i));
                bw.newLine();
            }

            bw.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Não foi possível escreve o arquivo de saída",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    }


    public static boolean compararMatrizes1(Integer[] matriz1, Integer[]matriz2) {
        if ( matriz1[0] != matriz2[0]){
            return false;

        }else {
            return true;

    }}

        public static boolean noContain (List < Integer[]>listaDeJodas, Integer[]ex){
            for (Integer[] item : listaDeJodas) {
                if (Arrays.equals(item, ex)) {
                    return false;
                }
            }
            return true;
        }

    }
