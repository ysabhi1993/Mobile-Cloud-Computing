package mcc.tagme;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import static mcc.tagme.NewsFragment.positionid;

/**
 * Created by suryanarayana on 5/4/17.
 */

public class eventlist extends AppCompatActivity implements HeadlineFragment.OnHeadlineSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("hi","event list class");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventlist);
    }

    public void onArticleSelected(int position) {


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
           NewsFragment newsFragment = (NewsFragment) getSupportFragmentManager().findFragmentById(R.id.news);
           newsFragment.updateArticleView(position);
        }
        else {
            Intent myIntent = new Intent(eventlist.this, event2list.class);
            myIntent.putExtra("key", position);
            eventlist.this.startActivity(myIntent);
        }

    }



}

