package com.aldridgetest.samodelkin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

//serial key
private const val dataKey = "dataKey"

private var Bundle.characterData
    get() = getSerializable(dataKey) as generateCharacter.characterData
    set(value) = putSerializable(dataKey, value)

class MainActivity : AppCompatActivity() {

    private var characterData = generateCharacter.generate()

    //realize serial state save
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.characterData = characterData
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //restore serialized data
        characterData = savedInstanceState?.characterData ?: generateCharacter.generate()

        generateButton.setOnClickListener {
            characterData = generateCharacter.generate()
            displayCharacterData()
        }

        displayCharacterData()
    }

    private fun displayCharacterData() {
        characterData.run {
            nameTextView.text = name
            raceTextView.text = race
            dexterityTextView.text = dex
            wisdomTextView.text = wis
            strengthTextView.text = str
        }
    }


}
