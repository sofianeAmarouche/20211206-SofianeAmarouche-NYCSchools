package com.app.school.management.system.app.schoolapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.school.management.system.app.schoolapp.databinding.ActivityMainBinding;
import com.app.school.management.system.app.schoolapp.model.School;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FirebaseRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this , R.layout.activity_main);

        bindRecyclerView();
		eventHandling();
    }
	
	 private void eventHandling(){
        binding.inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchQuery(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0){
                    binding.displayCancel.setVisibility(View.VISIBLE);
                    binding.imageCancel.setVisibility(View.VISIBLE);
                    String cap = s.toString().substring(0, 1).toUpperCase() + s.toString().substring(1);
                    searchQuery(cap);
                }else{

                    binding.displayCancel.setVisibility(View.INVISIBLE);
                    binding.imageCancel.setVisibility(View.INVISIBLE);
                    searchQuery(s.toString());

                }

            }
        });

        binding.imageCancel.setOnClickListener(v->{
            binding.inputSearch.setText("");
        });

        binding.displayCancel.setOnClickListener(v->{
            binding.inputSearch.setText("");
        });
    }
    void searchQuery(String str){


        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("SchoolManagement")
                .orderByChild("name").startAt(str).endAt(str + "\uf8ff");
        FirebaseRecyclerOptions<School> options =
                new FirebaseRecyclerOptions.Builder<School>()
                        .setQuery(query, School.class)
                        .build();

        adapter = new FirebaseRecyclerAdapter<School, SchoolViewHolder>(options) {
            @Override
            public SchoolViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.school_item_row, parent, false);

                return new SchoolViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(SchoolViewHolder holder, int position, School model) {
                holder.displayName(model.getName());
                holder.displayAddress(model.getAddress());
                holder.displayPhone(model.getPhone());
                holder.displayNavigateAddress(model.getLatitude() , model.getLongitude());
                
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(MainActivity.this , DetailActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        intent.putExtra("name" , model.getName());
                        intent.putExtra("readscore" , model.getReadscore());
                        intent.putExtra("writescore" , model.getWritescore());
                        intent.putExtra("mathscore" , model.getMathscore());
                        intent.putExtra("address" , model.getAddress());
                        intent.putExtra("overview" , model.getOverview());
                        intent.putExtra("phone" , model.getPhone());
                        intent.putExtra("latitude" , model.getLatitude());
                        intent.putExtra("longitude" , model.getLongitude());
                        intent.putExtra("website" , model.getWebsite());

                        startActivity(intent);
                    }
                });
            }
        };

        adapter.startListening();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(adapter);

    }


    void bindRecyclerView(){
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("SchoolManagement")
                .limitToLast(999);
        FirebaseRecyclerOptions<School> options =
                new FirebaseRecyclerOptions.Builder<School>()
                        .setQuery(query, School.class)
                        .build();

        adapter = new FirebaseRecyclerAdapter<School, SchoolViewHolder>(options) {
            @Override
            public SchoolViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.school_item_row, parent, false);

                return new SchoolViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(SchoolViewHolder holder, int position, School model) {
                holder.displayName(model.getName());
                holder.displayAddress(model.getAddress());
                holder.displayPhone(model.getPhone());
                holder.displayNavigateAddress(model.getLatitude() , model.getLongitude());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(MainActivity.this , DetailActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        intent.putExtra("name" , model.getName());
                        intent.putExtra("readscore" , model.getReadscore());
                        intent.putExtra("writescore" , model.getWritescore());
                        intent.putExtra("mathscore" , model.getMathscore());
                        intent.putExtra("address" , model.getAddress());
                        intent.putExtra("overview" , model.getOverview());
                        intent.putExtra("phone" , model.getPhone());
                        intent.putExtra("website" , model.getWebsite());
                        intent.putExtra("latitude" , model.getLatitude());
                        intent.putExtra("longitude" , model.getLongitude());

                        startActivity(intent);
                    }
                });
            }
        };

        adapter.startListening();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(adapter);

    }



    class SchoolViewHolder extends RecyclerView.ViewHolder {
        public SchoolViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void displayName(String name){
            TextView mName = itemView.findViewById(R.id.item_view_name);
            mName.setText(name);
        }
        void displayAddress(String address){
            TextView mAddress = itemView.findViewById(R.id.item_view_address);
            mAddress.setText(address);
        }
        void displayPhone(String phoneNumber){
            TextView mPhone = itemView.findViewById(R.id.item_view_phone);
            mPhone.setText(phoneNumber);
            mPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + phoneNumber));
                    startActivity(intent);
                }
            });
        }
        void displayNavigateAddress(String lat , String lot){
            TextView mDisplayNavigateAddress = itemView.findViewById(R.id.item_view_navigate_address);
            mDisplayNavigateAddress.setOnClickListener(v->{
                Intent intent = new Intent(MainActivity.this , NavigationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                intent.putExtra("latitude" , lat);
                intent.putExtra("longitude" , lot);

                startActivity(intent);
            });
        }
    }




}