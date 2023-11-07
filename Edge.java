package src.TURU;

public class Edge {
    // Field yang menyimpan bobot dari tepi ini
    int weight;

    // Field yang menyimpan Edge berikutnya dalam daftar Edge
    Edge next;

    // Field yang menyimpan vertex tujuan yang terhubung dengan tepi ini
    Vertex goal;

    // Constructor untuk membuat Edge baru dengan bobot tertentu
    Edge(int weight) {
        this.weight = weight;
        next = null;
        goal = null;
    }

}
