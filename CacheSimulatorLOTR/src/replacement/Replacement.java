package replacement;

import java.util.*;

public class Replacement {
    private static final Random random = new Random();

    public static void random(Map<Integer, String> cache) {
        Integer randomTag = cache.keySet().iterator().next();
        cache.remove(randomTag);
    }

    public static void random(ArrayList<String> arrayList) {
        arrayList.remove(random.nextInt(arrayList.size()));
    }

}
