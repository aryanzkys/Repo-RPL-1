# 💻 Sistem Kasir Sederhana (Java)

## 📘 Deskripsi
Implementasi sistem kasir berbasis **Object-Oriented Programming (OOP)** dengan bahasa **Java**. Versi terbaru memperluas fungsionalitas menjadi platform kasir multi-user dengan dukungan login, pengelolaan stok terpusat, diskon, beragam metode pembayaran, serta riwayat transaksi untuk setiap kasir maupun sistem secara global.

Proyek ini dikembangkan untuk memenuhi tugas **LK02 - Implementasi Perangkat Lunak (Individual Task)**  
**Nama:** Aryan Zaky Prayogo  
**NIM:** 255150207111059  
**Program Studi:** Teknik Informatika, Universitas Brawijaya

---

## ⚙️ Fitur Utama
1. **Login Kasir** - Autentikasi user kasir dengan username & password untuk memulai sesi kerja.
2. **Mulai Transaksi Baru** - Membuat transaksi baru dengan status `PENDING` dan cap waktu.
3. **Input Barang** - Menambahkan atau memperbarui item transaksi berdasarkan kode barang & jumlah.
4. **Hitung Total Harga** - Menghitung subtotal per item dan total keseluruhan secara otomatis.
5. **Penerapan Diskon** - Validasi kode diskon dengan dukungan persentase/potongan dan batas maksimal.
6. **Pilih Metode Pembayaran** - Menetapkan metode pembayaran (cash, card, e-wallet) sebelum checkout.
7. **Selesaikan Transaksi** - Mengubah status menjadi `COMPLETED`, mencatat waktu selesai, dan memperbarui stok.
8. **Update Stok Barang** - Menjaga stok barang secara thread-safe ketika transaksi selesai.
9. **Simpan Riwayat Transaksi** - Menyimpan transaksi yang selesai ke riwayat kasir dan riwayat global.
10. **Dukungan Multi-Kasir (Concurrency)** - Banyak kasir dapat aktif bersamaan melalui sesi berbeda tanpa konflik data.

---

## 🧩 Struktur Kelas
| Class | Deskripsi |
|:--|:--|
| `Barang` | Menyimpan data barang dengan stok thread-safe (`AtomicInteger`). |
| `ItemTransaksi` | Snapshot item dalam transaksi (kode, nama, harga, jumlah, subtotal). |
| `Transaksi` | Mengelola lifecycle transaksi (status, diskon, pembayaran, penyelesaian). |
| `Inventory` | Menyediakan gudang barang bersama untuk seluruh kasir. |
| `DiskonService` | Menyimpan & memvalidasi kode diskon yang tersedia. |
| `Kasir` | Menangani kredensial kasir, riwayat transaksi, dan penyelesaian transaksi. |
| `KasirService` | Registrasi kasir, login, dan manajemen sesi aktif. |
| `KasirSession` | Menyimpan konteks sesi kasir yang sedang bekerja. |
| `RiwayatTransaksi` | Menampung log transaksi yang sudah selesai secara global. |
| `SistemKasirApp` | Program utama berbasis CLI yang mengorkestrasi seluruh fitur. |

---

## 📂 Struktur Proyek
```
📦 SistemKasir
┣ 📜 Barang.java
┣ 📜 DiskonService.java
┣ 📜 Inventory.java
┣ 📜 ItemTransaksi.java
┣ 📜 Kasir.java
┣ 📜 KasirService.java
┣ 📜 KasirSession.java
┣ 📜 RiwayatTransaksi.java
┣ 📜 SistemKasirApp.java
┗ 📜 Transaksi.java
```

---

## 🧠 Alur Use Case Utama
1. Kasir melakukan login dan memperoleh `sessionId` unik.
2. Kasir memulai transaksi baru (status `PENDING`).
3. Kasir menambahkan atau memperbarui item berdasarkan stok di `Inventory`.
4. Sistem menghitung subtotal, total, dan potongan diskon (jika ada).
5. Kasir memilih metode pembayaran yang tersedia.
6. Kasir menyelesaikan transaksi → stok berkurang, status menjadi `COMPLETED`.
7. Transaksi tersimpan ke riwayat kasir dan riwayat global.

---

## ▶️ Cara Menjalankan Program
1. Pastikan **Java JDK 17 atau lebih baru** telah terpasang.
2. Buka terminal pada folder `SistemKasir`.
3. Kompilasi seluruh kelas:
	```bash
	javac *.java
	```
4. Jalankan aplikasi utama:
	```bash
	java SistemKasirApp
	```
5. Ikuti menu CLI untuk login, memulai transaksi, dan menguji setiap fitur.

---

## 🧾 Cuplikan Interaksi CLI
```
===== SISTEM KASIR ZAKY-MART =====
Username kasir: aryan
Password: 12345
Login berhasil. Session ID: 07dd4f7d-...

=== MENU SISTEM KASIR ===
1. Mulai transaksi baru
...
Transaksi KSR001-1699612345678 selesai.
Total pembayaran: Rp45000.0
```

---

## 🧱 Konsep OOP & Praktik Baik
- **Encapsulation** - Atribut privat dengan akses melalui getter/setter/metode domain.
- **Single Responsibility** - Setiap kelas fokus pada domain spesifik (inventory, diskon, sesi, dst.).
- **Thread Safety** - `AtomicInteger`, `ConcurrentHashMap`, dan `CopyOnWriteArrayList` memastikan multi-kasir aman.
- **Immutability & Snapshot** - `ItemTransaksi` menyimpan snapshot sehingga riwayat tetap konsisten meskipun data barang berubah.

---

## 🚀 Pengembangan Selanjutnya
- Integrasi database untuk penyimpanan persisten.
- Penerapan antarmuka GUI (JavaFX/Swing) atau REST API.
- Laporan penjualan harian dan analitik pendapatan.

---

## 📄 Lisensi
Proyek ini dibuat untuk keperluan akademik dan dapat digunakan sebagai referensi pembelajaran OOP.  
Hak cipta © 2025 oleh **Aryan Zaky Prayogo**.

