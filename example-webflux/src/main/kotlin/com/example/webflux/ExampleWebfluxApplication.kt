package com.example.webflux

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExampleWebfluxApplication

fun main(args: Array<String>) {
	runApplication<ExampleWebfluxApplication>(*args)
}
