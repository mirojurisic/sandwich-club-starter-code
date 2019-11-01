package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    public static final String TAG = JsonUtils.class.getSimpleName();
    private static final String KEY_MAIN_NAME = "mainName";
    private static final String KEY_ALSO_KNOW_AS = "alsoKnownAs";
    private static final String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String KEY_IMAGE= "image";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_INGREDIENTS = "ingredients";
    public static Sandwich parseSandwichJson(String json) {

        Log.v(TAG,json);
        String mainName="";
        List<String> alsoKnownAs = new ArrayList<>();
        String placeOfOrigin="";
        String description="";
        String image="";
        List<String> ingredients = new ArrayList<>();
        JSONArray ingredientsArray = null;

        try {
            JSONObject sandwiches = new JSONObject(json);
            JSONObject baseName = sandwiches.getJSONObject("name");
            mainName = baseName.getString(KEY_MAIN_NAME);
            JSONArray alsoKnownAs1 = baseName.getJSONArray(KEY_ALSO_KNOW_AS);
            if(alsoKnownAs1.length()>0)
            {
                for (int i = 0; i<alsoKnownAs1.length();i++)
                {
                    alsoKnownAs.add(alsoKnownAs1.getString(i));
                }
            }
            if(sandwiches.has(KEY_PLACE_OF_ORIGIN))
                placeOfOrigin = sandwiches.getString(KEY_PLACE_OF_ORIGIN);
            if(sandwiches.has(KEY_DESCRIPTION))
                description = sandwiches.getString(KEY_DESCRIPTION);

            if(sandwiches.has(KEY_IMAGE))
                image = sandwiches.getString(KEY_IMAGE);
            if(sandwiches.has(KEY_INGREDIENTS))
                 ingredientsArray = sandwiches.getJSONArray(KEY_INGREDIENTS);
            if(!(ingredientsArray==null) && ingredientsArray.length()>0)
            {
                for (int i = 0; i<ingredientsArray.length();i++)
                {
                    ingredients.add(ingredientsArray.getString(i));
                }
            }
        }
        catch (org.json.JSONException e)
        {
            Log.e(TAG,e.toString());
        }
        return new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,image,ingredients);
    }
}
