import com.fernandovicente.projetas.MainApplication;
import com.fernandovicente.projetas.controller.CarController;
import com.fernandovicente.projetas.exception.ResourceNotFoundException;
import com.fernandovicente.projetas.model.Car;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarTest {

    @Test
    public void contextLoads() {
    }


    @Autowired
    private CarController carController;

    private List<Car> carList = new ArrayList<>();
    private Car car1 = new Car("Hyundai", "Hb20", "Black", 2015, new BigDecimal(40000), false, "Perfect a new car");
    private Car car2 = new Car("Chevrolet", "Cruze Sport", "White", 2018, new BigDecimal(85000), true, "Your dream car");
    private Car car3 = new Car("Volkswagen", "Gol", "Red", 2017, new BigDecimal(32000), false, "Nice one");
    private Car updateCar1 = new Car("Volkswagen", "Gol", "Red", 2017, new BigDecimal(32000), false, "Nice one");

    @Before
    public void setUp() {
        carList.add(car1);
        carList.add(car2);
        carList.add(car3);
    }

    @Test
    public void test1() {

        List<Car> resultList = carController.getAllCars();
        Assert.assertNotNull("allCars cannot be null", carList);
        Assert.assertEquals("result list must be empty", resultList, new ArrayList<Car>());

    }

    @Test
    public void test2() {

        Car registredCar1 = carController.createCar(car1);
        Car registredCar2 = carController.createCar(car2);
        Car registredCar3 = carController.createCar(car3);

        Assert.assertNotNull("registredCar1 cannot be null", registredCar1);
        Assert.assertNotNull("registredCar2 cannot be null", registredCar2);
        Assert.assertNotNull("registredCar3 cannot be null", registredCar3);

    }

    @Test
    public void test3() {

        Car getCar1 = carController.getCarById(1l);
        Car getCar2 = carController.getCarById(2l);
        Car getCar3 = carController.getCarById(3l);

        Assert.assertNotNull("getCar1 cannot be null", getCar1);
        Assert.assertNotNull("getCar2 cannot be null", getCar2);
        Assert.assertNotNull("getCar3 cannot be null", getCar3);
    }

    @Test
    public void test4() {

        List<Car> resultList = carController.getAllCars();

        Assert.assertNotNull("allCars cannot be null", carList);
        Assert.assertEquals("result list cannot be different from carList", resultList.size(), carList.size());

    }

    @Test
    public void test5() {

        Car updatedCar = carController.updateCar(1l,updateCar1);
        Assert.assertNotNull("allCars cannot be null", updatedCar);

    }

    @Test
    public void test6() {

        carController.deleteCar(3l);
        List<Car> resultList = carController.getAllCars();
        Assert.assertEquals("result list cannot be different from carList", resultList.size(), carList.size()-1);

    }

    @Test(expected= ResourceNotFoundException.class)
    public void test7() {
        carController.getCarById(3l);
    }
}
