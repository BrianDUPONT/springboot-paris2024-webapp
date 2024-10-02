package bts.sio.webapp.service;

import bts.sio.webapp.model.Actualite;
import bts.sio.webapp.repository.ActualiteProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class ActualiteService {

    @Autowired
    private ActualiteProxy actualiteProxy;

    public Actualite getActualite(final int id) {
        return actualiteProxy.getActualite(id);
    }

    public Iterable<Actualite> getActualites() {
        return actualiteProxy.getActualites();
    }

    public void deleteActualite(final int id) {
        actualiteProxy.deleteActualite(id);
    }

    public Actualite saveActualite(Actualite actualite) {
        Actualite savedActualite;

        // Functional rule : Last name must be capitalized.
        actualite.setTitre(actualite.getTitre());

        if(actualite.getId() == null) {
            // If id is null, then it is a new employee.
            savedActualite = actualiteProxy.createActualite(actualite);
        } else {
            savedActualite = actualiteProxy.updateActualite(actualite);
        }

        return savedActualite;
    }
}
