package com.demo.nasapictureapp.utils

//This class is use to display custom toast message
object ToastUtil {

    private val duration = Toast.LENGTH_LONG
    private var toast: Toast? = null

    //display toast message from bottom of screen
    fun showMessage(context: Activity?, message: String) {

        if (toast != null) {
            toast!!.cancel()
        }
        toast = Toast.makeText(context, message, duration)
        toast!!.show()
    }

    //display toast message
    fun showMessage(context: Activity?, message: String, gravity: Int) {

        if (toast != null) {
            toast!!.cancel()
        }
        toast = Toast.makeText(context, message, duration)
        toast!!.setGravity(gravity, 0, 0)
        toast!!.show()
    }
}