package BW5group5.GestioneClientiFatture.service;

import BW5group5.GestioneClientiFatture.repository.ProvinciaRepository;
import BW5group5.GestioneClientiFatture.model.Provincia;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

@Service
public class ProvinciaService {

    private final ProvinciaRepository provinciaRepository;

    public ProvinciaService(ProvinciaRepository provinciaRepository) {
        this.provinciaRepository = provinciaRepository;
    }

    @Transactional
    public void saveProvinceFromCSV(MultipartFile file) {
        try {
              BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
//            reader.readLine();

            reader.lines().forEach(line -> saveProvinciaFromCSVLine(line));
        } catch (Exception e) {
            throw new RuntimeException("Error reading and saving data from CSV: " + e.getMessage());
        }
    }

    private void saveProvinciaFromCSVLine(String line) {
        String[] values = line.split(",");
        Arrays.stream(values).forEach(System.out::println);
//        if (values.length != 3) {
//            throw new RuntimeException("Invalid CSV format");
//        }

        Provincia provincia = new Provincia();
        provincia.setProvincia(values[1].trim());
        provincia.setSigla(values[2].trim());

        provinciaRepository.save(provincia);
    }

    public Provincia findProvinciaBySigla(String sigla) {
        return provinciaRepository.findProvinciaBySigla(sigla);
    }
}
