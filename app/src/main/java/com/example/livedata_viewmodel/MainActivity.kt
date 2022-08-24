package com.example.livedata_viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG : String = "로그"
    }

    lateinit var myNumberViewModel: MyNumberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 뷰 모델 프로바이더를 통해 뷰모델 가져오기
        myNumberViewModel = ViewModelProvider(this).get(MyNumberViewModel::class.java)

        // 뷰모델이 가지고 있는 값의 변경사항을 관찰할 수 있는 라이브 데이터를 옵저빙
        myNumberViewModel.currentValue.observe(this, Observer{
            Log.d(TAG, "MainActivity - myNumberViewModel - currentValue 라이브 데이터 값 변경 : $it")
            number_textview.text = it.toString()
        })

        // 리스너 연결
        plus_button.setOnClickListener(this)
        minus_button.setOnClickListener(this)
    }

    // 클릭
    override fun onClick(v: View?) {
        val userInput = user_input_editText.text.toString().toInt()

        when(v) {
            plus_button -> myNumberViewModel.updateValue(actionType = ActionType.PLUS, userInput)
            minus_button -> myNumberViewModel.updateValue(ActionType.MINUS, userInput)
        }
    }
}