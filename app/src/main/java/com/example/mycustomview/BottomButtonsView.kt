package com.example.mycustomview

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.mycustomview.databinding.PartButtonBinding

enum class BottomButtonsClass {
    POSITIVE, NEGATIVE
}

typealias OnBottomButtonClickListener = (BottomButtonsClass) -> Unit

class BottomButtonsView(
    context: Context,
    attributeSet: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : ConstraintLayout(context, attributeSet, defStyleAttr, defStyleRes) {

    private val binding: PartButtonBinding
    private var listener: OnBottomButtonClickListener? = null

    var isProgressMode
        get() = false
        set(value) {
            binding.progress.isVisible = value
        }

    constructor(
        context: Context,
        attributeSet: AttributeSet?, defStyleAttr: Int,
    ) : this(context, attributeSet, defStyleAttr, R.style.DefaultButtonStyleRes)

    constructor(context: Context, attributeSet: AttributeSet?) : this(
        context,
        attributeSet,
        R.attr.bottomButtonStyleAttr
    )

    constructor(context: Context) : this(context, null)

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.part_button, this, true)
        binding = PartButtonBinding.bind(this)
        initializeAttributes(attributeSet, defStyleAttr, defStyleRes)
        initializeBottomButtonClickListener()
    }

    private fun initializeAttributes(
        attributeSet: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) {
        if (attributeSet == null) return
        val typeArray = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.BottomButtonsView,
            defStyleAttr,
            defStyleRes
        )

        with(binding) {
            val positiveButtonText =
                typeArray.getString(R.styleable.BottomButtonsView_bottomPositiveButtonText)
            setBottomPositiveButtonText(positiveButtonText)

            val negativeButtonText =
                typeArray.getString(R.styleable.BottomButtonsView_bottomNegativeButtonText)
            setBottomNegativeButtonText(negativeButtonText)

            val positiveButtonBackground =
                typeArray.getColor(
                    R.styleable.BottomButtonsView_bottomPositiveBackgroundColor,
                    Color.WHITE
                )
            positiveButton.backgroundTintList = ColorStateList.valueOf(positiveButtonBackground)

            val negativeButtonBackground =
                typeArray.getColor(
                    R.styleable.BottomButtonsView_bottomNegativeBackgroundColor,
                    Color.BLACK
                )
            negativeButton.backgroundTintList = ColorStateList.valueOf(negativeButtonBackground)

            val progressMode =
                typeArray.getBoolean(R.styleable.BottomButtonsView_progressMode, false)
            this@BottomButtonsView.isProgressMode = progressMode
        }
        typeArray.recycle()
    }

    private fun initializeBottomButtonClickListener() {
        binding.positiveButton.setOnClickListener {
            listener?.invoke(BottomButtonsClass.POSITIVE)
        }
        binding.negativeButton.setOnClickListener {
            listener?.invoke(BottomButtonsClass.NEGATIVE)
        }
    }

    fun setOnClickInBottomButtonListener(listener: OnBottomButtonClickListener?) {
        this.listener = listener
    }

    fun setBottomPositiveButtonText(text: String?) {
        binding.positiveButton.text = text ?: "show"
    }

    fun setBottomNegativeButtonText(text: String?) {
        binding.negativeButton.text = text ?: "hide"
    }
}