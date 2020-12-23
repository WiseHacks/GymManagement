package com.example.testingproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.number.NumberRangeFormatter;
import android.net.Uri;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testingproject.DataHandler.DataHandlerCustomer;
import com.example.testingproject.models.Customer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.concurrent.TimeUnit;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Customer> customers;

    /*ArrayList<Integer> globalId = generate();
    public ArrayList<Integer> generate(){
        ArrayList<Integer> id = new ArrayList<>();
        for (int i = 0; i < 10001 ; i++) {
            id.add(i);
        }
        return id;
    }*/
    DataHandlerCustomer db;
    private Activity activity;
    public CustomAdapter(Activity activity,Context context, List<Customer> customers,DataHandlerCustomer db) {
        this.activity  = activity;
        this.context = context;
        this.customers = (ArrayList<Customer>) customers;
        this.db = db;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.members_list_view,parent,false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtName.setText(String.valueOf(customers.get(position).getName()));
        holder.txtAge.setText(String.valueOf(customers.get(position).getAge()));
        holder.txtGender.setText(String.valueOf(customers.get(position).getGender()));
        holder.txtEmail.setText(String.valueOf(customers.get(position).getEmail()));
        holder.txtPhone.setText(String.valueOf(customers.get(position).getPhone()));
        holder.txtSubs.setText(String.valueOf(customers.get(position).getSubs()));
        holder.txtChosen.setText(String.valueOf(customers.get(position).getChosen()));
//        holder.MemberId.setText(String.valueOf(customers.get(position).getId()));
        String Mcd = holder.txtSubs.getText().toString();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String CD = sdf.format(new Date());
        try {
            Date date1 = sdf.parse(Mcd);
            Date date2 = sdf.parse(CD);
            long diff = date2.getTime() - date1.getTime();
            long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
//            Log.d("CurDate","Date :" + days);
            if(days>30){
                holder.txtEx.setVisibility(View.VISIBLE);
                holder.txtAc.setVisibility(View.INVISIBLE);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.imgDeleteMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want to delete " + customers.get(position).getName() + "?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            Log.d("er", "onClick: " + db.getCount());
                            db.removeCustomer(customers.get(position).getId());
                            customers.remove(position);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Removed Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context,MemberDetailsActivity.class);
                            activity.startActivity(intent);
                        }
                        catch (Exception e){
                            Log.d("Er","Exception hua");
                        }
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();

            }
        });
        holder.imgUpdateMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MemberUpdateActivity.class);
                intent.putExtra("id",String.valueOf(customers.get(position).getId()));
                intent.putExtra("age",String.valueOf(customers.get(position).getAge()));
                intent.putExtra("email",String.valueOf(customers.get(position).getEmail()));
                intent.putExtra("phone",String.valueOf(customers.get(position).getPhone()));
                intent.putExtra("subs",String.valueOf(customers.get(position).getSubs()));
                intent.putExtra("name",String.valueOf(customers.get(position).getName()));
                intent.putExtra("gender",String.valueOf(customers.get(position).getGender()));
                intent.putExtra("chosenplan",String.valueOf(customers.get(position).getChosen()));

//                context.startActivity(intent);
                activity.startActivityForResult(intent,1);
            }
        });

        holder.imgmailmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to send an email to "+holder.txtName.getText().toString()+"?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","",null));
                        emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{holder.txtEmail.getText().toString()});
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT,"");
                        emailIntent.putExtra(Intent.EXTRA_TEXT,"");
                        context.startActivity(Intent.createChooser(emailIntent,"Send Email.."));
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });builder.create().show();
            }
        });
        holder.imgcallmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to call " + holder.txtName.getText().toString() + "?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:+91"+holder.txtPhone.getText().toString()));
                        context.startActivity(callIntent);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });builder.create().show();
            }
        });

        holder.imgexpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(holder.cardView);
                holder.expandedDetails.setVisibility(View.VISIBLE);
                holder.imgexpand.setVisibility(View.GONE);
                holder.imgcompress.setVisibility(View.VISIBLE);
                holder.imgs.setVisibility(View.GONE);
            }
        });

        holder.imgcompress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(holder.cardView);
                holder.expandedDetails.setVisibility(View.GONE);
                holder.imgexpand.setVisibility(View.VISIBLE);
                holder.imgcompress.setVisibility(View.GONE);
                holder.imgs.setVisibility(View.VISIBLE);

            }
        });
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName,txtAge,txtGender,txtEmail,txtPhone,txtSubs,txtMessage,txtChosen,txtAc,txtEx;
        private ImageView imgDeleteMember,imgUpdateMember,imgexpand,imgcompress,imgcallmember,imgmailmember;
        private RelativeLayout expandedDetails,imgs;
        private CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtAge = itemView.findViewById(R.id.txtAge);
            txtGender = itemView.findViewById(R.id.txtGender);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            txtPhone = itemView.findViewById(R.id.txtPhone);
            txtSubs = itemView.findViewById(R.id.txtSubs);
            txtMessage = itemView.findViewById(R.id.txtMessage);
            txtChosen = itemView.findViewById(R.id.txtChosen);
            txtAc = itemView.findViewById(R.id.txtAc);
            txtEx = itemView.findViewById(R.id.txtEx);
            imgDeleteMember = itemView.findViewById(R.id.imgDeleteMember);
            imgUpdateMember = itemView.findViewById(R.id.imgUpdateMember);
            imgexpand = itemView.findViewById(R.id.imgexpand);
            imgcompress = itemView.findViewById(R.id.imgcompress);
            imgcallmember = itemView.findViewById(R.id.imgcallmember);
            imgmailmember = itemView.findViewById(R.id.imgmailmember);
            expandedDetails = itemView.findViewById(R.id.expandedDetails);
            cardView = itemView.findViewById(R.id.cardView);
            imgs = itemView.findViewById(R.id.imgs);
        }
    }
}
