package ccpe001.familywallet.budget;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ccpe001.familywallet.R;

public class budgetTrack extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budget_track);
    }
    public void  updateBudget(View view){
        Intent newInt1 = new Intent(this,budgetUpdate.class);
        startActivity(newInt1);
    }

}
