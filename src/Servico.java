public abstract class Servico {
    private double preco;
    private ProvedorServico provedor;

    // Construtor padrão
    public Servico() {
    }

    // Construtor com parâmetros
    public Servico(double preco, ProvedorServico provedor) {
        this.preco = preco;
        this.provedor = provedor;
    }

    // Getters e Setters
    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public ProvedorServico getProvedor() {
        return provedor;
    }

    public void setProvedor(ProvedorServico provedor) {
        this.provedor = provedor;
    }

    // Método
    public abstract void exibirDetalhes();
}
