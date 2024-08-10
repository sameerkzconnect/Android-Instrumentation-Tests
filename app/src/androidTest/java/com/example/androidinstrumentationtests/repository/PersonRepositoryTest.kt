package com.example.androidinstrumentationtests.repository

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.androidinstrumentationtests.models.PersonDTO
import com.google.gson.JsonSyntaxException
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.FileNotFoundException

class PersonRepositoryTest {

    private lateinit var personRepository: PersonRespository

    @Before
    fun setup() {
        personRepository = PersonRespository()
    }

    @Test(expected = FileNotFoundException::class)
    fun populateQuoteFromAssets_emptyFileName_exception() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        personRepository.populateQuoteFromAssets(context, "")
    }

    @Test(expected = FileNotFoundException::class)
    fun populateQuoteFromAssets_wrongFileName_exception() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        personRepository.populateQuoteFromAssets(context, "test.json")
    }

    @Test(expected = JsonSyntaxException::class)
    fun populateQuoteFromAssets_malformed_exception() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        personRepository.populateQuoteFromAssets(context, "dummy-test.json")
    }

    @Test()
    fun populateQuoteFromAssets_validFileName_true() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        personRepository.populateQuoteFromAssets(context, "dummy.json")
        assertEquals(8, personRepository.personList.size)
    }

    @Test
    fun getPreviousPerson_expected_firstPerson() {
        //Arrange
        personRepository.populatePerson(
            arrayOf(
                PersonDTO(name = "Name1", "Lang1"),
                PersonDTO(name = "Name2", "Lang2"),
                PersonDTO(name = "Name3", "Lang3"),
            )
        )
        //Act
        val person = personRepository.getPreviousPerson()
        //Asset
        assertEquals("Name1", person.name)
    }

    @Test
    fun getNextPerson_expected_nextPerson() {
        //Arrange
        personRepository.populatePerson(
            arrayOf(
                PersonDTO(name = "Name1", "Lang1"),
                PersonDTO(name = "Name2", "Lang2"),
                PersonDTO(name = "Name3", "Lang3"),
            )
        )
        //Act
        val person = personRepository.getPreviousPerson()
        //Asset
        assertEquals("Name2", person.name)
    }
}