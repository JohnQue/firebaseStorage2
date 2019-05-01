package com.example.firebasestorage2;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PrintActivity extends AppCompatActivity {
    private DatabaseReference mDatabaseref;
    private String uploadId;
    private String fileLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Fresco.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);


        uploadId = getIntent().getStringExtra("title");
        mDatabaseref = FirebaseDatabase.getInstance().getReference("image").child(uploadId);
        mDatabaseref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ImageUpload imageUpload = dataSnapshot.getValue(ImageUpload.class);
                fileLink = imageUpload.getUrl();
                Uri uri = Uri.parse(fileLink);
                SimpleDraweeView draweeView = findViewById(R.id.my_image_view);
                draweeView.setImageURI(uri);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
