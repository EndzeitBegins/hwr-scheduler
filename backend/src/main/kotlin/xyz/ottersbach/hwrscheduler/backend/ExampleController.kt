package xyz.ottersbach.hwrscheduler.backend

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("api")
class ExampleController {

    @GetMapping("test")
    fun test(): String {
        return "Hello World"
    }
}