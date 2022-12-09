package pl.project.nbp;

import lombok.Data;

import java.util.List;

@Data
public class NbpResponseModel {
    private String table;
    private String currency;
    private String code;
    private List<Rates> rates;

    @Data
    public static class Rates {
        private String no;
        private String effectiveDate;
        private Double bid;
        private Double ask;
        private Double mid;
    }
}
