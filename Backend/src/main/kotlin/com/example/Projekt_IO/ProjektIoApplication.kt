package com.example.Projekt_IO

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class ProjektIoApplication

fun main(args: Array<String>) {
	runApplication<ProjektIoApplication>(*args)
}
