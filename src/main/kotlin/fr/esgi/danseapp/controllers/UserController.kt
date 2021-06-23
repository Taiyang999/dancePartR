package fr.esgi.danseapp.controllers

import fr.esgi.danseapp.models.UserEntity
import fr.esgi.danseapp.models.bodies.UserRegisterCommand
import fr.esgi.danseapp.services.UserServices
import io.jkratz.mediator.core.Mediator
import org.apache.catalina.User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.validation.BindingResult
import org.springframework.web.servlet.view.RedirectView
import java.net.URI
import java.util.*


@RestController
@RequestMapping("users")
class UserController/*(private val mediator: Mediator)*/{

    @RequestMapping(value = ["signUp"], method = [RequestMethod.GET])
    fun signUp(@RequestBody user: UserRegisterCommand, model : Model)  {
        model.addAttribute("user", user)
    }


    @PostMapping("registration")
    fun register(@RequestBody user: UserRegisterCommand, bindingResult: BindingResult): RequestEntity<Void> {
        if (bindingResult.hasErrors()) {
            ResponseEntity.badRequest().build<Unit>()
        }
        val encoder = BCryptPasswordEncoder()
        val password = encoder.encode(user.password)
        user.password = password
        return RequestEntity.post(URI("http://localhost:8080/users/signUp")).build()
    }
}