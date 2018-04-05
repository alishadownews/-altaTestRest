package altatest.service;

import altatest.model.Vacancy;

import java.util.List;

public interface VacancyService {

    List<Vacancy> getVacancyList();

    Vacancy get(Long id);

    Vacancy create(Vacancy vacancy);

    Vacancy delete(Long id);

    Vacancy update(Vacancy vacancyUpdate);


}
