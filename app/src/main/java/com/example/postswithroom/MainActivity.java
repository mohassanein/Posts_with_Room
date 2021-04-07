package com.example.postswithroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
RecyclerView recyclerView;
RecyclerView.LayoutManager layoutManager;
PostsAdapter postsAdapter;
Button insert_btn,get_btn;
TextView post_title,post_body;
EditText date_post;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date_post = findViewById(R.id.post_date);
        PostDatabase postDatabase = PostDatabase.getInstance(this);
        initview();
        date_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DialogPickerFragment();
                dialogFragment.show(getSupportFragmentManager(),"date picker");

            }
        });


        insert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("dddd", "onClick: ");
                postDatabase.postsDao().insertPost(new PostModel(date_post.getText().toString(),post_title.getEditableText().toString(),post_body.getEditableText().toString()))
                        .subscribeOn(Schedulers.computation())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onComplete() {

                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }
                        });
            }
        });
        //insert to

//get from
        get_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("dddd", "onClick:get ");
                postDatabase.postsDao().getPost().subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<List<PostModel>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull List<PostModel> postModels) {
                                postsAdapter.setPostModels((ArrayList<PostModel>) postModels);

                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });




    }

    public void initview(){
        insert_btn = findViewById(R.id.post_insert_btn);
        get_btn = findViewById(R.id.getpost_btn);
        recyclerView = findViewById(R.id.post_recycler);
        layoutManager = new LinearLayoutManager(this);
        postsAdapter = new PostsAdapter();
        recyclerView.setAdapter(postsAdapter);
        recyclerView.setLayoutManager(layoutManager);
        post_title = findViewById(R.id.post_title);
        post_body = findViewById(R.id.post_body);


    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
       date_post = (EditText) findViewById(R.id.post_date);
        date_post.setText(currentDateString);

    }



}