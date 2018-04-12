package com.yashbohara.sample;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.base.Verify;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import static android.media.AudioRecord.STATE_INITIALIZED;

public class Register extends AppCompatActivity {
    EditText password,mobile,engineno,userid,code;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private boolean mVerificationInProgress = false;
    private FirebaseAuth mAuth;
    Button verify,b1;
    int flag=0;
    ProgressBar p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        b1=(Button) findViewById(R.id.btn_reg);
        verify=(Button)findViewById(R.id.verify);
        userid=(EditText) findViewById(R.id.name);
        engineno=(EditText) findViewById(R.id.EngineNumber);
        mobile=(EditText) findViewById(R.id.regmobile);
        code=(EditText) findViewById(R.id.code);
        code.setVisibility(View.INVISIBLE);
        verify.setVisibility(View.INVISIBLE);
        password=(EditText) findViewById(R.id.reg_password);
        mAuth = FirebaseAuth.getInstance();
        p2=(ProgressBar)findViewById(R.id.progressBar_register);
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                Log.e("success", "onVerificationCompleted:" + credential);
                Toast.makeText(getApplicationContext(),"Number Already Registered",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.e("error ", "onVerificationFailed", e);

            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                flag=1;
                Log.e("Code ", "onCodeSent:" + verificationId);

                mVerificationId = verificationId;
                mResendToken = token;

            }
        };

    }
    public void send(View view){
        String phoneNumber=mobile.getText().toString();
        Log.e("num",phoneNumber);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
code.setVisibility(View.VISIBLE);
        verify.setVisibility(View.VISIBLE);
        b1.setVisibility(View.INVISIBLE);
    }
    public void verify(View view){
        String codes = code.getText().toString();
        p2.setVisibility(View.VISIBLE);
        verifyPhoneNumberWithCode(mVerificationId, codes);

    }
    public void fun()
    {
        String a,b,c,d;
        a=userid.getText().toString();
        b=password.getText().toString();
        c= mobile.getText().toString();
        d=engineno.getText().toString();
        Log.e("value  ",a+b+c+d);
        RequestQueue queue= Volley.newRequestQueue(Register.this);
        String url="https://login-api-demo.herokuapp.com/adduser/userid="+a+"&pwd="+b+"&mob="+c+"&engno="+d+"&type=user";
        Log.e("hh  ",url);
        final StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //JSONArray res=new JSONArray(response);
                    JSONObject obj=new JSONObject(response);
                    String result=obj.getString("status");
                    if(result.equals("success"))
                    {
                        p2.setVisibility(View.INVISIBLE);

                        Toast.makeText(getApplicationContext(),"Successfully Registered",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);

                        Register.this.finish();
                    }
                    else if(!result.equals("success"))
                    {
                        Toast.makeText(getApplicationContext(),"User name already available please choose another name",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse (VolleyError error){
                Log.e("error", "volley");
            }
        });
        queue.add(stringRequest);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.e("tasksuccess", "signInWithCredential:success");
                            fun();

                        } else {
                            Log.e("taskfailed", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                            }
                            Register.this.finish();


                        }
                    }
                });
    }
    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        // [START verify_with_code]
        Log.e("hello","hello");
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        // [END verify_with_code]
        signInWithPhoneAuthCredential(credential);
    }

}
