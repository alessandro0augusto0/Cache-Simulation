package trabalhopratico;

import java.util.ArrayList;
import java.lang.Math;

public class CacheMapeamentoDireto extends Cache {

	private String[] vetor_De_Linhas;

	public CacheMapeamentoDireto(ArrayList<String> vetor_Dos_Enderecos) {
		super(vetor_Dos_Enderecos);
		vetor_De_Linhas = new String[getNumero_De_Linhas()];
		for (int i = 0; i < getNumero_De_Linhas(); i++) {
			vetor_De_Linhas[i] = "";
		}

		executarBuscas(getVetor_De_Enderecos());
	}

	void imprimirEnderecos() {
		for (String linha : getVetor_De_Enderecos()) {
			System.out.println(linha);
		}
	}

	int linhaCache(String endereco_Bin) {
		int i;
		int linha = 0;
		String linha_Em_Bin = endereco_Bin.substring(5, 13);

		for (i = 0; i <= 7; i++) {
			if (linha_Em_Bin.substring(i, i + 1).equals("1")) {
				linha += Math.pow(2, 7 - i);
			}
		}
		return linha;
	}

	void buscarEndereco(String endereco, int linha_Da_Cache) {
		if (endereco.substring(0, 5).equals(vetor_De_Linhas[linha_Da_Cache])) {
			setAcertos(getAcertos() + 1);
		} else {
			setFalhas(getFalhas() + 1);
			vetor_De_Linhas[linha_Da_Cache] = endereco.substring(0, 5);
		}
	}

	void executarBuscas(ArrayList<String> vetor_De_Enderecos) {
		int linha;

		for (String endereco : vetor_De_Enderecos) {
			linha = linhaCache(endereco);
			buscarEndereco(endereco, linha);
		}

		System.out.println("Acertos: " + getAcertos());
		System.out.println("Falhas: " + getFalhas());
	}

	public String[] getVetor_De_Linhas() {
		return vetor_De_Linhas;
	}

	public void setVetor_De_Linhas(String[] vetor_De_Linhas) {
		this.vetor_De_Linhas = vetor_De_Linhas;
	}
}
