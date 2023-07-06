package Trabalho;
import java.util.Scanner;
public class Trabalho2 {

    public static void inserirTimes(String[][] tabela, int linhas, int colunas) {
        Scanner scanner = new Scanner(System.in);
        int linha;

        mostrarTabela(tabela, linhas, colunas);
        do {
            System.out.println("Informe a posição do time na tabela (valores de 1 a " + linhas + "):");
            while (!scanner.hasNextInt()) {
                System.out.println("Valor inválido. Informe um número válido:");
                scanner.next();
            }
            linha = scanner.nextInt();
        } while (linha < 1 || linha > linhas);

        System.out.println("Insira o nome do time:");
        tabela[linha - 1][0] = scanner.next();
        System.out.println("Insira a quantidade de vitórias:");
        tabela[linha - 1][1] = validarEntradaInteira(scanner);
        System.out.println("Insira a quantidade de empates:");
        tabela[linha - 1][2] = validarEntradaInteira(scanner);
        System.out.println("Insira a quantidade de derrotas:");
        tabela[linha - 1][3] = validarEntradaInteira(scanner);
        System.out.println("Insira a quantidade de gols marcados:");
        tabela[linha - 1][4] = validarEntradaInteira(scanner);
        System.out.println("Insira a quantidade de gols sofridos:");
        tabela[linha - 1][5] = validarEntradaInteira(scanner);
        System.out.println("Time adicionado à tabela.");
    }

    public static void mostrarTabela(String[][] tabela, int linhas, int colunas) {
        System.out.println("Posição | Time | Vitórias | Empates | Derrotas | Gols Marcados | Gols Sofridos");
        for (int i = 0; i < linhas; i++) {
            System.out.print((i + 1) + " - ");
            for (int j = 0; j < colunas; j++) {
                System.out.print(tabela[i][j] + " | ");
            }
            System.out.println();
        }
    }

    public static void calcularPontos(String[][] tabela, int linhas) {
        if (tabelaVazia(tabela)) {
            System.out.println("A tabela está vazia. Não é possível calcular os pontos.");
            return;
        }

        int[] pontos = new int[linhas];
        for (int i = 0; i < linhas; i++) {
            if (tabela[i][0] != null) {
                int vitorias = Integer.parseInt(tabela[i][1]);
                int empates = Integer.parseInt(tabela[i][2]);
                pontos[i] = (vitorias * 3) + empates;
            }
        }
        System.out.println("Pontuação:");
        for (int i = 0; i < linhas; i++) {
            if (tabela[i][0] != null) {
                System.out.println(tabela[i][0] + ": " + pontos[i] + " pontos");
            }
        }
    }

    public static void calcularSaldoGols(String[][] tabela, int linhas) {
        if (tabelaVazia(tabela)) {
            System.out.println("A tabela está vazia. Não é possível calcular o saldo de gols.");
            return;
        }

        System.out.println("Saldo de gols:");
        for (int i = 0; i < linhas; i++) {
            if (tabela[i][0] != null) {
                int golsMarcados = Integer.parseInt(tabela[i][4]);
                int golsSofridos = Integer.parseInt(tabela[i][5]);
                int saldoGols = golsMarcados - golsSofridos;
                System.out.println(tabela[i][0] + ": " + saldoGols);
            }
        }
    }

    public static void removerTime(String[][] tabela, int linhas) {
        if (tabelaVazia(tabela)) {
            System.out.println("A tabela está vazia. Não há times para remover.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean encontrado = false;

        while (!encontrado) {
            System.out.println("Informe o nome exato do time a ser removido:");
            String nomeTime = scanner.next();

            for (int i = 0; i < linhas; i++) {
                if (tabela[i][0] != null && tabela[i][0].equals(nomeTime)) {
                    encontrado = true;
                    tabela[i][0] = null;
                    tabela[i][1] = null;
                    tabela[i][2] = null;
                    tabela[i][3] = null;
                    tabela[i][4] = null;
                    tabela[i][5] = null;
                    System.out.println("Time removido da tabela.");
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("Time não encontrado na tabela. Tente novamente:");
            }
        }
    }

    public static void calcularMediaGolsPorJogo(String[][] tabela, int linhas) {
        if (tabelaVazia(tabela)) {
            System.out.println("A tabela está vazia. Não é possível calcular a média de gols.");
            return;
        }

        System.out.println("Média de gols:");
        for (int i = 0; i < linhas; i++) {
            if (tabela[i][0] != null) {
                int golsMarcados = Integer.parseInt(tabela[i][4]);
                int jogos = Integer.parseInt(tabela[i][1]) + Integer.parseInt(tabela[i][2]) + Integer.parseInt(tabela[i][3]);
                float mediaGolsPorJogo = (float) golsMarcados / jogos;
                System.out.println(tabela[i][0] + ": " + mediaGolsPorJogo);
            }
        }
    }

    public static String validarEntradaInteira(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int valor = scanner.nextInt();
                if (valor >= 0) {
                    return Integer.toString(valor);
                } else {
                    System.out.println("Valor inválido. Informe um número inteiro não negativo válido:");
                }
            } else {
                System.out.println("Valor inválido. Informe um número inteiro válido:");
                scanner.next();
            }
        }
    }

    public static boolean tabelaVazia(String[][] tabela) {
        for (String[] strings : tabela) {
            if (strings[0] != null) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int linhas;

        do {
            System.out.println("Informe a quantidade de times na tabela:");
            while (!scanner.hasNextInt()) {
                System.out.println("Valor inválido. Informe um número inteiro válido:");
                scanner.next();
            }
            linhas = scanner.nextInt();

            if (linhas <= 0) {
                System.out.println("Valor inválido. A quantidade de times deve ser maior que 0. Tente novamente:");
            }
        } while (linhas <= 0);

        int colunas = 6;
        String[][] tabela = new String[linhas][colunas];

        int opcao;
        do {
            System.out.println("Menu:");
            System.out.println("1 - Inserir times");
            System.out.println("2 - Mostrar tabela");
            System.out.println("3 - Calcular pontos dos times");
            System.out.println("4 - Calcular saldo de gols dos times");
            System.out.println("5 - Calcular média de gols por jogo dos times");
            System.out.println("6 - Remover um time eliminado da tabela");
            System.out.println("0 - Sair");
            System.out.println("Informe a opção desejada:");

            while (!scanner.hasNextInt()) {
                System.out.println("Opção inválida. Informe um número válido:");
                scanner.next();
            }
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    inserirTimes(tabela, linhas, colunas);
                    break;
                case 2:
                    mostrarTabela(tabela, linhas, colunas);
                    break;
                case 3:
                    calcularPontos(tabela, linhas);
                    break;
                case 4:
                    calcularSaldoGols(tabela, linhas);
                    break;
                case 5:
                    calcularMediaGolsPorJogo(tabela, linhas);
                    break;
                case 6:
                    removerTime(tabela, linhas);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente:");
                    break;
            }

            System.out.println();
        } while (opcao != 0);
    }
}