package com.example.colliensepodder.parentscare;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MyAppointmentAdopter extends RecyclerView.Adapter<MyAppointmentAdopter.ViewHolder> {

    Context mContext;

    ArrayList<AppointmentInfo> appointmentInfos;
    public static AppointmentInfo updateddoctorappointment;
    float minute;
    LayoutInflater layoutInflater;

    Callback callback;

    public MyAppointmentAdopter(Context mContext, ArrayList<AppointmentInfo> appointmentInfos, Callback callback) {
        this.layoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.appointmentInfos = appointmentInfos;
        this.callback = callback;

    }

    @Override
    public MyAppointmentAdopter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.doctor_appointment_information, parent, false);
        return new MyAppointmentAdopter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyAppointmentAdopter.ViewHolder holder, final int position) {
        holder.DoctorAppointmentTitleTV.setText(appointmentInfos.get(position).getDoctorAppointmentTitle());
        holder.DoctorAppointmentNameTV.setText(appointmentInfos.get(position).getDoctorAppointmentName());
        holder.DoctorAppointmentLocationTV.setText(appointmentInfos.get(position).getDoctorAppointmentLocation());
        holder.editsdoctorappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateddoctorappointment = appointmentInfos.get(position);
                Intent ii=new Intent(mContext,AddMyAppointsmentsActivity.class);
                ii.putExtra("mode","2");
                mContext.startActivity(ii);
            }
        });
        holder.deletedoctorappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setMessage("Do you really want to delete?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                MedicineManagementDatabase obj = new MedicineManagementDatabase(mContext);
                                obj.deleteDoctorAppointment(appointmentInfos.get(position).getDoctorAppointmentTitle());
                                Toast.makeText(mContext, "Deleted", Toast.LENGTH_SHORT).show();
                                appointmentInfos.remove(appointmentInfos.get(position));
                                notifyDataSetChanged();

                                if(appointmentInfos.size()==0){
                                    callback.Result("0");
                                }
                            }})
                        .setNegativeButton(android.R.string.no, null).show();

            }
        });
//        holder.doctorCallLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String phone = String.valueOf(doctorinfos.get(position).getDoctorName());
//                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
//                mContext.startActivity(intent);
//            }
//        });

    }


    @Override
    public int getItemCount() {
        return appointmentInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView DoctorAppointmentTitleTV;
        TextView DoctorAppointmentNameTV;
        TextView DoctorAppointmentLocationTV;
        TextView deletedoctorappointment;
        TextView editsdoctorappointment;
        LinearLayout doctorCallLinearLayout;

        ViewHolder(View itemView) {
            super(itemView);
            DoctorAppointmentTitleTV = itemView.findViewById(R.id.DoctorAppointmentTitleTV);
            DoctorAppointmentNameTV = itemView.findViewById(R.id.DoctorAppointmentNameTV);
            DoctorAppointmentLocationTV = itemView.findViewById(R.id.DoctorAppointmentLocationTV);
            deletedoctorappointment = itemView.findViewById(R.id.deletedoctorappointment);
            doctorCallLinearLayout = itemView.findViewById(R.id.doctorCallLinearLayout);
            editsdoctorappointment = itemView.findViewById(R.id.editsdoctorappointment);

        }

    }


}
