package com.example.myapplication.main;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.models.Donation;

import java.util.ArrayList;
import java.util.List;

public class DonationApp extends Application {
    public final int target = 10000;
    public static int totalDonated = 0;
    public List<Donation> donations = new ArrayList<>();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("Donate", "Donation App Started");
    }

    public boolean newDonation(Donation donation) {
        boolean targetAchieved = totalDonated > target;
        if (!targetAchieved) {
            donations.add(donation);
            totalDonated += donation.amount;
        } else {
            Toast toast = Toast.makeText(this, "Target Exceeded!", Toast.LENGTH_SHORT);
            toast.show();
        }
        return targetAchieved;
    }
}
