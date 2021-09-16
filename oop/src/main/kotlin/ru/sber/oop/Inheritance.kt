package ru.sber.oop

open class Room(val name: String, val size: Int) {
    protected open val dangerLevel = 5
    var monster: Monster = Goblin("Goblin", "Weak monster", "Fireball", 100)

    constructor(name: String) : this(name, 100)

    fun description() = "Room: $name"
    fun Monster.getSalutation() = "Hi noob"

    open fun load() = "Nothing much to see here...\nMonster: ${monster.getSalutation()}"
}

class TownSquare() : Room("Town Square", 1000) {
    override fun load() = "Loading..."

    final override val dangerLevel = super.dangerLevel - 3
}