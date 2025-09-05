public abstract class Feedback {
    private int nota;
    private String comentario;
    private String dataAvaliacao;

    // Construtor padrão
    public Feedback() {
    }

    // Construtor com parâmetros
    public Feedback(int nota, String comentario, String dataAvaliacao) {
        this.nota = nota;
        this.comentario = comentario;
        this.dataAvaliacao = dataAvaliacao;
    }

    // Getters e Setters
    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(String dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    // Método
    public abstract void registrar();
}
