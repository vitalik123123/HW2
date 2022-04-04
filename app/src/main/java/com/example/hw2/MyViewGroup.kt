package com.example.hw2

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.view.*

class MyViewGroup@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
): ViewGroup (context, attrs, defStyle) {

    private val firstChild: View?
        get() = if (childCount > 0) getChildAt(0) else null
    private val secondChild: View?
        get() = if (childCount > 1) getChildAt(1) else null
    private val thirdChild: View?
        get() = if (childCount > 2) getChildAt(2) else null
    private val fourthChild: View?
        get() = if (childCount > 3) getChildAt(3) else null


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        checkCountChild()

        firstChild?.let { countMeasureChild(it, widthMeasureSpec, heightMeasureSpec) }
        secondChild?.let { countMeasureChild(it, widthMeasureSpec, heightMeasureSpec) }
        thirdChild?.let { countMeasureChild(it, widthMeasureSpec, heightMeasureSpec) }
        fourthChild?.let { countMeasureChild(it, widthMeasureSpec, heightMeasureSpec) }

        secondChild?.marginBottom

        val firstWidth = firstChild?.measuredWidth ?: 0
        val firstHeight = firstChild?.measuredHeight ?: 0

        val secondWidth = secondChild?.measuredWidth ?: 0
        val secondHeight = secondChild?.measuredHeight ?: 0

        val thirdWidth = thirdChild?.measuredWidth ?: 0
        val thirdHeight = thirdChild?.measuredHeight ?: 0

        val fourthWidth = fourthChild?.measuredWidth ?: 0
        val fourthHeight = fourthChild?.measuredHeight ?: 0

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec) - paddingStart - paddingEnd - marginStart - marginEnd

        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val width = if (secondWidth >= thirdWidth && secondWidth >= fourthWidth){
            firstWidth + secondWidth
        }else if (thirdWidth >= secondWidth && thirdWidth >= fourthWidth){
            firstWidth + thirdWidth
        }else if (fourthWidth >= secondWidth && fourthWidth >= thirdWidth){
            firstWidth + fourthWidth
        }else{
            widthSize
        }

        val height = secondHeight + thirdHeight + fourthHeight

        setMeasuredDimension(width + paddingLeft + paddingRight + marginLeft + marginRight, height + paddingTop + paddingBottom + marginTop + marginBottom)
    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        firstChild?.layout(
            marginLeft + paddingLeft,
            marginTop + paddingTop,
            marginRight + paddingRight + firstChild?.measuredWidth!! ?: 0,
            marginBottom + paddingBottom + firstChild?.measuredHeight!! ?: 0
        )
        secondChild?.layout(
            marginLeft + paddingLeft + firstChild?.measuredWidth!! ?: 0,
            marginTop + paddingTop,
            marginRight + paddingRight + firstChild?.measuredWidth?.plus(secondChild?.measuredWidth!!)!! ?: 0,
            marginBottom + paddingBottom + secondChild?.measuredHeight!! ?: 0
        )
        thirdChild?.layout(
            marginLeft + paddingLeft + firstChild?.measuredWidth!! ?: 0,
            marginTop + paddingTop + secondChild?.measuredHeight!! ?:0,
            marginRight + paddingRight + firstChild?.measuredWidth?.plus(thirdChild?.measuredWidth!!)!! ?:0,
            marginBottom + paddingBottom + secondChild?.measuredHeight?.plus(thirdChild?.measuredHeight!!)!! ?:0
        )
        fourthChild?.layout(
            marginLeft + paddingLeft + firstChild?.measuredWidth!! ?: 0,
            marginTop + paddingTop + secondChild?.measuredHeight?.plus(thirdChild?.measuredHeight!!)!! ?: 0,
            marginRight + paddingRight + firstChild?.measuredWidth?.plus(fourthChild?.measuredWidth!!)!! ?: 0,
            marginBottom + paddingBottom + secondChild?.measuredHeight?.plus(thirdChild?.measuredHeight!!)?.plus(fourthChild?.measuredHeight!!)!!
                ?: 0
        )

    }

    private fun countMeasureChild(child: View, widthMeasureSpec: Int, heightMeasureSpec: Int){
        val specWidthSize = MeasureSpec.getSize(widthMeasureSpec) - paddingStart - paddingEnd - marginStart - marginEnd
        val specHeightSize = MeasureSpec.getSize(heightMeasureSpec) - paddingTop - paddingBottom - marginTop - marginBottom

        val childWidthSpec = when (MeasureSpec.getMode(widthMeasureSpec)){
            MeasureSpec.UNSPECIFIED -> widthMeasureSpec
            MeasureSpec.AT_MOST -> widthMeasureSpec
            MeasureSpec.EXACTLY -> MeasureSpec.makeMeasureSpec(specWidthSize, MeasureSpec.AT_MOST)
            else -> error("Unreachable")
        }

        val childHeightSpec = when (MeasureSpec.getMode(heightMeasureSpec)){
            MeasureSpec.UNSPECIFIED -> heightMeasureSpec
            MeasureSpec.AT_MOST -> heightMeasureSpec
            MeasureSpec.EXACTLY -> MeasureSpec.makeMeasureSpec(specHeightSize, MeasureSpec.AT_MOST)
            else -> error("Unreachable")
        }

        child.measure(childWidthSpec, childHeightSpec)
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun generateLayoutParams(p: LayoutParams?): LayoutParams {
        return MarginLayoutParams(p)
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }

    override fun checkLayoutParams(p: LayoutParams?): Boolean {
        return p is MarginLayoutParams
    }

    private fun checkCountChild() {
        if (childCount > 4) error("more 4 child")
    }
}