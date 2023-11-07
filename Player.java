package src.TURU;

import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Player {
   // Deklarasi konstanta untuk tombol panah
   final int up = 119;
   final int down = 115;
   final int left = 97;
   final int right = 100;
   final int enter = 13;
   final int exit = 101;
   final int checkScore = 99;
   
   // Deklarasi array 2D untuk cursor dan vision
   int[][] cursor = new int[10][10];
   int[][] vision = new int[10][10];
   // Deklarasi array untuk key horizontal dan vertical
   String[] horizontal_key = new String[5];
   String[] vertical_key = new String[5];
   // Inisialisasi variabel vertexTemp
   Vertex vertexTemp;
   // Inisialisasi variabel back dengan false
   boolean back = false;
   // Inisialisasi variabel intTemp
   int intTemp;
   // Inisialisasi variabel charTemp
   char charTemp;
   // Inisialisasi objek Answer dan Graph
   Answer answr = new Answer();
   Graph graph = new Graph();
   // Inisialisasi variabel charTemp_2 dan choose
   char charTemp_2, choose;
   // Inisialisasi variabel temp dengan 0
   int temp = 0;
   // Inisialisasi variabel intTemp_1 dan intTemp_2
   int intTemp_1, intTemp_2;
   // Inisialisasi variabel head
   Vertex head;
   // Inisialisasi scanner untuk input dari keyboard
   Scanner input = new Scanner(System.in);
   // Inisialisasi BufferedReader untuk input dari keyboard
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   
   private void space(char length) {
      // Jika panjang sama dengan 0, maka cetak spasi
      if (length == 0) {
         System.out.print(" ");
      }
   }
   
   public int check(){
      // Inisialisasi variabel cek dengan 0
      int cek = 0;
      // Inisialisasi array horizontal_key dan vertical_key dengan string kosong
      for (int i = 0; i < 5; i++) {
         horizontal_key[i] = "";
         vertical_key[i] = "";
      }
      //verticalkey
      // Memasukkan elemen dari answr.key ke dalam array vertical_key sesuai dengan indeks yang sesuai
      for (int i = 0; i < 10; i++){
         for (int j = 0; j < 10; j++){
            if (i >= 0 && i < 9 && j == 0){
               vertical_key[0] += answr.key[i][j]; 
            }else if (i > 3 && i < 7 && j == 2){
               vertical_key[1] += answr.key[i][j];
            }else if (i >= 0 && i <= 9 & j == 4){
               vertical_key[2] += answr.key[i][j];
            }else if (i > 5 && i < 9 && j == 6){
               vertical_key[3] += answr.key[i][j];
            }else if (i >= 0 && i < 7 && j == 9){
               vertical_key[4] += answr.key[i][j];
            }
         }
      }
      //horizontalKey
      // Memasukkan elemen dari answr.key ke dalam array horizontal_key sesuai dengan indeks yang sesuai
      for (int i = 0; i < 10; i++){
         for (int j = 0; j < 10; j++){
            if (i == 1 && j >= 0 && j < 5){
               horizontal_key[0] += answr.key[i][j];
            }else if (i == 4 && j >= 0 && j < 3){
               horizontal_key[1] += answr.key[i][j];
            }else if (i == 9 && j > 0 && j < 6){
               horizontal_key[2] += answr.key[i][j];
            }else if (i == 4 && j > 3 && j <= 9 ){
               horizontal_key[3] += answr.key[i][j];
            }else if (i == 6 && j > 3 && j < 8){
               horizontal_key[4] += answr.key[i][j];
            }
         }
      }
      // Bandingkan elemen dari array vertical_key dan horizontal_key dengan answr.verticalKey dan answr.horizontalKey, lalu tambahkan 1 pada cek jika sama
      for (int i = 0; i < 5; i++){
         if (vertical_key[i].equals(answr.verticalKey[i])){
            cek += 1;
            // Tambahkan 10 pada skor vertexTemp
            vertexTemp.score+=10;
         }
         if (horizontal_key[i].equals(answr.horizontalKey[i])){
            cek += 1;
            // Tambahkan 10 pada skor vertexTemp
            vertexTemp.score+=10;
         }
      }
      // Tambahkan vertexTemp ke dalam linked list graph
      graph.insertLast(vertexTemp.country, vertexTemp.score);

      // Kembalikan nilai cek
      return cek;
   }
   //mengganti nilai menjadi reset
   void reset_value(){
      // Mengganti semua elemen dari array answr.key, cursor, vertical_key, dan horizontal_key dengan 0 atau string kosong
      for(int i=0;i<10;i++){
         for(int j=0;j<10;j++){
            answr.key[i][j]=0;
            cursor[i][j]=0;
         }
      }
      for(int i=0;i<5;i++){
         vertical_key[i]="";
         horizontal_key[i]="";
      }
   }

   private void enter() {
      int temp;
      for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
            if (cursor[i][j] == 1) {
                  System.out.print("Masukkan jawaban = ");
                  answr.key[i][j] = input.next().charAt(0);
                  if (input.hasNextLine()) {
                  input.nextLine();
                  }
                  if ((int) answr.key[i][j] >= 97 && answr.key[i][j] <= 122) {
                     temp = (int) answr.key[i][j] - 32;
                     answr.key[i][j] = (char)temp;
                     vision[i][j] = 1;
                     clearScreen();
                  } else if ((int) answr.key[i][j] >= 65 && (int) answr.key[i][j] <= 90) {
                     vision[i][j] = 1;
                     clearScreen();
                  } else {
                     System.out.println("JAWABAN HANYA MENGGUNAKAN 1 KARAKTER KAPITAL !!!");
                     answr.key[i][j] = 0 ;
                     System.out.println("Press Any Key To Continue...");
                     new java.util.Scanner(System.in).nextLine();
                     clearScreen();
                  }
               }
            }
         }
   }
   
   private void column_space(int left_column, int right_column) {
      int kolom;
      kolom = (right_column - left_column) - 1;
      for (int i = 0; i < kolom; i++) {
         System.out.print("      ");
      }
   }
   
   public void cursor_move(int i, int j){
      System.out.print("[<");space(answr.key[i][j]);System.out.print(answr.key[i][j] + ">] ");
   }
   
   public void vision_(int i, int j, int check_1, int check_2){
      if (check_1==1){
         System.out.print("[ ");space(answr.key[i][j]);System.out.print(answr.key[i][j] + " ] ");
      }
      if(check_2 == 1){
         space(answr.key[i][j]); System.out.print(answr.key[i][j] + " ] ");
      }
   }

   public void cursorUp() {
      for (int i = 0; i < 10; i++) {
         for (int j = 0; j < 10; j++) {
            if ((i > 0 && i <= 8 && j == 0) || (i > 4 && i <= 7 && j == 2) || (i > 0 && i <= 9 && j == 4)
                  || (i > 6 && i <= 8 && j == 6) || (i > 0 && i <= 6 && j == 9)) {
               if (cursor[i][j] == 1) {
                  cursor[i - 1][j] = 1;
                  cursor[i][j] = 0;
                  return;
               }
            }
         }
      }
   }

   public void cursorDown() {
      for (int i = 0; i < 10; i++) {
         for (int j = 0; j < 10; j++) {
            if ((i <= 7 && j == 0) || (i > 3 && i < 6 && j == 2) || (i <= 8 && j == 4) || (i > 5 && i < 8 && j == 6)
                  || (i <= 5 && j == 9)) {
               if (cursor[i][j] == 1) {
                  cursor[i + 1][j] = 1;
                  cursor[i][j] = 0;
                  return;
               }
            }
         }
      }
   }

   public void cursorLeft() {
      for (int i = 0; i < 10; i++) {
         for (int j = 0; j < 10; j++) {
            if ((i == 1 && j <= 4) || (i == 4 && j <= 2) || (i == 9 && j > 1 && j <= 5) || (i == 4 && j > 4 && j <= 9)
                  || (i == 6 && j > 4 && j <= 7)) {
               if (j - 1 >= 0) {
                  if (cursor[i][j] == 1) {
                     cursor[i][j - 1] = 1;
                     cursor[i][j] = 0;
                     return;
                  }
               }
            }
         }
      }
   }

   public void cursorRight() {
      for (int i = 0; i < 10; i++) {
         for (int j = 0; j < 10; j++) {
            if ((i == 1 && j <= 3) || (i == 4 && j <= 1) || (i == 9 && j >= 1 && j <= 4) || (i == 4 && j >= 4 && j <= 8) || (i == 6 && j >= 4 && j <= 6)){
               if (cursor[i][j] == 1) {
                   cursor[i][j + 1] = 1;
                   cursor[i][j] = 0;
                  return;
               }
            }
           
         }
      }
   }

   public void quest() throws IOException {
      int scoreTemp = 0;
      cursor[0][0] = 1;
      System.out.println("Masukkan Nama Negara yang bermain");
      String values = input.nextLine();
      vertexTemp = graph.findVertex(values);

      do {
         System.out.println("Negara yang sedang bermain : " + vertexTemp.getCountry() + "\n");
            for (int i = 0; i < 10; i++) {
               for (int j = 0; j < 10; j++) {
                  if (i != 1 && j == 0 && i != 9 && i != 4) {  // ALGORITMA
                     if (cursor[i][j] == 0) {
                        if (vision[i][j] == 0) {
                           if (i == 0) {
                              System.out.print("[1");
                              vision_ (i,j,0,1);
                           } else {
                              vision_ (i,j,1,0);
                           }
                        } else if (vision[i][j] == 1) {
                           if (i == 0) {
                              System.out.print("[1");
                              vision_ (i,j,0,1);
                           } else {
                              vision_ (i,j,1,0);
                           }
                        }
                     }
                     // \xc2\xb2
                     else {
                        cursor_move(i,j);
                     }
                  } else if (i == 1 && j <= 4) {  // LOGIS
                     if (cursor[i][j] == 0) {
                        if (vision[i][j] == 0) {
                           if (i == 1 && j == 0) {
                              System.out.print("[1");
                              vision_ (i,j,0,1);
                           } else {
                              vision_ (i,j,1,0);
                           }
                        } else if (vision[i][j] == 1) {
                           if (i == 1 && j == 0) {
                              System.out.print("[1");
                              vision_ (i,j,0,1);
                           } else {
                              vision_ (i,j,1,0);
                           }
                        }
                     }
                     // \xc2\xb2
                     else {
                        cursor_move(i,j);
                     }
                  } else if (i == 4 && j <= 1) {  // RAM
                     if (cursor[i][j] == 0) {
                        if (vision[i][j] == 0) {
                           if (i == 4 && j == 0) {
                              System.out.print("[2");
                              vision_ (i,j,0,1);
                           } else {
                              vision_ (i,j,1,0);
                           }
                        } else if (vision[i][j] == 1) {
                           if (i == 4 && j == 0) {
                              System.out.print("[2");
                              vision_ (i,j,0,1);
                           } else {
                              vision_ (i,j,1,0);
                           }
                        }
                     }
                     // \xc2\xb2
                     else {
                        cursor_move(i,j);
                     }
                  } else if (i != 1 && j == 4 && i != 9 && i != 4 && i != 6) {  // PSEUDOCODE
                     if (cursor[i][j] == 0) {
                        if (vision[i][j] == 0) {
                           if (i == 4 || i == 5 || i == 6) {
                              column_space(2, 4);
                           } else {
                              column_space(0, 4);
                           }
                           if (i == 0) {
                              System.out.print("[3");
                              vision_ (i,j,0,1);
                           } else {
                              vision_ (i,j,1,0);
                           }
                        } else if (vision[i][j] == 1) {
                           if (i == 4 || i == 5 || i == 6) {
                              column_space(2, 4);
                           } else {
                              column_space(0, 4);
                           }
                           if (i == 0) {
                              System.out.print("[3");
                              vision_ (i,j,0,1);
                           } else {
                              vision_ (i,j,1,0);
                           }
                        }
                     } else {
                        if (i == 4 || i == 5 || i == 6) {
                           column_space(2, 4);
                        } else {
                           column_space(0, 4);
                        }
                        cursor_move(i,j);
                     }
                  } else if (i >= 4 && j == 2 && i <= 6) {  // MOD
                     if (cursor[i][j] == 0) {
                        if (vision[i][j] == 0) {
                           if (i == 5 || i == 6) {
                              column_space(0, 2);
                           }
                           if (i == 4) {
                              System.out.print("[2");
                              vision_ (i,j,0,1);
                           } else {
                              vision_ (i,j,1,0);
                           }
                        } else if (vision[i][j] == 1) {
                           if (i == 5 || i == 6) {
                              column_space(0, 2);
                           }
                           if (i == 4) {
                              System.out.print("[2");
                              vision_ (i,j,0,1);
                           } else {
                              vision_ (i,j,1,0);
                           }
                        }
                     } else {
                        if (i == 5 || i == 6) {
                           column_space(0, 2);
                        }
                        cursor_move(i,j);
                     }
         
                  } else if (i == 9 && j >= 1 && j <= 5) {  // EULER
                     if (cursor[i][j] == 0) {
                        if (vision[i][j] == 0) {
                           if (i == 9 && j == 1) {
                              column_space(-1, 1);
                           }
                           if (j == 1) {
                              System.out.print("[3");
                              vision_ (i,j,0,1);
                           } else {
                              vision_ (i,j,1,0);
                           }
                        } else if (vision[i][j] == 1) {
                           if (i == 9 && j == 1) {
                              column_space(-1, 1);
                           }
                           if (j == 1) {
                              System.out.print("[3");
                              vision_ (i,j,0,1);
                           } else {
                              vision_ (i,j,1,0);
                           }
                        }
                     } else {
                        if (i == 9 && j == 1) {
                           column_space(-1, 1);
                        }
                        cursor_move(i,j);
                     }
                  } else if (i == 4 && j >= 4 && j <= 9) {  // DOUBLE
                     if (cursor[i][j] == 0) {
                        if (vision[i][j] == 0) {
                           if (i == 4 && j == 4) {
                              column_space(2, 4);
                           }
                           if (j == 4) {
                              System.out.print("[4");
                              vision_ (i,j,0,1);
                           } else {
                              vision_ (i,j,1,0);
                           }
                        } else if (vision[i][j] == 1) {
                           if (i == 4 && j == 4) {
                              column_space(2, 4);
                           }
                           if (j == 4) {
                              System.out.print("[4");
                              vision_ (i,j,0,1);
                           } else {
                              vision_ (i,j,1,0);
                           }
                        }
                     } else {
                        if (i == 4 && j == 4) {
                           column_space(2, 4);
                        }
                        cursor_move(i,j);
                     }
         
                  } else if (i == 6 && j >= 4 && j <= 7 && j != 6) {  // CHAR
                     if (cursor[i][j] == 0) {
                        if (vision[i][j] == 0) {
                           if (i == 6 && j == 4) {
                              column_space(2, 4);
                           }
                           if (j == 4) {
                              System.out.print("[5");
                              vision_ (i,j,0,1);
                           } else {
                              vision_ (i,j,1,0);
                           }
                        } else if (vision[i][j] == 1) {
                           if (i == 6 && j == 4) {
                              column_space(2, 4);
                           }
                           if (j == 4) {
                              System.out.print("[5");
                              vision_ (i,j,0,1);
                           } else {
                              vision_ (i,j,1,0);
                           }
                        }
                     } else {
                        if (i == 6 && j == 4) {
                           column_space(2, 4);
                        }
                        cursor_move(i,j);
                     }
                  } else if (j == 6 && i >= 6 && i <= 8) {  // AND
                     if (cursor[i][j] == 0) {
                        if (vision[i][j] == 0) {
                           if (i == 7 || i == 8) {
                              column_space(4, 6);
                           }
                           if (i == 6) {
                              System.out.print("[4");
                              vision_ (i,j,0,1);
                           } else {
                              vision_ (i,j,1,0);
                           }
                        } else if (vision[i][j] == 1) {
                           if (i == 7 || i == 8) {
                              column_space(4, 6);
                           }
                           if (i == 6) {
                              System.out.print("[4");
                              vision_ (i,j,0,1);
                           } else {
                              vision_ (i,j,1,0);
                           }
                        }
                     } else {
                        if (i == 7 || i == 8) {
                           column_space(4, 6);
                        }
                        cursor_move(i,j);
                     }
                  } else if (j == 9 && i >= 0 && i <= 6 && i != 4) {  // BOOLEAN
                     if (cursor[i][j] == 0) {
                        if (vision[i][j] == 0) {
                           if (i == 6) {
                              column_space(7, 9);
                           } else {
                              column_space(4, 9);
                           }
                           if (i == 0) {
                              System.out.print("[5");
                              vision_ (i,j,0,1);
                           } else {
                              vision_ (i,j,1,0);
                           }
                        } else if (vision[i][j] == 1) {
                           if (i == 6) {
                              column_space(7, 9);
                           } else {
                              column_space(4, 9);
                           }
                           if (i == 0) {
                              System.out.print("[5");
                              vision_ (i,j,0,1);
                           } else {
                              vision_ (i,j,1,0);
                           }
                        }
                     } else {
                        if (i == 6) {
                           column_space(7, 9);
                        } else {
                           column_space(4, 9);
                        }
                        cursor_move(i,j);
                     }
                  }
               }
               System.out.println("\n");
            }
            System.out.println("MENURUN");
            System.out.println("1. Urutan langkah-langkah logis untuk menyelesaikan masalah");
            System.out.println("2. Operator sisa hasil bagi");
            System.out.println("3. Algoritma yang bentuknya sangat mirip dengan bahasa pemrograman (terstruktur seperti pascal)");
            System.out.println("4. Operator yang hanya bernilai benar apabila kedua nilai benar");
            System.out.println("5. Tipe data yang hanya memiliki dua nilai logika \n");
            System.out.println("MENDATAR");
            System.out.println("1. Algoritma harus memiliki sifat yang...agar dapat dijangkau oleh akal fikiran kita");
            System.out.println("2. ....Berfungsi untuk menyimpan hasil kompilasi program");
            System.out.println("3. Lintasan yang memiliki hanya dua buah derajat berjumlah ganjil");
            System.out.println("4. Tipe data yang dapat menyimpan nilai dengan angka desimal lebih banyak");
            System.out.println("5. Tipe data penyimpan karakter"); 

         try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            // Membaca input dari keyboard dan menampilkan ASCII code-nya
            System.out.print("INPUT : ");
            char value = (char) br.read();
            intTemp = (int) value;
            switch (intTemp) {
               case up:
                  cursorUp();
                  clearScreen();
                  break;
               case down:
                  cursorDown();
                  clearScreen();
                  break;
               case left:
                  cursorLeft();
                  clearScreen();
                  break;
               case right:
                  cursorRight();
                  clearScreen();
                  break;
               case enter:
                  System.out.println();
                  enter();
                  break;
               case checkScore:
                  clearScreen();
                  scoreTemp = check();
                  if (scoreTemp > 0){
                     scoreTemp *= 10;
                     line ();
                     System.out.println("-----------------------SELAMAT-----------------------");
                     System.out.println("SCORE AKHIR ANDA ADALAH = " + scoreTemp);
                  }else{
                     line ();
                     System.out.println("------------SCORE AKHIR ANDA ADALAH = " + scoreTemp + " -------------");
                     System.out.println("----------SEMOGA BERUNTUNG DILAIN WAKTU HEHE---------");
                  }
                  reset_value();
                  System.out.println();
                  System.out.println();
                  System.out.println("Press Any Key To Continue...");
                  new java.util.Scanner(System.in).nextLine();
                  clearScreen();
                  return;
               case exit:
                  clearScreen();
                  break;
               default:
                  System.out.println("WRONG INPUT TYPE !!!");
                  System.out.println("Press Any Key To Continue...");
                  new java.util.Scanner(System.in).nextLine();
                  clearScreen();
                  break;
            }
         } catch (IOException e) {
            // Menangkap kemungkinan terjadinya exception IOException
            System.out.println("Error: " + e.getMessage());
         }
      } while (intTemp != exit);
   }

   
   
   
   
   public void clearScreen() {
      try {
         new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      } catch (Exception e) {
         System.out.println("Error: " + e.getMessage());
      }
   }
   
   public void shortestPath(){
      System.out.println("Masukkan Negara Awal dan Negara Tujuan");
      System.out.print("Negara Awal : ");
      String value_1 = input.nextLine();
      System.out.println();
      System.out.print("Negara Tujuan : ");
      String value_2 = input.nextLine();
      int distance = graph.dijkstra(value_1, value_2);
      System.out.println("With distance : " + distance + " Km");
      System.out.println();
      System.out.println("Press Any Key To Continue...");
      new java.util.Scanner(System.in).nextLine();
   }
   
   public void neighborsInput() {
      System.out.println("Negara yang telah ditambahkan sebelumnya :");
      graph.printVertex();
      System.out.println();
      System.out.println("Masukkan nama Negara yang Bertetangga beserta jarak antar Negara");
      do{
         System.out.print("Negara Awal : ");
         String countryName = input.nextLine();
         System.out.println();
         System.out.print("Negara Tujuan : ");
         String countryNeighbor = input.nextLine();
         System.out.print("Masukkan Jarak antar Negara : ");
         int distance = input.nextInt();
         input.nextLine();
         graph.insertEdge(countryName, countryNeighbor, distance);
         System.out.print("Ingin menambahkan Tetangga yang lain? [Y/N) : ");
         charTemp_2 = input.next().charAt(0);
         input.nextLine();
     } while (charTemp_2 == 'y' || charTemp_2 == 'Y');
     System.out.println();
     System.out.println("=====[Tetangga telah ditambahkan]=====");
     graph.printEdge();
     System.out.println();
     System.out.println("Press Any Key To Continue...");
     new java.util.Scanner(System.in).nextLine();
   }
   
   public void score(){
      System.out.println("==============Rekor Peserta==============\n");
      graph.sortScores();
      graph.printAll();
      System.out.println();
      System.out.println("Press Any Key To Continue...");
      new java.util.Scanner(System.in).nextLine();
   }
   
   public void countryInput(){
        System.out.println("Berapa negara yang ingin ditambahkan?");
        int number = input.nextInt();
        input.nextLine();
        for(int i = 0; i < number; i++){
            System.out.print("Masukkan nama Negara indeks ke - " + temp + " : ");
            String countryName = input.nextLine();
            graph.insertVertex(countryName,temp);
            temp ++;
        }
        System.out.println();
        System.out.println("=====[Negara telah ditambahkan]=====");
        graph.printVertex();
        System.out.println();
        System.out.println("Press Any Key To Continue...");
        new java.util.Scanner(System.in).nextLine();
   }
   
   public void countryRemove(){
      System.out.println("Masukkan nama Negara yang ingin dihapus");
      System.out.print("Nama Negara : ");
      String value = input.nextLine();
      graph.removeVertex(value);
      System.out.println("Daftar Negara setelah dihapus : ");
      temp --;
      System.out.println("Negara telah dihapus dari list");
      graph.printVertex();
      System.out.println();
      System.out.println("Press Any Key To Continue...");
      new java.util.Scanner(System.in).nextLine();
   }
   
   public void neighborsRemove(){
      System.out.println("Masukkan tetangga yang ingin dihapus");
      System.out.print("Negara Awal : ");
      String value_1 = input.nextLine();
      System.out.println();
      System.out.print("Negara Tujuan : ");
      String value_2 = input.nextLine();
      graph.removeEdge(value_1, value_2);
      System.out.println("Tetangga telah dihapus dari Negara");
      graph.printEdge();
      System.out.println();
      System.out.println("Press Any Key To Continue...");
      new java.util.Scanner(System.in).nextLine();
   }
   
   public void countryList(){
      if (graph.numVertices == 0){
         System.out.println("NEGARA BELUM DITAMBAHKAN");
         System.out.println();
         System.out.println("Press Any Key To Continue...");
         new java.util.Scanner(System.in).nextLine();
      }else{
         System.out.println("=====[Daftar Negara beserta Tetangga]=====");
         graph.printEdge();
         System.out.println();
         System.out.println("Press Any Key To Continue...");
         new java.util.Scanner(System.in).nextLine();
      }
   }
   
   public void showFrame(){
      Frame gui = new Frame(graph);
      gui.setVisible(true);
   }
   
   private void line(){
      System.out.println("============================================================");
   }
   
   public void sleep(int duration) {
      try {
          Thread.sleep(duration);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
  }

   public void play() throws IOException {
      if (back == true){
         return;
      }else{
         do {
            System.out.println("============================================================");
            System.out.println("==                         TEKA-TEKI                      ==");
            System.out.println("==                          SILANG                        ==");
            System.out.println("==                        INFORMATIKA                     ==");
            System.out.println("============================================================");
            System.out.println("Created by: Kelompok 14\n\n");
            line();
            System.out.println("---------------------Petunjuk permainan---------------------");
            line ();
            System.out.println("Tekan W A S D lalu tekan ENTER untuk bergerak...............");
            System.out.println("Setiap 1 jawaban yang benar akan bernilai 10................");
            System.out.println("Tekan ENTER untuk mengisi jawaban...........................");
            System.out.println("Tekan C lalu tekan ENTER mengumpulkan jawaban...............");
            line ();
            System.out.println("----------------Tekan ENTER untuk melanjutkan---------------");
            try {
               // Membaca input dari keyboard dan menampilkan ASCII code-nya
               char value = (char) br.read();
               intTemp_1 = (int) value;
               switch (intTemp_1) {
                  case exit:
                     return;
                  case enter:
                     System.out.print("LOADING");
                     for (int i = 0; i < 5; i++){
                        sleep(300);
                        System.out.print(".");
                     }
                        clearScreen();
                        quest();
                     return;
                  default:
                     System.out.println("WRONG INPUT TYPE !!!");
                     System.out.println("Press Any Key To Continue...");
                     new java.util.Scanner(System.in).nextLine();
                     clearScreen();
                     break;
               }
            } catch (IOException e) {
               // Menangkap kemungkinan terjadinya exception IOException
               System.out.println("Error: " + e.getMessage());
            }
         } while (intTemp_1 != exit);
      }
   }
      
      public void menu() throws IOException{
            do{
            Scanner input = new Scanner (System.in);
            System.out.println("Silahkan pilih menu di bawah ini untuk memulai !");
            System.out.println("1. Masukkan Negara yang ingin bertanding");
            System.out.println("2. Masukkan Tetangga dari setiap Negara");
            System.out.println("3. Hapus Negara");
            System.out.println("4. Hapus tetangga");
            System.out.println("5. Daftar Negara & Tetangga");
            System.out.println("6. Tampilkan Visualisasi");
            System.out.println("7. Tampilkan jarak terdekat antar negara");
            System.out.println("8. Mulai Bermain");
            System.out.println("9. Score");
            System.out.println("E. untuk EXIT");
            System.out.print("Pilih : ");
            choose = input.next().charAt(0);
            input.nextLine();
            switch(choose){
               case '1':
                  clearScreen();
                  countryInput();
                  clearScreen();
                  break;
               case '2':
                  clearScreen();
                  neighborsInput();
                  clearScreen();
                  break;
               case '3':
                  clearScreen();
                  countryRemove();
                  clearScreen();
                  break;
               case '4':
                  clearScreen();
                  neighborsRemove();
                  clearScreen();
                  break;
               case '5':
                  clearScreen();
                  countryList();
                  clearScreen();
                  break;
               case '6':
                  clearScreen();
                  showFrame();
                  clearScreen();
                  break;
               case '7':
                  clearScreen();
                  shortestPath();
                  clearScreen();
                  break;
               case '8':
                  clearScreen();
                  play();
                  clearScreen();
                  break;
               case '9':
                  clearScreen();
                  score();
                  clearScreen();
                  break;
               case 'E':
                  return;
               default:
                  System.out.println("WRONG INPUT TYPE !!!");
                  System.out.println("Press Any Key To Continue...");
                  new java.util.Scanner(System.in).nextLine();
                  clearScreen();
                  break;
            }
         } while (choose != 'E');
      }
}
