package Uni6;

import java.util.Scanner;

public class Uni6Exe2 {
    Scanner scanner = new Scanner(System.in);
    double vetor[] = new double[12];
    double media = 0;

    public Uni6Exe2() {
        ler();
        calcular();
        escrever();
    }

    public void ler() {
        for (int i = 0; i <= 11; i++) {
            System.out.println("Insira um número real");
            vetor[i] = scanner.nextInt();
        }
    }

    public void calcular() {
        double total = 0;
        for (int i = 0; i <= 11; i++) {
            total += vetor[i];
        }
        media = total / 12;
    }

    public void escrever() {
        for (int i = 0; i <= 11; i++) {
            if (vetor[i] > media)
                System.out.println(vetor[i]);
        }
    }

    public static void main(String[] args) {
        new Uni6Exe2();
    }
}
