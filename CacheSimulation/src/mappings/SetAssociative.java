package mappings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import abstracts.Mappings;
import replacement.Replacement;
import utils.Convert;

public class SetAssociative extends Mappings {

    private Map<Integer, ArrayList<Integer>> cache;
    private Map<Integer, ArrayList<Integer>> auxCache;
    private ArrayList<String> listAuxCache;
    private int blockConfig;

    public SetAssociative(ArrayList<String> memoryData, ArrayList<String> dataConfig, String replace) {
        super(memoryData, dataConfig, replace);
    }

    @Override
    protected void initialize() {
        String[] memoryConfig = getDataConfig().get(0).split("[ #@_\\/.*;]");
        String[] wordConfig = getDataConfig().get(1).split("[ #@_\\/.*;]");
        String[] cacheConfig = getDataConfig().get(2).split("[ #@_\\/.*;]");
        String[] lineConfig = getDataConfig().get(3).split("[ #@_\\/.*;]");
        String[] blockConfig = getDataConfig().get(4).split("[ #@_\\/.*;]");

        Long memoryBytes = convertToBits(Long.parseLong(memoryConfig[2]), memoryConfig[3]);
        Long wordBytes = convertToBits(Long.parseLong(wordConfig[2]), wordConfig[3]);
        Long cacheBytes = convertToBits(Long.parseLong(cacheConfig[2]), cacheConfig[3]);

        setCache(new HashMap<Integer, ArrayList<Integer>>());
        setLimitCache(convertLineToDecimalBits(cacheBytes, wordBytes, Integer.parseInt(lineConfig[2])));

        setBlockConfig(getLimitCache().intValue() / Integer.parseInt(blockConfig[2]));
        setAddressBits(calcAddress(memoryBytes, wordBytes));
        setWordBits(calcWord(wordBytes));
        setLineBits(calcLine());
        setTagBits(calcTag(getAddressBits(), getWordBits(), getLineBits()));

        readMemory();
    }

    @Override
    protected void mapping(String[] partAddress) {
        int tag = Integer.parseInt(partAddress[0], 2);
        int line = (partAddress[1] == "") ? 0 : Integer.parseInt(partAddress[1], 2);
        if (!(getCache().get(line) != null && getCache().get(line).contains(tag))) {
            setMiss(getMiss() + 1);

            if (getCache().get(line) == null)
                getCache().put(line, new ArrayList<Integer>());

            if (getCache().get(line).size() >= getLimitCache() / getBlockConfig()) {
                switch (getReplace()) {
                    case "LFU":
                        break;
                    case "FIFO":
                        Replacement.fifo(getCache().get(line));
                        break;
                    case "LRU":
                        Replacement.lru(getCache().get(line), false, tag);
                        break;
                    case "RANDOM":
                        Replacement.random(getCache().get(line));
                        break;
                }
            }

            getCache().get(line).add(tag);

        } else {
            setHits(getHits() + 1);
            if (getReplace() == "LRU") {
                Replacement.lru(getCache().get(line), true, tag);
            }
        }
    }

    @Override
    protected String[] getPartAddress(Long value) {
        String[] partAddress = new String[3];
        String addressBinary = Convert.intToBinaryString(value, getAddressBits());
        partAddress[0] = addressBinary.substring(0, getTagBits().intValue());
        partAddress[1] = addressBinary.substring(getTagBits().intValue(), getLineBits() + getTagBits().intValue());
        partAddress[2] = addressBinary.substring(getLineBits() + getTagBits().intValue(), getAddressBits().intValue());
        return partAddress;
    }

    @Override
    public String toString() {
        String mapping = "Mapping: Set Associative\n";
        return mapping + super.toString();
    }

    @Override
    protected Integer calcLine() {
        return (int) ((Math.log(getBlockConfig()) / Math.log(2)));
    }

    public Map<Integer, ArrayList<Integer>> getCache() {
        return cache;
    }

    public void setCache(Map<Integer, ArrayList<Integer>> cache) {
        this.cache = cache;
    }

    public Map<Integer, ArrayList<Integer>> getAuxCache() {
        return auxCache;
    }

    public void setAuxCache(Map<Integer, ArrayList<Integer>> auxCache) {
        this.auxCache = auxCache;
    }

    public ArrayList<String> getListAuxCache() {
        return listAuxCache;
    }

    public void setListAuxCache(ArrayList<String> listAuxCache) {
        this.listAuxCache = listAuxCache;
    }

    public int getBlockConfig() {
        return blockConfig;
    }

    public void setBlockConfig(int blockConfig) {
        this.blockConfig = blockConfig;
    }
}
