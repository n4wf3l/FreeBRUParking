package com.example.mapsparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mapsparking.Controller.DatabaseHandler;
import com.example.mapsparking.Model.Place;

public class EditActivity extends AppCompatActivity {


    private EditText title;
    private Button editBtn;
    private DatabaseHandler db;
    private Bundle extras ;

    private String latitude ="" ;
    private String longitude ="" ;
    private int testNum = 0;
    private int placeId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        title = findViewById(R.id.nameEditpage);
        editBtn = findViewById(R.id.buttonEditpage) ;
        db = new DatabaseHandler(this);
        extras = getIntent().getExtras();

        if(extras != null){

            title.setText(extras.getString("title"));
            latitude = extras.getString("latitude");
            longitude = extras.getString("longitude");
            testNum =  extras.getInt("id") ;
        }

      //  editBtn.setOnClickListener(new View.OnClickListener() {
         //   @Override
           // public void onClick(View v) {
             //   if(testNum != 0 && !latitude.isEmpty() && !longitude.isEmpty()){
             //       db.editPlace(new Place(àestNum,latitude   ,longitude ,
             //               title.getText().toString()));

             //       Intent intent = new Intent( EditActivity.this,MapsActivity.class );
              //      startActivity(intent);
              //      finish();
              //  }
           // }
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeId != 0) {
                    String updatedTitle = title.getText().toString();
                    db.editPlace(new Place(placeId,latitude   ,longitude ,
                                          title.getText().toString()));

                    Intent intent = new Intent(EditActivity.this, MapsActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

        });
    }
}