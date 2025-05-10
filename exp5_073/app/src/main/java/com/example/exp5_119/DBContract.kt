package com.example.exp5_119
import android.provider.BaseColumns

object DBContract {
    object StudentEntry : BaseColumns {
        const val TABLE_NAME = "students"
        const val COLUMN_REG_NO = "reg_no"
        const val COLUMN_NAME = "name"
        const val COLUMN_CGPA = "cgpa"
    }
}
