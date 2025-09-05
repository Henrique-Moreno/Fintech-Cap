import java.util.ArrayList;
import java.util.List;

public class ProvedorServico extends Usuario {
    private double notaAvaliacao;
    private List<Viagem> ofertas;

    // Construtor padrão
    public ProvedorServico() {
        super();
        this.ofertas = new ArrayList<>();
    }

    // Construtor com parâmetros
    public ProvedorServico(String nomeFantasia, String cnpj, double notaAvaliacao) {
        super(nomeFantasia, cnpj);
        this.notaAvaliacao = notaAvaliacao;
        this.ofertas = new ArrayList<>();
    }

    // Getters e Setters
    public double getNotaAvaliacao() {
        return notaAvaliacao;
    }

    public void setNotaAvaliacao(double notaAvaliacao) {
        this.notaAvaliacao = notaAvaliacao;
    }

    public List<Viagem> getOfertas() {
        return ofertas;
    }

    public void setOfertas(List<Viagem> ofertas) {
        this.ofertas = ofertas;
    }

    // Métodos
    public void cadastrarOferta(String destino, String dataPartida, String dataRetorno, double preco) {
        if (preco <= 0 || destino == null || destino.isEmpty()) {
            System.out.println("Erro: Dados da viagem inválidos.");
            return;
        }
        Viagem novaViagem = new Viagem(destino, dataPartida, dataRetorno, preco, this);
        ofertas.add(novaViagem);
        System.out.printf("Oferta cadastrada pelo provedor %s: Viagem para %s, Preço: R$%.2f%n",
                getNome(), destino, preco);
    }

    public void visualizarReservas(List<Reserva> reservas) {
        if (reservas == null || reservas.isEmpty()) {
            System.out.println("Nenhuma reserva encontrada para o provedor " + getNome() + ".");
            return;
        }
        System.out.println("Reservas associadas ao provedor " + getNome() + ":");
        boolean encontrou = false;
        for (Reserva reserva : reservas) {
            if (reserva.getViagem().getProvedor().equals(this)) {
                System.out.printf(" - Reserva ID: %s, Cliente: %s, Destino: %s, Status: %s%n",
                        reserva.getIdReserva(), reserva.getCliente().getNome(),
                        reserva.getViagem().getDestino(), reserva.getStatusReserva());
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma reserva encontrada para as viagens deste provedor.");
        }
    }

    public void receberPagamento(Pagamento pagamento) {
        if (pagamento == null || !pagamento.getStatus().equals("Pendente")) {
            System.out.println("Erro: Pagamento inválido ou não pendente.");
            return;
        }
        pagamento.setStatus("Concluído");
        System.out.printf("Pagamento de R$%.2f recebido pelo provedor %s para a viagem %s.%n",
                pagamento.getValor(), getNome(), pagamento.getViagem().getDestino());
    }

    @Override
    public void realizarAcaoPrincipal() {
        System.out.println("Provedor " + getNome() + " cadastrou uma nova oferta.");
        cadastrarOferta("Rio de Janeiro, RJ", "15/02/2026", "22/02/2026", 1500.00);
    }
}