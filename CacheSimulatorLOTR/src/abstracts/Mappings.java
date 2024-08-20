package abstracts;

import java.util.ArrayList;

public abstract class Mappings {
    private ArrayList<String> memoryData;
    private ArrayList<String> dataConfig;
    private String replace;
    private int hits;
    private int miss;

    public Mappings(ArrayList<String> memoryData, ArrayList<String> dataConfig, String replace) {
        this.memoryData = memoryData;
        this.dataConfig = dataConfig;
        this.replace = replace;
        this.hits = 0;
        this.miss = 0;
    }

    protected abstract void initialize();

    protected abstract void mapping(String[] partAddress);

    protected abstract String[] getPartAddress(Long value);

    public void readMemory() {
        for (String address : memoryData) {
            mapping(getPartAddress(Long.parseLong(address)));
        }
    }

    public ArrayList<String> getMemoryData() {
        return memoryData;
    }

    public ArrayList<String> getDataConfig() {
        return dataConfig;
    }

    public String getReplace() {
        return replace;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getMiss() {
        return miss;
    }

    public void setMiss(int miss) {
        this.miss = miss;
    }
}
