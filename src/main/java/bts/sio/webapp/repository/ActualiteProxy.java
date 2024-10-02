package bts.sio.webapp.repository;


import bts.sio.webapp.CustomProperties;
import bts.sio.webapp.model.Actualite;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class ActualiteProxy {
    @Autowired
    private CustomProperties props;

    /**
     * Get all actualites
     * @return An iterable of all actualite
     */
    public Iterable<Actualite> getActualites() {

        String baseApiUrl = props.getApiUrl();
        String getActualitesUrl = baseApiUrl + "/actualites";
        System.out.println("url=" + getActualitesUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Actualite>> response = restTemplate.exchange(
                getActualitesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Actualite>>() {}
        );

        log.debug("Get Actualites call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Get an actualite by the id
     * @param id The id of the actualite
     * @return The actualite which matches the id
     */
    public Actualite getActualite(int id) {
        String baseApiUrl = props.getApiUrl();
        String getEmployeeUrl = baseApiUrl + "/actualite/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Actualite> response = restTemplate.exchange(
                getEmployeeUrl,
                HttpMethod.GET,
                null,
                Actualite.class
        );

        log.debug("Get Actualite call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Add a new actualite
     * @param a A new actualite (without an id)
     * @return The actualite full filled (with an id)
     */
    public Actualite createActualite(Actualite a) {

        String baseApiUrl = props.getApiUrl();
        String createActualiteUrl = baseApiUrl + "/actualite";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Actualite> request = new HttpEntity<Actualite>(a);
        ResponseEntity<Actualite> response = restTemplate.exchange(
                createActualiteUrl,
                HttpMethod.POST,
                request,
                Actualite.class);

        log.debug("Create Actualite call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Update an actualite - using the PUT HTTP Method.
     * @param e Existing actualite to update
     */
    public Actualite updateActualite(Actualite e) {
        String baseApiUrl = props.getApiUrl();
        String updateActualiteUrl = baseApiUrl + "/actualite/" + e.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Actualite> request = new HttpEntity<Actualite>(e);
        ResponseEntity<Actualite> response = restTemplate.exchange(
                updateActualiteUrl,
                HttpMethod.PUT,
                request,
                Actualite.class);

        log.debug("Update Actualite call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /*
     * Delete an actualite using exchange method of RestTemplate
     * instead of delete method in order to log the response status code.
     * @param e The actualite to delete
     */
    public void deleteActualite(int id) {
        String baseApiUrl = props.getApiUrl();
        String deleteActualiteUrl = baseApiUrl + "/actualite/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteActualiteUrl,
                HttpMethod.DELETE,
                null,
                Void.class);

        log.debug("Delete Actualite call " + response.getStatusCode().toString());
    }
}
