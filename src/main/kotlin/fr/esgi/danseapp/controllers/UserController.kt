package fr.esgi.danseapp.controllers

import fr.esgi.danseapp.models.UserEntity
import fr.esgi.danseapp.models.bodies.UserRegisterCommand
import org.springframework.http.*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.view.RedirectView
import java.net.URI
import java.util.*


@RestController
@RequestMapping("users")
class UserController/*(private val mediator: Mediator)*/{

    @RequestMapping(value = ["signUp"], method = [RequestMethod.GET])
    fun signUp(model : Model) : ModelAndView {
        model.addAttribute("user", UserEntity())
        return ModelAndView("users/signUp")
    }

    /*
    @RequestMapping("signUp")
    fun signUp(requestEntity: RequestEntity<UserEntity>, @ModelAttribute model : Model) : ResponseEntity<UserEntity>  {
    println("request body : " + requestEntity.body)
    val headers: HttpHeaders = requestEntity.headers
    println("request headers : $headers")
    val method = requestEntity.method
    println("request method : $method")
    println("request url: " + requestEntity.url)
    model.addAttribute("user", requestEntity.body)

    return ResponseEntity(
        requestEntity.body,
        HttpStatus.OK
        )
    }
     */

    @RequestMapping(value = ["registration"], method = [RequestMethod.POST] )
    fun register(@RequestBody user : UserEntity, bindingResult: BindingResult): RedirectView {
        if (bindingResult.hasErrors()) {
            ResponseEntity.badRequest().build<Unit>()
        }
        val userRegister = UserRegisterCommand(user.username, user.password, user.email)
        val requestEntity = RequestEntity(userRegister, HttpMethod.POST, URI("http://localhost:8080/users/registration"))
        println("request body : " + requestEntity.body)
        val headers: HttpHeaders = requestEntity.headers
        println("request headers : $headers")
        val method = requestEntity.method
        println("request method : $method")
        println("request url: " + requestEntity.url)
        ResponseEntity.created(URI.create("http://localhost:8080/users/signUp")).
            headers(requestEntity.headers).
            body(requestEntity.body)
        return RedirectView("users/signUp")
    }
}