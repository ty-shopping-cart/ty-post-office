package com.trendyol.typostoffice.model;

import com.trendyol.typostoffice.domain.enums.NotificationType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Table
public class Notification {
    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private Date createdDate = new Date();
    private Boolean isDeleted = false;
    private String message;
    @Enumerated(EnumType.STRING)
    private NotificationType type;
    private String subject;

}
