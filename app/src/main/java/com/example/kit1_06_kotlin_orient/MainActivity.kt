package com.example.kit1_06_kotlin_orient

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Surface
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*  // этот импорт дает доступ к полям Layout activity_main.xml


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        isLandscapeMode(this)
    }

    override fun onResume() {
        super.onResume()
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            editText.text = " Портретная ориентация "
            Grid.columnCount = 1
        } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            editText.text = " Альбомная ориентация "
            Grid.columnCount = 2
        } else
            editText.text = "Квадратная ориентация"
        editText.text = " ${editText.text}  ${getRotateOrientation()}"
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // Checks the orientation of the screen - перехватывает
        // в манифест: android:configChanges="orientation|screenSize" и тогда перехватывает
        // добавление этого прекращает смену ориентации и пересоздание активности
        // будет все время портретная зато не забывает поля
        // и ничего не надо сохранять - восстанавливать
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show()
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show()
        }
    }


    // Можно также вычислить ширину и высоту экрана, если высота больше ширины,
    // то устройство в портретной ориентации, иначе - в альбомной:

    private fun isLandscapeMode(activity: AppCompatActivity): Boolean {
        var mOrientation = ""
        val width = activity.windowManager.defaultDisplay.width
        val height = activity.windowManager.defaultDisplay.height

        val isLandscape = width > height

        if (isLandscape)
            mOrientation = "Альбомная"
        else
            mOrientation = "Портретная"
        Toast.makeText(this, " $mOrientation  ширина $width  высота $height ", Toast.LENGTH_SHORT).show()
        return isLandscape
        //  Сейчас этот код считается устаревшим
        // и для вычисления размера экрана
        // используются другие методы
    }

    fun getRotateOrientation(): String {
        val rotate = windowManager.defaultDisplay.rotation
        when (rotate) {
            Surface.ROTATION_0 -> return "Не поворачивали"
            Surface.ROTATION_90 -> return "Повернули на 90 градусов против часовой стрелки"
            Surface.ROTATION_180 -> return "Повернули на 180 градусов"
            Surface.ROTATION_270 -> return "Повернули на 90 градусов по часовой стрелке"
            else -> return "Не понятно"
        }
    }
}