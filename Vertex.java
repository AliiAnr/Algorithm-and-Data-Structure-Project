package src.TURU;

public class Vertex {
    // Field yang menyimpan nilai skor dari vertex ini
    int score = 0;

    // Field yang menyimpan nama negara dari vertex ini
    String country;

    // Field yang menyimpan vertex berikutnya dalam daftar vertex
    Vertex next;

    // Field yang menyimpan Edge yang terhubung dengan vertex ini
    Edge route;

    // Field yang menyimpan Edge terakhir yang terhubung dengan vertex ini
    Edge last;

    // Field yang menyimpan informasi apakah vertex ini telah dikunjungi atau tidak
    boolean visited = false;

    // Field yang menyimpan indeks vertex ini dalam daftar vertex
    int index;

    // Constructor untuk membuat vertex baru dengan nama negara dan indeks tertentu
    Vertex(String country, int index) {
        this.country = country;
        this.index = index;
    }

    // Method yang mengembalikan nama negara dari vertex ini
    public String getCountry() {
        return country;
    }

    // Method yang mengembalikan indeks dari vertex ini dalam daftar vertex
    public int getIndex() {
        return this.index;
    }
}
