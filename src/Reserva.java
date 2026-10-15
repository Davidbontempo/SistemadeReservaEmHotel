import java.util.Arrays;
import java.util.Scanner; // Permite ler a entrada do usuário

public class Reserva {
    // Atributos privados (Encapsulamento)
    private String nomeHospede;
    private String tipoQuarto;
    private int diasHospedagem;
    private double valorDiaria;
    private boolean confirmada;

    // Construtor
    public Reserva(String nomeHospede, String tipoQuarto, int diasHospedagem, double valorDiaria) {
        this.nomeHospede = nomeHospede;
        this.tipoQuarto = tipoQuarto;
        this.diasHospedagem = diasHospedagem;
        this.valorDiaria = valorDiaria;
        this.confirmada = false;
    }

    // Getters e Setters
    public String getNomeHospede() {
        return nomeHospede;
    }
    public void setNomeHospede(String nomeHospede) {
        this.nomeHospede = nomeHospede;
    }

    public String getTipoQuarto() { return tipoQuarto; }
    public void setTipoQuarto(String tipoQuarto) { this.tipoQuarto = tipoQuarto; }

    public int getDiasHospedagem() {
        return diasHospedagem;
    }
    public void setDiasHospedagem(int diasHospedagem) {
        this.diasHospedagem = diasHospedagem;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }
    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public boolean isConfirmada() { return  confirmada; }
    public void setConfirmada(boolean confirmada) { this.confirmada = confirmada; }

    // Regra de negócio
    public double calcularValorTotal() {
        return diasHospedagem * valorDiaria;
    }

    // Representação em String
    @Override
    public String toString() {
        String status = confirmada ? "CONFIRMADA" : "PENDENTE";
        return "\n --- DADOS DA RESERVA ---" +
                "\n Nome do Hospede: " + nomeHospede +
                "\n Tipo de Quarto: " + tipoQuarto +
                "\n Dias de Hospedagem: " + diasHospedagem +
                "\n Valor da Diaria: R$ " + valorDiaria +
                "\n Valor Total: R$ " + calcularValorTotal() +
                "\n Status do Pagamento: [" + status + "]";
    }

    // Método_ principal com Menu Iterativo
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Reserva[] reservas = new Reserva[10];
        int contadorReservas = 0;
        int opcao = 0;

        // Loop principal do menu
        System.out.println("\n===== SISTEMA DE CONTROLE DE RESERVAS DO HOTEL EBAC =====");

        while (opcao != 7) {
            System.out.println("\n===== MENU DE RESERVAS =====\n");
            System.out.println("1. Cadastrar nova reserva (" + contadorReservas + "/10 utilizados)");
            System.out.println("2. Listar reservas");
            System.out.println("3. Confirmar reserva (Mudar status)");
            System.out.println("4. Buscar reserva por nome do hóspede");
            System.out.println("5. Ordenar reservas por número de dias (decrescente)");
            System.out.println("6. Apagar uma reserva");
            System.out.println("7. Sair do sistema");
            System.out.print("\nESCOLHA UMA OPÇÃO: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do teclado após ler o número

            switch (opcao) {
                case 1:
                    System.out.println("\n--- CADASTRAR NOVA RESERVA ---");
                    if (contadorReservas < reservas.length) {
                        System.out.print("Digite o nome do hóspede: ");
                        String nome = scanner.nextLine();

                        System.out.print("Digite o tipo do quarto (Ex: Luxo, Standard): ");
                        String quarto = scanner.nextLine();

                        System.out.print("Digite os dias de hospedagem: ");
                        int dias = scanner.nextInt();

                        System.out.print("Digite o valor da diária: ");
                        double valor = scanner.nextDouble();
                        scanner.nextLine();

                        // Cria e adiciona a nova reserva no array
                        reservas[contadorReservas] = new Reserva(nome, quarto, dias, valor);
                        contadorReservas++;
                        System.out.println("\nReserva adicionada com sucesso!");
                    } else {
                        System.out.println("\n[ERRO] Não é possível adicionar mais reservas! Limite atingido.");
                    }
                    System.out.println("\nPressione ENTER para retornar ao menu principal...");
                    scanner.nextLine();
                    break;

            case 2:
                System.out.println("\n--- LISTA DE RESERVAS ---");
                if (contadorReservas == 0) {
                    System.out.println("Nenhuma reserva cadastrada até o momento.");
                } else {
                    // Ordena apenas as posições que foram preenchidas de fato
                    Arrays.sort(reservas, 0, contadorReservas, (r1, r2) -> Integer.compare(r2.getDiasHospedagem(), r1.getDiasHospedagem()));
                    //  4. Exibindo a lista final ordenada
                    System.out.println("\n--- LISTA DE RESERVAS (ORDENADA POR DIAS) ---");
                    for (int i = 0; i < contadorReservas; i++) {
                        System.out.println(reservas[i]);
                    }
                }
                System.out.println("\nPressione ENTER para retornar ao menu principal...");
                scanner.nextLine();
                break;

                case 3:
                    System.out.println("\n--- CONFIRMAR RESERVA ---");
                    if (contadorReservas == 0) {
                        System.out.println("Nenhuma reserva cadastrada para confirmar.");
                    } else {
                        System.out.print("Digite o nome exato do hóspede para confirmar: ");
                        String nomeBusca = scanner.nextLine();
                        boolean encontrada = false;

                        // Percorre o array procurando pelo nome informado
                        for (int i = 0; i < contadorReservas; i++) {
                            // equalsIgnoreCase ignora maiúsculas/minúsculas ao comparar
                            if (reservas[i].getNomeHospede().equalsIgnoreCase(nomeBusca)) {
                                reservas[i].setConfirmada(true);
                                encontrada = true;
                                System.out.println("\nSucesso: A reserva de " + reservas[i].getNomeHospede() + " foi CONFIRMADA!");
                                break;
                            }
                        }

                        if (!encontrada) {
                            System.out.println("\n[AVISO] Nenhuma reserva encontrada no nome de: " + nomeBusca);
                        }
                    }
                    System.out.println("\nPressione ENTER para retornar ao menu principal...");
                    scanner.nextLine();
                    break;

                case 4:
                    System.out.println("\n--- BUSCAR RESERVA ---");
                    if (contadorReservas == 0) {
                        System.out.println("Nenhuma reserva cadastrada no sistema.");
                    } else {
                        System.out.print("Digite o nome do hóspede que deseja buscar: ");
                        String nomeBusca = scanner.nextLine();
                        boolean encontrada = false;

                        for (int i = 0; i < contadorReservas; i++) {
                            if (reservas[i].getNomeHospede().equalsIgnoreCase(nomeBusca)) {
                                System.out.println("\nReserva encontrada:");
                                System.out.println(reservas[i]);
                                encontrada = true;
                                break;
                            }
                        }

                        if (!encontrada) {
                            System.out.println("\n[AVISO] Nenhuma reserva localizada para: " + nomeBusca);
                        }
                    }
                    System.out.println("\nPressione ENTER para retornar ao menu principal...");
                    scanner.nextLine();
                    break;

                case 5:
                    System.out.println("\n--- ORDENANDO RESERVAS POR DIAS (DECRESCENTE) ---");
                    if (contadorReservas == 0) {
                        System.out.println("Nenhuma reserva cadastrada para ordenar.");
                    } else {
                        // Faz a ordenação baseada no getDiasHospedagem de forma decrescente
                        Arrays.sort(reservas, 0, contadorReservas, (r1, r2) -> Integer.compare(r2.getDiasHospedagem(), r1.getDiasHospedagem()));
                        System.out.println("As reservas foram ordenadas com sucesso! Escolha a opção 2 para listar.");
                    }
                    System.out.println("\nPressione ENTER para retornar ao menu principal...");
                    scanner.nextLine();
                    break;

                case 6:
                    System.out.println("\n--- APAGAR UMA RESERVA ---");
                    if (contadorReservas == 0) {
                        System.out.println("Nenhuma reserva cadastrada para apagar.");
                    } else {
                        System.out.print("Digite o nome do hóspede dono da reserva que deseja apagar: ");
                        String nomeDeletar = scanner.nextLine();
                        boolean apagada = false;

                        for (int i = 0; i < contadorReservas; i++) {
                            if (reservas[i].getNomeHospede().equalsIgnoreCase(nomeDeletar)) {
                                // Desloca os elementos subsequentes para preencher o espaço vazio
                                for (int j = i; j < contadorReservas - 1; j++) {
                                    reservas[j] = reservas[j + 1];
                                }
                                reservas[contadorReservas - 1] = null; // Limpa a última posição que ficou duplicada
                                contadorReservas--; // Diminui a contagem total de itens ativos
                                apagada = true;
                                System.out.println("\nReserva de " + nomeDeletar + " foi removida com sucesso!");
                                break;
                            }
                        }
                        if (!apagada) {
                            System.out.println("\n[AVISO] Nenhuma reserva encontrada no nome de: " + nomeDeletar);
                        }
                    }
                    System.out.println("\nPressione ENTER para retornar ao menu principal...");
                    scanner.nextLine();
                    break;

                case 7:
                    System.out.println("\nEncerrando o sistema de reservas. Até logo!");
                    opcao = 7; // Força a saída do while
                    break;

            default:
                System.out.println("\n[ERRO] Opção inválida! Tente novamente.");
                System.out.println("\nPressione ENTER para retornar ao menu principal...");
                scanner.nextLine();
                break;
        }
    }
        scanner.close(); // Fecha o leitor de teclado
    }
}
