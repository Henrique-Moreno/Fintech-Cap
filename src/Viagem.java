import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Viagem extends Servico {
    private String destino;
    private String dataPartida;
    private String dataRetorno;

    // Construtor padrão
    public Viagem() {
        super();
    }

    // Construtor com parâmetros
    public Viagem(String destino, String dataPartida, String dataRetorno, double preco, ProvedorServico provedor) {
        super(preco, provedor);
        this.destino = destino;
        this.dataPartida = dataPartida;
        this.dataRetorno = dataRetorno;
    }

    // Getters e Setters
    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(String dataPartida) {
        this.dataPartida = dataPartida;
    }

    public String getDataRetorno() {
        return dataRetorno;
    }

    public void setDataRetorno(String dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    // Métodos
    @Override
    public void exibirDetalhes() {
        System.out.printf("Detalhes da Viagem:%n - Destino: %s%n - Data de Partida: %s%n - Data de Retorno: %s%n - Preço: R$%.2f%n - Provedor: %s (Nota: %.1f)%n",
                destino, dataPartida, dataRetorno, getPreco(), getProvedor().getNome(), getProvedor().getNotaAvaliacao());
    }

    public void aplicarDesconto(double percentual) {
        if (percentual < 0 || percentual > 100) {
            System.out.println("Erro: Percentual de desconto inválido (deve ser entre 0 e 100).");
            return;
        }
        double desconto = getPreco() * (percentual / 100);
        double novoPreco = getPreco() - desconto;
        if (novoPreco < 0) {
            System.out.println("Erro: Desconto resultaria em preço negativo.");
            return;
        }
        setPreco(novoPreco);
        System.out.printf("Desconto de %.1f%% aplicado! Novo preço: R$%.2f%n", percentual, getPreco());
    }

    public boolean verificarDisponibilidade() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate partida = LocalDate.parse(dataPartida, formatter);
            LocalDate hoje = LocalDate.now();
            boolean disponivel = getPreco() > 0 && partida.isAfter(hoje);
            System.out.println(disponivel ? "Viagem para " + destino + " está disponível!" : "Viagem para " + destino + " não está disponível.");
            return disponivel;
        } catch (Exception e) {
            System.out.println("Erro: Formato de data inválido em verificarDisponibilidade.");
            return false;
        }
    }
}