package com.skyfe.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import lombok.Builder

@Entity
@Table(name = "chat")
class Chat(

    @JsonIgnore
    @Builder.Default
    @OneToMany(
        mappedBy = "chat",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var userChats: MutableList<UserChat> = mutableListOf(),

    @JsonIgnore
    @Builder.Default
    @OneToMany(
        mappedBy = "chat",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var messages: MutableList<Message> = mutableListOf()

    ): BaseEntity<Long>()