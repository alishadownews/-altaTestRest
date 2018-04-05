package altatest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
public class VacancyInfo {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @NotEmpty
    @Column(name="VACANCY_NAME", length = 200)
    private String name;

    @Getter
    @Setter
    @Column(name="SALARY")
    private double salary;

    @Getter
    @Setter
    @Column(name="EXPERIENCE", length = 500)
    private String requiredWorkExperience;

    @Getter
    @Setter
    @Column(name="CITY", length = 100)
    private String city;

    public void update(Vacancy vacancy){
        this.id = vacancy.getId();
        this.name = vacancy.getName();
        this.salary = vacancy.getSalary();
        this.requiredWorkExperience = vacancy.getRequiredWorkExperience();
        this.city = vacancy.getCity();
    }

}
