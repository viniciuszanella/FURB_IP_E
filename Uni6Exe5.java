package Uni6;

import java.util.Scanner;

public class Uni6Exe5 {
    Scanner scanner = new Scanner(System.in);
    String perguntas[] = {
            "Gosta de música sertaneja?",
            "Gosta de futebol?",
            "Gosta de seriados?",
            "Gosta de redes sociais?",
            "Gosta da Oktoberfest?"
    };
    String vetorRapaz[] = new String[5];
    String vetorMoca[] = new String[5];
    double afinidade = 0;

    public Uni6Exe5() {
        ler();
        calcular();
        escrever();
    }

    public void ler() {
        System.out.println("Rapaz, responda:");
        for (int i = 0; i <= 4; i++) {
            System.out.println(perguntas[i]);
            vetorRapaz[i] = scanner.nextLine().toUpperCase();
        }
        System.out.println("Moca, responda:");
        for (int i = 0; i <= 4; i++) {
            System.out.println(perguntas[i]);
            vetorRapaz[i] = scanner.nextLine().toUpperCase();
        }
    }

    public void calcular() {
        for (int i = 0; i <= 4; i++) {
            if (vetorRapaz[i] == vetorMoca[i]) {
                afinidade += 3;
                continue;
            }
            if (vetorRapaz[i] == "IND" || vetorMoca[i] == "IND") {
                afinidade += 1;
                continue;
            }
            if (vetorRapaz[i] != vetorMoca[i]) {
                afinidade -= 2;
                continue;
            }
        }
    }

    public void escrever() {
        if (afinidade >= 15)
            System.out.println("Casem!");
        if (afinidade >= 10 && afinidade <= 14)
            System.out.println("Casem!");
        if (afinidade >= 5 && afinidade <= 9)
            System.out.println("Casem!");
        if (afinidade >= 0 && afinidade <= 4)
            System.out.println("Casem!");
        if (afinidade >= -1 && afinidade <= -9)
            System.out.println("Casem!");
        if (afinidade <= -10)
            System.out.println("Casem!");

    }

    public static void main(String[] args) {
        new Uni6Exe5();
    }
}
