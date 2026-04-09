package br.com.unesp.agenda.core;

public class Compromisso {

    private String nome;
    private String descricao;
    private Data dataInicio;
    private Data dataFim;

    public Compromisso(String nome, String descricao, Data dataInicio, Data dataFim) {
        this.nome = nome;
        this.descricao = descricao;

        if (dataInicio.compare(dataFim) == 1) {
            this.dataInicio = dataFim;
            this.dataFim = dataInicio;
            return;
        }

        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Compromisso(String nome, String descricao, Data data) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = data;
        this.dataFim = data;
    }

    public Data getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Data dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Data getDataFim() {
        return dataFim;
    }

    public void setDataFim(Data dataFim) {
        this.dataFim = dataFim;
    }

    public int getDuracao() {
        return dataInicio.diasEntre(dataFim);
    }

    @Override
    public String toString() {
        return this.nome + " [" + this.dataInicio.toString() + " - " + this.dataFim.toString() + "] (Duração de "
                + this.getDuracao() + " dia" + (this.getDuracao() > 1 ? "s" : "") + ")\n" + this.descricao + "\n";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
