package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Category;
import za.ac.cput.domain.Notification;
import za.ac.cput.domain.User;
import za.ac.cput.factory.CategoryFactory;
import za.ac.cput.factory.NotificationFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)

class NotificationControllerTest {

    private static Notification notification;
    private static User user;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String BASE_URL = "/notification";

    @BeforeAll
    public static void setup() {
        notification = NotificationFactory.createNotification("New Message", "Check out this week's trending digital artworks!", LocalDateTime.now(),false , user);
    }

    @Test
    @Order(1)
    void a_create() {
        String url = BASE_URL + "/create";
        Notification createdNotification = this.restTemplate.postForObject(url, notification, Notification.class);
        assertNotNull(createdNotification);
        assertEquals(notification.getTitle(), createdNotification.getTitle());
        notification = createdNotification;
        System.out.println("notification: " + createdNotification);
    }

    @Test
    @Order(2)
    void b_read() {
        String url = BASE_URL + "/read/" + notification.getNotificationId();
        ResponseEntity<Notification> response = this.restTemplate.getForEntity(url, Notification.class);
        assertNotNull(response.getBody());
        assertEquals(notification.getNotificationId(), response.getBody().getNotificationId());
        System.out.println("read: " + response.getBody());
    }

    @Test
    @Order(3)
    void c_update() {
        Notification updatedNotification = new Notification.Builder().copy(notification)
                .setTitle("Title updated")
                .build();

        String url = BASE_URL + "/update";
        ResponseEntity<Notification> response = this.restTemplate.postForEntity(url, updatedNotification, Notification.class);
        assertNotNull(response.getBody());
        assertEquals(updatedNotification.getTitle(), response.getBody().getTitle());
        notification = response.getBody();
        System.out.println("updated:" + response.getBody());

    }

    @Test
    @Order(5)
    void e_delete() {
        String url = BASE_URL + "/delete/" + notification.getNotificationId();
        this.restTemplate.delete(url);

        ResponseEntity<Notification> response = this.restTemplate.getForEntity( BASE_URL + "/read/" + notification.getNotificationId(), Notification.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        System.out.println("Deleted:" + "true");
    }

    @Test
    @Order(4)
    void d_getAll() {
        String url = BASE_URL + "/getAll";
        ResponseEntity<Notification[]> response = this.restTemplate. getForEntity(url, Notification[].class);
        assertNotNull(response.getBody());
        System.out.println("Get All; ");
        for (Notification notification : response.getBody()) {
            System.out.println(notification);
        }



        }
    }

