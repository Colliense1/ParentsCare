package com.example.colliensepodder.parentscare;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class DoctorAdopter extends RecyclerView.Adapter<DoctorAdopter.ViewHolder> {

    Context mContext;

    ArrayList<Doctorinfo> doctorinfos;
    public static Doctorinfo updateddoctor;
    float minute;
    LayoutInflater layoutInflater;

    Callback callback;

    public DoctorAdopter(Context mContext, ArrayList<Doctorinfo> doctorinfos, Callback callback) {
        this.layoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.doctorinfos = doctorinfos;
        this.callback = callback;

    }

    @Override
    public DoctorAdopter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.doctor_information, parent, false);
        return new DoctorAdopter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DoctorAdopter.ViewHolder holder, final int position) {
        holder.DoctorNameTV.setText(doctorinfos.get(position).getDoctorName());
        holder.DoctorNumberTV.setText(doctorinfos.get(position).getDoctorNumber());
        holder.DoctorEmailTV.setText(doctorinfos.get(position).getDoctorEmail());
        holder.DoctorspecialityTV.setText(doctorinfos.get(position).getSpeciality());
        if (doctorinfos.get(position).getAvatar().toString().equals("male")) {
            holder.avatarGender.setImageResource(R.drawable.doctor_avatar_male);

        }
        if (doctorinfos.get(position).getAvatar().toString().equals("female")) {
            holder.avatarGender.setImageResource(R.drawable.doctor_avatar_female);

        }
        holder.editsdoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateddoctor = doctorinfos.get(position);
                Intent ii = new Intent(mContext, AddDoctorActivity.class);
                ii.putExtra("mode", "2");
                mContext.startActivity(ii);
            }
        });
        holder.dotssaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setMessage("Do you really want to delete?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                MedicineManagementDatabase obj = new MedicineManagementDatabase(mContext);
                                obj.deleteDoctor(doctorinfos.get(position).getDoctorNumber());
                                Toast.makeText(mContext, "Deleted", Toast.LENGTH_SHORT).show();
                                doctorinfos.remove(doctorinfos.get(position));
                                notifyDataSetChanged();

                                if (doctorinfos.size() == 0) {
                                    callback.Result("0");
                                }
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();

            }
        });
        holder.emergencyDoctorTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = String.valueOf(doctorinfos.get(position).getDoctorNumber());
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return doctorinfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView DoctorNameTV;
        TextView DoctorNumberTV;
        TextView DoctorEmailTV;
        TextView DoctorspecialityTV;
        TextView dotssaa;
        TextView editsdoctor;
        TextView emergencyDoctorTV;
        LinearLayout doctorCallLinearLayout;
        CircleImageView avatarGender;

        ViewHolder(View itemView) {
            super(itemView);
            DoctorNameTV = itemView.findViewById(R.id.DoctorNameTV);
            DoctorNumberTV = itemView.findViewById(R.id.DoctorNumberTV);
            DoctorEmailTV = itemView.findViewById(R.id.DoctorEmailTV);
            DoctorspecialityTV = itemView.findViewById(R.id.DoctorspecialityTV);
            dotssaa = itemView.findViewById(R.id.dotssaa);
            doctorCallLinearLayout = itemView.findViewById(R.id.doctorCallLinearLayout);
            emergencyDoctorTV = itemView.findViewById(R.id.emergencyDoctorTV);
            editsdoctor = itemView.findViewById(R.id.editsdoctor);
            avatarGender = itemView.findViewById(R.id.imageViewAvatar);

        }

    }

}
