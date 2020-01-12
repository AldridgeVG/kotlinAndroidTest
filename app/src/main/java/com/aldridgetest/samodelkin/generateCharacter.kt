package com.aldridgetest.samodelkin

import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import java.io.Serializable
import java.net.URL

//get data from api
private const val data_api = "https://chargen-api.herokuapp.com"

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
    ) : Serializable

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
    //get data from api, web:   chargen-api.herokuapp.com
    fun fromApiData(apiData: String): characterData {
        val (race, name, dex, wis, str) = apiData.split(",")
        return characterData(name, race, dex, wis, str)
    }
}

//use coroutines to access network api
fun fetchCharacterData():Deferred<generateCharacter.characterData>{
    return async{
        val apiData = URL(data_api).readText()
        generateCharacter.fromApiData(apiData)
    }
}