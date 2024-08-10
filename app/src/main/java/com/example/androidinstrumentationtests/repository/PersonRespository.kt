package com.example.androidinstrumentationtests.repository

import android.content.Context
import com.example.androidinstrumentationtests.models.PersonDTO
import com.google.gson.Gson


class PersonRespository (){

    var personList = emptyArray<PersonDTO>()
    var currentPersionIndex = 0

    fun populateQuoteFromAssets(context: Context, fileName: String){
        val inputStream = context.assets.open(fileName)
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        personList = gson.fromJson(json, Array<PersonDTO>::class.java)
    }
    fun populatePerson(quotes: Array<PersonDTO>){
        personList = quotes
    }

    fun getCurrentPerson(): PersonDTO {
        return personList[currentPersionIndex]
    }

    fun getNextPerson(): PersonDTO {
        if (currentPersionIndex == personList.size - 1) return personList[currentPersionIndex]
        return personList[++currentPersionIndex]
    }

    fun getPreviousPerson(): PersonDTO {
        if (currentPersionIndex == 0) return personList[currentPersionIndex]
        return personList[--currentPersionIndex]
    }



}