package Uni6;

import java.util.Random;
import java.util.Scanner;

public class BatalhaNaval {

    // Itens utilitarios
    private String[][] mapa = new String[8][8];
    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);
    private int lastId = 0;

    // Dados
    private int totalTentativas = 0;
    private int maxTentativas = 30;
    private int totalAcertos = 0;
    private int totalErros = 0;
    private double taxaAcerto = 0;
    private int totalAfundados = 0;
    private int totalDeNavios = 0;

    private int[] navioTamanho;
    private int[] navioRestante;
    private String[] navios;
    private boolean debug = true;

    public BatalhaNaval() {
        lastId = 0;

        System.out.println("Gerando Mapa Aguarde!");

        // definir quantidades por tipo e alocar arrays de navios
        int portaAvioes = 1; // tamanho 4
        int cruzadores = 2;  // tamanho 3
        int destroyers = 3;  // tamanho 2
        int submarinos = 4;  // tamanho 1
        totalDeNavios = portaAvioes + cruzadores + destroyers + submarinos;

        navioTamanho = new int[totalDeNavios];
        navioRestante = new int[totalDeNavios];
        navios = new String[totalDeNavios];

        criarMapa();

        posicionarNavios("Submarino", 1, 4);
        posicionarNavios("Destroyer", 2, 3);
        posicionarNavios("Cruzador", 3, 2);
        posicionarNavios("Porta-aviões", 4, 1);

        while (totalAfundados != totalDeNavios && totalTentativas < maxTentativas) {
            System.out.println();
            taxaAcerto = (totalTentativas == 0) ? 0 : (totalAcertos / (double) totalTentativas);
            System.out.println("Tentativa: " + totalTentativas + "/" + maxTentativas + " Acertos: " + totalAcertos + " Taxa: " + String.format("%.2f", taxaAcerto * 100) + "%");
            System.out.println("Afundados: " + totalAfundados + "/" + totalDeNavios );
            System.out.println();

            if (debug) {               
                exibirMapaLog();
                System.out.println();
            }

            exibirMapa();
            System.out.println();

            System.out.print("Digite a linha (0-7): ");
            int linha = scanner.nextInt();

            if (linha < 0 || linha > 7) {
                System.out.println("Linha inválida");
                continue;
            }

            System.out.print("Digite a coluna (0-7): ");
            int coluna = scanner.nextInt();

            if (coluna < 0 || coluna > 7) {
                System.out.println("Coluna inválida");
                continue;
            }

            System.out.println();
            lancarCanhao(linha, coluna);
        }

        System.out.println();
        exibirEstatisticasFinais();
    }

    private void criarMapa() {
        int linhas = 8;
        int colunas = 8;

        for (int l = 0; l < linhas; l++) {
            for (int c = 0; c < colunas; c++) {
                mapa[l][c] = "~";
            }
        }
    }


    private void exibirMapaLog() {
        // Exibir números no topo 
        System.out.print("  0 1 2 3 4 5 6 7"); 
        System.out.println();
        
        // Exibe grid 8x8 com números na lateral esquerda
        for (int linha = 0; linha < mapa.length; linha++) {
            System.out.print(linha + " ");
            for (int coluna = 0; coluna < mapa.length; coluna++) {
                // Dados do navio
                String[] navio = consultarParteNavio(linha, coluna);
                if (navio != null) {
                    System.out.print(navio[2] + " ");
                } else {
                    System.out.print(mapa[linha][coluna] + " ");
                }
            }
            System.out.println();
        }
    }

    private void exibirMapa() {
        // Exibir números no topo 
        System.out.print("  0 1 2 3 4 5 6 7"); 
        System.out.println();
        
        // Exibe grid 8x8 com números na lateral esquerda
        for (int linha = 0; linha < mapa.length; linha++) {
            System.out.print(linha + " ");
            for (int coluna = 0; coluna < mapa.length; coluna++) {
                System.out.print(obterSimboloCelula(linha, coluna) + " ");
            }
            System.out.println();
        }
    }

    private void lancarCanhao(int linha, int coluna) {
        totalTentativas++;

        String celula = mapa[linha][coluna];

        if (celula.equals("~")) {   
            totalErros++;
            mapa[linha][coluna] = "X";
            System.out.println("ERROU! Você atingiu o mar!");
            return;
        }

        if (celula.equals("A") || celula.equals("X")) {   
            System.out.println("ATENÇÃO! Você já tem conhecimento dessas cordenadas!");
            return;
        }

        String[] navio = consultarParteNavio(linha, coluna);
        totalAcertos++;
        atualizarStatusNavio(linha, coluna, "A");
        System.out.println("ACERTOU! Um navio foi atingido!");

        if (navioAfundado(linha, coluna)) {
            totalAfundados++;
            System.out.println("AFUNDOU! Você afundou um " + navio[1] + "!");
        }
        return;
    }

    private String obterSimboloCelula(int linha, int coluna) {
        // Dados do navio
        String[] navio = consultarParteNavio(linha, coluna);
        if (navio != null) {
            if (navio[3].equals("A"))
                return "A";
            else
                return "~";
        }

        // Mapa vazio ou já marcado com X/A
        return mapa[linha][coluna];
    }


    // Lógica para posicionar navios no mapa
    private void posicionarNavios(String nome, int tamanho, int qtd) {
        for (int i = 0; i < qtd; i++) {
            boolean posicionado = false;
            int navioId = lastId++;

            while (!posicionado) {
                boolean vertical = random.nextBoolean();
                int linhaInicial = random.nextInt(mapa.length);
                int colunaInicial = random.nextInt(mapa.length);

                if (vertical && linhaInicial + (tamanho - 1) > (mapa.length - 1)) continue;
                if (!vertical && colunaInicial + (tamanho - 1) > (mapa.length - 1)) continue;

                boolean camposVazios = true;
                for (int t = 0; t < tamanho; t++) {
                    int linha= linhaInicial, coluna = colunaInicial;
                    if (vertical) linha += t; 
                    if (!vertical) coluna += t;
                    if (!mapa[linha][coluna].equals("~")) {
                        camposVazios = false;
                        break;
                    }
                }

                if (!camposVazios) continue;

                for (int t = 0; t < tamanho; t++) {
                    int linha= linhaInicial, coluna = colunaInicial;
                    if (vertical) linha += t; 
                    if (!vertical) coluna += t;
                    mapa[linha][coluna] = criarParteNavio(navioId, nome, tamanho);
                }

                posicionado = true;
            }
        }
    }

    // Cria a parte de um navio
    private String criarParteNavio(int id, String nome, int tamanho) {
        // Guarda informações do navio nos array
        if (id >= 0 && id < navioTamanho.length) {
            navioTamanho[id] = tamanho;
            navioRestante[id] = tamanho;
            navios[id] = nome;
        }

        String[] parte = {
            String.valueOf(id),                 // ID
            nome,                               // Nome
            nome.substring(0, 1).toUpperCase(), // Tipo
            "L",                                // Status (L = Levantado)
            String.valueOf(tamanho)             // Tamanho
        };

        return String.join(", ", parte);
    }

    // Atualiza o status de uma parte do navio
    private void atualizarStatusNavio(int linha, int coluna, String novoStatus) {
        String[] parte = consultarParteNavio(linha, coluna);

        if (parte == null) {
            System.out.println("Não há navio nesta posição!");
            return;
        }

        // Atualiza o status no array
        parte[3] = novoStatus;

        // Reconstrói a string para armazenar no mapa
        mapa[linha][coluna] = String.join(", ", parte);

        // Atualiza contador global de partes restantes para o navio
        int id = Integer.parseInt(parte[0]);
        int restantes = 0;
        for (int l = 0; l < mapa.length; l++) {
            for (int c = 0; c < mapa[l].length; c++) {
                String[] p = consultarParteNavio(l, c);
                if (p != null && Integer.parseInt(p[0]) == id && p[3].equals("L")) {
                    restantes++;
                }
            }
        }
        navioRestante[id] = restantes;
    }


    // Coleta informações de um navio
    private String[] consultarParteNavio(int linha, int coluna) {
        String navio = mapa[linha][coluna];
        if (navio == null || navio.equals("~") || navio.equals("X") || navio.equals("A")) {
            return null;
        }

        // Separar por vírgula e espaço
        String[] partes = navio.split(",\\s*");

        if (partes.length != 5) {
            return null;
        }

        return partes; // 0: ID, 1: Nome, 2: Tipo, 3: Status, 4: Tamanho
    }



    // logica para saber se um navio foi afundado completamente
    private boolean navioAfundado(int linha, int coluna) {
        String[] parteInicial = consultarParteNavio(linha, coluna);

        if (parteInicial == null) {
            // Não existe navio aqui
            return false;
        }

        int tamanho = Integer.parseInt(parteInicial[4]);
        int id = Integer.parseInt(parteInicial[0]);

        int partesDestruidas = 0;

        // Conta a própria célula
        partesDestruidas++;

        // Compara as celulas a esquerda
        for (int i = 1; i < tamanho; i++) {
            int c = coluna - i;
            if (c < 0) break;
            String[] p = consultarParteNavio(linha, c);
            if (p == null) break; // não é parte do navio
            if (Integer.parseInt(p[0]) != id) break; // parte de outro navio
            if (p[3].equals("A")) partesDestruidas++;
        }

        // Compara as celulas a direita
        for (int i = 1; i < tamanho; i++) {
            int c = coluna + i;
            if (c >= mapa[0].length) break;
            String[] p = consultarParteNavio(linha, c);
            if (p == null) break;
            if (Integer.parseInt(p[0]) != id) break;
            if (p[3].equals("A")) partesDestruidas++;
        }

        // Compara as celulas cima
        for (int i = 1; i < tamanho; i++) {
            int l = linha - i;
            if (l < 0) break;
            String[] p = consultarParteNavio(l, coluna);
            if (p == null) break;
            if (Integer.parseInt(p[0]) != id) break;
            if (p[3].equals("A")) partesDestruidas++;
        }

        // Compara as celulas baixo
        for (int i = 1; i < tamanho; i++) {
            int l = linha + i;
            if (l >= mapa.length) break;
            String[] p = consultarParteNavio(l, coluna);
            if (p == null) break;
            if (Integer.parseInt(p[0]) != id) break;
            if (p[3].equals("A")) partesDestruidas++;
        }

        // Se o número de partes atingidas é igual ao tamanho, navio afundado
        return partesDestruidas == tamanho;
    } 

    private int calcularPontos() {
        int totalPontos = (totalAcertos * 10) + (totalAfundados * 50) - (totalErros * 2);
        if (totalTentativas < 25 && totalAfundados == totalDeNavios) totalPontos += 100;
        return totalPontos;
    }

    private String classificacao() {
        int pontos = calcularPontos();
        if (pontos > 400) return "Exelente";
        if (pontos >= 300 && pontos <= 400) return "Bom";
        if (pontos >= 200 && pontos <= 299) return "Regular";
        return "Precisa melhorar";
    }

    private void exibirEstatisticasFinais() {
        System.out.println();
        System.out.println("=======================================");
        System.out.println("          ESTATÍSTICAS FINAIS          ");
        System.out.println("=======================================");

        String status;
        if (totalAfundados == totalDeNavios) {
            status = "VITÓRIA!";
        } else {
            status = "DERROTA!";
        }

        System.out.printf("Status           : %s%n", status);
        System.out.printf("Tentativas usadas : %d/%d%n", totalTentativas, maxTentativas);
        System.out.printf("Total de acertos  : %d%n", totalAcertos);
        System.out.printf("Total de erros    : %d%n", totalErros);

        double taxa;
        if (totalTentativas == 0) {
            taxa = 0.0;
        } else {
            taxa = (totalAcertos / (double) totalTentativas) * 100.0;
        }
        System.out.printf("Taxa de acerto    : %.2f%%%n", taxa);
        System.out.printf("Navios afundados  : %d/%d%n", totalAfundados, totalDeNavios);

        // Contagem por tipo (total e afundados)
        int portaAf = 0;
        int cruzAf = 0;
        int desAf = 0;
        int subAf = 0;

        for (int i = 0; i < navios.length; i++) {
            String navio = navios[i];
            int resNavio = navioRestante[i];
            if (navio.equals("Porta-aviões")) {
                if (resNavio == 0) {
                    portaAf = portaAf + 1;
                }
            } else if (navio.equals("Cruzador")) {
                if (resNavio == 0) {
                    cruzAf = cruzAf + 1;
                }
            } else if (navio.equals("Destroyer")) {
                if (resNavio == 0) {
                    desAf = desAf + 1;
                }
            } else if (navio.equals("Submarino")) {
                if (resNavio == 0) {
                    subAf = subAf + 1;
                }
            }
        }

        System.out.println();
        System.out.println("Navios por tipo:");
        System.out.printf("Porta-aviões : %d/%d%n", portaAf, 1);
        System.out.printf("Cruzadores   : %d/%d%n", cruzAf, 2);
        System.out.printf("Destroyers   : %d/%d%n", desAf, 3);
        System.out.printf("Submarinos   : %d/%d%n", subAf, 4);

        System.out.println();
        System.out.println("PONTUAÇÃO FINAL:");
        int pontos = calcularPontos();
        System.out.printf("Pontos            : %d%n", pontos);
        System.out.printf("Acertos           : %d x 10 = %d%n", totalAcertos, totalAcertos * 10);
        System.out.printf("Navios afundados  : %d x 50 = %d%n", totalAfundados, totalAfundados * 50);
        System.out.printf("Penalidade erros  : %d x -2 = %d%n", totalErros, -2 * totalErros);
        if (totalTentativas < 25 && totalAfundados == totalDeNavios) {
            System.out.println("Bônus vitória rápida: +100");
        }

        System.out.printf("Classificação     : %s%n", classificacao());
        System.out.println("=======================================\n");
    }


    public static void main(String[] args) {
        new BatalhaNaval();
    }
}
