package com.sbiitju.covid_19;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.UUID;

public class Admin extends AppCompatActivity {
    Button buttonchose, upload;
    EditText filename;
    ImageView imageView;
    ProgressBar progressBar;
    TextView textView;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;
    int image[]={R.drawable.iftar,R.drawable.koyen,R.drawable.student};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
//        buttonchose = findViewById(R.id.button_choose_image);
//        upload = findViewById(R.id.button_upload);
////        filename = findViewById(R.id.edit_text_file_name);
//        imageView = findViewById(R.id.image_view);
//        imageView.setImageResource(R.drawable.bangladesh);
//        progressBar = findViewById(R.id.progress_bar);
//        textView = findViewById(R.id.text_view_show_uploads);
//        mStorageRef = FirebaseStorage.getInstance().getReference("shahin");
//        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");
//        buttonchose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openFileChooser();
//            }
//        });
//        upload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                savedata();
//                Toast.makeText(Admin.this, "Check", Toast.LENGTH_SHORT).show();
//            }
//        });
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//    }
//
//    private void openFileChooser() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent, PICK_IMAGE_REQUEST);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
//                && data != null && data.getData() != null) {
//            mImageUri = data.getData();
//            Picasso.get().load(mImageUri).into(imageView);
//        }
//    }
//
//
//    private void savedata() {
//        EditText imagetext;
//        imagetext=findViewById(R.id.edit_text_file_name);
//        final String imagename=imagetext.getText().toString().trim();
//        StorageReference reference=mStorageRef.child(System.currentTimeMillis()+"."+getFileExtension(mImageUri));
//        reference.putFile(mImageUri)
//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        Task<Uri> task=taskSnapshot.getStorage().getDownloadUrl();
//                        while (!task.isSuccessful());
//                        Uri saveuri=task.getResult();
//                        // Get a URL to the uploaded content
//                        Upload uploadInfo=new Upload(imagename,saveuri.toString());
//                        String uploadid=mDatabaseRef.push().getKey();
//                        mDatabaseRef.child(uploadid).setValue(uploadInfo);
//                        Toast.makeText(Admin.this, "SUccess", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception exception) {
//                        // Handle unsuccessful uploads
//                        // ...
//                        Toast.makeText(Admin.this, "Failed", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//
//    }
////    private void uploadFile() {
////        if (mImageUri != null) {
////            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
////                    + "." + getFileExtension(mImageUri));
////
////            mStorageRef.putFile(mImageUri)
////                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
////                        @Override
////                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
////                            Handler handler = new Handler();
////                            handler.postDelayed(new Runnable() {
////                                @Override
////                                public void run() {
////                                    progressBar.setProgress(0);
////                                }
////                            }, 500);
////
////                            Toast.makeText(Admin.this, "Upload successful", Toast.LENGTH_LONG).show();
////                            Upload upload = new Upload(filename.getText().toString().trim(),
////                                    taskSnapshot.getMetadata().getReference().getDownloadUrl().toString().toString());
////                            String uploadId = mDatabaseRef.push().getKey();
////                            mDatabaseRef.child(uploadId).setValue(upload);
////                        }
////                    })
////                    .addOnFailureListener(new OnFailureListener() {
////                        @Override
////                        public void onFailure(@NonNull Exception e) {
////                            Toast.makeText(Admin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
////                        }
////                    })
////                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
////                        @Override
////                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
////                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
////                            progressBar.setProgress((int) progress);
////                        }
////                    });
////        } else {
////            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
////        }
////    }
//private String getFileExtension(Uri uri) {
//    ContentResolver cR = getContentResolver();
//    MimeTypeMap mime = MimeTypeMap.getSingleton();
//    return mime.getExtensionFromMimeType(cR.getType(uri));
//}

    }
}