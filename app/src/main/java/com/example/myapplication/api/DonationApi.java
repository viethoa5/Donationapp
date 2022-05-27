package com.example.myapplication.api;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.example.myapplication.models.Donation;


public class DonationApi {
	//////////////////////////////////////////////////////////////////////////////////	
	@RequiresApi(api = Build.VERSION_CODES.N)
	public static List<Donation> getAll(String call) {
		try {
			String json = Rest.get(call);
			Type collectionType = new TypeToken<Map<String, Donation>>(){}.getType();
			Map map = new Gson().fromJson(json, collectionType);

			if (map != null) {
				map.forEach((k,v)-> ((Donation) v)._id = (String) k);
				List<Donation> result = new ArrayList<>(map.values());
				Log.v("donate3", "GETALL RESULT : " + result);
				return result;
			}
		} catch (Exception e) {
			Log.v("donate", "ERROR: " + e.getMessage());
		}
		return new ArrayList<>();
	}
	//////////////////////////////////////////////////////////////////////////////////
	public static Donation get(String call,String id) {
		String json = Rest.get(call + "/" + id);
		Log.v("donate", "JSON RESULT : " + json);
		Type objType = new TypeToken<Donation>(){}.getType();

		return new Gson().fromJson(json, objType);
	}
	//////////////////////////////////////////////////////////////////////////////////
	public static String deleteAll(String call) {
		return Rest.delete(call);
	}
	//////////////////////////////////////////////////////////////////////////////////
	public static String delete(String call, String id) {
		return Rest.delete(call + "/" + id);
	}
	//////////////////////////////////////////////////////////////////////////////////
	public static String insert(String call,Donation donation) {		
		Type objType = new TypeToken<Donation>(){}.getType();
		String json = new Gson().toJson(donation, objType);
		Log.v("donate2", json);
  
		return Rest.post(call,json);
	}
}
