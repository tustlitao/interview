package cn.tustlitao.interview.aidl

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.os.Parcel
import android.os.RemoteException


class RemoteService : Service() {

    companion object {
        private const val TAG = "Remote"
    }

    private lateinit var mPerson: Person

    private val mBinder = object: IRemoteService.Stub() {
        override fun getSimpleData(): Person {
            return mPerson
        }

        /**此处可用于权限拦截 */
        @Throws(RemoteException::class)
        override fun onTransact(code: Int, data: Parcel, reply: Parcel, flags: Int): Boolean {
            return super.onTransact(code, data, reply, flags)
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
        mPerson = Person(21, "LiTao")
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d(TAG, "onBind")
        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnBind")
        return super.onUnbind(intent)
    }
}
