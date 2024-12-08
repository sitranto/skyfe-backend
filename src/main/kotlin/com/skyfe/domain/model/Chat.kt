package com.skyfe.domain.model

import jakarta.persistence.*

@Entity
@Table(name = "Chat")
class Chat(
    val isPrivate: Boolean,
    @OneToMany(
        mappedBy = "chat",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var userChat: MutableList<UserChat>
): BaseEntity<Long>()