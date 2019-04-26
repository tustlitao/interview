package cn.tustlitao.interview.handler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Toast
import cn.tustlitao.interview.R
import kotlinx.android.synthetic.main.activity_handler.*

class HandlerActivity : AppCompatActivity() {

    companion object {
        const val FIRST_MESSAGE = 1
    }


    /**
     * 使用post方法 创建的Handler会自动绑定 当前线程, 此处即UI线程
     */

    private val postHandler = Handler()

    private val messageHandler = Handler { message ->
        when(message.what) {
            FIRST_MESSAGE -> {
                Toast.makeText(this, "FIRST MESSAGE:${message.obj}", Toast.LENGTH_SHORT).show()
                textView.text = "Message Handler!"
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler)

        //创建线程重写run方法 postHandler.post(runnable) 启动线程
        postButton.setOnClickListener {
            object: Thread() {
                override fun run() {
                    Thread.sleep(2000L)
                    postHandler.post{ this@HandlerActivity.textView.text = "Post Handler!" }
                }
            }.start()
        }

        sendMessageButton.setOnClickListener {
            object: Thread() {
                override fun run() {
                    Thread.sleep(2000L)

                    //创建message的三种方式
                    //1. 直接message
                    //2. Message.obtain(messageHandler)
                    //3. messageHandler.obtainMessage()
                    val message = Message()

                    //message.what 用于识别不同的message
                    message.what = FIRST_MESSAGE

                    //message.arg1 message.arg2 可以传一些简单的Int数据
                    message.arg1 = 1
                    //message.obj 可以传入对象
                    message.obj = null
                    //message.data 可以传入Bundle
                    message.data = Bundle()

                    //通过sendMessage 将message传入handler中
                    messageHandler.sendMessage(message)
                }
            }.start()
        }
    }
}
