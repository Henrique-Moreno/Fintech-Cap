public abstract class Usuario {
    private String nome;
    private String identificador;

    // Construtor padrão
    public Usuario() {
    }

    // Construtor com parâmetros
    public Usuario(String nome, String identificador) {
        this.nome = nome;
        this.identificador = identificador;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    // Método
    public abstract void realizarAcaoPrincipal();
}
