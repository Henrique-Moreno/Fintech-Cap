public class Reserva {
    private String idReserva;
    private Cliente cliente;
    private Viagem viagem;
    private Pagamento pagamento;
    private String statusReserva;

    // Construtor padrão
    public Reserva() {
    }

    // Construtor com parâmetros
    public Reserva(String idReserva, Cliente cliente, Viagem viagem, Pagamento pagamento, String statusReserva) {
        this.idReserva = idReserva;
        this.cliente = cliente;
        this.viagem = viagem;
        this.pagamento = pagamento;
        this.statusReserva = statusReserva;
    }

    // Getters e Setters
    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public String getStatusReserva() {
        return statusReserva;
    }

    public void setStatusReserva(String statusReserva) {
        this.statusReserva = statusReserva;
    }

    // Métodos
    public void confirmarReserva() {
        if (viagem == null || !viagem.verificarDisponibilidade()) {
            System.out.println("Erro: Viagem não disponível para reserva.");
            return;
        }
        if (pagamento == null || !pagamento.getStatus().equals("Concluído")) {
            System.out.println("Erro: Pagamento não concluído.");
            return;
        }
        setStatusReserva("Confirmada");
        cliente.getReservas().add(this); // Adiciona a reserva à lista do cliente
        System.out.printf("Reserva %s confirmada para o cliente %s, viagem para %s.%n",
                idReserva, cliente.getNome(), viagem.getDestino());
    }

    public void cancelarReserva() {
        if (!statusReserva.equals("Confirmada")) {
            System.out.println("Erro: Apenas reservas confirmadas podem ser canceladas.");
            return;
        }
        setStatusReserva("Cancelada");
        pagamento.solicitarReembolso();
        cliente.getReservas().remove(this); // Remove a reserva da lista do cliente
        System.out.printf("Reserva %s cancelada para o cliente %s.%n", idReserva, cliente.getNome());
    }

    public void enviarConfirmacaoEmail() {
        if (!statusReserva.equals("Confirmada")) {
            System.out.println("Erro: Apenas reservas confirmadas podem enviar email de confirmação.");
            return;
        }
        System.out.println("=== Email de Confirmação de Reserva ===");
        System.out.printf("Prezado(a) %s,%n", cliente.getNome());
        System.out.printf("Sua reserva %s foi confirmada!%n", idReserva);
        System.out.printf(" - Destino: %s%n", viagem.getDestino());
        System.out.printf(" - Data de Partida: %s%n", viagem.getDataPartida());
        System.out.printf(" - Data de Retorno: %s%n", viagem.getDataRetorno());
        System.out.printf(" - Valor Pago: R$%.2f (%s)%n", pagamento.getValor(), pagamento.getMetodoPagamento());
        System.out.printf(" - Provedor: %s%n", viagem.getProvedor().getNome());
        System.out.println("Obrigado por escolher nossa Fintech de Viagens!");
        System.out.println("=====================================");
    }
}