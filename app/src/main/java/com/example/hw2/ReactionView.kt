package com.example.hw2

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatTextView

class ReactionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
): AppCompatTextView(context, attrs, defStyle) {

    private var mySmile: String? = null
    private var myCountReaction: Int? = null
    private var mySelectedView: Boolean


    init {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.ReactionView)

        mySmile = typedArray.getString(R.styleable.ReactionView_smile)
        myCountReaction = typedArray.getInt(R.styleable.ReactionView_countReaction, 0)
        mySelectedView = typedArray.getBoolean(R.styleable.ReactionView_selectedView, true)

        text = "$mySmile $myCountReaction"

        setBackgroundResource(R.drawable.reaction_bg_unselected)

        typedArray.recycle()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action){
            MotionEvent.ACTION_DOWN -> {
                return true
            }
            MotionEvent.ACTION_UP -> {
                if (mySelectedView){
                    setBackgroundResource(R.drawable.reaction_bg_selected)
                    mySelectedView = false
                }else{
                    setBackgroundResource(R.drawable.reaction_bg_unselected)
                    mySelectedView = true
                }
            }
        }
        return false
    }
}