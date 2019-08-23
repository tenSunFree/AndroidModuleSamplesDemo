package com.home.androidmodulesamplesdemo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = "/app/main")
class MainActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_camera.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("action", "MainActivity -> CameraActivity")
            bundle.putString("name", "sun")
            bundle.putString("email", "a0985092384@gmail.com")
            ARouter.getInstance().build("/camera/main")
                .with(bundle)
                .navigation()
        }
    }
}