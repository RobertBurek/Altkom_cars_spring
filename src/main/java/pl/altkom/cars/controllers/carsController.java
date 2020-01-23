package pl.altkom.cars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.altkom.cars.entities.Car;
import pl.altkom.cars.repositories.CarsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class carsController {

    @Autowired
    CarsRepository carsRepository;

    @GetMapping("/cars")
    public String findAll(Model model) {
        model.addAttribute("carListModel", carsRepository.findAll());
        model.addAttribute("carFind", new Car());
        return "carsView";
    }

    @GetMapping("/carEdit")
    public String editCar(@RequestParam(name = "carId") Long carId, Model model) {
        Car car = carsRepository.findById(carId.longValue());
        model.addAttribute("carModel", car);
        model.addAttribute("carFind", new Car());
        return "carEditView";
    }

    @GetMapping("/carDelete")
    public String deleteCar(@RequestParam Long carId, Model model) {
        carsRepository.deleteById(carId.longValue());
        return "redirect:/cars";
    }

    @PostMapping("/car/carFind")
    public String findCar(@ModelAttribute Car carFind, Model model) {
        List<Car> cars = (List<Car>) carsRepository.findAll();
        cars = cars.stream()
                .filter(car -> car.getBrand().equals(carFind.getBrand()))
                .collect(Collectors.toList());
        model.addAttribute("carListModel", cars);
        model.addAttribute("carFind", new Car());
        return "carsViewFind";
    }

    @PostMapping("/car/save")
    public String save(@ModelAttribute Car car) {
        carsRepository.save(car);
        return "redirect:/cars";
    }

    @GetMapping("/carAdd")
    public String add(Model model) {
        model.addAttribute("carModel", new Car());
        model.addAttribute("carFind", new Car());
        return "carEditView";
    }
}
