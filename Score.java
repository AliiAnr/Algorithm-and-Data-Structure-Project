package src.TURU;

public class Score {
   // Field yang menyimpan nilai skor dari negara ini
   int score = 0;

   // Field yang menyimpan nama negara dari skor ini
   String countryName;

   // Field yang menyimpan Score berikutnya dalam daftar Score
   Score next;

   // Constructor untuk membuat Score baru dengan nama negara dan skor tertentu
   Score(String countryName, int score) {
      this.score = score;
      this.countryName = countryName;
   }

}
