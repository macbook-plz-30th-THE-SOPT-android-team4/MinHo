package com.example.soptexampleproject.data.model.db

import androidx.room.*

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user_data_base WHERE id=:id")
    suspend fun getUser(id: Int): User
}