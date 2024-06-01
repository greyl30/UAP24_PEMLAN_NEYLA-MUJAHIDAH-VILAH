import java.util.Scanner;

public class AksiAdmin extends Aksi {
    @Override
    public void tampilanAksi() {
        System.out.println("Aksi Admin:");
        System.out.println("1. Tambah Film");
        System.out.println("2. Lihat List Film");
        System.out.println("3. Logout");
        System.out.println("4. Tutup Aplikasi");
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
        if (Film.getFilms().isEmpty())  {
            System.out.println("Tidak ada film tersedia.");
        
        } else {
            for (Film film : Film.getFilms().values()) {
                System.out.println("Film: " + film.getName() + " - Deskripsi: " + film.getDescription() + " - Harga: " + film.getPrice() + " - Stok: " + film.getStock());
            }
        }
    }

    public void tambahFilm() {
        // Implementasi menambahkan film
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nama Film: ");
        String name = scanner.nextLine();

        System.out.println("Deskripsi Film: ");
        String description = scanner.nextLine();

        System.out.println("Harga Tiket: ");
        double price = scanner.nextDouble();

        System.out.println("Stok Tiket: ");
        int stock = scanner.nextInt();

        Film.addFilm(name, description, price, stock);
    }
}