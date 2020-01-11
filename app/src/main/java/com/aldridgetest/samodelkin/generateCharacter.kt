package com.aldridgetest.samodelkin

private fun <T> List<T>.rand() = shuffled().first()

private fun Int.roll() = (0 until this).map { (1..6).toList().rand() }.sum().toString()

private val firstName = listOf("Eli", "Alex", "Sophie")

private val lastName = listOf("Swordbreaker", "Lightweaver", "Dragonmaster")

object generateCharacter {
    data class characterData(
        val name: String,
        val race: String,
        val dex: String,
        val wis: String,
        val str: String
    )

    private fun name() = "${firstName.rand()} ${lastName.rand()}"
    private fun race() = listOf("dwarf", "elf", "human").rand()
    private fun dex() = 4.roll()
    private fun wis() = 3.roll()
    private fun str() = 5.roll()

    fun generate() = characterData(
        name = name(),
        race = race(),
        dex = dex(),
        wis = wis(),
        str = str()
    )
}