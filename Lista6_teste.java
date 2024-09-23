import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.EOFException;
import java.io.FileNotFoundException;

class ArquivoTextoLeitura {

    public BufferedReader entrada;

    ArquivoTextoLeitura(String nomeArquivo) {
        try {
            entrada = new BufferedReader(new FileReader(nomeArquivo));
        } catch (FileNotFoundException excecao) {
            System.out.println("Arquivo nao encontrado");
        }
    }

    public void fecharArquivo() {
        try {
            entrada.close();
        } catch (IOException excecao) {
            System.out.println("Erro no fechamento do arquivo de leitura: " + excecao);
        }
    }

    @SuppressWarnings("finally")
    public String ler() {
        String textoEntrada = null;

        try {
            textoEntrada = entrada.readLine();
        } catch (EOFException excecao) {
            textoEntrada = null;
        } catch (IOException excecao) {
            System.out.println("Erro de leitura: " + excecao);
            textoEntrada = null;
        } finally {
            return textoEntrada;
        }
    }
}

class Livro {

    private long ISBN;
    private String titulo;
    private String autor_principal;
    private String segundo_autor;
    private String categoria;
    private String descricao;
    private int ano_publicacao;
    private int quantidade_paginas;
    private float nota_avaliacao;
    private int quantidade_avaliacoes;
    private float nota_media;

    void setNota_media(float x) {
        this.nota_media = x;
    }

    void setISBN(long x) {
        this.ISBN = x;
    }

    void setTitulo(String x) {
        this.titulo = x;
    }

    void setAutor_principal(String x) {
        this.autor_principal = x;
    }

    void setSegundo_autor(String x) {
        this.segundo_autor = x;
    }

    void setCategoria(String x) {
        this.categoria = x;
    }

    void setDescricao(String x) {
        this.descricao = x;
    }

    void setAno_publicacao(int x) {
        this.ano_publicacao = x;
    }

    void setQuantidade_paginas(int x) {
        this.quantidade_paginas = x;
    }

    void setNota_avaliacao(float x) {
        this.nota_avaliacao = x;
    }

    void setQuantidade_avaliacoes(int x) {
        this.quantidade_avaliacoes = x;
    }

    long getISBN() {
        return this.ISBN;
    }

    String getTitulo() {
        return this.titulo;
    }

    String getAutor_principal() {
        return this.autor_principal;
    }

    String getSegundo_autor() {
        return this.segundo_autor;
    }

    String getCategoria() {
        return this.categoria;
    }

    String getDescricao() {
        return this.descricao;
    }

    int getAno_publicacao() {
        return this.ano_publicacao;
    }

    int getQuantidade_paginas() {
        return this.quantidade_paginas;
    }

    float getNota_avaliacao() {
        return this.nota_avaliacao;
    }

    int getQuantidade_avaliacoes() {
        return this.quantidade_avaliacoes;
    }

    float getNota_media() {
        return this.nota_media;
    }

    @Override
    public String toString() {
        if (this.getSegundo_autor().equals("")) {
            return this.getAutor_principal() + ". " + this.getTitulo() + ". " + this.getAno_publicacao() + ". ISBN: " + this.getISBN() + ".";
        } else {
            return this.getAutor_principal() + ", " + this.getSegundo_autor() + ". " + this.getTitulo() + ". " + this.getAno_publicacao() + ". ISBN: " + this.getISBN() + ".";
        }
    }

    public void imprimir() {
        System.out.println(toString());
    }

    public Livro clone() {
        return new Livro(this.ISBN, this.titulo, this.autor_principal, this.segundo_autor, this.categoria,
                this.descricao, this.ano_publicacao,
                this.quantidade_paginas, this.nota_avaliacao, this.quantidade_avaliacoes);
    }

    Livro() {
    }

    Livro(long isbn, String titulo, String autor_principal, String segundo_autor, String categoria,
            String descricao, int ano_publicacao, int quantidade_paginas, float nota_avaliacao,
            int quantidade_avaliacoes) {
        this.ISBN = isbn;
        this.titulo = titulo;
        this.autor_principal = autor_principal;
        this.segundo_autor = segundo_autor;
        this.categoria = categoria;
        this.descricao = descricao;
        this.ano_publicacao = ano_publicacao;
        this.quantidade_paginas = quantidade_paginas;
        this.nota_avaliacao = nota_avaliacao;
        this.quantidade_avaliacoes = quantidade_avaliacoes;
    }

    void ler(String linha) {
        String[] info = linha.split("\\|");
        this.setISBN(Long.parseLong(info[0]));
        this.setTitulo(info[1]);
        this.setAutor_principal(info[2]);
        this.setSegundo_autor(info[3]);
        this.setCategoria(info[4]);
        this.setDescricao(info[5]);
        this.setAno_publicacao(Integer.parseInt(info[6]));
        this.setQuantidade_paginas(Integer.parseInt(info[7]));
        this.setNota_avaliacao(Float.parseFloat(info[8]));
        this.setQuantidade_avaliacoes(Integer.parseInt(info[9]));
    }
}

class Ordenacoes {

    void swap(ArrayList<Livro> vetor, int i, int j) {
        Livro temp = vetor.get(i);
        vetor.set(i, vetor.get(j));
        vetor.set(j, temp);
    }

    ArrayList<Livro> selection(ArrayList<Livro> vetor) {
        for (int i = 0; i < (vetor.size() - 1); i++) {
            int menor = i;
            for (int j = i + 1; j < vetor.size(); j++) {
                if (vetor.get(menor).getCategoria().equals(vetor.get(j).getCategoria())) {
                    if (vetor.get(menor).getNota_media() == vetor.get(j).getNota_media()) {
                        if (vetor.get(menor).getQuantidade_avaliacoes() > vetor.get(j).getQuantidade_avaliacoes()) {
                            menor = j;
                        }
                    } else if (vetor.get(menor).getNota_media() < vetor.get(j).getNota_media()) {
                        menor = j;
                    }
                } else if (vetor.get(menor).getCategoria().compareTo(vetor.get(j).getCategoria()) > 0) {
                    menor = j;
                }
            }
            swap(vetor, menor, i);
        }
        return vetor;
    }

    ArrayList<Livro> bubble(ArrayList<Livro> vetor) {
        for (int i = vetor.size(); i > 0; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (vetor.get(j).getCategoria().equals(vetor.get(j + 1).getCategoria())) {
                    if (vetor.get(j).getNota_media() == vetor.get(j + 1).getNota_media()) {
                        if (vetor.get(j).getQuantidade_avaliacoes() > vetor.get(j + 1).getQuantidade_avaliacoes()) {
                            swap(vetor, j, j + 1);
                        }
                    } else if (vetor.get(j).getNota_media() < vetor.get(j + 1).getNota_media()) {
                        swap(vetor, j, j + 1);
                    }
                } else if (vetor.get(j).getCategoria().compareTo(vetor.get(j + 1).getCategoria()) > 0) {
                    swap(vetor, j, j + 1);
                }
            }
        }
        return vetor;
    }

    ArrayList<Livro> insertion(ArrayList<Livro> vetor) {
        for (int i = 1; i < vetor.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (vetor.get(j).getCategoria().equals(vetor.get(j - 1).getCategoria())) {
                    if (vetor.get(j).getNota_media() == vetor.get(j - 1).getNota_media()) {
                        if (vetor.get(j).getQuantidade_avaliacoes()