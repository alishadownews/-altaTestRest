package altatest.service;

import altatest.model.Vacancy;
import altatest.repositories.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service("vacancyServiceImpl")
@Transactional
public class VacancyServiceImpl implements VacancyService {

	@Autowired
	private VacancyRepository vacancyRepository;

	/**
	 * возвращает список вакансий
	 *
	 * @return список всех вакансий
	 */
	public List<Vacancy> getVacancyList() {

		return vacancyRepository.findAll();
		//return vacancyList;
	}

	/**
	 * выдает Vacancy по идентификатору или null.
	 * 
	 * @param id идентификатор вакансии
	 * @return выдает Vacancy по идентификатору
	 */
	public Vacancy get(Long id) {
		return vacancyRepository.getOne(id);
	}

	/**
	 * создание вакансии и возврат её обновленную, по сути присвоение id значения и запись в список
	 *
	 * @param vacancy вакансия объект Vacancy
	 * @return объект Vacancy с обновленным id
	 */
	public Vacancy create(Vacancy vacancy) {

		return vacancyRepository.save(vacancy);
	}

	/**
	 * Удаление вакансии по его идентификатору, если не найдёт то возвратит null
	 *
	 * @param id вакансии объекта Vacancy
	 * @return вакансия объекта Vacancy
	 */
	public Vacancy delete(Long id) {

		Vacancy find = vacancyRepository.getOne(id);
		vacancyRepository.deleteById(id);
		return find;

	}

	/**
	 * обновление данных вакансии по идентификатору, если не найдёт, то возвращает null
	 *
	 * @param vacancyUpdate объект Vacancy
	 * @return vacancy объект Vacancy
	 */
	public Vacancy update(Vacancy vacancyUpdate) {

		if (vacancyUpdate == null || vacancyUpdate.getId() == null ){
			return null;
		}

		vacancyRepository.save(vacancyUpdate);
		return vacancyUpdate;

	}

}