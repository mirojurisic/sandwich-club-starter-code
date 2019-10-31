package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Log.v("Json_parsing",json);
        String mainName="";
        List<String> alsoKnownAs = new ArrayList<>();
        String placeOfOrigin="";
        String description="";
        String image="";
        List<String> ingredients = new ArrayList<>();
        JSONArray ingredients1 = null;

        try {
            JSONObject sandwiches = new JSONObject(json);
            JSONObject baseName = sandwiches.getJSONObject("name");
            mainName = baseName.getString("mainName");
            Log.v("Json_parsing",mainName);
            JSONArray alsoKnownAs1 = baseName.getJSONArray("alsoKnownAs");
            if(alsoKnownAs1.length()>0)
            {
                for (int i = 0; i<alsoKnownAs1.length();i++)
                {
                    alsoKnownAs.add(alsoKnownAs1.getString(i));
                }
            }
            if(sandwiches.has("placeOfOrigin"))
                placeOfOrigin = sandwiches.getString("placeOfOrigin");
            if(sandwiches.has("description"))
                description = sandwiches.getString("description");
            Log.v("Json_parsing",description);

            if(sandwiches.has("image"))
                image = sandwiches.getString("image");
            Log.v("Json_parsing",image);
            if(sandwiches.has("ingredients"))
                 ingredients1 = sandwiches.getJSONArray("ingredients");
            if(!ingredients.isEmpty() && ingredients1.length()>0)
            {
                for (int i = 0; i<ingredients1.length();i++)
                {
                    ingredients.add(ingredients1.getString(i));
                }
            }
        }
        catch (org.json.JSONException e)
        {
            Log.e("JSON Error",e.toString());
        }
        return new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,image,ingredients);
    }
}
