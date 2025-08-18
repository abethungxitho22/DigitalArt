package za.ac.cput.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Notification;
import za.ac.cput.domain.User;
import za.ac.cput.repository.NotificationRepository;
import java.util.List;

@Service
public class NotificationService implements INotificationService {

    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationService(NotificationRepository repository) {
        this.notificationRepository = repository;
    }


    @Override
    public Notification create(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification read(Long notificationId) {
        return this.notificationRepository.findById(notificationId).orElse(null);
    }

    @Override
    public Notification update(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public void delete(Long notificationId) {
        this.notificationRepository.deleteById(notificationId);

    }

    @Override
    public List<Notification> getAll() {
        return this.notificationRepository.findAll();
    }

    public List<Notification> getNotificationsByUser(User user) {
        return notificationRepository.findByUser(user);
    }


    public List<Notification> getNotificationsByStatus(Boolean status) {
        return notificationRepository.findByStatus(status);
    }
}
