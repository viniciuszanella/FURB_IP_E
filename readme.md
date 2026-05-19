# Uni6Exe10.java - Dado um vetor de números inteiros, com capacidade limitada a 50 elementos, faça um programa que construa um menu com as seguintes opções:

“1 – Incluir valor”: nesta opção inclua o valor no fim do vetor, se houver espaço. Informe o usuário se o valor foi incluído no vetor ou não;
“2 – Pesquisar valor”: nesta opção leia um valor e informe se o mesmo está no vetor;
“3 – Alterar valor”: nesta opção informe um número a ser alterado e um novo número a ser colocado no lugar (só para a primeira ocorrência deste número). Caso o número a ser alterado exista no vetor, substitua-o pelo novo número. Caso contrário, informe “número não encontrado”;
“4 – Excluir valor”: nesta opção leia um valor e, caso ele esteja no vetor, exclua-o. Informe o usuário se o valor foi excluído do vetor ou não. A posição que foi excluída o valor deve ser preenchida pelo valor seguinte, sucessivamente até o final dos valores do vetor;
“5 – Mostrar valores”: nesta opção mostre todos os valores armazenados no vetor;
“6 – Ordenar valores”: ordene todos os valores do vetor em ordem crescente;
“7 – Inverter valores”: desafio (ver abaixo);
“8 – Sair do sistema”: nesta opção deve ser finalizada a execução do programa.
O menu deve-se repetir até que o usuário escolha a opção 8.
Para a opção "6 - Ordenar valores" se pode usar o "método bolha" explicado neste vídeo. Existem também outros vídeos que mostram a lógica de ordenação usando o "método bolha", no caso é só procurar por "Bubble Sort". Um deles é Bubble Sort | GeeksforGeeks. Um outro um pouco mais "divertido" procura mostrar a lógica usando uma coreografia de dança: Bubble sort with Hungarian, folk dance. E, por fim, tem uma forma interativa e visual com explicação em: https://www.hackerearth.com/practice/algorithms/sorting/bubble-sort/visualize/.

Desafio: adapte o código acima para ter mais uma opção no menu para também poder inverter os elementos dentro de um vetor. Assim o último elemento passa a ser o primeiro, o penúltimo passa ser o segundo, e assim sucessivamente para todos os elementos da lista. Mas lembre, não se deve considerar o vetor inteiro, mas somente os elementos que já foram adicionados no vetor. Como base use o código descrito em vetorInverter.java.

import java.util.Scanner;

public class Uni6Exe10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] vetor = new int[50];
        int tamanho = 0;
        int opcao;

        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Incluir valor");
            System.out.println("2 - Pesquisar valor");
            System.out.println("3 - Alterar valor");
            System.out.println("4 - Excluir valor");
            System.out.println("5 - Mostrar valores");
            System.out.println("6 - Ordenar valores");
            System.out.println("7 - Inverter valores");
            System.out.println("8 - Sair");
            System.out.print("Opcao: ");
            opcao = sc.nextInt();

            switch (opcao) {

                case 1:
                    if (tamanho == 50) {
                        System.out.println("Vetor cheio, valor nao incluido.");
                    } else {
                        System.out.print("Valor: ");
                        vetor[tamanho] = sc.nextInt();
                        tamanho++;
                        System.out.println("Valor incluido.");
                    }
                    break;

                case 2:
                    System.out.print("Valor a pesquisar: ");
                    int busca = sc.nextInt();
                    boolean achouBusca = false;
                    for (int i = 0; i < tamanho; i++) {
                        if (vetor[i] == busca) {
                            System.out.println("Valor encontrado na posicao " + i + ".");
                            achouBusca = true;
                            break;
                        }
                    }
                    if (!achouBusca) System.out.println("Valor nao encontrado.");
                    break;

                case 3:
                    System.out.print("Valor a alterar: ");
                    int antigo = sc.nextInt();
                    System.out.print("Novo valor: ");
                    int novo = sc.nextInt();
                    boolean achouAlt = false;
                    for (int i = 0; i < tamanho; i++) {
                        if (vetor[i] == antigo) {
                            vetor[i] = novo;
                            System.out.println("Valor alterado.");
                            achouAlt = true;
                            break;
                        }
                    }
                    if (!achouAlt) System.out.println("Numero nao encontrado.");
                    break;

                case 4:
                    System.out.print("Valor a excluir: ");
                    int excluir = sc.nextInt();
                    boolean achouExc = false;
                    for (int i = 0; i < tamanho; i++) {
                        if (vetor[i] == excluir) {
                            // Desloca os elementos seguintes para a esquerda
                            for (int j = i; j < tamanho - 1; j++) {
                                vetor[j] = vetor[j + 1];
                            }
                            tamanho--;
                            System.out.println("Valor excluido.");
                            achouExc = true;
                            break;
                        }
                    }
                    if (!achouExc) System.out.println("Valor nao encontrado.");
                    break;

                case 5:
                    if (tamanho == 0) {
                        System.out.println("Vetor vazio.");
                    } else {
                        System.out.print("Valores: ");
                        for (int i = 0; i < tamanho; i++) {
                            System.out.print(vetor[i] + " ");
                        }
                        System.out.println();
                    }
                    break;

                case 6:
                    // Bubble Sort: a cada passagem o maior elemento vai para o fim
                    for (int i = 0; i < tamanho - 1; i++) {
                        for (int j = 0; j < tamanho - 1 - i; j++) {
                            if (vetor[j] > vetor[j + 1]) {
                                int temp = vetor[j];
                                vetor[j] = vetor[j + 1];
                                vetor[j + 1] = temp;
                            }
                        }
                    }
                    System.out.println("Vetor ordenado.");
                    break;

                case 7:
                    // Dois ponteiros trocando as extremidades até se cruzarem
                    int inicio = 0;
                    int fim = tamanho - 1;
                    while (inicio < fim) {
                        int temp = vetor[inicio];
                        vetor[inicio] = vetor[fim];
                        vetor[fim] = temp;
                        inicio++;
                        fim--;
                    }
                    System.out.println("Vetor invertido.");
                    break;

                case 8:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }

        } while (opcao != 8);
    }
}
