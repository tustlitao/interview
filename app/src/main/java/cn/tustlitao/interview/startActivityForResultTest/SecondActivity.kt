package cn.tustlitao.interview.startActivityForResultTest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import cn.tustlitao.interview.R
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        btnFinish.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btnFinish -> finishWithResult()
        }
    }

    private fun finishWithResult() {
        val text = etResult.text.toString()
        val intent = Intent()
        intent.putExtra("result", text)
        setResult(1, intent)
        finish()
    }
}
