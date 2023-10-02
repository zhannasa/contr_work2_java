import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;

class Toy {
    private String id;
    private String name;
    private int frequency;

    public Toy(String id, String name, int frequency) {
        this.id = id;
        this.name = name;
        this.frequency = frequency;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFrequency() {
        return frequency;
    }
}

class ToyStore {
    public static void main(String[] args) {
        String[] toyIds = {"1", "2", "3"};
        String[] toyNames = {"Constructor", "Doll", "Car"};
        int[] toyFrequencies = {5, 3, 2};

        ToyStore toyStore = new ToyStore(toyIds, toyNames, toyFrequencies);
        toyStore.runToyDrawing(10);
    }

    private String[] toyIds;
    private String[] toyNames;
    private int[] toyFrequencies;

    public ToyStore(String[] toyIds, String[] toyNames, int[] toyFrequencies) {
        this.toyIds = toyIds;
        this.toyNames = toyNames;
        this.toyFrequencies = toyFrequencies;
    }

    public void runToyDrawing(int numDraws) {
        PriorityQueue<Toy> toyQueue = new PriorityQueue<>((t1, t2) -> t2.getFrequency() - t1.getFrequency());

        for (int i = 0; i < toyIds.length; i++) {
            Toy toy = new Toy(toyIds[i], toyNames[i], toyFrequencies[i]);
            for (int j = 0; j < toyFrequencies[i]; j++) {
                toyQueue.add(toy);
            }
        }

        try (FileWriter fileWriter = new FileWriter("output.txt")) {
            for (int i = 0; i < numDraws; i++) {
                Toy toy = toyQueue.poll();
                if (toy != null) {
                    fileWriter.write("Toy: " + toy.getName() + "\n");
                }
            }
            System.out.println("The results are written in file 'output.txt'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
