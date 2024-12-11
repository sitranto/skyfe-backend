package com.skyfe.domain.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.validation.constraints.Size
import lombok.Builder
import org.jetbrains.annotations.NotNull

@Entity
@Table(name = "users")
class User(
    @JsonBackReference("user_id")
    @NotNull
    @Size(min = 1, max = 32)
    var firstName: String,

    @Size(max = 32)
    var lastName: String,

    @NotNull
    @Column(unique = true)
    @Size(min = 1, max = 32)
    var username: String,

    @JsonBackReference("number")
    @NotNull
    @Column(unique = true)
    var number: String,

    @JsonBackReference("bio")
    @Size(max = 150)
    var bio: String,

    @JsonBackReference("password")
    @NotNull
    @Column(unique = true)
    @Size(min = 1, max = 32)
    var password: String,

    @JsonIgnore
    @Builder.Default
    @OneToMany(
        mappedBy = "user",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var userChats: MutableList<UserChat>

    ): BaseEntity<Long>()
