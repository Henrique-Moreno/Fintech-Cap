import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Fintech de Viagens - Demonstração do Sistema ===");
        System.out.println();

        // 1. Criando um Provedor de Serviço
        System.out.println("1. Criando Provedor de Serviço:");
        ProvedorServico hotelSolEMar = new ProvedorServico("Hotel Sol e Mar", "00.111.222/0001-33", 4.5);
        System.out.printf(" - Nome Fantasia: %s%n", hotelSolEMar.getNome());
        System.out.printf(" - CNPJ: %s%n", hotelSolEMar.getIdentificador());
        System.out.printf(" - Nota de Avaliação: %.1f%n", hotelSolEMar.getNotaAvaliacao());
        hotelSolEMar.realizarAcaoPrincipal();
        hotelSolEMar.cadastrarOferta("Porto Seguro, BA", "20/03/2026", "27/03/2026", 1800.00);
        System.out.println();

        // 2. Criando um Cliente
        System.out.println("2. Criando Cliente:");
        Cliente novoCliente = new Cliente("Maria Silva", "123.456.789-00", "maria@email.com", "senha123");
        System.out.printf(" - Nome: %s%n", novoCliente.getNome());
        System.out.printf(" - CPF: %s%n", novoCliente.getIdentificador());
        System.out.printf(" - Email: %s%n", novoCliente.getEmail());
        novoCliente.gerenciarPerfil("maria11silva@gmail.com", "novaSenha123");
        List<Viagem> viagensDisponiveis = hotelSolEMar.getOfertas();
        viagensDisponiveis.add(new Viagem("Salvador, BA", "10/01/2026", "17/01/2026", 1250.00, hotelSolEMar));
        novoCliente.buscarOfertas(viagensDisponiveis);
        System.out.println();

        // 3. Criando uma Viagem
        System.out.println("3. Criando Viagem:");
        Viagem viagemBahia = viagensDisponiveis.get(2); // Salvador, BA
        viagemBahia.exibirDetalhes();
        viagemBahia.aplicarDesconto(10.0);
        System.out.println();

        // 4. Criando um Pagamento
        System.out.println("4. Criando Pagamento:");
        Pagamento pagamentoViagem = novoCliente.realizarPagamento(viagemBahia, "Cartão de Crédito");
        if (pagamentoViagem != null) {
            pagamentoViagem.emitirRecibo();
            hotelSolEMar.receberPagamento(pagamentoViagem);
        }
        System.out.println();

        // 5. Criando uma Reserva
        System.out.println("5. Criando Reserva:");
        Reserva reserva = new Reserva("RES_1234", novoCliente, viagemBahia, pagamentoViagem, "Pendente");
        reserva.confirmarReserva();
        reserva.enviarConfirmacaoEmail();
        System.out.println();
        System.out.println("Visualizando reservas do provedor:");
        List<Reserva> reservas = new ArrayList<>();
        reservas.add(reserva);
        hotelSolEMar.visualizarReservas(reservas);
        System.out.println();

        // 6. Criando uma Avaliação
        System.out.println("6. Criando Avaliação:");
        Avaliacao avaliacao = new Avaliacao(novoCliente, hotelSolEMar, 5, "Ótima experiência!", "05/09/2025");
        avaliacao.registrar();
        avaliacao.exibirAvaliacao();
        System.out.println();

        // 7. Criando outra Avaliação para testar média
        System.out.println("7. Criando outra Avaliação:");
        Avaliacao avaliacao2 = new Avaliacao(novoCliente, hotelSolEMar, 4, "Boa estadia, mas pode melhorar.", "06/09/2025");
        avaliacao2.registrar();
        System.out.println();

        // 8. Cancelando a Reserva
        System.out.println("8. Cancelando Reserva:");
        reserva.cancelarReserva();
        System.out.println();

        System.out.println("--- Teste concluído com sucesso! ---");
    }
}