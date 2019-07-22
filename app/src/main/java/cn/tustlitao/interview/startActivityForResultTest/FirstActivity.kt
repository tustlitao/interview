package cn.tustlitao.interview.startActivityForResultTest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.tustlitao.interview.R
import kotlinx.android.synthetic.main.activity_first.*

/**
 *
 * 理解了， 使用NEW_TASK方式启动的Activity, 会立即调用onActivityResult方法， 且Result标志位为RESULT_CANCEL。
 *
 * 解释:   官方建议， 不同的任务栈不默认是不能传递数据的， 如果要传， 使用Intent(这不是废话吗)
 *
 * 场景解释:  场景解释很重要， 我们整两个栈 A->B(前台，B为栈顶) C->D(后台， D为栈顶)
 *
 * 此时 B启动了D，使用startActivityForResult, 此时呢， D所在的栈变为前台， 而C才是D finish掉所要显示的界面。
 *
 * 那么这样就会导致B的startActivityForResult没有结果。
 *
 * 因此， 不允许通过startActivityForResult获取其他栈的内容， 而直接会调用onActivityResult, 标志位为RESULT_CANCEL
 */
class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        btnStartSecondActivityStandard.setOnClickListener {
            val intent = Intent(this@FirstActivity, SecondActivity::class.java)
            startActivityForResult(intent, 1)
        }
        btnStartSecondActivityNewTask.setOnClickListener {
            val intent = Intent(this@FirstActivity, SecondActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.let {
            tvResult.text = data.getStringExtra("result")
        }
    }
}
