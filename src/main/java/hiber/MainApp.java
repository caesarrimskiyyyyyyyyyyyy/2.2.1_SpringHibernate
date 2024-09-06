package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car carFirst = new Car(654, "MB");
        Car carSecond = new Car(789, "BW");
        Car carThird = new Car(122, "AD");

        User user1 = new User("Tony", "Soprano", "panicattack@gmail.com");
        User user2 = new User("Christopher", "Moltisanti", "babysoldier@gmail.com");
        User user3 = new User("Silvio", "Dante", "outlawz@gmail.ru");

        user1.setCar(carFirst);
        user2.setCar(carSecond);
        user3.setCar(carThird);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);

        userService.listUsers().stream()
                .map(entry -> String.format("%s : %s", entry.getFirstName(), entry.getCar().getModel()))
                .forEach(System.out::println);

        context.close();
    }
}
