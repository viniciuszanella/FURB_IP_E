package Uni6;

import java.util.Scanner;

public class Uni6Exe07 {
    Scanner scanner = new Scanner(System.in);
    int vetor[];
    int n;

    public Uni6Exe07() {
        ler();
        ordenarVetor();
        exibirVetor();
    }

    public void ler() {
        construirVetor();

        int valor;
        for (int i = 0; i < vetor.length; i++) {
            System.out.print("Informe o valor da posição " + i + ": ");
            valor = scanner.nextInt();

            while (existeNoVetor(valor)) {
                System.out.print("Número já existe no vetor, informe outro valor para a posição " + i + ": ");
                valor = scanner.nextInt();
            }
            vetor[i] = valor;
        }
    }

    public void construirVetor() {
        System.out.print("Informe o tamanho do vetor: ");
        n = scanner.nextInt();

        if (n > 20)
            n = 20;

        vetor = new int[n];
    }

    public boolean existeNoVetor(int valor) {
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == valor) {
                return true;
            }
        }
        return false;
    }

    public void ordenarVetor() {
        int temp;
        for (int i = 0; i < vetor.length - 1; i++) {
            for (int j = 0; j < vetor.length - 1 - i; j++) {
                if (vetor[j] > vetor[j + 1]) {
                    temp = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temp;
                }
            }
        }
    }

    public void exibirVetor() {
        System.out.print("Vetor ordenado: ");
        for (int i = 0; i < vetor.length; i++) {
            System.out.print("[" + vetor[i] + "]");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        new Uni6Exe07();
    }
}
