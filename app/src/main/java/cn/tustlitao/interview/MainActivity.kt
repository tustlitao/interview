package cn.tustlitao.interview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import cn.tustlitao.interview.aidl.AIDLActivity
import cn.tustlitao.interview.handler.HandlerActivity
import cn.tustlitao.interview.startActivityForResultTest.FirstActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            btnStartAIDLActivity.setOnClickListener(this)
        btnStartHandlerActivity.setOnClickListener(this)
        btnStartActivityForResultTest.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnStartHandlerActivity -> startActivity(HandlerActivity::class.java)
            R.id.btnStartAIDLActivity -> startActivity(AIDLActivity::class.java)
            R.id.btnStartActivityForResultTest -> startActivity(FirstActivity::class.java)
        }
    }

    fun startActivity(cls: Class<*>) = startActivity(Intent(this, cls))
}



