package com.trendyol.typostoffice.repository;

import com.trendyol.typostoffice.model.CustomerNotification;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerNotificationRepository extends CassandraRepository<CustomerNotification, Long> {
}
