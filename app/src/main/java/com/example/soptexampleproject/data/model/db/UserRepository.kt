package com.example.soptexampleproject.data.model.db

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update

class UserRepository(private val dao: UserDao) {
    suspend fun updateUser(user: User){
        dao.updateUser(user)
    }
    suspend fun deleteUser(user: User){
        dao.deleteUser(user)
    }

    suspend fun insertUser(user: User){
        dao.insertUser(user)
    }

    suspend fun getUser(id: Int):User=dao.getUser(id)

}