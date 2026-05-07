package Uni6;

import java.util.Scanner;

public class Uni6Exe1 {
    Scanner scanner = new Scanner(System.in);
    int vetor[] = new int[10];

    public Uni6Exe1() {
        ler();
        escrever();
    }

    public void ler() {
        for (int i = 0; i <= 9; i++) {
            System.out.println("Insira um número inteiro");
            vetor[i] = scanner.nextInt();
        }
    }

    public void escrever() {
        for (int i = 9; i >= 0; i--) {
            System.out.println(vetor[i]);
        }
    }

    public static void main(String[] args) {
        new Uni6Exe1();
    }
}
