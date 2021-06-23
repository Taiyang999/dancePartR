package fr.esgi.danseapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DanseAppApplication

fun main(args: Array<String>) {
    runApplication<DanseAppApplication>(*args)
}
