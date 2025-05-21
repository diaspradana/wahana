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
        // Inisialisasi data dummy
        tambahWahana(new Wahana(null, "Kincir Ria", "Iso muter bjir", "Semua Umur", 30000, "\uD83C\uDFA1", 200));
        tambahWahana(new Wahana(null, "Rumah Hantu", "Alamak takutnyooo", "Dewasa/Remaja", 25000, "\uD83D\uDC7B", 200));
        tambahWahana(new Wahana(null, "Roller Coaster", "Kereta jungkir walik muter-muter", "Dewasa/Remaja", 30000, "\uD83C\uDFA2", 200));
        tambahWahana(new Wahana(null, "Komedi Putar", "Peningnya kepalaku", "Semua Umur", 20000, "\uD83C\uDFA0", 200));
        tambahWahana(new Wahana(null, "Rumah Kaca", "Kalian bisa lihat wajah diri sendiri yang buruk rupa dari segala sisi awokawok", "Semua Umur", 18000, "\uD83E\uDE9E", 200));
        tambahWahana(new Wahana(null, "Kora-Kora", "Kapal flying Dutchman oleng bjirr", "Dewasa/Remaja", 25000, "⛵", 200));
        tambahWahana(new Wahana(null, "Kereta Gantung", "Kereta tapi gantung?", "Semua Umur", 20000, "\uD83D\uDEA0", 200));
        tambahWahana(new Wahana(null, "Kereta Mini", "Intinya kalian di ajak berkeliling taman bermain sampai JMK48 debut", "Semua Umur", 18000, "\uD83D\uDE82", 200));
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
}