package com.example.kooora;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainPageActivity extends AppCompatActivity {

    private FirebaseUser user;
    private static final String LOG_TAG = MainPageActivity.class.getName();
    private static final int SECRET_KEY = 99;
    private static final String noti = "Hello!!";

    private NotifactionHandler myNotificationHandler;
    private AlarmManager myAlarmManager;


    private void laligatable(){
        Intent intent = new Intent(this, LaligaTable.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }

    private void premierleaguetable(){
        Intent intent = new Intent(this, PremierLeagueTable.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }

    private void serieatable(){
        Intent intent = new Intent(this, SerieATable.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }

    private void ligue1table(){
        Intent intent = new Intent(this, Ligue1Table.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            Log.d(LOG_TAG, "Authenticated user!");
        } else {
            Log.d(LOG_TAG, "Unauthenticated user!");
            finish();
        }

        myNotificationHandler = new NotifactionHandler(this);
        myAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        setAlarmManager();
    }

    public void laligatable(View view) {
        laligatable();
    }

    public void premierleaguetable(View view) { premierleaguetable(); }

    public void serieatable(View view) {
        serieatable();
    }

    public void ligue1table(View view) {
        ligue1table();
    }

    private void setAlarmManager() {
        long repeatInterval = 60*1000; //AlarmManager.INTERVAL_FIFTEEN_MINUTES;
        long triggerTime = SystemClock.elapsedRealtime() + repeatInterval;

        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent, PendingIntent.FLAG_UPDATE_CURRENT);

        myAlarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime,repeatInterval,pendingIntent);

        //myAlarmManager.cancel(pendingIntent);
    }
}