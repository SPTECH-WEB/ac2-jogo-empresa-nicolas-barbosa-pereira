package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.JogoInvalidoException;
import school.sptech.exception.JogoNaoEncontradoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private String nome;
    private List<Jogo> jogos;



    public Empresa(String nome, List<Jogo> jogos) {
        this.nome = nome;
        this.jogos = (jogos != null) ? jogos : new ArrayList<>();
    }


    public Empresa() {
        this.jogos=new ArrayList<>();
    }


    public void adicionarJogo(Jogo jogo) throws JogoInvalidoException, ArgumentoInvalidoException {
        if (jogo == null) {
            throw new JogoInvalidoException("jogo nulo");

        }

        if (jogo.getCodigo() == null || jogo.getCodigo().isEmpty() ||
                jogo.getNome() == null || jogo.getNome().isEmpty() ||
                jogo.getGenero() == null || jogo.getGenero().isEmpty() ||
                jogo.getPreco() == null || jogo.getPreco() <= 0) {
            throw new JogoInvalidoException("jogo invalido");
        }

        if (jogo.getAvaliacao() == null || jogo.getAvaliacao() < 0 || jogo.getAvaliacao() > 5) {
            throw new JogoInvalidoException("avaliacao invalida");
        }

        if (jogo.getDataLancamento() == null || jogo.getDataLancamento().isAfter(LocalDate.now())) {
            throw new JogoInvalidoException("data invalida");
        }

        this.jogos.add(jogo);
    }



    public Jogo buscarJogoPorCodigo(String codigo) throws ArgumentoInvalidoException, JogoNaoEncontradoException{

        if(codigo ==null || codigo.isBlank() ){
            throw new ArgumentoInvalidoException("invalido o codigo");
        }
        for (Jogo jogo : jogos) {
            if (jogo.getCodigo().equals(codigo)) {
                return jogo;

            }

        }
        throw new JogoNaoEncontradoException("Jogo não encontrado");
    }


    public void removerJogoPorCodigo(String codigo)
            throws ArgumentoInvalidoException, JogoNaoEncontradoException {

        if (codigo == null || codigo.isBlank()) {
            throw new ArgumentoInvalidoException("codigo invalido");
        }

        boolean achou = false;

        for (int i = 0; i < jogos.size(); i++) {
            if (jogos.get(i).getCodigo().equals(codigo)) {
                jogos.remove(i);
                achou = true;
                break;
            }
        }

        if (!achou) {
            throw new JogoNaoEncontradoException("Jogo não encontrado");
        }
    }



    public Jogo buscarJogoComMelhorAvaliacao() throws JogoNaoEncontradoException {
        if (jogos.isEmpty()) {
            throw new JogoNaoEncontradoException("lista vazia");
        }

        int index = 0;
        for (int i = 1; i < jogos.size(); i++) {
            Jogo atual = jogos.get(i);
            Jogo melhor = jogos.get(index);

            if (atual.getAvaliacao() > melhor.getAvaliacao()) {
                index = i;
            } else if (atual.getAvaliacao().equals(melhor.getAvaliacao())) {
                if (atual.getDataLancamento().isAfter(melhor.getDataLancamento())) {
                    index = i;
                }
            }
        }
        return jogos.get(index);
    }



    public List<Jogo> buscarJogoPorPeriodo(LocalDate dataInicio, LocalDate dataFim)
            throws ArgumentoInvalidoException {

        if (dataInicio == null || dataFim == null) {
            throw new ArgumentoInvalidoException("datas invalidas");
        }

        if (dataInicio.isAfter(dataFim)) {
            throw new ArgumentoInvalidoException("periodo invalido");
        }

        List<Jogo> dentro = new ArrayList<>();

        for (Jogo j : jogos) {
            LocalDate lancamento = j.getDataLancamento();

            if ((lancamento.isAfter(dataInicio) || lancamento.isEqual(dataInicio)) &&
                    (lancamento.isBefore(dataFim) || lancamento.isEqual(dataFim))) {
                dentro.add(j);
            }
        }

        return dentro;
    }



















    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }
}
