package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Notification;
import za.ac.cput.domain.User;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository <Notification, Long> {

    List<Notification> findByUser(User user);

    List<Notification> findByUserAndNotificationId(User user, Long notificationId);

    List<Notification> findByStatus(Boolean status);

}

