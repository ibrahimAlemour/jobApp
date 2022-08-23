package com.example.jobs;

import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.User;
import com.example.jobs.model.UserProfile;
import com.example.jobs.util.MyPreferences;

import java.io.File;
import java.io.FileOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {


    private CircleImageView btnProfilePicture;
    private CircleImageView imgProfile;
    private TextView etName;
    private TextView etPhoneNum;
    private TextView etEmail;
    private TextView tvAboutMe;
    private Button btnLogout;
    private Button btnComplete;
    public String aboutMe;
    private EditText etPath;
    String imagePath;
    Bitmap bitmap;
    ProgressDialog pd;
    private Button btnUpdatePhoto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        initView(v);
        MyPreferences.context = getContext();

        //Dialog
        pd = new ProgressDialog(getContext());
        pd.setMessage("انتظر لحظة...");

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(getContext(), LoginActivity.class);
                MyPreferences.setStr("access_token", null);
                startActivity(b);

            }
        });

        getProfileUser();

        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), CompleteInfoActivity.class);
                intent.putExtra("name", etName.getText().toString());
                intent.putExtra("email", etName.getText().toString());
                intent.putExtra("phone", etPhoneNum.getText().toString());
                intent.putExtra("aboutMe", tvAboutMe.getText().toString());
                startActivity(intent);
            }
        });

        //ImageProfile

        btnProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent, 100);

            }
        });

        btnUpdatePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateProfile(etName.getText().toString(),etPhoneNum.getText().toString());
            }
        });

        return v;
    }

    private void initView(View v) {
        btnProfilePicture = (CircleImageView) v.findViewById(R.id.btnProfile_picture);
        imgProfile = (CircleImageView) v.findViewById(R.id.imgProfile);
        etName = (TextView) v.findViewById(R.id.etName);
        etPhoneNum = (TextView) v.findViewById(R.id.etPhoneNum);
        etEmail = (TextView) v.findViewById(R.id.etEmail);
        tvAboutMe = (TextView) v.findViewById(R.id.tvAboutMe);
        btnLogout = (Button) v.findViewById(R.id.btnLogout);
        btnComplete = (Button) v.findViewById(R.id.btnComplete);
        etPath = (EditText) v.findViewById(R.id.etPath);
        btnUpdatePhoto = (Button) v.findViewById(R.id.btnUpdatePhoto);
    }

    private void updateProfile(String name , String phone) {

        String image = etPath.getText().toString().trim();


        File file = new File(image);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("photo", file.getName(), requestFile);

        if (body == null || image.isEmpty()) {

        } else {

            pd.show();
            RetrofitClient.getRetrofitInstance()
                    .create(ApiInterface.class)
                    .updateProfileEmp(name,phone,"PUT",body)
                    .enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {

                            if (response.isSuccessful()) {
                                Toast.makeText(getContext(), "تم تحديث الصورة ", Toast.LENGTH_SHORT).show();
                                pd.dismiss();
                                btnUpdatePhoto.setVisibility(View.INVISIBLE);
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                            Log.e("UpdatePhotoemp", "onFailure: "+t.getMessage() );
                            pd.dismiss();
                        }
                    });

        }
    }

    private void getProfileUser() {

        pd.show();
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .getProfile()
                .enqueue(new Callback<UserProfile>() {
                    @Override
                    public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                        Log.e("TAG", "onResponse: " + response.body().email);

                        etName.setText(response.body().name);
                        etEmail.setText(response.body().email);
                        etPhoneNum.setText(response.body().phone);
                        tvAboutMe.setText(response.body().about_me);

                        Glide.with(getContext())
                                .load("https://mehan.optiona1.com/public/storage/"+response.body().photo)
                                .fitCenter()
                                .into(imgProfile);

                        pd.dismiss();

                    }

                    @Override
                    public void onFailure(Call<UserProfile> call, Throwable t) {
                        Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

    }


    //EditImageProfile

    void copyImage(Bitmap bitmap) {

        File data = Environment.getDataDirectory();

        // انشاء ملف داخلل داتا التطبيق لصور
        String currentDBPath = "//data//" + getContext().getPackageName() + "//images//" + "";
        File fileDir = new File(data, currentDBPath);

        if (fileDir.exists()) {
            Log.e(getContext().getPackageName(), "Found Dir");
        } else { // اذا مش موجود اصنعة
            fileDir.mkdir();
            Log.e(getContext().getPackageName(), "Make DirDone");

        }


        String fileName = String.format("%d.jpg", System.currentTimeMillis()); // لعدم تكرار الصورة
        imagePath = fileName;//تم توريد الباث لمتغير الصورة
        // edit_path.setText(imagePath);
        Log.e("ibm", "imagePath : " + imagePath);
        File outFile = new File(fileDir, fileName);
        Log.e("ibm", "outFile : " + outFile);
        try {
            // نسخ الصورة في الملف بصيغتها العالية وهي 100
            FileOutputStream outStream = new FileOutputStream(outFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();
            Log.e(getContext().getPackageName(), "Save image Done !");
            Log.e(getContext().getPackageName(), " image path : " + outFile.getAbsolutePath());
            Log.e("ibm", "outFilePath : " + outFile.getAbsolutePath());
            etPath.setText(outFile.getAbsolutePath());


        } catch (Exception ex) { // اعطاء خطاء اذا لم يتم نسخ الصورة في الفايل الخاص بصور الذي تم انشاءه

            Log.e(getContext().getPackageName(), "Save image Error");

            Log.e(getContext().getPackageName(), " " + ex.getMessage());

        }

    }


    // اخذ الصورة من الاستيديو
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // MyPreferences.context = this;
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Uri uri = data.getData();

            Log.e("uri", uri + "");
            try {
                //عرض صورة من الاستيديو
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                imgProfile.setImageBitmap(bitmap);

                Log.e("path", uri.getPath());
                copyImage(bitmap);

            } catch (Exception ex) {

                Log.e(getContext().getPackageName(), "Get image Error");

                Log.e(getContext().getPackageName(), " " + ex.getMessage());
            }


            Toast.makeText(getContext(), "تم عرض الصورة", Toast.LENGTH_SHORT).show();
            btnUpdatePhoto.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(getContext(), "هناك خطأ في جلب الصورة!", Toast.LENGTH_SHORT).show();
        }


    }
}