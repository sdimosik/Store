package ru.ozon.route256.core_utils.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import ru.ozon.route256.core_utils.R

class CountButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val textTV: TextView
    private val container: ConstraintLayout
    private val controller: LinearLayout
    private val minusTV: TextView
    private val plusTV: TextView
    private val countTV: TextView

    private var callback: (Int) -> Unit = {}

    private var stateOneBackground: Int = 0
    private var stateTwoBackground: Int = 0
    private var stateOneTextColor: Int = 0
    private var stateTwoTextColor: Int = 0

    private var count = 0
    private var sum = 0
    private var priceForItem = 0
    private var buttonText: String? = ""

    init {
        val root = LayoutInflater.from(context).inflate(R.layout.count_button, this, true)
        textTV = root.findViewById(R.id.button_text)
        container = root.findViewById(R.id.pg_container)
        controller = root.findViewById(R.id.controller)
        minusTV = root.findViewById(R.id.minus)
        plusTV = root.findViewById(R.id.plus)
        countTV = root.findViewById(R.id.count)
        loadAttr(attrs, defStyleAttr)
    }

    private fun loadAttr(attrs: AttributeSet?, defStyleAttr: Int) {
        val arr = context.obtainStyledAttributes(
            attrs,
            R.styleable.CountButton,
            defStyleAttr,
            0
        )

        buttonText = arr.getString(R.styleable.CountButton_textA)
        val textSize = arr.getDimensionPixelSize(R.styleable.CountButton_textSizeA, 0).toFloat()
        if (textSize > 0) {
            textTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
        }
        stateOneBackground = arr.getColor(
            R.styleable.CountButton_stateOneBackgroundA,
            R.color.stateOneBackgroundColor
        )
        stateTwoBackground =
            arr.getColor(R.styleable.CountButton_stateTwoBackgroundA, R.color.stateTwoBackground)
        stateOneTextColor =
            arr.getColor(R.styleable.CountButton_stateOneTextColorA, R.color.stateOneTextColor)
        stateTwoTextColor =
            arr.getColor(R.styleable.CountButton_stateTwoTextColorA, R.color.stateTwoTextColor)

        arr.recycle()

        plusTV.setOnClickListener {
            setCount(count + 1)
        }

        minusTV.setOnClickListener {
            setCount(count - 1)
        }

        textTV.setOnClickListener {
            if (count <= 0) {
                setCount(1)
            }
        }

        container.setBackgroundColor(context.getColor(stateOneBackground))
        textTV.setTextColor(context.getColorStateList(stateOneTextColor))
        minusTV.setTextColor(context.getColorStateList(stateOneTextColor))
        plusTV.setTextColor(context.getColorStateList(stateOneTextColor))

        setCount(0)
    }

    fun setCount(count: Int) {
        this.count = count
        callback(count)
        countTV.text = count.toString()
        this.sum = priceForItem * count
        if (count <= 0) {
            controller.visibility = GONE
            setText(buttonText)
        } else {
            controller.visibility = VISIBLE
            setText(sum.toString())
        }
    }

    fun setCallback(action: (Int) -> Unit) {
        this.callback = action
    }

    fun setText(text: String?) {
        textTV.text = text
    }

    fun setPrice(price: Int) {
        this.priceForItem = price
    }
}