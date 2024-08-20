package mappings;

import abstracts.Mappings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Associative extends Mappings {
    private Map<Integer, String> cache;

    public Associative(ArrayList<String> memoryData, ArrayList<String> dataConfig, String replace) {
        super(memoryData, dataConfig, replace);
        initialize();
    }

    @Override
    protected void initialize() {
        cache = new HashMap<>();
    }

    @Override
    protected void mapping(String[] partAddress) {
        Integer tag = Integer.parseInt(partAddress[0], 2);
        if (!cache.containsKey(tag)) {
            setMiss(getMiss() + 1);
            if (cache.size() >= 16) {
                replacement.Replacement.random(cache);
            }
            cache.put(tag, partAddress[1]);
        } else {
            setHits(getHits() + 1);
        }
    }

    @Override
    protected String[] getPartAddress(Long value) {
        String binaryAddress = utils.Convert.intToBinaryString(value, 16);
        return new String[] { binaryAddress.substring(0, 12), binaryAddress.substring(12) };
    }
}
