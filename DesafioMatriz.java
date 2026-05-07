package Uni6;

import java.util.Random;

public class DesafioMatriz {
    Random random = new Random();
    int matriz[][] = new int[50][50];

    public DesafioMatriz() {
        populaMatriz();
        calcularLinhas();
        calcularColunas();
        calcularDiagonalPrincipal();
        calcularDiagonalSecundaria();
        calcularMaiorValor();
        imprimirMatriz();
    }

    public void populaMatriz() {
        for (int linha = 0; linha < matriz.length; linha++) {
            for (int coluna = 0; coluna < matriz[linha].length; coluna++) {
                matriz[linha][coluna] = random.nextInt(-500, 500);
            }
        }
    };

    public void calcularLinhas() {
        System.out.println("Total por linhas:");
        for (int linha = 0; linha < matriz.length; linha++) {
            int total = 0;
            for (int coluna = 0; coluna < matriz[linha].length; coluna++) {
                total += matriz[linha][coluna];
            }
            System.out.println("Total da linha " + linha + " = " + total);
        }
    };

    public void calcularColunas() {
        System.out.println("Total por colunas:");
        for (int coluna = 0; coluna < matriz.length; coluna++) {
            int total = 0;
            for (int linha = 0; linha < matriz.length; linha++) {
                total += matriz[linha][coluna];
            }
            System.out.println("Total da coluna " + coluna + " = " + total);
        }
    };

    public void calcularDiagonalPrincipal() {
        int total = 0;
        for (int linha = 0; linha < matriz.length; linha++) {
            int coluna = linha;
            total += matriz[linha][coluna];
        }
        System.out.println("Total da diagonal principal = " + total);
    };

    public void calcularDiagonalSecundaria() {
        int total = 0;
        for (int linha = 0; linha < matriz.length; linha++) {
            int coluna = (matriz.length - 1) - linha;
            total += matriz[linha][coluna];
        }
        System.out.println("Total da diagonal secundaria = " + total);
    };

    public void calcularMaiorValor() {
        int maiorValor = 0;
        for (int linha = 0; linha < matriz.length; linha++) {
            for (int coluna = 0; coluna < matriz[linha].length; coluna++) {
                if (matriz[linha][coluna] > maiorValor)
                    maiorValor = matriz[linha][coluna];
            }
        }
        System.out.println("Maior valor da Matriz é = " + maiorValor);

    };

    public void imprimirMatriz() {
        for (int linha = 0; linha < matriz.length; linha++) {
            for (int coluna = 0; coluna < matriz[linha].length; coluna++) {
                System.out.print(matriz[linha][coluna] + "\t");
            }
            System.out.println("");
        }
    };

    public static void main(String[] args) {
        new DesafioMatriz();
    }
}
