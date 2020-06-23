package th.ac.psu.comsci.mariam

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.ArrayList

class DatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    val allSubjectList: ArrayList<String>//การโชวทั้งหมดมาเรียกใช้ตัวนี้
    get() {
        val gradesArrayList = ArrayList<String>()
        var sum = 0
        var insum =0
        var ID = ""
        var MONTH = ""
        var MONEYINTO = ""
        var MONEYOUT = ""
        val selectQuery = "SELECT * FROM $TABLE_GRADES"//select sum(moneyout) from $table_grade
        val db = this.readableDatabase
        val c = db.rawQuery(selectQuery, null)
        if (c.moveToFirst()) {
            do {
                ID  = c.getString(c.getColumnIndex(B_ID))
                MONTH = c.getString(c.getColumnIndex(B_MONTH))
                MONEYINTO = c.getString(c.getColumnIndex(B_MONEYINTO))
                MONEYOUT = c.getString(c.getColumnIndex(B_MONEYOUT))
                gradesArrayList.add("แก้วที่" + ID + "เมนู" + MONTH + "ราคาต่อแก้ว" + MONEYINTO+ "รายรับ" + MONEYOUT)

            } while (c.moveToNext())
            val selectsum = "SELECT SUM(MONEYOUT) as sum_cost FROM $TABLE_GRADES"
            val s = db.rawQuery(selectsum, null)
            if (s.moveToFirst()) {
                do {
                    sum = s.getInt(s.getColumnIndex(B_sumpric))
                } while (c.moveToNext())
            }
            val selectsumin = "SELECT SUM(MONEYINTO) as sum_costin FROM $TABLE_GRADES"
            val si = db.rawQuery(selectsumin, null)
            if (si.moveToFirst()) {
                do {
                    insum = si.getInt(si.getColumnIndex(B_sumpricin))
                } while (c.moveToNext())
            }
            Log.d("array", gradesArrayList.toString())

        }
        gradesArrayList.add("ส่วนลด"+sum.toString()+ "ราคารวม"+insum.toString())
        return gradesArrayList

    }

    init {

        Log.d("table", CREATE_TABLE_GRADES)
    }

    /*val allStudentsList: ArrayList<String>
        get() {
            val studentsArrayList = ArrayList<String>()
            var name = ""
            var lastname = ""
            val selectQuery = "SELECT  * FROM $TABLE_STUDENTS"
            val db = this.readableDatabase
            val c = db.rawQuery(selectQuery, null)
            if (c.moveToFirst()) {
                do {
                    name = c.getString(c.getColumnIndex(KEY_FIRSTNAME))
                    lastname = c.getString(c.getColumnIndex(KEY_LASTNAME))
                    studentsArrayList.add(name + " " + lastname)
                } while (c.moveToNext())
                Log.d("array", studentsArrayList.toString())
            }
            return studentsArrayList
        }

    init {

        Log.d("table", CREATE_TABLE_STUDENTS)
    } */
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_GRADES)
    }
    /*override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_STUDENTS)
    }*/

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_GRADES'")
        onCreate(db)
    }

    /*override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_STUDENTS'")
        onCreate(db)
    }*/

    fun resetDatabase() {
        val db = this.writableDatabase
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_GRADES'")
        db.execSQL(CREATE_TABLE_GRADES)
    }

    fun addGradeDetail(
        b_id: String,
        b_month: String,
        b_monyinto: String,
        b_monyout: String
    ): Long {
        val db = this.writableDatabase
        //val insertSQL = "INSERT INTO " + TABLE_GRADES + "(" + SUBJECT_ID + "," + SUBJECT_NAME +
        //        ", " + SEMESTER + "," + EDU_YEAR + "," + GRADE + ") VALUES (" +
        //        sid + "," + sname + "," + sem + "," + eduyear + "," + grade + ");"
        //db.execSQL(insertSQL)
        // Creating content values

        val values = ContentValues()
        values.put(B_ID, b_id)
        values.put(B_MONTH, b_month)
        values.put(B_MONEYINTO, b_monyinto)
        values.put(B_MONEYOUT, b_monyout)
        // insert row in students table

        return db.insert(TABLE_GRADES, null, values)
    }
    /*fun addStudentDetail(student: String, lastname: String): Long {
        val db = this.writableDatabase
        // Creating content values
        val values = ContentValues()
        values.put(KEY_FIRSTNAME, student)
        values.put(KEY_LASTNAME, lastname)
        // insert row in students table

        return db.insert(TABLE_STUDENTS, null, values)
    }*/

    companion object {
        var DATABASE_NAME = "grade_database"
        private val DATABASE_VERSION = 1
        private val TABLE_GRADES = "grades"
        private val B_ID = "ID"
        private val B_MONTH = "month"
        private val B_MONEYINTO= "moneyinto"
        private val B_MONEYOUT = "moneyout"
        private val B_sumpric =  "sum_cost"
        private val B_sumpricin =  "sum_costin"

        private val CREATE_TABLE_GRADES = ("CREATE TABLE " + TABLE_GRADES +
                "(" + B_ID + " TEXT PRIMARY KEY," + B_MONTH + " TEXT, " + B_MONEYINTO + " INTEGER," + B_MONEYOUT + " INTEGER," + " TEXT);")
    }

    /*companion object {

        var DATABASE_NAME = "student_database"
        private val DATABASE_VERSION = 1
        private val TABLE_STUDENTS = "students"
        private val KEY_ID = "id"
        private val KEY_FIRSTNAME = "name"
        private val KEY_LASTNAME = "lastname"

        /*CREATE TABLE students ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_number TEXT......);*/
        private val CREATE_TABLE_STUDENTS = ("CREATE TABLE "
                + TABLE_STUDENTS + "(" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FIRSTNAME + " TEXT, " + KEY_LASTNAME + " TEXT );")
    } */
}
