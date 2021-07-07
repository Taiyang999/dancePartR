package fr.esgi.danseapp.models.bodies


import fr.esgi.danseapp.models.UserEntity
import fr.esgi.danseapp.services.UserServices
import io.jkratz.mediator.core.Request
import io.jkratz.mediator.core.RequestHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.util.*

data class UserRegisterCommand(var username: String, var password: String, var email: String) : Request<UUID>

@Component
class RegisterUserCommandHandler
    //private val hashingService: IHashingService
 :
    RequestHandler<UserRegisterCommand, UUID> {

    @Autowired
    private lateinit var service : UserServices

    override fun handle(request: UserRegisterCommand): UUID {
        val userUnq = service.findByEmail(request.email)
        if (userUnq != null) {
            return userUnq.id
        }
        val encoder = BCryptPasswordEncoder()
        val newAccount = UserEntity(
            id = service.getNewId(),
            username = request.username,
            email = request.email,
            password = encoder.encode(request.password)
        )
        return service.save(newAccount)
    }
}
