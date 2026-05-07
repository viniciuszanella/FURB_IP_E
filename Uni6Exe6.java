package Uni6;

import java.util.Scanner;

public class Uni6Exe6 {
    Scanner scanner = new Scanner(System.in);
    int vetor[];
    int n;

    public Uni6Exe6() {
        ler();
        escrever();
    }

    public void ler() {
        System.out.print("Informe o tamanho do vetor: ");
        n = scanner.nextInt();
        vetor = new int[n];

        for (int i = 0; i < vetor.length; i++) {
            System.out.print("Informe o valor da posição " + i + " : ");
            vetor[i] = scanner.nextInt();
        }
    }

    public void escrever() {
        System.out.print("Informe o número que deseja encontrar no vetor: ");
        int aEncontrar = scanner.nextInt();
        boolean valorEncontrado = false;

        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == aEncontrar) {
                valorEncontrado = true;
            }
        }

        if (!valorEncontrado) {
            System.out.println("O valor não foi encontrado dentro do vetor");
        } else {
            System.out.println("O valor está no vetor");
        }

        scanner.close();
    }

    public static void main(String[] args) {
        new Uni6Exe6();
    }
}