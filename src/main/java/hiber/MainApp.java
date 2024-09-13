package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        User userFirst = new User("Tony", "Soprano", "panicattack@gmail.com");
        User userSecond = new User("Christopher", "Moltisanti", "babysoldier@gmail.com");
        User userThird = new User("Silvio", "Dante", "outlawz@gmail.ru");

        userFirst.addCar(new Car(654, "Cadillac escalade"));
        userSecond.addCar(new Car(789, "Mercedes-Benz CLK 430"));
        userThird.addCar(new Car(122, "Cadillac Seville"));

        UserService userService = context.getBean(UserService.class);

        userService.add(userFirst);
        userService.add(userSecond);
        userService.add(userThird);

        userService.listUsers().stream()
                .map(entry -> String.format(
                        "%s %s : %s",
                        entry.getFirstName(),
                        entry.getLastName(),
                        entry.getCar().getModel()
                ))
                .forEach(System.out::println);

        System.out.println("\nOwner car: "
                + userService.getUser(789, "Mercedes-Benz CLK 430").getFirstName());

        context.close();
    }
}
