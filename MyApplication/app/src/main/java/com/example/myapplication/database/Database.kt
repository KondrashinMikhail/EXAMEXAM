package com.example.myapplication.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.sql.SQLException

class Database(context: Context) : SQLiteOpenHelper(context, "AppDatabase", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS client (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun addClient(name: String) {
        val db = writableDatabase
        val contentValues = ContentValues().apply { put("name", name) }
        db.insert("client", null, contentValues)
        db.close()
    }

    @SuppressLint("Range")
    fun getAllClients(): List<Client> {
        val userList = mutableListOf<Client>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM client", null)

        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndex("id"))
            val name = cursor.getString(cursor.getColumnIndex("name"))
            userList.add(Client(id, name))
        }
        cursor.close()
        db.close()

        return userList
    }

    @SuppressLint("Range")
    fun getById(id: Long): Client? {
        var client: Client? = null
        val db = readableDatabase
        try {
            val cursor = db.rawQuery("SELECT * FROM CLIENT WHERE id =?", arrayOf(id.toString()))
            if (cursor.moveToFirst()) {
                client = Client(
                    id = cursor.getLong(cursor.getColumnIndex("id")),
                    name = cursor.getString(cursor.getColumnIndex("name"))
                )
            }
            cursor.close()
        } catch (_: SQLException) {}

        db.close()

        return client
    }

    fun updateClient(clientId: Long, newName: String) {
        val db = writableDatabase
        db.beginTransaction()
        try {
            val contentValues = ContentValues().apply {
                put("name", newName)
            }
            db.update("client", contentValues, "id=?", arrayOf(clientId.toString())).toInt()
            db.setTransactionSuccessful()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db.endTransaction()
        }
    }

    fun deleteUser(clientId: Long) {
        val db = writableDatabase
        db.delete("client", "id=?", arrayOf(clientId.toString()))
        db.close()
    }
}