package com.homework.donation.api;

import android.util.Log;

import com.homework.donation.models.Donation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


public class DonationApi {
	//////////////////////////////////////////////////////////////////////////////////	
	public static List<Donation> getAll(String call) {
		try {
			Log.d("DonationApi", "Making request to: " + call);
			String json = Rest.get(call);
			Log.d("DonationApi", "Received response: " + json);
			
			if (json == null || json.isEmpty()) {
				Log.e("DonationApi", "Received empty response from server");
				return null;
			}
			
			Type collectionType = new TypeToken<List<Donation>>(){}.getType();
			List<Donation> donations = new Gson().fromJson(json, collectionType);
			Log.d("DonationApi", "Parsed donations: " + donations.size());
			return donations;
		} catch (Exception e) {
			Log.e("DonationApi", "Error getting donations: " + e.getMessage(), e);
			return null;
		}
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
  
		return Rest.post(call,json);
	}
}
