package mcc.tagme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by suryanarayana on 5/2/17.
 */

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i("hi","menu class");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);





    }

    public void sharelocation(View view){


        Log.i("hi","share button pressed ");

        Intent i = new Intent(this, latlang.class);
        startActivity(i);
    }

    public void eventlistpage(View view){

        Log.i("hi","starting event list activity ");

        Intent i = new Intent(this, eventlist.class);
        startActivity(i);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
