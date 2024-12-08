package com.skyfe.domain.model

import jakarta.persistence.*

@Entity
@Table(name = "Users")
class User(
    var firstName: String,
    var lastName: String,
    var username: String,
    var number: String,
    var bio: String,
    @OneToMany(
        mappedBy = "user",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var userChat: MutableList<UserChat>
): BaseEntity<Long>()