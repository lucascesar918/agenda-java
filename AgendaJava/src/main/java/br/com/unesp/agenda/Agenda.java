package br.com.unesp.agenda;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.unesp.agenda.core.Compromisso;
import br.com.unesp.agenda.core.Data;

public class Agenda {
    private List<Compromisso> compromissos;

    public Agenda() {
        this.compromissos = new ArrayList<>();
    }

    public void addCompromisso(Compromisso c) {
        this.compromissos.add(c);
    }

    public List<Compromisso> listarTodos() {
        return this.compromissos;
    }

    public List<Compromisso> buscarCompromissosDoDia(Data dataAlvo) {
        List<Compromisso> encontrados = new ArrayList<>();
        int diasAlvo = dataAlvo.calcularDiasAbsolutos();

        for (Compromisso c : this.compromissos) {
            int diasInicio = c.getDataInicio().calcularDiasAbsolutos();
            int diasFim = c.getDataFim().calcularDiasAbsolutos();

            if (diasAlvo >= diasInicio && diasAlvo <= diasFim)
                encontrados.add(c);
        }

        return encontrados;
    }

    public void printarCompromissosDoDia(Data data) {
        System.out.println("Buscando compromissos na data " + data.toString() + "...");
        List<Compromisso> encontrados = this.buscarCompromissosDoDia(data);

        System.out.println(encontrados == null ? "Nenhum compromisso encontrado!" : "Encontrados " + encontrados.size() + " compromissos!");
            for (Compromisso c : encontrados)
                System.out.println(c.toString());
    }

    public void printarCompromissos() {
        System.out.println("Listando compromissos...");
        for (Compromisso c : this.listarTodos())
            System.out.println(c.toString());
    }

    public static void main(String[] args) {
        Data dtInicial = new Data(3,8,2004);
        Agenda a = new Agenda();
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Data inicial: " + dtInicial.toString());
            System.out.print("Quantos dias adicionar? ");
            int diasAdd = Integer.parseInt(sc.nextLine());
            System.out.println("Nova data: " + dtInicial.adicionarDias(diasAdd).toString());

            Compromisso comp1 = new Compromisso(
                "SECOMPP",
                "Semana da Computação do Curso de Ciência da Computação da FCT Unesp Prudente",
            new Data(27,9,2025),
            new Data(3,10,2025));
            Compromisso comp2 = new Compromisso(
                "Aaron Swartz Day",
                "Palestras do Instituto Aaron Swartz na FCT Unesp Prudente",
                new Data(8,11,2025),
                new Data(9,11,2025)
            );
            Compromisso comp3 = new Compromisso(
                "Bully",
                "Lançamento do Álbum Bully do Kanye West",
                new Data(28,3,2026)
            );
            Compromisso comp4 = new Compromisso(
                "Lançamento de álbum",
                "Lançamento do melhor álbum do ano",
                new Data(28,3,2026)
            );

            a.addCompromisso(comp1);
            a.addCompromisso(comp2);
            a.addCompromisso(comp3);
            a.addCompromisso(comp4);

            System.out.printf("Há %d dias de distâncias entre o dia %s e o evento %s\n\n",
            dtInicial.diasEntre(comp1.getDataInicio()),
            dtInicial.toString(),
            comp1.getNome());

            a.printarCompromissos();

            a.printarCompromissosDoDia(comp4.getDataInicio());
            a.printarCompromissosDoDia(new Data());

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
