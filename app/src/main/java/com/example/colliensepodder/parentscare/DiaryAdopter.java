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


public class DiaryAdopter extends RecyclerView.Adapter<DiaryAdopter.ViewHolder> {
    Context mContext;

    ArrayList<Diary> diaries;
    float minute;
    public static Diary updateddiary;
    LayoutInflater layoutInflater;

    Callback callback;

    public DiaryAdopter(Context mContext, ArrayList<Diary> diaries, Callback callback) {
        this.layoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.diaries = diaries;
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.diary_text, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.DiaryTextTV.setText(diaries.get(position).getDiaryText());

        holder.edits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateddiary = diaries.get(position);
                Intent ii=new Intent(mContext,AddDiaryActivity.class);
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
                                obj.deleteDiary(diaries.get(position).getDiaryText());
                                Toast.makeText(mContext, "Deleted", Toast.LENGTH_SHORT).show();
                                diaries.remove(diaries.get(position));

                                notifyDataSetChanged();
                                if(diaries.size()==0){
                                    callback.Result("0");
                                }
                            }})
                        .setNegativeButton(android.R.string.no, null).show();

            }
        });
        /*holder.MedicineCallLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = String.valueOf(contacts.get(position).getContactNumber());
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                mContext.startActivity(intent);
            }
        });*/

    }


    @Override
    public int getItemCount() {
        return diaries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView DiaryTextTV;
        TextView dotss,edits;
        LinearLayout DiaryCallLinearLayout;

        ViewHolder(View itemView) {
            super(itemView);
            DiaryTextTV = itemView.findViewById(R.id.DiaryTextTV);
            dotss = itemView.findViewById(R.id.dotssa);
            DiaryCallLinearLayout = itemView.findViewById(R.id.DiaryCallLinearLayout);

            edits = itemView.findViewById(R.id.edits);

        }

    }


}
