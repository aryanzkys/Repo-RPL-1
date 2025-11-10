# 📖 Tutorial Penggunaan Sistem Kasir ZAKY-MART

Selamat datang di Sistem Kasir ZAKY-MART! Tutorial ini akan membantu Anda menggunakan sistem kasir dengan mudah. Panduan ini dibuat dengan bahasa sederhana agar siapa saja dapat memahaminya.

---

## 🚀 Cara Memulai Program

### Langkah 1: Membuka Program
1. Buka folder **SistemKasir** di komputer Anda
2. Klik kanan di dalam folder, pilih **"Open in Terminal"** atau **"Buka di Command Prompt"**
3. Ketik perintah berikut:
   ```
   java SistemKasirApp
   ```
4. Tekan **Enter**

### Langkah 2: Login ke Sistem
Setelah program terbuka, Anda akan diminta untuk login.

**Akun Kasir yang Tersedia:**
- **Kasir 1:**
  - Username: `aryan`
  - Password: `12345`

- **Kasir 2:**
  - Username: `zaky`
  - Password: `54321`

**Contoh Login:**
```
===== SISTEM KASIR ZAKY-MART =====
Username kasir: aryan
Password: 12345
Login berhasil. Session ID: xxx-xxx-xxx
```

⚠️ **Catatan:** Anda punya 3 kali kesempatan untuk login. Jika salah 3 kali, program akan tertutup.

---

## 📋 Menu Utama

Setelah login berhasil, Anda akan melihat menu seperti ini:

```
=== MENU SISTEM KASIR ===
1. Mulai transaksi baru
2. Tambah barang ke transaksi aktif
3. Terapkan atau hapus diskon
4. Pilih metode pembayaran
5. Selesaikan transaksi
6. Lihat ringkasan transaksi aktif
7. Lihat riwayat transaksi kasir
8. Lihat stok barang
9. Lihat riwayat transaksi sistem
0. Logout dan keluar
```

---

## 🛍️ Cara Melayani Pembeli (Langkah demi Langkah)

### 📝 Langkah 1: Mulai Transaksi Baru
1. Pilih menu **1** (Mulai transaksi baru)
2. Sistem akan membuat kode transaksi otomatis
3. Anda siap untuk menambahkan barang!

**Contoh:**
```
Pilih menu: 1
Transaksi baru dimulai dengan kode: KSR001-1731234567890
```

---

### 🛒 Langkah 2: Tambahkan Barang yang Dibeli Pelanggan

1. Pilih menu **2** (Tambah barang ke transaksi aktif)
2. Sistem akan menampilkan daftar barang yang tersedia
3. Ketik **kode barang** yang ingin dibeli
4. Ketik **jumlah barang** yang dibeli
5. Sistem akan menambahkan barang ke transaksi

**Daftar Barang yang Tersedia:**
- BRG01 - Sabun Mandi (Rp 5.000)
- BRG02 - Sampo (Rp 12.000)
- BRG03 - Pasta Gigi (Rp 9.000)
- BRG04 - Tissue (Rp 8.000)
- BRG05 - Hand Sanitizer (Rp 15.000)

**Contoh:**
```
Pilih menu: 2

=== Daftar Barang ===
Sabun Mandi (BRG01) - Rp5000.0 | Stok: 25
Sampo (BRG02) - Rp12000.0 | Stok: 15
...

Masukkan kode barang: BRG01
Masukkan jumlah: 2
Barang berhasil ditambahkan ke transaksi.
```

**Tips:** Ulangi langkah ini untuk setiap barang yang dibeli pelanggan!

---

### 💰 Langkah 3: Terapkan Diskon (Jika Ada)

Jika pelanggan punya **kode diskon**, ikuti langkah ini:

1. Pilih menu **3** (Terapkan atau hapus diskon)
2. Sistem akan menampilkan daftar diskon yang berlaku
3. Ketik **kode diskon** yang diberikan pelanggan
4. Sistem akan menghitung potongan harga otomatis

**Kode Diskon yang Tersedia:**
- **HEMAT10** - Diskon 10% (maksimal potongan Rp 20.000)
- **LEBIHHEMAT** - Potongan langsung Rp 5.000
- **SUPER20** - Diskon 20% (maksimal potongan Rp 50.000)

**Contoh:**
```
Pilih menu: 3

=== Daftar Diskon Aktif ===
Kode HEMAT10 - Potongan 10.0% + Rp0.0 (maks Rp20000.0)
Kode LEBIHHEMAT - Potongan 0.0% + Rp5000.0
Kode SUPER20 - Potongan 20.0% + Rp0.0 (maks Rp50000.0)

Masukkan kode diskon (kosongkan untuk menghapus): HEMAT10
Diskon berhasil diterapkan.
```

**Catatan:** Jika pelanggan tidak punya diskon, lewati langkah ini!

---

### 💳 Langkah 4: Pilih Metode Pembayaran

1. Pilih menu **4** (Pilih metode pembayaran)
2. Tanyakan ke pelanggan: **"Mau bayar pakai apa?"**
3. Pilih nomor sesuai jawaban pelanggan:
   - Ketik **1** untuk Cash (Tunai)
   - Ketik **2** untuk Kartu (Debit/Kredit)
   - Ketik **3** untuk E-Wallet (GoPay, OVO, DANA, dll)

**Contoh:**
```
Pilih menu: 4

Pilih metode pembayaran:
1. Cash
2. Kartu debit/kredit
3. E-Wallet
Pilihan: 1
Metode pembayaran diset ke: Cash
```

---

### ✅ Langkah 5: Selesaikan Transaksi

Setelah semua barang ditambahkan, diskon diterapkan (jika ada), dan metode pembayaran dipilih:

1. Pilih menu **5** (Selesaikan transaksi)
2. Sistem akan memproses transaksi
3. **Stok barang otomatis berkurang**
4. Transaksi tersimpan ke riwayat
5. Kasir siap melayani pembeli berikutnya!

**Contoh:**
```
Pilih menu: 5
Transaksi KSR001-1731234567890 selesai.
Total pembayaran: Rp10000.0
```

---

## 📊 Fitur Tambahan

### 🔍 Menu 6: Lihat Ringkasan Transaksi Aktif
Gunakan menu ini jika Anda ingin **melihat detail transaksi** yang sedang berjalan sebelum diselesaikan.

**Contoh:**
```
Pilih menu: 6

Kode Transaksi: KSR001-1731234567890
Status: PENDING
Mulai: 2025-11-10T14:30:00
Metode Pembayaran: Cash
Daftar Item:
Sabun Mandi x2 = Rp 10000.0
Subtotal: Rp10000.0
Diskon: Rp0.0
Total: Rp10000.0
```

---

### 📜 Menu 7: Lihat Riwayat Transaksi Kasir
Anda bisa melihat **semua transaksi** yang sudah Anda selesaikan hari ini.

**Kegunaan:** Untuk mengecek apakah transaksi sudah tersimpan dengan benar.

---

### 📦 Menu 8: Lihat Stok Barang
Gunakan menu ini untuk **mengecek stok barang** yang masih tersedia di toko.

**Contoh:**
```
Pilih menu: 8

=== Daftar Barang ===
Sabun Mandi (BRG01) - Rp5000.0 | Stok: 23
Sampo (BRG02) - Rp12000.0 | Stok: 15
Pasta Gigi (BRG03) - Rp9000.0 | Stok: 20
Tissue (BRG04) - Rp8000.0 | Stok: 30
Hand Sanitizer (BRG05) - Rp15000.0 | Stok: 18
```

**Tips:** Jika stok barang hampir habis, segera laporkan ke atasan!

---

### 📚 Menu 9: Lihat Riwayat Transaksi Sistem
Menu ini menampilkan **semua transaksi dari semua kasir**. Biasanya digunakan oleh supervisor atau manager.

---

### 🚪 Menu 0: Logout dan Keluar
Gunakan menu ini jika Anda sudah selesai bekerja atau ingin **logout dari sistem**.

**Contoh:**
```
Pilih menu: 0
Terima kasih. Sampai jumpa!
```

---

## ⚠️ Tips dan Peringatan Penting

### ✅ Yang Harus Dilakukan:
1. **Selalu mulai transaksi baru** (Menu 1) sebelum melayani pelanggan baru
2. **Cek stok barang** (Menu 8) jika pelanggan ingin membeli dalam jumlah banyak
3. **Pilih metode pembayaran** (Menu 4) sebelum menyelesaikan transaksi
4. **Selesaikan transaksi** (Menu 5) setelah semua barang ditambahkan

### ❌ Yang Tidak Boleh Dilakukan:
1. **Jangan langsung tambah barang** tanpa mulai transaksi baru dulu
2. **Jangan lupa selesaikan transaksi** sebelum melayani pelanggan berikutnya
3. **Jangan menutup program** saat masih ada transaksi yang belum selesai

---

## 🆘 Mengatasi Masalah Umum

### ❓ "Tidak ada transaksi aktif"
**Penyebab:** Anda belum mulai transaksi baru  
**Solusi:** Pilih menu **1** untuk mulai transaksi baru

---

### ❓ "Stok tidak mencukupi"
**Penyebab:** Barang yang diminta pelanggan melebihi stok yang tersedia  
**Solusi:** 
- Cek stok tersedia dengan menu **8**
- Beritahu pelanggan stok yang tersedia
- Kurangi jumlah pembelian

---

### ❓ "Metode pembayaran belum dipilih"
**Penyebab:** Anda belum memilih cara pembayaran  
**Solusi:** Pilih menu **4** dan tentukan metode pembayaran

---

### ❓ "Transaksi belum memiliki barang"
**Penyebab:** Anda mencoba selesaikan transaksi tanpa menambah barang  
**Solusi:** Tambahkan barang dulu dengan menu **2**

---

### ❓ "Kode diskon tidak valid"
**Penyebab:** Kode diskon yang diketik salah atau tidak ada  
**Solusi:** 
- Cek daftar diskon yang tersedia
- Ketik ulang kode diskon dengan benar
- Pastikan huruf besar/kecil sesuai

---

## 🎯 Contoh Kasus Lengkap: Melayani Pembeli

**Situasi:** Ibu Ani membeli 2 Sabun Mandi dan 1 Sampo, punya kode diskon HEMAT10, bayar pakai Cash.

**Langkah-langkah:**

1. **Login**
   ```
   Username: aryan
   Password: 12345
   ```

2. **Mulai Transaksi** (Menu 1)
   ```
   Pilih menu: 1
   ```

3. **Tambah Sabun Mandi** (Menu 2)
   ```
   Pilih menu: 2
   Masukkan kode barang: BRG01
   Masukkan jumlah: 2
   ```

4. **Tambah Sampo** (Menu 2)
   ```
   Pilih menu: 2
   Masukkan kode barang: BRG02
   Masukkan jumlah: 1
   ```

5. **Terapkan Diskon** (Menu 3)
   ```
   Pilih menu: 3
   Masukkan kode diskon: HEMAT10
   ```

6. **Pilih Pembayaran Cash** (Menu 4)
   ```
   Pilih menu: 4
   Pilihan: 1
   ```

7. **Selesaikan Transaksi** (Menu 5)
   ```
   Pilih menu: 5
   ```

8. **Selesai!** Kasir siap melayani pembeli berikutnya.

---

## 🌟 Keuntungan Sistem Kasir Ini

✅ **Otomatis** - Stok berkurang sendiri setelah transaksi selesai  
✅ **Akurat** - Perhitungan total dan diskon otomatis  
✅ **Aman** - Setiap kasir punya akun sendiri  
✅ **Lengkap** - Ada riwayat transaksi untuk laporan  
✅ **Cepat** - Tidak perlu hitung manual  

---

## 💡 Kesimpulan

Sistem Kasir ZAKY-MART dirancang untuk **memudahkan pekerjaan kasir**. Dengan mengikuti tutorial ini, Anda bisa melayani pelanggan dengan cepat dan efisien.

**Ingat urutan utamanya:**
1. Login → 2. Mulai Transaksi → 3. Tambah Barang → 4. Pilih Pembayaran → 5. Selesaikan

---

**Selamat bekerja! 🎉**

Jika ada pertanyaan atau masalah, hubungi supervisor atau admin sistem.
