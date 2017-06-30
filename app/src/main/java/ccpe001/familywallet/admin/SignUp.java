package ccpe001.familywallet.admin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;
import ccpe001.familywallet.R;
import ccpe001.familywallet.Validate;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class SignUp extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener{

    private Button signUpBtn;
    private EditText emailTxt,passTxt;
    private SignInButton googleBtn;
    private LoginButton fbBtn;
    private ProgressDialog progressBar;

    private final static int RC_SIGN_IN = 0;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();

        if(mAuth.getCurrentUser() != null){
            finish();
            Intent intent = new Intent("ccpe001.familywallet.DASHBOARD");
            startActivity(intent);
        }

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null)
                    Log.d("Google", "user logged in: " + user.getEmail());
                else
                    Log.d("Google", "user logged out.");
            }
        };

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        getSupportActionBar().setTitle(R.string.signup_title);
        signUpBtn= (Button)findViewById(R.id.signupBtn);
        emailTxt= (EditText)findViewById(R.id.emailTxt);
        progressBar = new ProgressDialog(this);
        //fbBtn = (LoginButton) findViewById(R.id.fbOptBtn);
        passTxt= (EditText)findViewById(R.id.passwordTxt);
        googleBtn= (SignInButton)findViewById(R.id.googleOptBtn);
        signUpBtn.setOnClickListener(this);
        googleBtn.setOnClickListener(this);
        //fbBtn.setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null)
            mAuth.removeAuthStateListener(mAuthListener);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.signupBtn){

            if(Validate.anyValidMail(emailTxt.getText().toString().trim())) {
                if(Validate.anyValidPass(passTxt.getText().toString().trim())){
                    progressBar.setMessage("Registering user");
                    progressBar.show();
                    mAuth.createUserWithEmailAndPassword(emailTxt.getText().toString().trim(),
                                                        passTxt.getText().toString().trim())
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.dismiss();
                                    if(task.isSuccessful()){
                                        saveSession(mAuth.getCurrentUser().getEmail());
                                        Toast.makeText(SignUp.this,"Registration successful",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent("ccpe001.familywallet.GETINFO");
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(SignUp.this,"Registration Error",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    passTxt.setError("Invalid password");
                }
            }else {
                emailTxt.setError("Invalid email");
            }

        }else if(view.getId()== R.id.googleOptBtn){
            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
            startActivityForResult(signInIntent, RC_SIGN_IN);
        }
    }

    private void saveSession(String email) {
        SharedPreferences prefs = getSharedPreferences("Session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("userMail",email);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this,"Connection failed.",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if(result.isSuccess()){
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);//once it auth with google it does others
            }
            else
                Toast.makeText(this, "Google Login Failed",Toast.LENGTH_SHORT).show();

        }
    }


    private void firebaseAuthWithGoogle(final GoogleSignInAccount acct){
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Google", "signInWithCredential:oncomplete: " + task.isSuccessful());
                        Intent intent = new Intent("ccpe001.familywallet.DASHBOARD");
                        intent.putExtra("firstname",acct.getFamilyName());
                        intent.putExtra("lastname",acct.getDisplayName());
                        saveData(acct.getFamilyName(),acct.getDisplayName());
                        try {
                            intent.putExtra("profilepic", acct.getPhotoUrl().toString());
                            uploadImg(acct.getPhotoUrl());
                        }catch (Exception e){

                        }
                        startActivity(intent);
                    }
                });
    }

    private void saveData(String fname,String lname) {
        UserData userData = new UserData(fname,lname, mAuth.getCurrentUser().getUid());
        databaseReference.child("UserInfo").child(mAuth.getCurrentUser().getUid()).setValue(userData);
    }

    private void uploadImg(Uri sendProPicURI){

        if(sendProPicURI != null) {

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Setting up..");
            progressDialog.show();

            StorageReference reference = storageReference.child("UserPics/"+mAuth.getCurrentUser().getUid()+".jpg");
            reference.putFile(sendProPicURI)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                    progressDialog.setMessage((int)progress+"% Uploaded..");
                }
            });

        }

    }
}
