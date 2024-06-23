# Submission Aplikasi Story App : Kirim dan Review

Proyek ini dibuat sebagai contoh untuk belajar. Gunakan dengan bijak dan jangan berlebihan, karena tindakan plagiarisme dapat menyebabkan akun Anda diblokir.

## Kriteria Submission

Aplikasi ini harus memenuhi beberapa fitur berikut:

### Halaman Autentikasi

1. **Halaman Login**:
   - Menampilkan halaman login dengan input:
     - Email: `R.id.ed_login_email`
     - Password: `R.id.ed_login_password`

2. **Halaman Register**:
   - Membuat halaman register dengan input:
     - Nama: `R.id.ed_register_name`
     - Email: `R.id.ed_register_email`
     - Password: `R.id.ed_register_password`
   - Password wajib disembunyikan.
   - Membuat Custom View berupa EditText pada halaman login atau register dengan ketentuan:
     - Jika jumlah password kurang dari 8 karakter, menampilkan pesan error secara langsung pada EditText tanpa harus pindah form atau klik tombol dulu.

3. **Menyimpan Data Sesi dan Token di Preferences**:
   - Data sesi digunakan untuk mengatur alur aplikasi dengan spesifikasi:
     - Jika sudah login langsung masuk ke halaman utama.
     - Jika belum login maka akan masuk ke halaman login.

4. **Fitur Logout**:
   - Terdapat fitur untuk logout (R.id.action_logout) pada halaman utama dengan ketentuan:
     - Ketika tombol logout ditekan, informasi token dan sesi harus dihapus.

### Daftar Cerita (List Story)

1. **Menampilkan Daftar Cerita dari API**:
   - Minimal informasi yang wajib ditampilkan:
     - Nama user: `R.id.tv_item_name`
     - Foto: `R.id.iv_item_photo`

2. **Tampilan Detail Cerita**:
   - Muncul tampilan detail ketika salah satu item cerita ditekan.
   - Minimal informasi yang wajib ditampilkan:
     - Nama user: `R.id.tv_detail_name`
     - Foto: `R.id.iv_detail_photo`
     - Deskripsi: `R.id.tv_detail_description`

### Tambah Cerita

1. **Halaman Tambah Cerita**:
   - Membuat halaman untuk menambah cerita baru yang dapat diakses dari halaman daftar cerita.
   - Input minimal yang dibutuhkan:
     - File foto (wajib bisa dari gallery)
     - Deskripsi cerita: `R.id.ed_add_description`

2. **Ketentuan Tambah Cerita**:
   - Terdapat tombol `R.id.button_add` untuk upload data ke server.
   - Setelah tombol tersebut diklik dan proses upload berhasil, maka akan kembali ke halaman daftar cerita.
   - Data cerita terbaru harus muncul di paling atas.

### Menampilkan Animasi

1. **Jenis Animasi**:
   - Membuat animasi pada aplikasi dengan menggunakan salah satu jenis animasi berikut:
     - Property Animation
     - Motion Animation
     - Shared Element

2. **Student Note**:
   - Tuliskan jenis dan lokasi animasi pada Student Note.

