package com.example.exp5_119
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UsersDBHelper(context: Context) :
    SQLiteOpenHelper(context, "StudentsDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE ${DBContract.StudentEntry.TABLE_NAME} (" +
                "${DBContract.StudentEntry.COLUMN_REG_NO} TEXT PRIMARY KEY," +
                "${DBContract.StudentEntry.COLUMN_NAME} TEXT," +
                "${DBContract.StudentEntry.COLUMN_CGPA} TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${DBContract.StudentEntry.TABLE_NAME}")
        onCreate(db)
    }

    fun insertStudent(user: UserModel): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(DBContract.StudentEntry.COLUMN_REG_NO, user.regNo)
            put(DBContract.StudentEntry.COLUMN_NAME, user.name)
            put(DBContract.StudentEntry.COLUMN_CGPA, user.cgpa)
        }
        val result = db.insert(DBContract.StudentEntry.TABLE_NAME, null, values)
        return result != -1L
    }

    fun updateStudent(user: UserModel): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(DBContract.StudentEntry.COLUMN_NAME, user.name)
            put(DBContract.StudentEntry.COLUMN_CGPA, user.cgpa)
        }
        val result = db.update(
            DBContract.StudentEntry.TABLE_NAME,
            values,
            "${DBContract.StudentEntry.COLUMN_REG_NO} = ?",
            arrayOf(user.regNo)
        )
        return result > 0
    }

    fun deleteStudent(regNo: String): Boolean {
        val db = writableDatabase
        val result = db.delete(
            DBContract.StudentEntry.TABLE_NAME,
            "${DBContract.StudentEntry.COLUMN_REG_NO} = ?",
            arrayOf(regNo)
        )
        return result > 0
    }

    fun getAllStudents(): List<UserModel> {
        val students = mutableListOf<UserModel>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM ${DBContract.StudentEntry.TABLE_NAME}", null)
        if (cursor.moveToFirst()) {
            do {
                val regNo = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.StudentEntry.COLUMN_REG_NO))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.StudentEntry.COLUMN_NAME))
                val cgpa = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.StudentEntry.COLUMN_CGPA))
                students.add(UserModel(regNo, name, cgpa))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return students
    }
}
