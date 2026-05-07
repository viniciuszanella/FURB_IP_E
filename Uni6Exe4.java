package Uni6;

import java.util.Scanner;

public class Uni6Exe4 {
    Scanner scanner = new Scanner(System.in);
    double vetor1[] = new double[10];
    double vetor2[] = new double[10];
    double vetor3[] = new double[10];

    public Uni6Exe4() {
        ler();
        ajustar();
        escrever();
    }

    public void ler() {
        for (int i = 0; i <= 9; i++) {
            System.out.println("Insira um número real");
            vetor1[i] = scanner.nextInt();
        }
        for (int i = 0; i <= 9; i++) {
            System.out.println("Insira um número real");
            vetor2[i] = scanner.nextInt();
        }
    }

    public void ajustar() {
        for (int i = 0; i <= 9; i++) {
            vetor3[i] = vetor1[i] + vetor2[i];
        }
    }

    public void escrever() {
        for (int i = 0; i <= 9; i++) {
            System.out.println(vetor3[i]);
        }
    }

    public static void main(String[] args) {
        new Uni6Exe4();
    }
}
