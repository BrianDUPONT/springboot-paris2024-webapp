package bts.sio.webapp.model;


import lombok.Data;

import java.time.LocalDate;

@Data
public class Actualite {

    private Integer id;
    private String titre;
    private String contenu;
    private LocalDate date ;
    private Epreuve epreuve;
    private Sport sport;

}
