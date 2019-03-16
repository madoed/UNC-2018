package com.netcreker.meetup.service.fns;

import com.netcreker.meetup.entity.fns.FnsCheck;
import com.netcreker.meetup.entity.fns.FnsCheckInfo;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;

@Service
public class FnsService {
    public boolean login(String username, String password) {
        final String uri = "https://proverkacheka.nalog.ru:9999/v1/mobile/users/login";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.exchange
                (uri, HttpMethod.GET, new HttpEntity<>(createAuthHeaders(username, password)), String.class);

        return result.getStatusCode() == HttpStatus.OK;
    }

    public ResponseEntity<FnsCheck> getCheckDetails(String username, String password, FnsCheckInfo checkInfo) {
        final String uri = String.format(
                "https://proverkacheka.nalog.ru:9999/v1/inns/*/kkts/*/fss/%s/tickets/%s",
                checkInfo.getFn(), checkInfo.getFd()
        );
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri)
                .queryParam("fiscalSign", checkInfo.getFpd())
                .queryParam("sendToEmail", "no");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = createAuthHeaders(username, password);
        headers.add("device-id", "");
        headers.add("device-os", "");

        return restTemplate.exchange
                (builder.toUriString(), HttpMethod.GET, new HttpEntity<>(headers), FnsCheck.class);
    }

    private HttpHeaders createAuthHeaders(String username, String password) {
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("UTF-8")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }
}
