package com.example.first

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FirstExampleApplication

fun main(args: Array<String>) {
	runApplication<FirstExampleApplication>(*args)
}
