package pl.project.computer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Validated
@Controller
@CrossOrigin(maxAge = 60)
@RequestMapping("/computer/")
public class ComputerController {
    @Autowired
    private ComputerService computerService;

    @PostMapping("add")
    ResponseEntity<Computer> addComputer(@Valid @RequestBody Computer computer) {
        return new ResponseEntity<>(computerService.addComputerToDB(computer), HttpStatus.OK);
    }

    @PostMapping("addList")
    ResponseEntity<List<Computer>> addComputerList(@RequestBody List<@Valid Computer> computerList) {
        return new ResponseEntity<>(computerService.addListComputer(computerList), HttpStatus.OK);
    }

    @GetMapping("listByName")
    ResponseEntity<List<Computer>> containName
            (@Size(min = 1, message = "The computer name should contain at least 1 letter") @RequestParam String name) {
        return new ResponseEntity<>(computerService.findByNameContaining(name), HttpStatus.OK);
    }

    @GetMapping("listByDate")
    ResponseEntity<List<Computer>> byDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return new ResponseEntity<>(computerService.findByDate(date), HttpStatus.OK);
    }
}
