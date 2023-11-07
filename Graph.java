package src.TURU;

import java.util.NoSuchElementException;
//Kelas Graph merupakan kelas yang digunakan untuk menyimpan informasi tentang graf, yang terdiri dari simpul-simpul (vertex)
//dan tautan-tautan (edge) yang menghubungkan simpul-simpul tersebut.
public class Graph {
    //Variabel head merupakan pointer ke simpul pertama dalam graf.
    //Variabel tail merupakan pointer ke simpul terakhir dalam graf.
    Vertex head, tail;
    //Variabel ini merupakan pointer ke tautan pertama dari simpul pertama dalam graf.
    Score first;
    //Variabel ini merupakan pointer ke tautan terakhir dari simpul pertama dalam graf.
    Score last;
    //Variabel ini menyimpan jumlah simpul yang ada dalam graf.
    int size = 0;
    // Variabel ini menyimpan jumlah simpul yang ada dalam graf.
    int numVertices;
    //Metode ini digunakan untuk menambahkan simpul baru ke dalam graf.
    public void insertVertex(String country, int index) {
        //Membuat objek baru dari kelas Vertex dengan nama negara dan indeks tertentu.
        Vertex baru = new Vertex(country, index);
        //Jika head masih kosong (belum ada simpul yang ditambahkan ke dalam graf),
        //maka simpul baru yang ditambahkan menjadi simpul pertama dan terakhir dalam graf (head = tail = baru).
        if (head == null) {
            head = tail = baru;
        } 
        //Jika sudah ada simpul yang telah ditambahkan ke dalam graf,
        //maka simpul baru yang ditambahkan menjadi simpul terakhir dalam graf (tail.next = baru; tail = baru).
        else {
            tail.next = baru;
            tail = baru;
        }
        //Menambahkan jumlah simpul yang ada dalam graf dengan 1.
        numVertices++;
    }
    //Metode ini digunakan untuk menemukan simpul dengan nama negara tertentu.
    public Vertex findVertex(String country) {
        //Menyimpan pointer ke simpul pertama dalam graf ke dalam variabel current.
        Vertex current = head;
        //Perulangan akan dilakukan selama current masih memiliki nilai (simpul masih ada dalam graf).
        while (current != null) {
            //Jika nama negara dari simpul yang sedang dicari sama dengan nama negara yang diberikan, maka simpul tersebut dikembalikan.
            if (current.country.equals(country)) {
                return current;
            }
            //Mengubah nilai current menjadi simpul berikutnya dalam graf.
            current = current.next;
        }
        //Jika simpul yang dicari tidak ditemukan, maka akan dicetak pesan bahwa negara tersebut tidak tersedia.
        System.out.println("Country isn't available");
        //Mengembalikan nilai null jika simpul yang dicari tidak ditemukan.
        return null;
    }
    //Metode ini digunakan untuk menambahkan tautan baru antara dua simpul.
    public void insertEdge(String start, String end, int weight) {
        //Mencari simpul dengan nama negara sesuai dengan yang diberikan pada parameter start.
        Vertex firstVertex = findVertex(start);
        // Mencari simpul dengan nama negara sesuai dengan yang diberikan pada parameter end.
        Vertex endVertex = findVertex(end);
        //Membuat objek baru dari kelas Edge dengan bobot sesuai dengan yang diberikan pada parameter weight.
        Edge newEdge = new Edge(weight);
        //Jika simpul pertama belum memiliki tautan ke simpul lain,
        //maka tautan baru yang ditambahkan menjadi tautan pertama dan terakhir dari simpul pertama (firstVertex.route = firstVertex.last = newEdge).
        if (firstVertex.route == null) {
            firstVertex.route = firstVertex.last = newEdge;
        }
        //Jika sudah ada tautan yang terhubung ke simpul pertama,
        //maka tautan baru yang ditambahkan menjadi tautan terakhir dari simpul pertama (firstVertex.last.next = newEdge; firstVertex.last = newEdge).
        else {
            firstVertex.last.next = newEdge;
            firstVertex.last = newEdge;
        }
        //Menyimpan pointer ke simpul tujuan dari tautan terakhir dari simpul pertama (simpul yang diberikan pada parameter end).
        firstVertex.last.goal = endVertex;
    }
    //Metode ini digunakan untuk menghapus simpul dari graf.
    public void removeVertex(String country){
        //Menyimpan pointer ke simpul pertama dalam graf ke dalam variabel current.
        Vertex current = head;
        //Jika graf kosong, maka akan ditampilkan pesan bahwa tidak ada simpul yang dapat dihapus.
        if (isEmpty()) throw  new NoSuchElementException();
        //Jika graf hanya memiliki satu simpul, maka pointer head dan tail akan disetel ke null (head = tail = null).
        if (head == tail){
            head = tail = null;
            return;
        }
        //Jika simpul pertama memiliki nama negara sesuai dengan yang diberikan pada parameter country, maka simpul tersebut dihapus dari graf.
        if (head.getCountry().equals(country)){
            //Jika simpul tersebut tidak merupakan simpul terakhir dalam graf, maka pointer head akan diarahkan ke simpul berikutnya (head = head.next)
            if (head.next != null){
                head = head.next;
            }
            //Jika simpul tersebut merupakan simpul terakhir dalam graf, maka pointer head dan tail akan disetel ke null (head = tail = null).
            else {
                head = tail = null;
                return;
            }
        }
        //Jika simpul pertama tidak memiliki nama negara sesuai dengan yang diberikan pada parameter country
        else{
            //Dilakukan perulangan untuk mencari simpul yang memiliki nama negara sesuai dengan yang diberikan.
            //Jika simpul tersebut ditemukan, maka simpul tersebut dihapus dari graf. 
            while(current != null){
                if(current.next.getCountry().equals(country)){
                    // Jika simpul tersebut tidak merupakan simpul terakhir dalam graf, 
                    //maka pointer current.next akan diarahkan ke simpul berikutnya (current.next = current.next.next).
                    if (current.next.next != null){
                        current.next = current.next.next;
                        return;
                    }
                    //Jika simpul tersebut merupakan simpul terakhir dalam graf,
                    //maka pointer tail akan diarahkan ke simpul sebelumnya (current.next = null; tail = current).
                    else{
                        current.next = null;
                        tail = current;
                    }
                }
                //Baris ini digunakan untuk mengubah nilai current menjadi simpul berikutnya dalam graf
                //setelah memproses simpul saat ini. Ini digunakan selama perulangan untuk menelusuri semua simpul dalam graf.
                current = current.next;
            }
        }
        // Menghitung ulang jumlah simpul yang ada dalam graf dengan mengurangi 1.
        numVertices--;
    }
    //Metode ini digunakan untuk menghapus tautan antara dua simpul.
    public void removeEdge(String start, String end) {
        //Mencari simpul dengan nama negara sesuai dengan yang diberikan pada parameter start.
        Vertex firstVertex = findVertex(start);
        //Mencari simpul dengan nama negara sesuai dengan yang diberikan pada parameter end.
        Vertex secondVertex = findVertex(end);
        // Jika salah satu simpul tidak ditemukan, maka akan ditampilkan pesan bahwa simpul tersebut tidak tersedia.
        if (firstVertex == null || secondVertex == null) {
            throw  new NoSuchElementException();
        }
        //Menyimpan pointer ke tautan pertama dari simpul pertama ke dalam variabel currentEdge.
        Edge currentEdge = firstVertex.route;
        //Jika simpul pertama hanya memiliki satu tautan, maka tautan tersebut dihapus dan pointer firstVertex.route disetel ke null (firstVertex.route = null).
        if (firstVertex.route.next == null){
            firstVertex.route = null;
            return;
        }
        //Jika tautan pertama dari simpul pertama terhubung ke simpul kedua, maka tautan tersebut dihapus.
        if (firstVertex.route.goal == secondVertex){
            // Jika tautan tersebut bukan tautan terakhir dari simpul pertama,
            //maka pointer firstVertex.route diarahkan ke tautan berikutnya (firstVertex.route = firstVertex.route.next).
            if (firstVertex.route.next != null){
                firstVertex.route = firstVertex.route.next;
            }
            //Jika tautan tersebut merupakan tautan terakhir dari simpul pertama,
            //maka pointer firstVertex.route dan firstVertex.last disetel ke null (firstVertex.route = firstVertex.last = null).
            else {
                firstVertex.route = firstVertex.last = null;
                return;
            }
        }
        //Jika tautan pertama dari simpul pertama tidak terhubung ke simpul kedua, maka dilakukan perulangan untuk mencari tautan yang terhubung ke simpul kedua.
        else {
            while (currentEdge != null){
                if (currentEdge.next.goal == secondVertex){
                    // Jika tautan tersebut bukan tautan terakhir dari simpul pertama,
                    //maka pointer currentEdge.next diarahkan ke tautan berikutnya (currentEdge.next = currentEdge.next.next).
                    if (currentEdge.next.next != null){
                        currentEdge.next = currentEdge.next.next;
                        return;
                    }
                    //Jika tautan tersebut ditemukan, maka tautan tersebut dihapus. Jika tautan tersebut merupakan tautan terakhir dari simpul pertama,
                    //maka pointer firstVertex.last diarahkan ke tautan sebelumnya (currentEdge.next = null; firstVertex.last = currentEdge).
                    else{
                        currentEdge.next = null;
                        firstVertex.last = currentEdge;
                    }
                }
                //Mengubah nilai currentEdge menjadi tautan berikutnya dari simpul pertama.
                currentEdge = currentEdge.next;
            }
        }
    }
    //etode ini digunakan untuk menampilkan nama negara dari semua simpul yang ada dalam graf.
    //Nilai current diubah menjadi simpul berikutnya setelah nama negara dari simpul saat ini ditampilkan.
    //Perulangan akan terus dilakukan selama ada simpul yang belum diproses.
    public void printVertex() {
        //Menyimpan pointer ke simpul pertama dalam graf ke dalam variabel current.
        Vertex current = head;
        //Perulangan akan dilakukan selama current tidak bernilai null, yang artinya masih ada simpul yang belum diproses.
        while (current != null) {
            // Menampilkan nama negara dari simpul saat ini.
            System.out.println(current.getCountry());
            //current.next: Mengubah nilai current menjadi simpul berikutnya dalam graf.
            current = current.next;
        }
    }
    //Metode ini digunakan untuk mencetak semua tautan yang ada dalam graf.
    public void printEdge() {
        // Menyimpan pointer ke simpul pertama dalam graf ke dalam variabel current.
        Vertex current = head;
        //Variabel yang digunakan untuk menyimpan pointer ke tautan dari simpul saat ini.
        Edge visit;
        //Perulangan akan dilakukan selama current tidak bernilai null, yang artinya masih ada simpul yang belum diproses.
        while (current != null) {
            //Menampilkan nama negara dari simpul saat ini dan teks "neighbors with :".
            System.out.print(current.getCountry() + " neigbors with : ");
            //Menyimpan pointer ke tautan pertama dari simpul saat ini ke dalam variabel visit.
            visit = current.route;
            //Perulangan akan dilakukan selama visit tidak bernilai null, yang artinya masih ada tautan yang belum diproses dari simpul saat ini.
            while (visit != null) {
                //Menampilkan nama negara dari simpul yang dituju oleh tautan saat ini di dalam tanda kurung siku.
                System.out.print("[" + visit.goal.getCountry() + "]");
                // Mengubah nilai visit menjadi tautan berikutnya dari simpul saat ini.
                visit = visit.next;
            }
            //Menampilkan baris baru setelah semua tautan dari simpul saat ini selesai diproses.
            System.out.println();
            //Mengubah nilai current menjadi simpul berikutnya dalam graf.
            current = current.next;
        }
    }
    //metode ini digunakan untuk mencari jarak terpendek antar objek
    public int dijkstra(String start, String end) {
        //Menyimpan pointer ke simpul sumber di dalam variabel source.
        Vertex source = findVertex(start);
        //Menyimpan pointer ke simpul tujuan di dalam variabel destination.
        Vertex destination = findVertex(end);
        //Membuat array untuk menyimpan jarak dari sumber ke setiap simpul.
        int[] distance = new int[numVertices];
        //Membuat array untuk menyimpan simpul sebelumnya dari setiap simpul.
        Vertex[] previous = new Vertex[numVertices];
        // Membuat array untuk menandakan apakah suatu simpul sudah dikunjungi atau belum.
        boolean[] visited = new boolean[numVertices];

        // Inisialisasi semua jarak dengan nilai infiniti dan seluruh vertex sebagai belum dikunjungi
        //Perulangan untuk menginisialisasi semua jarak dengan nilai infiniti dan seluruh vertex sebagai belum dikunjungi.
        for (int i = 0; i < numVertices; i++) {
            distance[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        // Jarak dari sumber ke sumber adalah 0
        distance[source.getIndex()] = 0;

        // Cari jalur terpendek dari sumber ke seluruh vertex lainnya
        for (int i = 0; i < numVertices - 1; i++) {
            // Cari vertex dengan jarak terpendek yang belum dikunjungi
            //Variabel untuk menyimpan indeks dan jarak terpendek dari vertex yang belum dikunjungi.
            int minIndex = -1;
            int minDistance = Integer.MAX_VALUE;
            //Perulangan untuk mencari vertex dengan jarak terpendek yang belum dikunjungi.
            for (int j = 0; j < numVertices; j++) {
                //Jika vertex belum dikunjungi dan jaraknya lebih kecil dari jarak terpendek sebelumnya, maka simpan indeks dan jaraknya.
                if (!visited[j] && distance[j] < minDistance) {
                    minDistance = distance[j];
                    minIndex = j;
                }
            }

            // Kunjungi vertex dengan jarak terpendek
            // Tandai vertex dengan jarak terpendek sebagai sudah dikunjungi.
            visited[minIndex] = true;

            // Perbarui jarak ke vertex lainnya yang terhubung dengan vertex yang sedang dikunjungi
            //Menyimpan pointer ke vertex dengan jarak terpendek ke dalam variabel current.
            Vertex current = findVertexByIndex(minIndex);
            //Menyimpan pointer ke tautan pertama
            Edge edge = current.route;
            // Perulangan untuk mengecek setiap tetangga dari vertex yang sedang dikunjungi.
            while (edge != null) {
                //Menyimpan pointer ke simpul tetangga dari vertex yang sedang dikunjungi.
                Vertex neighbor = edge.goal;
                //Menghitung jarak baru dari sumber ke simpul tetangga melalui simpul yang sedang dikunjungi.
                int newDistance = distance[current.getIndex()] + edge.weight;
                //Jika jarak baru lebih kecil dari jarak sebelumnya dari sumber ke simpul tetangga, maka update jarak terpendek ke simpul tetangga dan simpul sebelumnya.
                if (newDistance < distance[neighbor.getIndex()]) {
                    distance[neighbor.getIndex()] = newDistance;
                    previous[neighbor.getIndex()] = current;
                }
                // Pindah ke tautan selanjutnya.
                edge = edge.next;
            }
        }

        // Cetak jalur terpendek dari sumber ke tujuan
        System.out.print("Shortest path from " + start + " to " + end + ": ");
        //Setelah seluruh vertex telah dikunjungi, cetak jalur terpendek dari sumber ke tujuan dengan memanggil method printPath().
        printPath(previous, source, destination);

        //Kembalikan jarak terpendek dari sumber ke tujuan.
        return distance[destination.getIndex()];
    }

    // Mencetak jalur terpendek dari sumber ke tujuan
    private void printPath(Vertex[] previous, Vertex source, Vertex destination) {
        //Cek apakah ada jalur terpendek dari sumber ke tujuan. Jika tidak, tampilkan pesan dan keluar dari method.
        if (previous[destination.getIndex()] == null) {
            System.out.println("There is no path from " + source.getCountry() + " to " + destination.getCountry());
            return;
        }

        //Membuat stack untuk menyimpan jalur terpendek dari tujuan ke sumber.
        Stack<Vertex> path = new Stack<>();
        // Menyimpan pointer ke tujuan.
        Vertex current = destination;
        //Perulangan untuk menambahkan setiap simpul sebelumnya ke stack sampai mencapai sumber.
        while (current != source) {
            //Menambahkan simpul ke stack.
            path.push(current);
            //Pindah ke simpul sebelumnya.
            current = previous[current.getIndex()];
        }
        path.push(source);

        // Cetak jalur terpendek dari sumber ke tujuan
        //Perulangan untuk mencetak setiap simpul dari stack sampai kosong.
        while (!path.isEmpty()) {
            //Mencetak nama negara dari simpul yang diambil dari stack.
            System.out.print(path.pop().getCountry() + " ");
        }
        System.out.println();
    }

    // Mencari vertex dengan indeks yang diberikan
    private Vertex findVertexByIndex(int index) {
        // Inisialisasi variabel current dengan simpul pertama dari graph
        Vertex current = head;
        // Selama current tidak null, periksa apakah indeks dari current sama dengan parameter index
        // Jika sama, kembalikan current. Jika tidak, pindah ke simpul selanjutnya
        while (current != null) {
            if (current.getIndex() == index) {
                return current;
            }
            current = current.next;
        }
        // Jika tidak ada simpul yang memiliki indeks sama dengan parameter index, kembalikan null
        return null;
    }
    //metode ini digunakan untuk memasukkan objek ke score agar dapat
    //menyimpan score setiap objek
    // Method ini digunakan untuk menambahkan elemen baru di bagian akhir dari linked list
    // Parameter yang diterima adalah nama negara dan skor yang ingin ditambahkan
    public void insertLast(String countryName, int score) {
        // Membuat objek baru dengan nama negara dan skor yang diterima
        Score newScore = new Score(countryName, score);
        // linked list kosong
        if (first == null) {
            // Jika linked list masih kosong, maka elemen baru akan menjadi elemen pertama dan terakhir
           first = last = newScore;
        }
        // linked list nya udah ada elemen
        else {
            // Jika tidak, maka elemen baru akan menjadi elemen terakhir dan next dari elemen terakhir sebelumnya akan diatur ke elemen baru
           last.next = newScore;
           last = newScore;
        }
        // Jumlah elemen dalam linked list bertambah satu
        size++;
     }
     //metode ini digunakan untuk mengurutkan score
     public void sortScores() {
        // Jika ukuran lebih dari satu, maka lakukan loop sebanyak ukuran
        if (size > 1) {
             // Loop sebanyak ukuran
            for (int i = 0; i < size; i++ ) {
                // Inisialisasi current dan currentNext dengan first
                Score current = first;
                Score currentNext = first.next;
                // Loop sebanyak ukuran - 1
                for (int j = 0; j < size - 1; j++) {
                     // Jika skor current lebih kecil dari skor currentNext, maka tukar posisi skor dan nama negara
                    if (current.score < currentNext.score) {
                        int tempScore = currentNext.score;
                        current.next.score = current.score;
                        current.score = tempScore;
                        
                        String tempCountry = current.next.countryName;
                        current.next.countryName = current.countryName;
                        current.countryName = tempCountry;
                    } 
                     // Pindahkan current dan currentNext ke elemen selanjutnya
                    current = current.next;
                    currentNext = currentNext.next;                   
                } 
            }
        }
    }
    //metode ini digunakan untuk mencetak list pada score
     public void printAll(){
        // Inisialisasi variabel current dengan first
        Score current = first;
        // Loop sampai current sama dengan elemen setelah last
        while (current != last.next){
            // Cetak nama negara dan skor current
            System.out.println(current.countryName + " : " + current.score);
            // Pindahkan current ke elemen selanjutnya
            current = current.next;
        }
    }
    //Metode ini digunakan untuk mengecek apakah graf kosong atau tidak.
    private boolean isEmpty(){
        // Jika head dan tail kosong, maka true, sebaliknya false
        return (head == null && tail == null);
    }

}

