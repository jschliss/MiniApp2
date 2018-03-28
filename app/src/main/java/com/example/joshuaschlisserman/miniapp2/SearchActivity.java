package com.example.joshuaschlisserman.miniapp2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

/**
 * Created by JoshuaSchlisserman on 3/9/18.
 */

public class SearchActivity extends AppCompatActivity {

    Context mContext;
    Spinner mDietSpinner;
    Spinner mTimeSpinner;
    Spinner mServingsSpinner;
    Button mSubmitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //start new activty
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mContext = this;
        mDietSpinner = findViewById(R.id.diet_spinner);
        mTimeSpinner = findViewById(R.id.time_cook_spinner);
        mServingsSpinner = findViewById(R.id.servings_spinner);
        mSubmitButton = findViewById(R.id.search_button);

        //to go to next activity
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextActivity = new Intent(mContext, ResultActivity.class);
                nextActivity.putExtra("DietSpinner", mDietSpinner.getSelectedItem().toString());
                nextActivity.putExtra("ServingsSpinner",mServingsSpinner.getSelectedItem().toString());
                nextActivity.putExtra("TimeSpinner",mTimeSpinner.getSelectedItem().toString());
                startActivity(nextActivity);

            }


        });

        //method written below
        List<String> dietCategories = getDietCategories();
        //hard code
        String[]servingCategories = new String[]{"All", "Less than 4", "4-6", "7-9", "More than 10"};
        String[]timeCategories = new String[]{"All", "30 mins or less", "Less than 1 hr", "More than 1 hr"};

        ///For Each Spinner, an ArrayAdapter
        ArrayAdapter<String> dietAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, dietCategories);
        ArrayAdapter<String> servingsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, servingCategories);
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, timeCategories);
        mDietSpinner.setAdapter(dietAdapter);
        mServingsSpinner.setAdapter(servingsAdapter);
        mTimeSpinner.setAdapter(timeAdapter);



    }

    //DietCategories Method to search for Diet Labels
    //Could not hard code
    //Retrieved from JSON
    private List<String> getDietCategories() {
        List<Recipe>recipeList = Recipe.getRecipesFromFile("recipes.json", this);
        List<String>dietCategories = new ArrayList<String>();
        dietCategories.add("All");
        for(int i = 0; i < recipeList.size(); i++){
            if(dietCategories.contains(recipeList.get(i).dietLabel)){

            }
            else{
                dietCategories.add(recipeList.get(i).dietLabel);
            }
        }
        return dietCategories;
    }

}