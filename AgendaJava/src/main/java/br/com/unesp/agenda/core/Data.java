package br.com.unesp.agenda.core;

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

        if (!isDataValida(this.dia, mes, this.ano))
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

    @Override
    public String toString() {
        return this.dia + "/" + this.mes + "/" + this.ano;
    }

    public Data adicionarDias(int qtdDias) {
        if (isDataValida(this.dia + qtdDias, this.mes, this.ano))
            return new Data(this.dia + qtdDias, this.mes, this.ano);

        int novoDia = this.dia + qtdDias;
        int novoMes = this.mes;
        int novoAno = this.ano;

        while (novoDia > diasNoMes(novoMes, novoAno)) {
            novoDia -= diasNoMes(novoMes, novoAno);
            novoMes++;
            if (novoMes > 12) {
                novoMes = 1;
                novoAno++;
            }
        }

        while (novoDia < 0) {
            novoMes--;
            if (novoMes < 1) {
                novoMes = 12;
                novoAno--;
            }
            novoDia += diasNoMes(novoMes, novoAno);
        }

        return new Data(novoDia, novoMes, novoAno);
    }

    public int calcularDiasAbsolutos() {
        int totalDias = this.dia;
        for (int m=1; m<this.mes; m++)
            totalDias += diasNoMes(m, this.ano);
        
        for (int a=0; a<this.ano; a++)
            totalDias += isBissexto(a) ? 366 : 365;
        return totalDias;
    }

    public int diasEntre(Data dtSecundaria) {
        int diasDataAtual = this.calcularDiasAbsolutos();
        int diasOutraData = dtSecundaria.calcularDiasAbsolutos();
        return Math.abs(diasOutraData - diasDataAtual) + 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Data outraData = (Data) obj;
        return this.dia == outraData.dia && this.mes == outraData.mes && this.ano == outraData.ano;
    }

    /***
     * Função para comparar duas datas de forma relacional.
     * Retorna 0 para datas iguais
     * Retorna -1 quando a data do parâmetro é maior
     * Retorna 1 quando a data da instância é maior
     * @param rData Data para comparar
     * @return Resultado da comparação
     */
    public int compare(Data rData) {
        // Comparando anos
        if (this.ano > rData.ano)
            return 1;
        if (this.ano < rData.ano)
            return -1;

        // Comparando meses
        if (this.mes > rData.mes)
            return 1;
        if (this.mes < rData.mes)
            return -1;

        // Comparando dias
        if (this.dia > rData.dia)
            return 1;
        if (this.dia < rData.dia)
            return -1;

        return 0;
    }
}
