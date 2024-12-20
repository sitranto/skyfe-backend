package com.skyfe.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.type.descriptor.jdbc.TimestampWithTimeZoneJdbcType
import org.jetbrains.annotations.NotNull

@Entity
@Table(name = "message")
class Message(
    @Column(length = 2500)
    @NotNull
    var content: String,

    @NotNull
    var timeSend: TimestampWithTimeZoneJdbcType,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "from_id")
    var from: User,

    @ManyToOne(fetch = FetchType.LAZY)
    var chat: Chat,

    var isRead: Boolean,

    var extension: String

    ): BaseEntity<Long>()

