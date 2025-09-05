import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {
    private String email;
    private String senha;
    private List<Reserva> reservas;

    // Construtor padrão
    public Cliente() {
        super();
        this.reservas = new ArrayList<>();
    }

    // Construtor com parâmetros
    public Cliente(String nome, String identificador, String email, String senha) {
        super(nome, identificador);
        this.email = email;
        this.senha = senha;
        this.reservas = new ArrayList<>();
    }

    // Getters e Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    // Métodos
    public void buscarOfertas(List<Viagem> viagensDisponiveis) {
        if (viagensDisponiveis == null || viagensDisponiveis.isEmpty()) {
            System.out.println("Nenhuma viagem disponível no momento.");
            return;
        }
        System.out.println("Ofertas disponíveis para " + getNome() + ":");
        for (Viagem viagem : viagensDisponiveis) {
            System.out.printf(" - Destino: %s, Preço: R$%.2f, Provedor: %s%n",
                    viagem.getDestino(), viagem.getPreco(), viagem.getProvedor().getNome());
        }
    }

    public Pagamento realizarPagamento(Viagem viagem, String metodoPagamento) {
        if (viagem == null || viagem.getPreco() <= 0) {
            System.out.println("Erro: Viagem inválida ou preço não especificado.");
            return null;
        }
        Pagamento pagamento = new Pagamento("TRANS_" + System.currentTimeMillis(), viagem.getPreco(), metodoPagamento, "Pendente", viagem);
        pagamento.processar();
        System.out.printf("Pagamento de R$%.2f iniciado para %s pelo cliente %s.%n",
                pagamento.getValor(), viagem.getDestino(), getNome());
        return pagamento;
    }

    public void gerenciarPerfil(String novoEmail, String novaSenha) {
        if (novoEmail != null && novoEmail.contains("@")) {
            this.email = novoEmail;
            System.out.println("Email atualizado para: " + novoEmail);
        } else {
            System.out.println("Erro: Email inválido.");
        }
        if (novaSenha != null && !novaSenha.isEmpty()) {
            this.senha = novaSenha;
            System.out.println("Senha atualizada com sucesso.");
        } else {
            System.out.println("Erro: Senha inválida.");
        }
    }

    @Override
    public void realizarAcaoPrincipal() {
        System.out.println("Cliente " + getNome() + " está buscando ofertas...");

        List<Viagem> viagens = new ArrayList<>();
        ProvedorServico provedor = new ProvedorServico("Hotel Sol e Mar", "00.111.222/0001-33", 4.5);
        viagens.add(new Viagem("Salvador, BA", "10/01/2026", "17/01/2026", 1250.00, provedor));
        buscarOfertas(viagens);
    }
}