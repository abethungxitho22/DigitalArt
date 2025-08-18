package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Notification;
import za.ac.cput.domain.User;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class NotificationFactoryTest {


    private static User user = new User.Builder().setUserId(1L).build();

    private static Notification notification1 = NotificationFactory.createNotification("New Message" , "You have a new message", LocalDateTime.now(),false,user);

    private static Notification notification2 = NotificationFactory.createNotification("", "Your order has been successfully placed", LocalDateTime.now(), false, user);

    private static Notification notification3 = NotificationFactory.createNotification("null","Message",LocalDateTime.now(), false, user);

    @Test
    void createNotification() {
        assertNotNull(notification1);
        System.out.println(notification1.toString());
    }

    @Test
    void createNotificationWithNullTitle() {
        assertNotNull(notification2);
        System.out.println(notification2.toString());
    }

    @Test
    void createNotificationThatWillFail() {
        //fail();
        assertNotNull(notification3);
        System.out.println(notification3.toString());
    }
}

