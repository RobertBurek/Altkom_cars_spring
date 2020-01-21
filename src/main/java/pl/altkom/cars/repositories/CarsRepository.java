package pl.altkom.cars.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.altkom.cars.entities.Car;

@Repository
public interface CarsRepository extends CrudRepository<Car, Long> {

    public Car findById(long id);
}
