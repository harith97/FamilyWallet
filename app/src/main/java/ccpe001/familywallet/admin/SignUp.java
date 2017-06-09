package ccpe001.familywallet.admin;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import ccpe001.familywallet.ExportData;
import ccpe001.familywallet.R;
import ccpe001.familywallet.Validate;
import com.facebook.*;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private Button signUpBtn,googleUpBtn;
    private EditText emailTxt,passTxt;

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    private ImageView p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                try {
                    displayWelcomeMsg(currentProfile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();

        setContentView(R.layout.signup);
        init();
    }

    private void init() {
        getSupportActionBar().setTitle(R.string.signup_title);

        signUpBtn= (Button)findViewById(R.id.signupBtn);
        emailTxt= (EditText)findViewById(R.id.emailTxt);
        passTxt= (EditText)findViewById(R.id.passwordTxt);
        googleUpBtn= (Button)findViewById(R.id.googleOptBtn);
        signUpBtn.setOnClickListener(this);
        googleUpBtn.setOnClickListener(this);
        p = (ImageView) findViewById(R.id.imageView);

        loginButton = (LoginButton) findViewById(R.id.fbOptBtn);

        loginButton.setReadPermissions("email,public_profile");//and profile
        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                Profile profile = Profile.getCurrentProfile();

                try {
                    displayWelcomeMsg(profile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(),"Login attempt cancel",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getApplicationContext(),"Login attempt error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*private String getEmail(LoginResult loginResult) {
        final String[] ret = new String[1];
        GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                if ((response.getError()) != null){
                    Toast.makeText(getApplicationContext(),"No email",Toast.LENGTH_SHORT).show();
                }else{
                    //ret[0] = object.optString("email");
                    ret[0] = object.optString("picture");
                }
            }
        });
        return ret[0];
    }

    private static Bitmap getFbProfilePic(String usrId) throws IOException {
        URL imageUrl = new URL("https://graph.facebook.com/"+usrId+"/picture?type=large");

        return BitmapFactory.decodeStream(ImageUdfijps);
    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onStop() {
        super.onStop();
        profileTracker.stopTracking();
        accessTokenTracker.stopTracking();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.signupBtn){
            if(Validate.anyValidMail(emailTxt.getText().toString())) {
                if(Validate.anyValidPass(passTxt.getText().toString())){
                    Intent intent = new Intent("ccpe001.familywallet.GETINFO");
                    startActivity(intent);
                }else{
                    passTxt.setError("Invalid password");
                }
            }else {
                emailTxt.setError("Invalid email");
            }
        }else if(view.getId()== R.id.googleOptBtn) {
            Intent intent = new Intent("ccpe001.familywallet.DASHBOARD");
            startActivity(intent);
        }
    }




    private void displayWelcomeMsg(Profile profile) throws IOException {
        if(profile != null) {
            Intent intent = new Intent("ccpe001.familywallet.DASHBOARD");
            intent.putExtra("firstname", profile.getFirstName());
            intent.putExtra("lastname", profile.getLastName());
            intent.putExtra("profilepic", profile.getProfilePictureUri(400,400));
            //intent.putExtra("email",);
            startActivity(intent);
        }
    }


}
