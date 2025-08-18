package za.ac.cput.service;



import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Category;
import za.ac.cput.domain.Notification;
import za.ac.cput.domain.User;
import za.ac.cput.factory.NotificationFactory;
import za.ac.cput.factory.UserFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)

public class NotificationServiceTest {

    @Autowired
    private NotificationService service;
    @Autowired
    private static UserService userService;

    @Autowired
    private NotificationService notificationService;

    private static Notification notification1;
    private static Notification notification2;

    private static User user1;
    private static User user2;


    @BeforeAll
    @Order(1)
    static void setUp() {

        user1 = UserFactory.createUser(
                "Gabriel", "Mbali", "GabrielMbali@gmail.com", "MG@7892", LocalDateTime.now(), LocalDate.now());
        user1 = userService.create(user1);


        notification1 = NotificationFactory.createNotification("Test Title", "Test Message", LocalDateTime.now(), false, user1);
        assertNotNull(notification1);
        System.out.println(notification1);

        notification2 = NotificationFactory.createNotification("Test Title ", "Test Message notification", LocalDateTime.now(), false, user2);
        assertNotNull(notification2);
        System.out.println(notification2);
    }


    @Test
    @Order(2)
    void a_create() {
        Notification created1 = service.create(notification1);
        assertNotNull(created1);
        assertEquals(user1.getUserId(), created1.getUser().getUserId());
        System.out.println(created1);
        System.out.println();

        Notification created2 = service.create(notification2);
        assertNotNull(created2);
        assertEquals(user2.getUserId(), created2.getUser().getUserId());
        System.out.println(created2);
        System.out.println();
    }

    @Test
    @Order(2)
    void b_read() {
        Notification read = service.read(notification1.getNotificationId());
        assertNotNull(read);
        System.out.println(read);

    }

    @Test
    @Order(3)
    void d_update() {
        Notification newNotification = new Notification.Builder().copy(notification1).setTitle("Title updated")
                .build();
        Notification updated = service.update(newNotification);
        assertNotNull(updated);
        System.out.println(updated);
    }


    @Test
    @Order(4)
    void e_getAll() {
        System.out.println(service.getAll());
    }

    @Test
    @Order(5)
    void e_delete() {
        service.delete(notification1.getNotificationId());
        Notification deleted = service.read(notification1.getNotificationId());
        assertNull(deleted);
        System.out.println("Notification deleted successfully");
    }

}
