package com.ashkansajadi.clubinvite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    InviteApi inviteApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inviteApi = InviteApi.retrofit.create(InviteApi.class);
        setContentView(R.layout.activity_main);

        Button inviteButton = findViewById(R.id.invite_button);
        EditText phoneEditText = findViewById(R.id.phone_edit_text);
        ProgressBar inviteWait = findViewById(R.id.invite_wait);
        Button cafebazaarButton = findViewById(R.id.cafebazaar_button);
        Button telegramButton = findViewById(R.id.telegram_button);

        phoneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                inviteButton.setEnabled(phoneEditText.getText().toString().length()==11);
            }
        });

        inviteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(phoneEditText.getText().toString().equals("")){
                    showAlert("لطفا یک شماره وارد کنید");
                }else{

                    inviteButton.setEnabled(false);
                    inviteWait.setVisibility(View.VISIBLE);

                    Call<InviteResponse> sendInvitation = inviteApi.getInvitation(phoneEditText.getText().toString());
                    sendInvitation.enqueue(new Callback<InviteResponse>() {
                        @Override
                        public void onResponse(Call<InviteResponse> call, Response<InviteResponse> response) {
                            phoneEditText.setText("");
                            String serverResponse = response.body().getMsg();
                            showAlert(serverResponse);
                            inviteButton.setEnabled(true);
                            inviteWait.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onFailure(Call<InviteResponse> call, Throwable t) {
                            showAlert("اتصال به سرور برقرار نشد");
                            inviteButton.setEnabled(true);
                            inviteWait.setVisibility(View.INVISIBLE);
                        }
                    });
                }

            }
        });

        cafebazaarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cafebazaarIntent = new Intent(Intent.ACTION_VIEW);
                cafebazaarIntent.setData(Uri.parse("bazaar://details?id=com.clubhouse.app"));
                cafebazaarIntent.setPackage("com.farsitel.bazaar");
                startActivity(cafebazaarIntent);
            }
        });

        telegramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telegramIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/tarasalahichannel"));
                startActivity(telegramIntent);
            }
        });
    }

    private void showAlert(String alertText){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("Clubinvite")
                .setMessage(alertText)
                .setPositiveButton("تایید", null)
                .show();
    }
}