package ru.ozon.route256.core_utils.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import ru.ozon.route256.core_utils.R
import ru.ozon.route256.core_utils.fadeVisibility

class ProgressButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private val progressBar: ProgressBar
    private val buttonTextView: TextView
    private val container: RelativeLayout

    private var isTouched: Boolean = false
    private var stateOneBackground: Int = 0
    private var stateTwoBackground: Int = 0
    private var stateOneTextColor: Int = 0
    private var stateTwoTextColor: Int = 0

    init {
        val root = LayoutInflater.from(context).inflate(R.layout.progress_button, this, true)
        buttonTextView = root.findViewById(R.id.button_text)
        progressBar = root.findViewById(R.id.progress_indicator)
        container = root.findViewById(R.id.pg_container)
        loadAttr(attrs, defStyleAttr)
    }

    private fun loadAttr(attrs: AttributeSet?, defStyleAttr: Int) {
        val arr = context.obtainStyledAttributes(
            attrs,
            R.styleable.ProgressButton,
            defStyleAttr,
            0
        )

        val buttonText = arr.getString(R.styleable.ProgressButton_text)
        val textSize = arr.getDimensionPixelSize(R.styleable.ProgressButton_textSize, 0).toFloat()
        if (textSize > 0) {
            buttonTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
        }
        val loading = arr.getBoolean(R.styleable.ProgressButton_loading, false)
        val enabled = arr.getBoolean(R.styleable.ProgressButton_enabled, true)
        isTouched = arr.getBoolean(R.styleable.ProgressButton_isTouched, false)
        stateOneBackground = arr.getColor(
            R.styleable.ProgressButton_stateOneBackground,
            R.color.stateOneBackgroundColor
        )
        stateTwoBackground =
            arr.getColor(R.styleable.ProgressButton_stateTwoBackground, R.color.stateTwoBackground)
        stateOneTextColor =
            arr.getColor(R.styleable.ProgressButton_stateOneTextColor, R.color.stateOneTextColor)
        stateTwoTextColor =
            arr.getColor(R.styleable.ProgressButton_stateTwoTextColor, R.color.stateTwoTextColor)

        arr.recycle()
        setIsTouched(isTouched)
        isEnabled = enabled
        buttonTextView.isEnabled = enabled
        setText(buttonText)
        setLoading(loading)
    }

    fun setIsTouched(isTouched: Boolean) {
        this.isTouched = isTouched
        if (isTouched) {
            container.setBackgroundColor(context.getColor(stateTwoBackground))
            buttonTextView.setTextColor(buttonTextView.context.getColorStateList(stateTwoTextColor))
        } else {
            container.setBackgroundColor(context.getColor(stateOneBackground))
            buttonTextView.setTextColor(buttonTextView.context.getColorStateList(stateOneTextColor))
        }
    }

    fun setLoading(loading: Boolean) {
        isClickable = !loading //Disable clickable when loading
        if (loading) {
            buttonTextView.fadeVisibility(View.GONE, 0)
            progressBar.fadeVisibility(View.VISIBLE, 0)
        } else {
            buttonTextView.fadeVisibility(View.VISIBLE, 1000)
            progressBar.fadeVisibility(View.GONE, 1000)
        }
    }

    fun setText(text: String?) {
        buttonTextView.text = text
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        buttonTextView.isEnabled = enabled
    }
}