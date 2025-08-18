package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Notification;
import za.ac.cput.service.NotificationService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/notifications")
public class NotificationController {



        private NotificationService service;

        @Autowired
        private NotificationController(NotificationService service) {
           this.service = service;
        }

        @PostMapping("/create")
        public Notification create(@RequestBody Notification notification) {
            return service.create(notification);
        }

        @GetMapping("/read/{notificationId}")
        public Notification read(@PathVariable Long Id) {
            return service.read(Id);
        }

        @PutMapping("/update")
        public Notification update(@RequestBody Notification notification) {
            return service.update(notification);
        }

        @DeleteMapping("/delete/{notificationId}")
        public void delete(@PathVariable Long notificationId) {
            service.delete(notificationId);
        }

        @GetMapping("/getall")
        public List<Notification> getAll() {
            return service.getAll();
        }


    }


