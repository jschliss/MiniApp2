package com.example.joshuaschlisserman.miniapp2;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by JoshuaSchlisserman on 3/7/18.
 */

public class Recipe {

    //instance variables or fields
    //constructor
    //method

    public String title;
    public String image;
    public String url;
    public String description;
    public int servings;
    public String prepTime;
    public String dietLabel;
    //constructor
    //default

    //method
    //static methods that the read the json file in and load into Recipe

    //static method that loads our recipes.json using the helper method
    //this method will return an array list of movies constructed from the JSON file
    public static ArrayList<Recipe> getRecipesFromFile(String filename, Context context){
        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();

        //try to read from JSON file
        //get information by using the tags
        //construct a Movie Object for each movie in JSON
        //Add the object to arrayList
        //return arrayList
        try{
            String jsonString = loadJsonFromAsset("recipes.json", context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray recipes = json.getJSONArray("recipes");

            //for loop to go through each movie in your movies array

            for(int i = 0; i < recipes.length(); i++){
                Recipe recipe = new Recipe();
                recipe.title = recipes.getJSONObject(i).getString("title");
                recipe.image = recipes.getJSONObject(i).getString("image");
                recipe.url = recipes.getJSONObject(i).getString("url");
                recipe.description = recipes.getJSONObject(i).getString("description");
                recipe.servings = recipes.getJSONObject(i).getInt("servings");
                recipe.prepTime = recipes.getJSONObject(i).getString("prepTime");
                recipe.dietLabel = recipes.getJSONObject(i).getString("dietLabel");
                //add to arrayList
                recipeList.add(recipe);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipeList;
    }

    //helper method that loads from any Json file
    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }


    //methods for all my constructors
    //get
    //set
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(String prepTime) {
        this.prepTime = prepTime;
    }

    public String getDietLabel() {
        return dietLabel;
    }

    public void setDiet(String dietLabel) {
        this.dietLabel = dietLabel;
    }

}



