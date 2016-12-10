package com.example.michael.kassenautomat_dhbw.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.michael.kassenautomat_dhbw.OnButtonClickedCallback;
import com.example.michael.kassenautomat_dhbw.util.KassenautomatContext;
import com.example.michael.kassenautomat_dhbw.R;
import com.example.michael.kassenautomat_dhbw.datatypes.Ticket;
import com.example.michael.kassenautomat_dhbw.exceptions.DbException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Michael on 30.04.2016.
 */
public class EditTicketDialog extends DialogFragment {
    public static final String DIALOG_BUNDLE_ID = "DIALOG_BUNDLE_ID";

    private int year = 1970;
    private int month = 1;
    private int day = 1;

    OnButtonClickedCallback mCallback;
    Ticket ticket;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.dialog_edit_ticket, null);


        final TextView dateText = (TextView) view.findViewById(R.id.dialog_edit_ticket_date_text);
        final TimePicker timePicker = (TimePicker) view.findViewById(R.id.dialog_edit_ticket_time_dialog);
        TextView validText = (TextView) view.findViewById(R.id.dialog_edit_ticket_text_valid);
        TextView paidText = (TextView) view.findViewById(R.id.dialog_edit_ticket_text_paid);
        final Switch validSwitch = (Switch) view.findViewById(R.id.dialog_edit_ticket_switch_valid);
        final Switch paidSwitch = (Switch) view.findViewById(R.id.dialog_edit_ticket_switch_paid);
        Button okButton = (Button) view.findViewById(R.id.dialog_edit_ticket_button_ok);
        Button cancleButton = (Button) view.findViewById(R.id.dialog_edit_ticket_button_cancel);

        Bundle bundle = getArguments();

        long id = bundle.getLong(DIALOG_BUNDLE_ID);



        try {
            this.ticket = mCallback.getKassenautomatContext().getDatabaseConnection().getTicket(id);
        } catch (DbException e) {
            e.printStackTrace();
        }


        Date date = new Date(ticket.getTimestamp());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH)+1;
        day = cal.get(Calendar.DAY_OF_MONTH);

        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(cal.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(cal.get(Calendar.MINUTE));


        dateText.setText(beautifyDateToString(day)+"."+beautifyDateToString(month)+"."+year);

        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int pYear, int pMonthOfYear, int pDayOfMonth) {
                                year = pYear;
                                month = pMonthOfYear+1;
                                day = pDayOfMonth;
                                dateText.setText(beautifyDateToString(day)+"."+beautifyDateToString(month)+"."+year);
                            }
                        },year,month,day);
                dialog.updateDate(year, month-1, day);
                dialog.show();
            }
        });


        validSwitch.setChecked(ticket.isValid());
        validText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validSwitch.setChecked(!validSwitch.isChecked());
            }
        });

        paidSwitch.setChecked(ticket.isPaid());
        paidText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paidSwitch.setChecked(!paidSwitch.isChecked());
            }
        });


        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticket.setValid(validSwitch.isChecked() ? 1 : 0);
                ticket.setPaid(paidSwitch.isChecked() ? 1 : 0);

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/hh/mm");
                String strYear = year + "";
                String strMonth = beautifyDateToString(month);
                String strDay = beautifyDateToString(day);
                String strHours = beautifyDateToString(timePicker.getCurrentHour());
                String strMinutes = beautifyDateToString(timePicker.getCurrentMinute());

                String newDate = strYear + "/" + strMonth + "/" + strDay + "/" + strHours + "/" + strMinutes;
                System.out.println(newDate);

                Date date = null;
                try {
                    date = dateFormat.parse(newDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if(date != null) {
                    ticket.setTimestamp(date.getTime());
                } else {
                    dismiss();
                }

                try {
                    mCallback.getKassenautomatContext().getDatabaseConnection().updateTicket(ticket);
                } catch (DbException e) {
                    e.printStackTrace();
                }
                dismiss();
            }
        });

        cancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);

        return builder.create();
    }

   String beautifyDateToString (int date)
   {
       String beautifiedDate = date < 10 ? "0" + date : date + "";
       return beautifiedDate;
   }
    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
        if(mCallback.getKassenautomatContext() == null || mCallback == null) {
            System.out.println("KassenautomatContext and mCallback has to be set notNull to show.");
            Toast.makeText(mCallback.getKassenautomatContext().getContext(), "KassenautomatContext and mCallback has to be set notNull to show.", Toast.LENGTH_SHORT).show();
            System.exit(1);
        }

    }

    public static Bundle getBundle(long id) {
        Bundle bundle = new Bundle();
        bundle.putLong(DIALOG_BUNDLE_ID, id);
        return bundle;
    }
    public void setmCallback(OnButtonClickedCallback mCallback) {
        this.mCallback = mCallback;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        mCallback.updateTicketList();
    }
}