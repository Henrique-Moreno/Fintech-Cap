public class Pagamento extends Transacao {
    private String metodoPagamento;
    private Viagem viagem;

    // Construtor padrão
    public Pagamento() {
        super();
    }

    // Construtor com parâmetros
    public Pagamento(String idTransacao, double valor, String metodoPagamento, String status, Viagem viagem) {
        super(idTransacao, valor, status);
        this.metodoPagamento = metodoPagamento;
        this.viagem = viagem;
    }

    // Getters e Setters
    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    // Métodos
    @Override
    public void processar() {
        if (getValor() <= 0 || metodoPagamento == null || metodoPagamento.isEmpty()) {
            System.out.println("Erro: Não foi possível processar o pagamento. Valor ou método inválido.");
            return;
        }
        setStatus("Concluído");
        System.out.printf("Pagamento de R$%.2f para a viagem %s foi processado com sucesso (%s).%n",
                getValor(), viagem.getDestino(), metodoPagamento);
    }

    public void emitirRecibo() {
        System.out.println("=== Recibo de Pagamento ===");
        System.out.printf(" - ID da Transação: %s%n", getIdTransacao());
        System.out.printf(" - Valor: R$%.2f%n", getValor());
        System.out.printf(" - Método: %s%n", metodoPagamento);
        System.out.printf(" - Status: %s%n", getStatus());
        System.out.printf(" - Viagem: %s (Provedor: %s)%n", viagem.getDestino(), viagem.getProvedor().getNome());
        System.out.println("=========================");
    }

    public void solicitarReembolso() {
        if (!getStatus().equals("Concluído")) {
            System.out.println("Erro: Apenas pagamentos concluídos podem ser reembolsados.");
            return;
        }
        setStatus("Reembolsado");
        setValor(0.0);
        System.out.printf("Reembolso solicitado para a viagem %s. Status atualizado para %s.%n",
                viagem.getDestino(), getStatus());
    }
}