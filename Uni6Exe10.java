package Uni6;

import java.util.Scanner;

public class Uni6Exe10 {
    private Uni6Exe10() {
        Scanner scanner = new Scanner(System.in);
        int vet[] = new int[50];
        int posFim = 0;
        System.out.println("\033[H\033[2J");
        int opcao;
        do {
            System.out.println("_______________MENU_______________");
            System.out.println("1 - Incluir valor.");
            System.out.println("2 - Pesquisar valor.");
            System.out.println("3 - Alterar valor.");
            System.out.println("4 - Excluir valor.");
            System.out.println("5 - Mostrar valors.");
            System.out.println("6 - Ordenar valors.");
            System.out.println("7 - Inverter valors.");
            System.out.println("8 - Sair do sistema.");
            System.out.print("Opção:");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    posFim = incluir(vet, posFim, scanner);
                    break;
                case 2:
                    pesquisar(vet, posFim, scanner);
                    break;
                case 3:
                    alterar(vet, posFim, scanner);
                    break;
                case 4:
                    posFim = excluir(vet, posFim, scanner);
                    break;
                case 5:
                    mostrar(vet, posFim, scanner);
                    break;
                case 6:
                    ordenar(vet, posFim, scanner);
                    break;
                case 7:
                    inverter(vet, posFim, scanner);
                    break;
                case 8:
                    System.out.println("______________FIM_______________");
                    break;
                default:
                    System.out.println(" .. opção inválida .. ");
                    break;
            }
        } while (opcao != 8);
        scanner.close();
    }

    private int excluir(int[] vet, int posFim, Scanner scanner) {
        int valorId = pesquisar(vet, posFim, scanner);
        if (valorId != -1) {
            for (int i = valorId; i < (posFim - 1); i++) {
                vet[i] = vet[i++];
            }
            posFim--;
            System.out.println("_ Valor excluido.");
        }
        return posFim;
    }

    private void inverter(int[] vet, int posFim, Scanner scanner) {
        int temp = 0;
        for (int i = 0; i < posFim; i++) {
            for (int j = 0; j < i; j++) {
                temp = vet[i];
                vet[i] = vet[j];
                vet[j] = temp;
            }
        }
        System.out.println("_ Valores invertidos.");
    }

    private void ordenar(int[] vet, int posFim, Scanner scanner) {
        int bolha;
        for (int i = 0; i < posFim - 1; i++) {
            if (vet[i] > vet[i++]) {
                bolha = vet[i];
                vet[i] = vet[i++];
                vet[i++] = bolha;
                i = -1;
            }
        }
        System.out.println("_ Valores ordenados.");
    }

    // private void ordenar2(int[] vet, int posFim, Scanner scanner) {
    // int armazenar = 0;
    // int j = posFim - 1;
    // for (int i = 0; i < posFim / 2; i++) {
    // armazenar = vet[j];
    // vet[j] = vet[i];
    // vet[i] = armazenar;
    // j--;
    // }
    // System.out.println("_ Valores ordenados.");
    // }

    private void mostrar(int[] vet, int posFim, Scanner scanner) {
        System.out.println("______________VETOR_______________");
        for (int i = 0; i < posFim; i++) {
            System.out.println("vet[" + i + "] " + vet[i]);
        }
    }

    private void alterar(int[] vet, int posFim, Scanner scanner) {
        int valorId = pesquisar(vet, posFim, scanner);
        if (valorId != -1) {
            System.out.println("Valor:");
            vet[valorId] = scanner.nextInt();
            System.out.println("_ Valor troca vet[" + valorId + "]: " + vet[valorId]);
        }
    }

    private int pesquisar(int[] vet, int posFim, Scanner scanner) {
        System.out.print("Valor: ");
        int valor = scanner.nextInt();
        for (int i = 0; i < posFim; i++) {
            if (valor == vet[i]) {
                System.out.println("_ Encontrado em vet[" + i + "].");
                return i;
            }
        }
        System.out.println("_ Não encontrado.");
        return -1;
    }

    private int incluir(int[] vet, int posFim, Scanner scanner) {
        if (posFim < vet.length) {
            System.out.print("Valor: ");
            vet[posFim] = scanner.nextInt();
            System.out.print("_ Incluido em vet[" + posFim + "].");
            posFim++;
        } else {
            System.out.println("_ Não incluido - Vetor cheio.");
        }
        return posFim;
    }

    public static void main(String[] args) {
        new Uni6Exe10();
    }
}
