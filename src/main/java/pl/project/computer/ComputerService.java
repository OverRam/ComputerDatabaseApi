package pl.project.computer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.project.nbp.NbpApiService;
import pl.project.nbp.NbpResponseModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ComputerService {
    @Autowired
    private ComputerRepo computerRepo;

    @Autowired
    private NbpApiService nbpApiService;

    public List<Computer> findByNameContaining(String name) {
        if (name == null || name.strip().length() == 0)
            throw new IllegalArgumentException("The length of the name without whitespace must be greater than 0.");

        List<Computer> computerList = computerRepo.findByNameContainingIgnoreCase(name);

        if (computerList.isEmpty())
            throw new NoSuchElementException(String.format("There are no computers with %s in the name.", name));

        return computerList;
    }

    public List<Computer> findByDate(LocalDate date) {
        if (date == null)
            throw new IllegalArgumentException("Date cannot be null.");

        List<Computer> computerList = computerRepo.findByDate(date);

        if (computerList.isEmpty())
            throw new NoSuchElementException(String.format("There are no computers with %s in the date.", date));

        return computerList;
    }

    public Computer addComputerToDB(Computer computer) {
        return computerRepo.save(usdPlnPriceConversion(computer));
    }

    @Transactional
    public List<Computer> addListComputer(List<Computer> computerList) {
        return computerList.stream()
                .map(this::usdPlnPriceConversion)
                .map(this::addComputerToDB)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    private Computer usdPlnPriceConversion(Computer computer) {
        if (computer == null)
            throw new IllegalArgumentException("Computer cannot be null.");

        NbpResponseModel rate = nbpApiService.getUsdExchangeRateByDay(computer.getDate());

        if (rate == null)
            return computer;

        List<NbpResponseModel.Rates> rates = rate.getRates();

        Double exchangeRate = rates.get(0).getMid();

        if (computer.getCostPln() == 0 && computer.getCostUsd() > 0) {
            BigDecimal bd = new BigDecimal(computer.getCostUsd() * exchangeRate)
                    .setScale(2, RoundingMode.CEILING);
            computer.setCostPln(bd.doubleValue());
        } else if (computer.getCostPln() > 0 && computer.getCostUsd() == 0) {
            BigDecimal bd = new BigDecimal(computer.getCostPln() / exchangeRate)
                    .setScale(2, RoundingMode.CEILING);
            computer.setCostUsd(bd.doubleValue());
        }
        return computer;
    }
}
