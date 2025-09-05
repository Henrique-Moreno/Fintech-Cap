public abstract class Transacao {
    private String idTransacao;
    private double valor;
    private String status;

    // Construtor padrão
    public Transacao() {
    }

    // Construtor com parâmetros
    public Transacao(String idTransacao, double valor, String status) {
        this.idTransacao = idTransacao;
        this.valor = valor;
        this.status = status;
    }

    // Getters e Setters
    public String getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(String idTransacao) {
        this.idTransacao = idTransacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Método
    public abstract void processar();
}
