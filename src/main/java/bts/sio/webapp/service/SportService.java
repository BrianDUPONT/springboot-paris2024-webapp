package bts.sio.webapp.service;

import bts.sio.webapp.model.Sport;
import bts.sio.webapp.repository.SportProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class SportService {

    @Autowired
    private SportProxy athleteProxy;

    public Sport getSport(final int id) {
        return athleteProxy.getSport(id);
    }

    public Iterable<Sport> getLesSports() {
        return athleteProxy.getSports();
    }

    public void deleteSport(final int id) {
        athleteProxy.deleteSport(id);
    }

    public Sport saveSport(Sport athlete) {
        Sport savedSport;

        // Functional rule : Last name must be capitalized.
        athlete.setNom(athlete.getNom().toUpperCase());

        if(athlete.getId() == null) {
            // If id is null, then it is a new employee.
            savedSport = athleteProxy.createSport(athlete);
        } else {
            savedSport = athleteProxy.updateSport(athlete);
        }

        return savedSport;
    }
}
