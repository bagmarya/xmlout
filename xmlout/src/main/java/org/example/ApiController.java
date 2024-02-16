package org.example;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.function.ServerRequest;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiController {





    @RequestMapping(value = { "/get_xml" }, method = RequestMethod.GET, produces = "application/xml;charset=UTF-8")
    public ResponseEntity<String> getXml() throws IOException {
        HttpHeaders h = new HttpHeaders();
        h.set("Content-Disposition", "attachment; filename=\"SZT_" + LocalDate.now().format(DateTimeFormatter.ofPattern("uuuuMMdd")) + ".xml\"");
        return new ResponseEntity<>("lpuService.getFileSpFundingNormaSmp()",h, HttpStatus.OK);
    }

}
