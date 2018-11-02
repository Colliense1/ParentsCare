package com.example.colliensepodder.parentscare;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


public class EmergencyContactAdopter extends RecyclerView.Adapter<EmergencyContactAdopter.ViewHolder> {
    Context mContext;

    ArrayList<Contact> contacts;
    float minute;
    public static Contact updatedcontact;
    LayoutInflater layoutInflater;

    Callback callback;

    public EmergencyContactAdopter(Context mContext, ArrayList<Contact> contacts, Callback callback) {
        this.layoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.contacts = contacts;
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.emergency_contact, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.contactNameTV.setText(contacts.get(position).getContactName());
        holder.contactNumberTV.setText(contacts.get(position).getContactNumber());
        holder.edits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedcontact = contacts.get(position);
                Intent ii=new Intent(mContext,AddContactActivity.class);
                ii.putExtra("mode","2");
                mContext.startActivity(ii);

            }
        });
        holder.dotss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setMessage("Do you really want to delete?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                MedicineManagementDatabase obj = new MedicineManagementDatabase(mContext);
                                obj.deleteContact(contacts.get(position).getContactNumber());
                                Toast.makeText(mContext, "Deleted", Toast.LENGTH_SHORT).show();
                                contacts.remove(contacts.get(position));

                                notifyDataSetChanged();
                                if(contacts.size()==0){
                                    callback.Result("0");
                                }


                            }})
                        .setNegativeButton(android.R.string.no, null).show();

            }
        });
        holder.MedicineCallLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = String.valueOf(contacts.get(position).getContactNumber());
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                mContext.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView contactNameTV, contactNumberTV;
        TextView dotss,edits;
        LinearLayout MedicineCallLinearLayout;

        ViewHolder(View itemView) {
            super(itemView);
            contactNameTV = itemView.findViewById(R.id.ContactNameTV);
            contactNumberTV = itemView.findViewById(R.id.ContactNumberTV);
            dotss = itemView.findViewById(R.id.dotssa);
            MedicineCallLinearLayout = itemView.findViewById(R.id.MedicineCallLinearLayout);

            edits = itemView.findViewById(R.id.edits);

        }

    }


}
