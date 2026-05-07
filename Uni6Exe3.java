package Uni6;

import java.util.Scanner;

public class Uni6Exe3 {
    Scanner scanner = new Scanner(System.in);
    double vetor[] = new double[12];

    public Uni6Exe3() {
        ler();
        ajustar();
        escrever();
    }

    public void ler() {
        for (int i = 0; i <= 11; i++) {
            System.out.println("Insira um número real");
            vetor[i] = scanner.nextInt();
        }
    }

    public void ajustar() {
        for (int i = 0; i <= 11; i++) {
            if (vetor[i] % 2 == 0)
                vetor[i] = vetor[i] * 0.5;
            if (vetor[i] % 2 != 0)
                vetor[i] = vetor[i] * 0.2;
        }
    }

    public void escrever() {
        for (int i = 0; i <= 11; i++) {
            System.out.println(vetor[i]);
        }
    }

    public static void main(String[] args) {
        new Uni6Exe3();
    }
}
