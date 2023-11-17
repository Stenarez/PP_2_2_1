package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user = new User("User1", "Lastname1", "user1@mail.ru");
        Car car = new Car("model1", 5);

        user.setCar(car);
        userService.add(user);

        Car car1 = new Car("model2", 3);
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        user2.setCar(car1);
        userService.add(user2);

        List<User> users = userService.listUsers();

        System.out.println(userService.findUser("model1", 5));
        System.out.println();

        for (User us : users) {
            System.out.println(us);
        }

        context.close();
    }
}
