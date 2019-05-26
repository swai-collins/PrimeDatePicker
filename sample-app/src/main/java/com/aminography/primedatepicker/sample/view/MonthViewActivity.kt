package com.aminography.primedatepicker.sample.view

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.aminography.primecalendar.common.CalendarType
import com.aminography.primedatepicker.MyMonthView
import com.aminography.primedatepicker.sample.R
import com.aminography.primedatepicker.tools.CurrentCalendarType
import com.aminography.primedatepicker.tools.PersianUtils
import com.aminography.primedatepicker.tools.Utils
import kotlinx.android.synthetic.main.activity_month_view.*

class MonthViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_month_view)

        val calendar = Utils.newCalendar()
        monthView.setDate(calendar.year, calendar.month)

        val typeface: Typeface? = when (CurrentCalendarType.type) {
            CalendarType.CIVIL -> null
            CalendarType.PERSIAN -> Typeface.createFromAsset(assets, FONT_PATH_PERSIAN)
            CalendarType.HIJRI -> Typeface.createFromAsset(assets, FONT_PATH_ARABIC)
        }

        monthView.fontTypeface = typeface

        val minDateCalendar = Utils.newCalendar()
        minDateCalendar.dayOfMonth = 4
        monthView.minDateCalendar = minDateCalendar

        val maxDateCalendar = Utils.newCalendar()
        maxDateCalendar.dayOfMonth = 28
        monthView.maxDateCalendar = maxDateCalendar

        selectSingleDateRadioButton.setOnCheckedChangeListener { button, isChecked ->
            if (button.isPressed && isChecked) {
                monthView.selectType = MyMonthView.SelectType.SINGLE
            }
        }

        selectStartDateRadioButton.setOnCheckedChangeListener { button, isChecked ->
            if (button.isPressed && isChecked) {
                monthView.selectType = MyMonthView.SelectType.START_RANGE
            }
        }

        selectEndDateRadioButton.setOnCheckedChangeListener { button, isChecked ->
            if (button.isPressed && isChecked) {
                monthView.selectType = MyMonthView.SelectType.END_RANGE
            }
        }
    }
}

const val FONT_PATH_PERSIAN = "fonts/IRANSans(FaNum).ttf"
const val FONT_PATH_ARABIC = "fonts/Uthmani.otf"
