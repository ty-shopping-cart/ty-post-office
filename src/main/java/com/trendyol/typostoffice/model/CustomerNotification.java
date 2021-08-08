package com.trendyol.typostoffice.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Set;

@Getter
@Setter
@Table
public class CustomerNotification {

    @PrimaryKey("customerid")
    private long customerId;
    @Column("notificationids")
    private Set<String> notificationIds;
}
