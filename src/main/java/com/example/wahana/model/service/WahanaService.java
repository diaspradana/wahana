package com.example.wahana.model.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.example.wahana.model.entity.Wahana;
import org.springframework.stereotype.Service;

@Service
public class WahanaService {
    private final List<Wahana> wahanaList = new ArrayList<>();
    private Long idCounter = 3L;

    public WahanaService() {
        // Data Dummy
        wahanaList.add(new Wahana(1L,"Kincir Ria", "Iso muter bjir", "Semua Umur", 30000, "\uD83C\uDFA1",200));
        wahanaList.add(new Wahana(2L,"Rumah Hantu", "Alamak takutnyooo", "Dewasa/Remaja", 25000, "\uD83D\uDC7B",200));
        wahanaList.add(new Wahana(3L,"Roller Coaster","Kereta jungkir walik muter-muter","Dewasa/Remaja",30000,"\uD83C\uDFA2",200));
        wahanaList.add(new Wahana(4L,"Komedi Putar","Peningnya kepalaku","Semua Umur",20000,"\uD83C\uDFA0",200));
        wahanaList.add(new Wahana(5L,"Rumah Kaca","Kalian bisa lihat wajah diri sendiri yang buruk rupa dari segala sisi awokawok","Semua Umur",18000,"\uD83E\uDE9E",200));
        wahanaList.add(new Wahana(6L,"Kora-Kora","Kapal fliying Dutchman oleng bjirr","Dewasa/Remaja",25000,"⛵",200));
        wahanaList.add(new Wahana(7L,"Kereta Gantung","Kereta tapi gantung?","Semua Umur",20000,"\uD83D\uDEA0",200));
        wahanaList.add(new Wahana(8L,"Kereta Mini","Intinya kalian di ajak berkeliling taman bermain sampai JMK48 debut","Semua Umur",18000,"\uD83D\uDE82",200));
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
