package ma.emsi.tp3;

import ma.emsi.tp3.entities.Patient;
import ma.emsi.tp3.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class Tp3Application implements CommandLineRunner {
    @Autowired
    PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(Tp3Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Stream.of("patient1","patient2","patient3")
                .forEach(name->{
                    Patient patient=new Patient();
                    patient.setNom(name);
                    patient.setDateNaissance(new Date());
                    patient.setMalade(false);
                    patientRepository.save(patient);
                });
    }
}
