import java.util.ArrayList;
import java.util.List;

public class Avaliacao extends Feedback {
    private Cliente cliente;
    private ProvedorServico provedor;
    private static List<Avaliacao> todasAvaliacoes = new ArrayList<>();

    // Construtor padrão
    public Avaliacao() {
        super();
        todasAvaliacoes.add(this);
    }

    // Construtor com parâmetros
    public Avaliacao(Cliente cliente, ProvedorServico provedor, int nota, String comentario, String dataAvaliacao) {
        super(nota, comentario, dataAvaliacao);
        this.cliente = cliente;
        this.provedor = provedor;
        todasAvaliacoes.add(this);
    }

    // Getters e Setters
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ProvedorServico getProvedor() {
        return provedor;
    }

    public void setProvedor(ProvedorServico provedor) {
        this.provedor = provedor;
    }

    public static List<Avaliacao> getTodasAvaliacoes() {
        return todasAvaliacoes;
    }

    // Métodos
    @Override
    public void registrar() {
        if (getNota() < 0 || getNota() > 5) {
            System.out.println("Erro: Nota deve estar entre 0 e 5.");
            return;
        }
        if (provedor == null) {
            System.out.println("Erro: Provedor inválido.");
            return;
        }
        calcularMediaNotas();
        System.out.printf("Avaliação registrada pelo cliente %s para %s: Nota %d, Comentário: %s%n",
                cliente.getNome(), provedor.getNome(), getNota(), getComentario());
    }

    public void exibirAvaliacao() {
        System.out.printf("Avaliação:%n - Cliente: %s%n - Provedor: %s%n - Nota: %d%n - Comentário: %s%n - Data: %s%n",
                cliente.getNome(), provedor.getNome(), getNota(), getComentario(), getDataAvaliacao());
    }

    public void calcularMediaNotas() {
        if (todasAvaliacoes.isEmpty()) {
            System.out.println("Nenhuma avaliação disponível para calcular média.");
            return;
        }
        double somaNotas = 0;
        int count = 0;
        for (Avaliacao avaliacao : todasAvaliacoes) {
            if (avaliacao.getProvedor().equals(this.provedor)) {
                somaNotas += avaliacao.getNota();
                count++;
            }
        }
        if (count > 0) {
            double media = somaNotas / count;
            provedor.setNotaAvaliacao(media);
            System.out.printf("Média de notas atualizada para o provedor %s: %.1f%n", provedor.getNome(), media);
        } else {
            System.out.println("Nenhuma avaliação encontrada para o provedor " + provedor.getNome() + ".");
        }
    }
}