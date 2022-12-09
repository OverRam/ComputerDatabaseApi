package pl.project.computer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Computer {
    @Id
    @GeneratedValue()
    @JsonIgnore
    private long id;

    @NonNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @NotBlank
    @NonNull
    private String name;

    private double costUsd;

    private double costPln;

    public Computer() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Computer computer = (Computer) o;

        if (id != computer.id) return false;
        if (Double.compare(computer.costUsd, costUsd) != 0) return false;
        if (Double.compare(computer.costPln, costPln) != 0) return false;
        if (!date.equals(computer.date)) return false;
        return name.equals(computer.name);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + date.hashCode();
        result = 31 * result + name.hashCode();
        temp = Double.doubleToLongBits(costUsd);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(costPln);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
