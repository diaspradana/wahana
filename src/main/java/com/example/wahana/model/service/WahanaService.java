package com.example.wahana.model.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.example.wahana.model.entity.Wahana;

public class WahanaService {
    private final List<Wahana> wahanaList = new ArrayList<>();
    private Long idCounter = 2L;

    public WahanaService() {
        // Data Dummy
        wahanaList.add(new Wahana(1L, "Bianglala", "Iso muter bjir", "Semua Umur", 30000, "\uD83C\uDFA1"));
        wahanaList.add(new Wahana(2L, "Rumah Hantu", "Alamak takutnyooo", "Dewasa", 25000, "\uD83D\uDC7B"));
    }

    // Menampilkan semua list wahana
    public List<Wahana> getAllWahana(){
        return wahanaList;
    }

    // Searching dan Sorting
    public  List<Wahana> cariWahana(String keyword, String sortBy, String sortDir){
        List<Wahana> result = wahanaList;

        if (keyword != null && !keyword.isEmpty()) {
            final String lowerKeyword = keyword.toLowerCase();
            result = result.stream().filter(w -> w.getNamaWahana().toLowerCase().contains(lowerKeyword)).toList();
        }

        // Sorting wahana
        if (sortBy != null && !sortBy.isEmpty()){
            Comparator<Wahana> comparator;

            switch (sortBy) {
                case "namaWahana":
                    comparator = Comparator.comparing(Wahana::getNamaWahana);
                    break;
                case "kategoriWahana":
                    comparator = Comparator.comparing(Wahana::getKategori);
                    break;
                case "id":
                default:
                    comparator = Comparator.comparing(Wahana::getId);
                    break;
            }

            if ("desc".equals(sortDir)){
                comparator = comparator.reversed();
            }

            result = result.stream().sorted(comparator).collect(Collectors.toList());
        }

        return result;
    }

    public Wahana saveWahana(Wahana wahana) {
        if (wahana.getId() == null){
            wahana.setId(idCounter++);
            wahanaList.add(wahana);
        }
        return wahana;
    }

    public Wahana getWahanaById(Long id){
        return wahanaList.stream().filter(w -> w.getId().equals(id)).findFirst().orElse(null);
    }

    public void updateWahana(Wahana wahana){
        wahanaList.replaceAll(w -> w.getId().equals(wahana.getId()) ? wahana : w);
    }

    public void deleteWahana(Long id){
        wahanaList.removeIf(w -> w.getId().equals(id));
    }

    public Wahana getWahanaByID(Long id){
        return wahanaList.stream().filter(w -> w.getId().equals(id)).findFirst().orElse(null);
    }
}
