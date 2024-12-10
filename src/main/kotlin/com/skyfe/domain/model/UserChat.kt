package com.skyfe.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "user_chat")
class UserChat(
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User,

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "chat_id")
    var chat: Chat

    ): BaseEntity<Long>()