package com.netcreker.meetup.controller.fns;

import com.netcreker.meetup.entity.fns.FnsCheck;
import com.netcreker.meetup.entity.fns.FnsCheckInfo;
import com.netcreker.meetup.service.fns.FnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/fns")
@CrossOrigin(origins = "http://localhost:4200")
public class FnsRestController {
    @Autowired
    private FnsService fnsService;

    private final String username = "+79102430036";
    private final String password = "800031";

    @PostMapping("/check-details")
    public ResponseEntity<?> getCheckDetails(@RequestBody FnsCheckInfo checkInfo) {
        if (fnsService.login(username, password)) {
            ResponseEntity<FnsCheck> result = fnsService.getCheckDetails(username, password, checkInfo);
            if (result.getStatusCode() != HttpStatus.OK) {
                return result.getStatusCode() == HttpStatus.NOT_FOUND ?
                        new ResponseEntity<>(HttpStatus.NOT_FOUND)  :
                        new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
            }
            return ResponseEntity.ok(Objects.requireNonNull(result.getBody()).getDocument().getReceipt());
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
