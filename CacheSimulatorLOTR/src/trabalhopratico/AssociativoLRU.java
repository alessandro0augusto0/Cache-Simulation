package trabalhopratico;

import java.util.LinkedList;
import java.util.ArrayList;

public class AssociativoLRU extends Cache {

	public LinkedList<String> linhas = new LinkedList<String>();

	public AssociativoLRU(ArrayList<String> enderecos) {
		super(enderecos);
		executarBuscas(getVetor_De_Enderecos());
	}

	void buscarEndereco(String endereco) {
		if (linhas.contains(endereco)) {
			setAcertos(getAcertos() + 1);
			linhas.remove(endereco);
			linhas.add(endereco);
		} else {
			setFalhas(getFalhas() + 1);
			if (linhas.size() < getNumero_De_Linhas()) {
				linhas.add(endereco);
			} else {
				linhas.remove(linhas.getFirst());
				linhas.add(endereco);
			}
		}
	}

	void executarBuscas(ArrayList<String> vetor_De_Enderecos) {
		for (String endereco : vetor_De_Enderecos) {
			buscarEndereco(endereco.substring(0, 13));
		}
		System.out.println("Acertos: " + getAcertos());
		System.out.println("Falhas: " + getFalhas());
	}

	public LinkedList<String> getLinhas() {
		return linhas;
	}

	public void setLinhas(LinkedList<String> linhas) {
		this.linhas = linhas;
	}
}
