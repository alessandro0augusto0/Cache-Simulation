package mappings;

import abstracts.Mappings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SetAssociative extends Mappings {
    private Map<Integer, ArrayList<String>> cache;
    private int setSize = 4;

    public SetAssociative(ArrayList<String> memoryData, ArrayList<String> dataConfig, String replace) {
        super(memoryData, dataConfig, replace);
        initialize();
    }

    @Override
    protected void initialize() {
        cache = new HashMap<>();
    }

    @Override
    protected void mapping(String[] partAddress) {
        int setIndex = Integer.parseInt(partAddress[1], 2) % setSize;
        ArrayList<String> set = cache.getOrDefault(setIndex, new ArrayList<>());
        if (!set.contains(partAddress[0])) {
            setMiss(getMiss() + 1);
            if (set.size() >= setSize) {
                replacement.Replacement.random(set);
            }
            set.add(partAddress[0]);
            cache.put(setIndex, set);
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
