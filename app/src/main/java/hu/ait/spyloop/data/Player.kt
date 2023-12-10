package hu.ait.spyloop.data

data class Player(
    val name: String,
    var outOfLoop: Boolean,
    var votes: Int
)
