package bts.sio.webapp.repository;

import bts.sio.webapp.CustomProperties;
import bts.sio.webapp.model.Sport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class SportProxy {

    @Autowired
    private CustomProperties props;

    /**
     * Get all sports
     * @return An iterable of all sports
     */
    public Iterable<Sport> getLesSports() {

        String baseApiUrl = props.getApiUrl();
        String getSportsUrl = baseApiUrl + "/sports";
        System.out.println("url=" + getSportsUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Sport>> response = restTemplate.exchange(
                getSportsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Sport>>() {}
        );

        log.debug("Get Pays call " + response.getStatusCode().toString());

        return response.getBody();
    }

}
