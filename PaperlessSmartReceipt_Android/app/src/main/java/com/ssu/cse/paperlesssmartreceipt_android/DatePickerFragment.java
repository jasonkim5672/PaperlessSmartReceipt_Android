package com.ssu.cse.paperlesssmartreceipt_android;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by qwsdx on 2017-09-11.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), R.style.DialogTheme, this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        TextView tv1= (TextView) getActivity().findViewById(R.id.action_text);
        String dateString = view.getYear() + "/" + String.format("%02d", view.getMonth() + 1) + "/" + String.format("%02d", view.getDayOfMonth());
        tv1.setText(dateString);
        MainActivity.receiptInformHandler.showDateSearchLayout(dateString);
    }
}
