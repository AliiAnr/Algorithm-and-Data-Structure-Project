package src.TURU;

import java.util.EmptyStackException;

public class Stack<T> {
  // Kelas StackNode merupakan kelas anak dari kelas Stack, yang digunakan untuk menyimpan satu elemen dari Stack
private static class StackNode<T> {
   // Field yang menyimpan data dari elemen Stack
   private T data;

   // Field yang menyimpan elemen Stack berikutnya dalam Stack
   private StackNode<T> next;

   // Constructor untuk membuat elemen Stack baru dengan data tertentu
   public StackNode(T data) {
       this.data = data;
   }
}

// Field yang menyimpan elemen Stack teratas (top)
private StackNode<T> top;

// Method yang mengeluarkan elemen teratas dari Stack
public T pop() {
   // Jika Stack kosong, lempar exception
   if (top == null) {
       throw new EmptyStackException();
   }
   // Simpan data elemen teratas ke dalam variabel
   T item = top.data;
   // Set top menjadi elemen berikutnya dalam Stack
   top = top.next;
   // Kembalikan data elemen teratas yang telah disimpan
   return item;
}

// Method yang menambahkan elemen baru ke dalam Stack
public void push(T item) {
   // Buat elemen Stack baru dengan data yang diberikan
   StackNode<T> newNode = new StackNode<T>(item);
   // Set elemen baru sebagai elemen teratas (top)
   newNode.next = top;
   top = newNode;
}

// Method yang mengembalikan elemen teratas dari Stack tanpa mengeluarkannya dari Stack
public T peek() {
   // Jika Stack kosong, lempar exception
   if (top == null) {
       throw new EmptyStackException();
   }
   // Kembalikan data elemen teratas
   return top.data;
}

// Method yang mengembalikan boolean yang menyatakan apakah Stack kosong atau tidak
public boolean isEmpty() {
   return top == null;
}

}