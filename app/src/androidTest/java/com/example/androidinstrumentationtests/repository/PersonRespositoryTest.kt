package com.example.androidinstrumentationtests.repository

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class PersonRespositoryTest{

    lateinit var personRespository:PersonRespository
    @Before
    fun setup(){
        personRespository= PersonRespository()
    }

    @Test
    fun populateQuoteFromAssets_emptyFileName_false(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        personRespository.populateQuoteFromAssets(context,"")

    }

}