import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
public class NFU {
    private Map<Integer, Integer> counters;
    private ArrayDeque<Integer> pages;
    private Set<Integer> inMemory;
    private int capacity;
    public static void main(String[] args) {
        NFU nfu = new NFU(3);
        int[] referenceString = {1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5};
        System.out.println("Paso | Página | Memoria            | Contadores          | Reemplazo");
        System.out.println("-----|--------|--------------------|---------------------|----------");
        int step = 1;
        for (int page : referenceString) {
            String replacedPage = nfu.accessPage(page);
            nfu.printState(step, page, replacedPage);
            step++;
        }
    }
    public NFU(int capacity) {
        this.capacity = capacity;
        counters = new HashMap<>();
        pages = new ArrayDeque<>();
        inMemory = new HashSet<>();
    }
    public String accessPage(int page) {
        counters.putIfAbsent(page, 0);
        String replacedPage = "Ninguno";
        if (!inMemory.contains(page)) {
            if (pages.size() == capacity) {
                // Reemplazar la página menos frecuentemente utilizada
                int victim = getVictim();
                pages.remove(victim);
                inMemory.remove(victim);
                counters.remove(victim);
                replacedPage = String.valueOf(victim);
            }
            pages.offer(page);
            inMemory.add(page);
        }
        // Incrementar el contador de la página
        counters.put(page, counters.get(page) + 1);

        return replacedPage;
    }
    public int getVictim() {
        int min = Integer.MAX_VALUE;
        int victim = -1;
        for (int page : pages) {
            if (counters.get(page) < min) {
                min = counters.get(page);
                victim = page;
            }
        }
        return victim;
    }
    public void printState(int step, int page, String replacedPage) {
        System.out.printf("%-5d| %-7d| %-19s| %-20s| %-10s%n",
                step,
                page,
                pages.toString(),
                counters.toString(),
                replacedPage);
    }
}