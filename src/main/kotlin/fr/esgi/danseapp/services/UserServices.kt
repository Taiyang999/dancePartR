package fr.esgi.danseapp.services

import fr.esgi.danseapp.models.UserEntity
import fr.esgi.danseapp.repositories.IUsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServices  {
    @Autowired
    private lateinit var userRepo : IUsersRepository

    fun findAll() : MutableIterable<UserEntity> {
        return userRepo.findAll()
    }

    fun getNewId() : UUID {
        return UUID.randomUUID()
    }

    fun findById(id : UUID) : UserEntity? {
        return userRepo.findById(id).get()
    }

    fun findByEmail(email : String) : UserEntity? {
        return userRepo.findByEmail(email)
    }

    fun save(user : UserEntity) : UUID {
        val u = userRepo.save(user)
        return u.id
    }

    fun delete(user: UserEntity) {
        userRepo.delete(user)
    }
}