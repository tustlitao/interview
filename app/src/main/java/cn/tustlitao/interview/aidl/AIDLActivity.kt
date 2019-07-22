package cn.tustlitao.interview.aidl

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import cn.tustlitao.interview.R
import kotlinx.android.synthetic.main.activity_aidl.*

class AIDLActivity : AppCompatActivity(), View.OnClickListener {

    private val mConnection = object: ServiceConnection {

        override fun onBindingDied(name: ComponentName?) {
            tvPersonInfo.text = "断开啦"
        }

        override fun onServiceDisconnected(name: ComponentName?) {
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val remoteService = IRemoteService.Stub.asInterface(service)
            tvPersonInfo.text = remoteService.simpleData.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aidl)
        btnBindService.setOnClickListener(this)
        btnUnBindService.setOnClickListener(this)


    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btnBindService -> bindService()
            R.id.btnUnBindService -> unbindService()
        }
    }

    private fun bindService() {
        val intent = Intent(this@AIDLActivity, RemoteService::class.java)
        bindService(intent, mConnection, Service.BIND_AUTO_CREATE)
    }

    private fun  unbindService() {
        unbindService(mConnection)
    }
}
