package th.ac.psu.commsci.cal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val num1 = findViewById<EditText>(R.id.num1)
        val num2 = findViewById<EditText>(R.id.num2)
        val result = findViewById<EditText>(R.id.result)
        val plus = findViewById<Button>(R.id.plusBtn)
        val minus = findViewById<Button>(R.id.minusBtn)
        val multiply = findViewById<Button>(R.id.multiplyBtn)
        val divide = findViewById<Button>(R.id.divideBtn)

        plus.setOnClickListener{
            num1.text.toString().toInt()
            val n1 =num1.text.toString().toInt()
            val n2 =num2.text.toString().toInt()
            val r = n1 + n2
            result.setText(r.toString())
        }
        minus.setOnClickListener{
            num1.text.toString().toInt()
            val n1 =num1.text.toString().toInt()
            val n2 =num2.text.toString().toInt()
            val r = n1 - n2
            result.setText(r.toString())
        }
        multiply.setOnClickListener{
            val n1 =num1.text.toString().toInt()
            val n2 =num2.text.toString().toInt()
            val r = n1 * n2
            result.setText(r.toString())
        }
        divide.setOnClickListener{
            val n1 =num1.text.toString().toInt()
            val n2 =num2.text.toString().toInt()
            val r = n1 / n2
            result.setText(r.toString())
        }

    }
}

