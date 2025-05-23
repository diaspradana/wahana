package com.example.wahana.model.service;

import com.example.wahana.model.entity.Wahana;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WahanaService {
    private final List<Wahana> wahanaList = new ArrayList<>();
    private Long idCounter = 1L;

    public WahanaService() {
    tambahWahana(new Wahana(2l, "Kincir Ria", "Wahana ikonik yang membawa pengunjung menikmati pemandangan dari ketinggian.", "Semua Umur", 15000, "\uD83C\uDFA1", 200));
    tambahWahana(new Wahana(3l, "Rumah Hantu", "Rasakan ketegangan saat melewati lorong gelap dengan efek mengejutkan.", "Dewasa/Remaja", 18000, "\uD83D\uDC7B", 200));
    tambahWahana(new Wahana(4l, "Roller Coaster", "Lintasan cepat dan menantang untuk pengunjung yang suka adrenalin.", "Dewasa/Remaja", 20000, "\uD83C\uDFA2", 200));
    tambahWahana(new Wahana(5l, "Komedi Putar", "Wahana berputar dengan desain klasik, cocok untuk keluarga dan anak-anak.", "Semua Umur", 15000, "\uD83C\uDFA0", 200));
    tambahWahana(new Wahana(6l, "Rumah Kaca", "Labirin cermin yang menantang arah dan menciptakan efek visual menarik.", "Semua Umur", 15000, "\uD83E\uDE9E", 200));
    tambahWahana(new Wahana(7l, "Kora-Kora", "Kapal besar yang berayun tinggi, memberikan pengalaman seru dan mendebarkan.", "Dewasa/Remaja", 18000, "⛵", 200));
    tambahWahana(new Wahana(8l, "Kereta Gantung", "Menikmati pemandangan dari atas dengan kereta yang berjalan di atas kabel.", "Semua Umur", 16000, "\uD83D\uDEA0", 200));
    tambahWahana(new Wahana(9l, "Kereta Mini", "Tur santai mengelilingi taman bermain menggunakan kereta kecil.", "Semua Umur", 15000, "\uD83D\uDE82", 200));
    tambahWahana(new Wahana(null, "Bianglala Mini", "Versi mini dari bianglala untuk anak-anak, tetap menyenangkan dan aman.", "Anak-anak", 15000, "\uD83C\uDF88", 200));
    tambahWahana(new Wahana(null, "Cinema 4D", "Wahana bioskop dengan efek gerak, suara, dan getaran untuk pengalaman realistis.", "Dewasa/Remaja", 20000, "\uD83C\uDFAC", 200));
    tambahWahana(new Wahana(null, "Mobil Listrik", "Naik mobil mini di lintasan khusus, cocok untuk anak dan dewasa.", "Semua Umur", 16000, "\uD83D\uDE97", 200));
    tambahWahana(new Wahana(null, "Flying Chair", "Kursi terbang berputar di udara memberikan sensasi melayang yang seru.", "Semua Umur", 17000, "\uD83C\uDF1F", 200));
    }

    // Menampilkan semua wahana
    public List<Wahana> getAllWahana() {
        return new ArrayList<>(wahanaList);
    }

    // Mencari dan sorting wahana
    public List<Wahana> cariWahana(String keyword, String sortBy, String sortDir) {
        List<Wahana> result = new ArrayList<>(wahanaList);

        // Filtering
        if (keyword != null && !keyword.isEmpty()) {
            final String lowerKeyword = keyword.toLowerCase();
            result = result.stream()
                    .filter(w -> w.getNamaWahana().toLowerCase().contains(lowerKeyword))
                    .collect(Collectors.toList());
        }

        // Sorting
        if (sortBy != null && !sortBy.isEmpty()) {
            Comparator<Wahana> comparator = switch (sortBy) {
                case "namaWahana" -> Comparator.comparing(Wahana::getNamaWahana);
                case "kategoriWahana" -> Comparator.comparing(Wahana::getKategori);
                default -> Comparator.comparing(Wahana::getId);
            };

            if ("desc".equals(sortDir)) {
                comparator = comparator.reversed();
            }

            result = result.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        }

        return result;
    }

    // CRUD Operations
    public Wahana tambahWahana(Wahana wahana) {
        wahana.setId(idCounter++);
        wahanaList.add(wahana);
        return wahana;
    }

    public Wahana getWahanaById(Long id) {
        return wahanaList.stream()
                .filter(w -> w.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Wahana saveWahana(Wahana wahana) {
        if (wahana.getId() == null) {
            return tambahWahana(wahana);
        } else {
            return updateWahana(wahana);
        }
    }

    public Wahana updateWahana(Wahana wahana) {
        for (int i = 0; i < wahanaList.size(); i++) {
            if (wahanaList.get(i).getId().equals(wahana.getId())) {
                wahanaList.set(i, wahana);
                return wahana;
            }
        }
        return null;
    }

    public void deleteWahana(Long id) {
        wahanaList.removeIf(w -> w.getId().equals(id));
    }

    // Manajemen Stok
    public boolean kurangiStok(Long wahanaId, int jumlah) {
        Wahana wahana = getWahanaById(wahanaId);
        if (wahana != null && wahana.getStokTiket() >= jumlah) {
            wahana.setStokTiket(wahana.getStokTiket() - jumlah);
            return true;
        }
        return false;
    }

    public List<Wahana> getWahanaByKategori(String kategori) {
        if (kategori == null || kategori.isEmpty()) {
            return getAllWahana();
        }
        return wahanaList.stream()
                .filter(w -> w.getKategori().equals(kategori))
                .collect(Collectors.toList());
    }
}