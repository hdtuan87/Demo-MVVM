package hdtuan.com.person.models

class Person {
    var name: String = ""
    var age: Int = 0

    constructor()

    constructor(name: String, age: Int){
        this.name = name
        this.age = age
    }
}