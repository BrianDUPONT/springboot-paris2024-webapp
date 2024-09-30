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
    private SportProxy sportProxy;
    public Iterable<Sport> getLesSports() {
        return sportProxy.getLesSports();
    }
}
