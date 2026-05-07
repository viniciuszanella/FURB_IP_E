package Uni6;

import java.util.Scanner;

public class Uni6Exe11 {
    Scanner sc = new Scanner(System.in);
    int vetorA[] = new int[10];
    int vetorB[] = new int[10];
    int vetorC[] = new int[10];
    
    public void lerVetores() {
        System.out.println("Digite 10 números para o vetor A:");
        for (int i = 0; i < 10; i++) {
            System.out.print("Posição " + i + ": ");
            vetorA[i] = sc.nextInt();
        }
        
        System.out.println("Digite 10 números para o vetor B:");
        for (int i = 0; i < 10; i++) {
            System.out.print("Posição " + i + ": ");
            vetorB[i] = sc.nextInt();
        }
    }
    
    public void compararVetores() {
        for (int i = 0; i < 10; i++) {
            if (vetorA[i] == vetorB[i]) {
                vetorC[i] = 1;
            } else {
                vetorC[i] = 0;
            }
        }
    }
    
    public void exibirResultado() {
        System.out.println("\nVetor A: ");
        exibirVetor(vetorA);
        
        System.out.println("Vetor B: ");
        exibirVetor(vetorB);
        
        System.out.println("Vetor C (comparação - 1 se iguais, 0 se diferentes): ");
        exibirVetor(vetorC);
    }
    
    public void exibirVetor(int[] vetor) {
        for (int i = 0; i < vetor.length; i++) {
            System.out.print(vetor[i] + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        Uni6Exe11 programa = new Uni6Exe11();
        programa.lerVetores();
        programa.compararVetores();
        programa.exibirResultado();
    }

}
