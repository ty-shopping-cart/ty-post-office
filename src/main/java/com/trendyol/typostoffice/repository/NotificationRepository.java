package com.trendyol.typostoffice.repository;

import com.trendyol.typostoffice.model.Notification;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends CassandraRepository<Notification, String> {
}
