package trabalhopratico;

import java.util.ArrayList;

public abstract class Cache {

    private int tamanho;
    private int palavra;
    private int palavras_Por_Linha;
    private int numero_De_Linhas;
    private String potencia;
    private int acertos;
    private int falhas;
    private ArrayList<String> vetor_De_Enderecos = new ArrayList<String>();

    public Cache(ArrayList<String> vetor_De_Enderecos) {
        quantidadeDeLinhas();
        this.vetor_De_Enderecos = vetor_De_Enderecos;
    }

    public void quantidadeDeLinhas() {
        String linhaConfig;
        String spliter[] = new String[5];
        ArrayList<String> dadosConfig = FileManager.stringReader("./dados/config2.txt");

        linhaConfig = dadosConfig.get(1);
        spliter = linhaConfig.split(" ");
        setPalavra(Integer.parseInt(spliter[2]));

        linhaConfig = dadosConfig.get(2);
        spliter = linhaConfig.split(" ");
        setTamanho(Integer.parseInt(spliter[2]));

        linhaConfig = dadosConfig.get(3);
        spliter = linhaConfig.split(" ");
        setPalavras_Por_Linha(Integer.parseInt(spliter[2]));

        linhaConfig = dadosConfig.get(2);
        spliter = linhaConfig.split(" ");
        setPotencia(spliter[3]);

        switch (getPotencia()) {
            case "B;":
                setNumero_De_Linhas((int) ((tamanho * Math.pow(2, 0)) / (palavras_Por_Linha * palavra)));
                break;
            case "KB;":
                setNumero_De_Linhas((int) ((tamanho * Math.pow(2, 10)) / (palavras_Por_Linha * palavra)));
                break;
            case "MB;":
                setNumero_De_Linhas((int) ((tamanho * Math.pow(2, 20)) / (palavras_Por_Linha * palavra)));
                break;
            case "GB;":
                setNumero_De_Linhas((int) ((tamanho * Math.pow(2, 30)) / (palavras_Por_Linha * palavra)));
                break;
        }
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getPalavra() {
        return palavra;
    }

    public void setPalavra(int palavra) {
        this.palavra = palavra;
    }

    public int getNumero_De_Linhas() {
        return numero_De_Linhas;
    }

    public void setNumero_De_Linhas(int numero_De_Linhas) {
        this.numero_De_Linhas = numero_De_Linhas;
    }

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }

    public int getPalavras_Por_Linha() {
        return palavras_Por_Linha;
    }

    public void setPalavras_Por_Linha(int palavras_Por_Linha) {
        this.palavras_Por_Linha = palavras_Por_Linha;
    }

    public int getAcertos() {
        return acertos;
    }

    public void setAcertos(int acertos) {
        this.acertos = acertos;
    }

    public int getFalhas() {
        return falhas;
    }

    public void setFalhas(int falhas) {
        this.falhas = falhas;
    }

    public ArrayList<String> getVetor_De_Enderecos() {
        return vetor_De_Enderecos;
    }

    public void setVetor_De_Enderecos(ArrayList<String> vetor_De_Enderecos) {
        this.vetor_De_Enderecos = vetor_De_Enderecos;
    }
}
