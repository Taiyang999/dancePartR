package fr.esgi.danseapp.repositories

import fr.esgi.danseapp.models.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface IUsersRepository : JpaRepository<UserEntity, UUID> {
//    fun findByCredentials(username: String, password: String): UserEntity?

    @Query("SELECT u FROM UserEntity u WHERE u.username = ?1")
    fun findByUsername(username: String): UserEntity?

    @Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
    fun findByEmail(email: String) : UserEntity?

}