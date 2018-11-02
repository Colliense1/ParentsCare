package com.example.colliensepodder.parentscare;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;
import android.widget.ViewFlipper;


public class HomeFragment extends Fragment {

    private CardView cardViewPillReminder,cardViewMyAppointments,
            cardViewMedicinelibrary,cardViewHealthStats,cardViewMyDiary;
    LinearLayout linearLayoutCallMsgEmContacts;

    //ImageButton btn,emercontact,medlib,mymed;

    ViewFlipper v_flipper;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);


        int images[] = {R.drawable.pslide1,R.drawable.pslide2};

        v_flipper = (ViewFlipper)view.findViewById(R.id.v_flipper);

        /*for (int i = 0;i<images.length;i++){
            flipperImages(images[i]);
        }*/

        for (int image:images){
            flipperImages(image);
        }

        /*btnn =(Button)view.findViewById(R.id.btn3);
        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mymdcn();
                Intent i = new Intent(getActivity(),Mymedicines.class);
                startActivity(i);

            }
        });*/

        cardViewPillReminder =(CardView) view.findViewById(R.id.cardViewPillReminder);
        cardViewPillReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pillrmdr();
                Intent i = new Intent(getActivity(),Pillreminder.class);
                startActivity(i);
            }
        });

        cardViewMyAppointments = (CardView) view.findViewById(R.id.cardViewMyAppointments);
        cardViewMyAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicinelibrary();
                //Intent i = new Intent(getActivity(),MedicinePreviewActivity.class);
                //startActivity(i);
            }
        });

        cardViewMedicinelibrary = (CardView) view.findViewById(R.id.cardViewMedicinelibrary);
        cardViewMedicinelibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mymedicine();
                Intent i = new Intent(getActivity(),MedicineLibrary.class);
                startActivity(i);
            }
        });

        cardViewHealthStats = (CardView) view.findViewById(R.id.cardViewHealthStats);
        cardViewHealthStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent i = new Intent(getActivity(),Emergencycontacts.class);
                //startActivity(i);
            }
        });
        cardViewMyDiary =(CardView)view.findViewById(R.id.cardViewMyDiary);
        cardViewMyDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),MyDiary.class);
                startActivity(i);

            }
        });

        linearLayoutCallMsgEmContacts =(LinearLayout)view.findViewById(R.id.linearLayoutCallMsgEmContacts);
        linearLayoutCallMsgEmContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emergencycontact();
                Intent i = new Intent(getActivity(),Emergencycontacts.class);
                startActivity(i);

            }
        });


       return view;
    }

    public void flipperImages(int image){
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);
        v_flipper.setInAnimation(getActivity(),android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(getActivity(),android.R.anim.slide_out_right);
    }

     public void pillrmdr(){}
     public void mymdcn(){}
     public void medicinelibrary(){}
     public void mymedicine(){}
     public void emergencycontact(){}


}




