package src.TURU;

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {
   // Field yang menyimpan graph yang akan ditampilkan
   private Graph graph;

   // Field yang menyimpan jari-jari dari vertex dalam frame ini
   private int vertexRadius = 20;

   // Field yang menyimpan jarak antar vertex dalam frame ini
   private int vertexSpacing = 150;

   // Field yang menyimpan panjang dari tepi dalam frame ini
   private int edgeLength = 500;

   // Field yang menyimpan kemiringan tepi dalam frame ini
   private int edgeCurve = 50;

   // Constructor untuk membuat frame baru dengan graph tertentu
   public Frame(Graph graph) {
      this.graph = graph;
      setTitle("Graph Representation");
      setSize(800, 600);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

   // Method yang menggambar komponen dari frame ini
   public void paint(Graphics g) {
      super.paint(g);
      drawGraph(g);
   }

   // Method yang menggambar graph dalam frame ini
   private void drawGraph(Graphics g) {
      // Iterasi melalui setiap vertex dalam graph
      Vertex current = graph.head;
      int x = vertexSpacing;
      while (current != null) {
         // Gambar vertex saat ini
         drawVertex(g, current, x, vertexRadius);
         // Iterasi melalui setiap tepi yang terhubung dengan vertex saat ini
         Edge visit = current.route;
         while (visit != null) {
            // Gambar tepi saat ini
            drawEdge(g, current, visit.goal, x, edgeLength, edgeCurve);
            visit = visit.next;
         }
         current = current.next;
         x += vertexSpacing;
      }
   }

   // Method yang menggambar vertex dalam frame ini
   private void drawVertex(Graphics g, Vertex vertex, int x, int radius) {
      // Set warna hitam untuk menggambar vertex
      g.setColor(Color.BLACK);
      // Gambar lingkaran untuk vertex
      g.fillOval(x - radius, getHeight() / 2 - radius, 2 * radius, 2 * radius);
      // Set warna abu-abu untuk menuliskan nama negara di dalam vertex
      g.setColor(Color.GRAY);
      // Set font untuk menuliskan nama negara di dalam vertex
      g.setFont(new Font("Arial", Font.BOLD, 14));
      // Tuliskan nama negara di tengah vertex
      g.drawString(vertex.getCountry(), x - g.getFontMetrics().stringWidth(vertex.getCountry()) / 2,
            getHeight() / 2 + 5);
   }

   // Method yang menggambar tepi dalam frame ini
   private void drawEdge(Graphics g, Vertex start, Vertex end, int x, int length, int curve) {
      // Dapatkan koordinat x dari vertex start
      int startX = x;
      // Dapatkan koordinat y dari vertex start
      int startY = getHeight() / 2;
      // Dapatkan koordinat x dari vertex end
      int endX = vertexSpacing * (getVertexIndex(end) + 1);
      // Dapatkan koordinat y dari vertex end
      int endY = getHeight() / 2;
      // Dapatkan koordinat x dari titik kontrol tepi (titik di tengah tepi yang
      // menentukan kemiringan tepi)
      int controlX = startX + (endX - startX) / 2;
      // Dapatkan koordinat y dari titik kontrol tepi
      int controlY = startY - curve;
      // Set warna hitam untuk menggambar tepi
      g.setColor(Color.BLACK);
      // Gambar bagian atas tepi (dari vertex start ke titik kontrol tepi)
      g.drawLine(startX, startY, controlX, controlY);
      // Gambar bagian bawah tepi (dari titik kontrol tepi ke vertex end)
      g.drawLine(controlX, controlY, endX, endY);

      // Set warna merah untuk menggambar panah di ujung tepi
      g.setColor(Color.RED);
      // Gambar poligon berbentuk panah di ujung tepi
      g.fillPolygon(new int[] { endX, endX - 5, endX + 5 }, new int[] { endY - 5, endY + 5, endY + 5 }, 3);
   }

   // Method yang mengembalikan indeks dari vertex dalam graph
   private int getVertexIndex(Vertex vertex) {
      // Iterasi melalui setiap vertex dalam graph
      Vertex current = graph.head;
      int index = 0;
      while (current != vertex) {
         current = current.next;
         index++;
      }
      return index;
   }
}