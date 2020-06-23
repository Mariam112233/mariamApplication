package th.ac.psu.comsci.mariam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var save: Button? = null
    private var SHOW: Button? = null
    private var ID: EditText? = null
    private var MONTH: EditText? = null
    private var MONEYINTO: EditText? = null
    private var MONEYOUT: EditText? = null
    private var databaseHelper: DatabaseHelper? = null
    private var tvname: TextView? = null
    private var resetBtn: Button? = null
    private var arrayList: ArrayList<String>? = null
    private var Sumlist: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        databaseHelper = DatabaseHelper(this)
        tvname = findViewById(R.id.tvname) as TextView
        save = findViewById(R.id.save) as Button
        SHOW = findViewById(R.id.SHOW) as Button
        ID = findViewById(R.id.ID) as EditText
        MONTH = findViewById(R.id.MONTH) as EditText
        MONEYINTO = findViewById(R.id.MONEYINTO) as EditText
        MONEYOUT = findViewById(R.id.MONEYOUT) as EditText
        resetBtn = findViewById(R.id.resetBtn) as Button


        save!!.setOnClickListener {
            databaseHelper!!.addGradeDetail( ID !!.text.toString(), MONTH!!.text.toString(), MONEYINTO!!.text.toString(), MONEYOUT!!.text.toString())
            ID!!.setText("")
            MONTH!!.setText("")
            MONEYINTO!!.setText("")
            MONEYOUT !!.setText("")

            Toast.makeText(this@MainActivity, "Stored Successfully!", Toast.LENGTH_SHORT).show()
        }


        SHOW!!.setOnClickListener {
            arrayList = databaseHelper!!.allSubjectList
            tvname!!.text = ""
            var ord = 1
            for (i in arrayList!!.indices) {
                tvname!!.text = tvname!!.text.toString() + (ord++) + ". " + arrayList!![i] + "\n"
            }
        }

        resetBtn!!.setOnClickListener {
            databaseHelper!!.resetDatabase()
        }
    }
}