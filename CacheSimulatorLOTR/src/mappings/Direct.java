package mappings;

import abstracts.Mappings;
import utils.Convert;
import java.util.ArrayList;

public class Direct extends Mappings {
    private String[] cache;

    public Direct(ArrayList<String> memoryData, ArrayList<String> dataConfig) {
        super(memoryData, dataConfig, null);
        initialize();
    }

    @Override
    protected void initialize() {
        int cacheSize = getCacheSize();
        cache = new String[cacheSize];
    }

    @Override
    protected void mapping(String[] partAddress) {
        int lineIndex = getLineIndex(partAddress[1]);
        if (lineIndex >= cache.length) {
            System.err.println("Erro: índice de linha fora dos limites do cache");
            return;
        }
        if (cache[lineIndex] == null || !cache[lineIndex].equals(partAddress[0])) {
            setMiss(getMiss() + 1);
            cache[lineIndex] = partAddress[0];
        } else {
            setHits(getHits() + 1);
        }
    }

    @Override
    protected String[] getPartAddress(Long value) {
        String binaryAddress = Convert.intToBinaryString(value, 32);
        return new String[] { binaryAddress.substring(0, binaryAddress.length() - getLineIndexBits()),
                binaryAddress.substring(binaryAddress.length() - getLineIndexBits()) };
    }

    private int getCacheSize() {
        int cacheSize = (int) Math.pow(2, Integer.parseInt(getDataConfig().get(3).split(" ")[2].replace("pal", "")));
        return cacheSize;
    }

    private int getLineIndex(String lineBits) {
        return Integer.parseInt(lineBits, 2);
    }

    private int getLineIndexBits() {
        return (int) (Math.log(cache.length) / Math.log(2));
    }
}
