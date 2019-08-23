package com.home.module_camera

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_camera.*
import org.jetbrains.anko.toast

@Route(path = "/camera/main")
class CameraActivity : AppCompatActivity() {

    @Autowired
    @JvmField
    var action: String? = null
    @Autowired
    @JvmField
    var name: String? = null
    @Autowired
    @JvmField
    var email: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        initializeView()
        getPermissions()
    }

    @SuppressLint("CheckResult")
    private fun getPermissions() {
        val rxPermissions = RxPermissions(this)
        rxPermissions
            .request(Manifest.permission.CAMERA)
            .subscribe { granted ->
                if (granted) {
                    toast("取得CAMERA權限 成功")
                } else {
                    toast("取得CAMERA權限 失敗")
                }
            }
    }

    private fun initializeView() {
        text_view_action.text = action
        text_view_name.text = name
        text_view_email.text = email
    }
}
