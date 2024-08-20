package trabalhopratico;

import java.util.ArrayList;
import java.util.HashMap;

public class AssociativoLFU extends Cache {

	HashMap<String, Integer> linhas = new HashMap<String, Integer>();

	public AssociativoLFU(ArrayList<String> enderecos) {
		super(enderecos);
		executarBuscas(getVetor_De_Enderecos());
	}

	void buscarEndereco(String endereco) {
		int contador;
		Integer menosUsado = null;
		String lfu = "";
		if (linhas.containsKey(endereco)) {
			setAcertos(getAcertos() + 1);
			contador = linhas.get(endereco);
			++contador;
			linhas.replace(endereco, contador);
		} else {
			setFalhas(getFalhas() + 1);
			if (linhas.size() < getNumero_De_Linhas()) {
				linhas.put(endereco, 0);
			} else {
				for (String i : linhas.keySet()) {
					if (menosUsado == null) {
						menosUsado = linhas.get(i);
					}
					if (linhas.get(i) <= menosUsado) {
						menosUsado = linhas.get(i);
						lfu = i;
					}
				}
				linhas.remove(lfu);
				linhas.put(endereco, 0);
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

	public HashMap<String, Integer> getLinhas() {
		return linhas;
	}

	public void setLinhas(HashMap<String, Integer> linhas) {
		this.linhas = linhas;
	}
}
