package altatest.model;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="VACANCY")
@NoArgsConstructor
@ToString(exclude="id")
@EqualsAndHashCode(exclude="id")
public class Vacancy implements Serializable {

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

	public Vacancy(Long id, String name, long salary, String requiredWorkExperience, String city) {
		this.id = id;
		update(name, salary, requiredWorkExperience, city);
	}

	public void update(String name, Long salary, String requiredWorkExperience, String city) {
		this.name = name;
		this.salary = salary;
		this.requiredWorkExperience = requiredWorkExperience;
		this.city = city;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Vacancy vacancy = (Vacancy) o;

		if (Double.compare(vacancy.salary, salary) != 0) return false;
		if (id != null ? !id.equals(vacancy.id) : vacancy.id != null) return false;
		if (name != null ? !name.equals(vacancy.name) : vacancy.name != null) return false;
		if (city != null ? !city.equals(vacancy.city) : vacancy.city != null) return false;
		return requiredWorkExperience != null ? requiredWorkExperience.equals(vacancy.requiredWorkExperience) : vacancy.requiredWorkExperience == null;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (requiredWorkExperience != null ? requiredWorkExperience.hashCode() : 0);
		result = 31 * result + (city != null ? city.hashCode() : 0);
		temp = Double.doubleToLongBits(salary);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", salary=" + salary
				+ ", salary=" + salary + "]";
	}




}