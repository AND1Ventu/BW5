package BW5group5.GestioneClientiFatture.service;

import BW5group5.GestioneClientiFatture.repository.ComuneRepository;
import BW5group5.GestioneClientiFatture.model.Comune;
import BW5group5.GestioneClientiFatture.model.Provincia;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class ComuneService {

    private final ComuneRepository comuneRepository;

    public ComuneService(ComuneRepository comuneRepository) {
        this.comuneRepository = comuneRepository;
    }

    @Transactional
    public void saveComuniFromCSV(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            reader.readLine();

            reader.lines().forEach(line -> saveComuneFromCSVLine(line));
        } catch (Exception e) {
            throw new RuntimeException("Error reading and saving data from CSV: " + e.getMessage());
        }
    }

    private void saveComuneFromCSVLine(String line) {
        String[] values = line.split(",");
        if (values.length != 3) {
            throw new RuntimeException("Invalid CSV format");
        }

        Comune comune = new Comune();
        comune.setComune(values[0].trim());
        comune.setCap(Integer.parseInt(values[2].trim()));

        Provincia provincia = new Provincia();
        provincia.setSigla(values[1].trim());
        comune.setProvincia(provincia);

        comuneRepository.save(comune);
    }

    public Comune findComuneById(int id) {
        return comuneRepository.findComuneById(id);
    }
}

