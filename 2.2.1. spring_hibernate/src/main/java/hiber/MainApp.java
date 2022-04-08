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

      userService.add(new User("User5", "Lastname5", "user5@mail.ru", new Car("Model1", 1)));
      userService.add(new User("User6", "Lastname6", "user6@mail.ru", new Car("Model2", 2)));

      List<User> users2 = userService.listUsers();

      User user1 = userService.getUserByCarModelAndSeries("Model1", 1);

      users2.add(user1);

      for (User user : users2) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car Model = "+user.getCar().getModel());
         System.out.println("Car Series = "+user.getCar().getSeries());
         System.out.println();
      }

      context.close();
   }
}
