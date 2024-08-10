package com.example.androidinstrumentationtests.repository

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.gson.JsonSyntaxException
import org.junit.Before
import org.junit.Test
import java.io.FileNotFoundException

class PersonRepositoryTest{

    private lateinit var personRepository:PersonRespository
    @Before
    fun setup(){
        personRepository= PersonRespository()
    }

    @Test(expected = FileNotFoundException::class)
    fun populateQuoteFromAssets_emptyFileName_exception(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        personRepository.populateQuoteFromAssets(context,"")
    }

    @Test(expected = FileNotFoundException::class)
    fun populateQuoteFromAssets_wrongFileName_exception(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        personRepository.populateQuoteFromAssets(context,"test.json")
    }

    @Test(expected = JsonSyntaxException::class)
    fun populateQuoteFromAssets_malformed_exception(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        personRepository.populateQuoteFromAssets(context,"dummy-test.json")
    }

}