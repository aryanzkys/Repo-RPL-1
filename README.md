# 💻 Sistem Kasir Sederhana (Java)

## 📘 Deskripsi
Proyek ini merupakan implementasi sederhana dari sistem kasir berbasis **Object-Oriented Programming (OOP)** menggunakan bahasa **Java**.  
Fitur utama yang diimplementasikan adalah **pemrosesan transaksi penjualan**, mulai dari penambahan barang ke transaksi, perhitungan total harga, hingga penyelesaian transaksi dengan pembaruan stok barang secara otomatis.

Proyek ini dikembangkan untuk memenuhi tugas **LK02 - Implementasi Perangkat Lunak (Individual Task)**  
**Nama:** Aryan Zaky Prayogo  
**NIM:** 255150207111059  
**Program Studi:** Teknik Informatika, Universitas Brawijaya  

---

## ⚙️ Fitur Utama
- Menambahkan barang ke dalam transaksi.  
- Menghitung subtotal dan total harga pembelian.  
- Mengurangi stok barang secara otomatis setelah transaksi.  
- Menyelesaikan transaksi dengan metode pembayaran tertentu.  
- Menampilkan hasil transaksi ke layar (CLI Output).  

---

## 🧩 Struktur Kelas (Berdasarkan Class Diagram)
| Class | Deskripsi |
|:--|:--|
| `Barang` | Menyimpan informasi barang (kode, nama, harga, stok) dan mengelola pembaruan stok. |
| `ItemTransaksi` | Menyimpan detail setiap item dalam transaksi seperti jumlah dan subtotal. |
| `Transaksi` | Mengelola daftar item, menghitung total harga, dan menyelesaikan transaksi. |
| `Kasir` | Mewakili pengguna sistem yang memulai dan mengelola transaksi. |
| `SistemKasirApp` | Kelas utama yang menjalankan simulasi sistem kasir. |

---

## 📂 Struktur Proyek
```

📦 SistemKasir
┣ 📜 Barang.java
┣ 📜 ItemTransaksi.java
┣ 📜 Transaksi.java
┣ 📜 Kasir.java
┗ 📜 SistemKasirApp.java

````

---

## 🧠 Alur Use Case: “Kasir Memproses Transaksi Penjualan Barang”
1. Kasir login dan memulai transaksi baru.  
2. Kasir menambahkan barang ke transaksi dengan jumlah tertentu.  
3. Sistem menghitung subtotal setiap item dan total keseluruhan.  
4. Stok barang berkurang secara otomatis sesuai jumlah pembelian.  
5. Transaksi diselesaikan dengan metode pembayaran tertentu.  
6. Sistem menampilkan total harga dan status transaksi.

---

## ▶️ Cara Menjalankan Program
1. Pastikan **Java JDK 17 atau lebih baru** sudah terpasang di komputer kamu.  
2. Simpan semua file `.java` dalam satu folder.  
3. Buka terminal atau command prompt, lalu arahkan ke folder proyek.  
4. Jalankan perintah berikut:

```bash
javac SistemKasirApp.java
java SistemKasirApp
````



## 🧱 Konsep OOP yang Diterapkan

* **Encapsulation:** Setiap class memiliki atribut privat dengan getter dan method spesifik.
* **Association:** Relasi antar class mencerminkan hubungan nyata antara Kasir, Transaksi, Barang, dan ItemTransaksi.
* **Abstraction:** Tiap class memiliki tanggung jawab terpisah sesuai domain bisnis.
* **Reusability:** Struktur kode modular dan dapat diperluas (misalnya penambahan fitur diskon, metode pembayaran digital, atau laporan penjualan).

---

## 🚀 Pengembangan Selanjutnya

* Menambahkan antarmuka pengguna berbasis GUI (JavaFX/Swing).
---

## 📄 Lisensi

Proyek ini dibuat untuk keperluan akademik dan dapat digunakan sebagai referensi pembelajaran OOP.
Hak cipta © 2025 oleh **Aryan Zaky Prayogo**.

