package br.com.unesp.agenda.util;

public class Data {
    private int dia;
    private int mes;
    private int ano;

    public Data(int dia, int mes, int ano) {
        if (!isDataValida(dia, mes, ano)) {
            this.ano = 1900;
            this.mes = 1;
            this.dia = 1;
            return;
        }

        this.ano = ano;
        this.dia = dia;
        this.mes = mes;
    }

    public Data() {
        this.ano = 1900;
        this.mes = 1;
        this.dia = 1;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        if (!isDataValida(dia, this.mes, this.ano))
            throw new IllegalArgumentException("Dia inválido para o mês/ano atual.");

        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        if (!(mes >= 1 && mes <= 12))
            throw new IllegalArgumentException("Mês inválido!");

        if (!isDataValida(this.dia, mes, this.dia))
            throw new IllegalArgumentException("Mês inválido para o dia/ano atual");

        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        if (ano <= 0)
            throw new IllegalArgumentException("Ano inválido!");

        if (!isDataValida(this.dia, this.mes, ano))
            throw new IllegalArgumentException("Ano inválido para o dia atual.");

        this.ano = ano;
    }

    public static boolean isBissexto(int a) {
        return (a % 4 == 0 && a % 100 != 0) || (a % 400 == 0);
    }

    public boolean isBissexto() {
        return (this.ano % 4 == 0 && this.ano % 100 != 0) || (this.ano % 400 == 0);
    }

    public static int diasNoMes(int m, int a) {
        return switch (m) {
            case 4, 6, 9, 11 -> 30;
            case 2 -> isBissexto(a) ? 29 : 28;
            default -> 31;
        };
    }

    public static boolean isDataValida(int d, int m, int a) {
        if (a < 1 || m < 1 || m > 12) return false;
        return (d >= 1) && (d <= diasNoMes(m, a));
    }
}
