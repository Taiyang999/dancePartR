package fr.esgi.danseapp.models

import lombok.AccessLevel
import lombok.AllArgsConstructor
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user")
class UserEntity() {

    @Id
    @Type(type = "uuid-char")
    @Column(name = "id")
    lateinit var id: UUID

    @Column(name = "username")
    var username = ""
    @Column(name = "email")
    var email =""
    @Column(name = "password")
    var password = ""

    constructor(id : UUID, username : String, email : String, password : String) : this() {
        this.id = id
        this.username = username
        this.email = email
        this.password = password
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserEntity

        if (id != other.id) return false
        if (username != other.username) return false
        if (email != other.email) return false
        if (password != other.password) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + username.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + password.hashCode()
        return result
    }

    override fun toString(): String {
        return "UserEntity(id=$id, username='$username', email='$email', password='$password')"
    }


}