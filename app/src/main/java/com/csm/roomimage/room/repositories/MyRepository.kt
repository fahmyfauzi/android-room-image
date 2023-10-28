package com.csm.roomimage.room.repositories

import androidx.lifecycle.LiveData
import com.csm.roomimage.room.MyDao
import com.csm.roomimage.room.models.Person

class MyRepository(private val myDao: MyDao) {

    val readPerson: LiveData<List<Person>> = myDao.readPerson()

    suspend fun insertPerson(person: Person){
        myDao.insertPerson(person)
    }

}