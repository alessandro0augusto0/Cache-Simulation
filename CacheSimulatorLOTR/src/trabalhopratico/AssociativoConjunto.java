package trabalhopratico;

import java.util.ArrayList;
import java.util.LinkedList;

public class AssociativoConjunto extends Cache {

	private int numero_De_Conjuntos = 0;
	private int linhas_Por_Conjunto = 4;
	LinkedList<String>[] conjuntos;

	public AssociativoConjunto(ArrayList<String> enderecos) {
		super(enderecos);
		numero_De_Conjuntos = getNumero_De_Linhas() / 4;
		conjuntos = new LinkedList[numero_De_Conjuntos];
		for (int i = 0; i < conjuntos.length; i++) {
			conjuntos[i] = new LinkedList<String>();
		}
		executarBuscas(enderecos);
	}

	void buscarEndereco(String endereco, int conjunto_Da_Cache) {
		if (conjuntos[conjunto_Da_Cache].contains(endereco)) {
			setAcertos(getAcertos() + 1);
		} else {
			setFalhas(getFalhas() + 1);
			if (conjuntos[conjunto_Da_Cache].size() < linhas_Por_Conjunto) {
				conjuntos[conjunto_Da_Cache].add(endereco);
			} else {
				conjuntos[conjunto_Da_Cache].remove(conjuntos[conjunto_Da_Cache].getFirst());
				conjuntos[conjunto_Da_Cache].add(endereco);
			}
		}
	}

	int conjuntoCache(String endereco_Bin) {
		int i;
		int conjunto = 0;
		String linha_Em_Bin = endereco_Bin.substring(10, 13);
		for (i = 0; i <= 2; i++) {
			if (linha_Em_Bin.substring(i, i + 1).equals("1")) {
				conjunto += Math.pow(2, 2 - i);
			}
		}
		return conjunto;
	}

	void executarBuscas(ArrayList<String> vetor_De_Enderecos) {
		int conjunto;
		for (String endereco : vetor_De_Enderecos) {
			conjunto = conjuntoCache(endereco);
			buscarEndereco(endereco.substring(0, 10), conjunto);
		}
		System.out.println("Acertos: " + getAcertos());
		System.out.println("Falhas: " + getFalhas());
	}

	void mostar_Linhas_Por_Conjunto() {
		int i;
		for (i = 0; i < numero_De_Conjuntos; i++) {
			System.out.println(conjuntos[i]);
		}
	}

	public int getNumero_De_Conjuntos() {
		return numero_De_Conjuntos;
	}

	public void setNumero_De_Conjuntos(int numero_De_Conjuntos) {
		this.numero_De_Conjuntos = numero_De_Conjuntos;
	}

	public int getLinhas_Por_Conjunto() {
		return linhas_Por_Conjunto;
	}

	public void setLinhas_Por_Conjunto(int linhas_Por_Conjunto) {
		this.linhas_Por_Conjunto = linhas_Por_Conjunto;
	}

	public LinkedList<String>[] getConjuntos() {
		return conjuntos;
	}

	public void setConjuntos(LinkedList<String>[] conjuntos) {
		this.conjuntos = conjuntos;
	}
}
