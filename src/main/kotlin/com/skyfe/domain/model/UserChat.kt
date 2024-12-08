package com.skyfe.domain.model

import jakarta.persistence.*

@Entity
@Table(name = "UserChat")
class UserChat(
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,
    @ManyToOne
    @JoinColumn(name = "chat_id")
    val chat: Chat
): BaseEntity<Long>()