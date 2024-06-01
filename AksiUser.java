import java.util.Scanner;

public class AksiUser extends Aksi {
    @Override
    public void tampilanAksi() {
        System.out.println("Aksi User:");
        System.out.println("1. Pesan Film");
        System.out.println("2. Lihat Saldo");
        System.out.println("3. Lihat List Film");
        System.out.println("4. Lihat Pesanan");
        System.out.println("5. Logout");
        System.out.println("6. Tutup Aplikasi");
    }

    @Override
    public void keluar() {
        Akun.logout();
        System.out.println("Anda telah logout.");
    }

    @Override
    public void tutupAplikasi() {
        System.out.println("Aplikasi ditutup.");
        System.exit(0);
    }

    @Override
    public void lihatListFilm() {
        // Implementasi melihat list film
        if (Film.getFilms().isEmpty()) {
            System.out.println("Tidak ada film tersedia.");
        
        } else {
            for (Film film : Film.getFilms().values()) {
                System.out.println("Film: " + film.getName() + " - Deskripsi: " + film.getDescription() + " - Harga: " + film.getPrice() + " - Stok: " + film.getStock());
            } 
        }
    }

    public void lihatSaldo() {
        // Implementasi lihat Saldo
        System.out.println("Saldo anda: " + Akun.getCurrentUser().getSaldo());
    }

    public void pesanFilm() {
        // Implementasi pemesanan film
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nama Film yang ingin dipesan");
        String filmName = scanner.nextLine();
        Film film = Film.getFilms().get(filmName);

        if (film == null) {
            System.out.println("Film yang dicari tidak ditemukan");
            return;
        }

        System.out.println("Jumlah tiket yang ingin dipesan");
        int jumlah = scanner.nextInt();

        if (jumlah > film.getStock()) {
            System.out.println("Stok tiket tidak mencukupi.");
            return;
        }

        double totalHarga = jumlah * film.getPrice();

        if (Akun.getCurrentUser().getSaldo() < totalHarga) {
            System.out.println("Saldo tidak mencukupi, saldo yang dimiliki " + Akun.getCurrentUser().getSaldo());
            return;
        }

        Akun.getCurrentUser().setSaldo(Akun.getCurrentUser().getSaldo() - totalHarga);
        film.setStock(film.getStock() - jumlah);
        Akun.getCurrentUser().addPesanan(film, jumlah);

        System.out.println("Harga satuan tiket " + film.getPrice());
        System.out.println("Total harga " + totalHarga);
        System.out.println("Tiket berhasil dipesan.");
    }

    public void lihatPesanan() {
        // Implementasi melihat pesanan
        if (Akun.getCurrentUser().getPesanan().isEmpty()) {
            System.out.println("Kamu belum pernah melakukan pemesanan.");
        } else {
            for (Pesanan pesanan : Akun.getCurrentUser().getPesanan().values()) {
                System.out.print("Film: " + pesanan.getFilm().getName() + " - Jumlah: " + pesanan.getKuantitas() + " - Total Harga: " + (pesanan.getKuantitas() * pesanan.getFilm().getPrice()));
            }
        }
    }
}