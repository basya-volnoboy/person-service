package telran.java48.person.dao;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import telran.java48.person.dto.ChildDto;
import telran.java48.person.dto.CityPopulationDto;
import telran.java48.person.dto.EmployeeDto;
import telran.java48.person.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer>{

	//@Query(value = "", nativeQuery = true)//обычный нативный sql запрос
	//@Query("select p from Person p where p.name =?1") //JPQL обьекто ориентированный sql подобный язык запросов
	Stream <Person> findByNameIgnoreCase(String name);

	//@Query("select p from Person p where p.address.city =?1")
	Stream <Person> findByAddressCityIgnoreCase(String city);
	
	Stream <Person> findByBirthDateBetween(LocalDate from, LocalDate to);
	
	@Query("select new telran.java48.person.dto.CityPopulationDto(p.address.city, count(p)) from Person p group by p.address.city order by count(p) asc")
	List<CityPopulationDto> getCityPopulation();
	
	@Query("select c from Child c")
	List<ChildDto> findAllChildren();
	
	@Query("select e from Employee e where e.salary >=?1 and e.salary<=?2")
	List<EmployeeDto> findEmployeesBySalaryBetween(Integer minSalary, Integer maxSalary);
	
}
