package pl.project.nbp;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
@Log4j2
public class NbpApiService {

    /**
     * @param date Date of exchange rate day.
     * @return NbpResponseModel.
     */
    public NbpResponseModel getUsdExchangeRateByDay(LocalDate date) {
        RestTemplate template = new RestTemplate();

        String url = String.format("http://api.nbp.pl/api/exchangerates/rates/a/USD/%s/", date);
        ResponseEntity<NbpResponseModel> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);

        try {
            response = template.getForEntity(url, NbpResponseModel.class);
        } catch (RestClientException e) {
            log.warn("Api bank http status " + e.getMessage());
        }

        return response.getBody();
    }
}
