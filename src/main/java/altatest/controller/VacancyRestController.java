package altatest.controller;

import altatest.model.Vacancy;
import altatest.model.VacancyInfo;
import altatest.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class VacancyRestController {
    final String NO_VACANCY_WITH_THIS_ID = "Нет вакансии с таким идентификатором ";

	
	@Autowired
	private VacancyService vacancyService;




    /**
     * возвращает список вакансий
     *
     * @return список всех вакансий
     */
	@CrossOrigin()
	@GetMapping("/vacancies")
	public List<Vacancy> getVacancies() {
		return vacancyService.getVacancyList();
	}

    /**
     * выдает Vacancy по идентификатору или null.
     *
     * @param id идентификатор вакансии
     * @return выдает Vacancy по идентификатору
     */
	@CrossOrigin()
	@GetMapping("/vacancy/{id}")
	public ResponseEntity getVacancy(@PathVariable("id") Long id) {

		Vacancy vacancy = vacancyService.get(id);
		if (vacancy == null) {
			return new ResponseEntity(NO_VACANCY_WITH_THIS_ID + id, HttpStatus.NOT_FOUND);
		}

		VacancyInfo vacancyInfo = new VacancyInfo();
		vacancyInfo.update(vacancy);

		return new ResponseEntity(vacancyInfo, HttpStatus.OK);
	}

    /**
     * создание вакансии и возврат его обновленной, по сути присвоение id значения и запись в список
     *
     * @param vacancy вакансия объекта Vacancy
     * @return объект Vacancy с обновленным id
     */
	@CrossOrigin()
    @PutMapping(value = "/vacancy")
	public ResponseEntity createVacancy(@RequestBody Vacancy vacancy) {

		vacancyService.create(vacancy);

		return new ResponseEntity(vacancy, HttpStatus.OK);
	}

    /**
     * Удаление вакансии по его идентификатору, если не найдёт то возвратит null
     *
     * @param id вакансия объекта Vacancy
     * @return вакансия объекта Vacancy
     */
	@CrossOrigin()
	@DeleteMapping("/vacancy/{id}")
	public ResponseEntity deleteVacancy(@PathVariable Long id) {

		if (null == vacancyService.delete(id)) {
			return new ResponseEntity(NO_VACANCY_WITH_THIS_ID + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(id, HttpStatus.OK);

	}


    /**
     * обновление данных сотрудника по идентификатору, если не найдёт, то возвращает null
     *
     * @param vacancy объект Vacancy
     * @return vacancy объект Vacancy
     */
	@CrossOrigin()
    @PostMapping("/vacancy")
	public ResponseEntity updateVacancy(@RequestBody Vacancy vacancy) {

		Vacancy vacancyUpdate = vacancyService.update(vacancy);

		if (null == vacancyUpdate) {
			return new ResponseEntity(NO_VACANCY_WITH_THIS_ID + vacancy.getId().toString(), HttpStatus.NOT_FOUND);
		}

		VacancyInfo vacancyInfo = new VacancyInfo();
		vacancyInfo.update(vacancyUpdate);

		return new ResponseEntity(vacancyInfo, HttpStatus.OK);
	}

}