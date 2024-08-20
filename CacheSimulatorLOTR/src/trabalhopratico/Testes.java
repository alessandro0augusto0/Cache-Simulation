package trabalhopratico;

import java.util.ArrayList;
import utils.Convert;

public class Testes {

    public static void main(String[] args) {

        ArrayList<String> arquivo = FileManager.stringReader("./dados/teste_LFU");
        ArrayList<String> enderecos = new ArrayList<String>();
        for (String linha : arquivo) {
            int acesso = Integer.parseInt(linha);
            String stringBin = Convert.intToBinaryString((long) acesso, 15);
            enderecos.add(stringBin);
        }

        AssociativoLFU cache = new AssociativoLFU(enderecos);
        System.out.println(cache.linhas);
    }

}
