package com.skyfe.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.type.descriptor.jdbc.TimestampWithTimeZoneJdbcType

@Entity
@Table(name = "Message")
class Message(
    var text: String,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val from: UserChat,
    val date: TimestampWithTimeZoneJdbcType
): BaseEntity<Long>()