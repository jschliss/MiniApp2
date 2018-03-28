package com.example.joshuaschlisserman.miniapp2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by JoshuaSchlisserman on 3/16/18.
 */

public class ResultActivity extends AppCompatActivity {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private List<Recipe> mRecipeList;
    private List<Recipe> mSortedList;
    private RecipeAdapter mRecipeAdapter;

    protected void onCreate(Bundle savedInstanceState) {

        //open app to activity_main
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_recipe);
        mContext = this;

        Intent mIntent = getIntent();
        String dietSpinner = mIntent.getStringExtra("DietSpinner");
        String servingsSpinner = mIntent.getStringExtra("ServingsSpinner");
        String timeSpinner = mIntent.getStringExtra("TimeSpinner");
        mSortedList = new ArrayList<Recipe>();

        mRecipeList = Recipe.getRecipesFromFile("recipes.json", mContext);
        switch (servingsSpinner) {
            case "Less than 4":
                mSortedList = SortedByServing(0,4);
                break;
            case "4-6":
                mSortedList = SortedByServing(3,7);
                break;

            case "7-9":
                mSortedList = SortedByServing(6,10);
                break;

            case "More than 10":
                mSortedList = SortedByServing(10,Integer.MAX_VALUE);
                break;

            default:
                mSortedList = mRecipeList;
        }

        switch (timeSpinner){
            case "30 mins or less":
                filterByTime(mSortedList, 0,31);
                break;
            case "Less than 1 hr":
                filterByTime(mSortedList, 0,61);
                break;
            case "More than 1 hr":
                filterByTime(mSortedList, 60,Integer.MAX_VALUE);
                break;
        }

        if (!dietSpinner.equalsIgnoreCase("all")) {
            filterByDiet(mSortedList, dietSpinner);
        }

        mRecipeAdapter = new RecipeAdapter(mContext, mSortedList);
        mRecyclerView = (RecyclerView)findViewById(R.id.recipe_recycler_view);
        mRecyclerView.setAdapter(mRecipeAdapter);
        //Dividers in Recycler View
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    private List<Recipe> SortedByServing(int min, int max){
        List<Recipe> recipes = new ArrayList<Recipe>();
        for (Recipe r: mRecipeList){
            if (r.getServings() < max && r.getServings() > min){
                recipes.add(r);
            }
        }
        return recipes;
    }

    private List<Recipe> filterByTime(List<Recipe> list, int min, int max){
        List<Recipe> recipes = list;
        Iterator<Recipe> iter = recipes.iterator();
        while (iter.hasNext()) {
            Recipe r = iter.next();
            int prepTime = convertToInt(r.getPrepTime());
            if (prepTime > max || prepTime < min) {
                iter.remove();
            }
        }
        return recipes;

    }
    private int convertToInt(String prepString){
        int prepTimeinMins;
        switch (prepString){
            case "25 minutes":
                prepTimeinMins = 25;
                break;
            case "1 hour":
                prepTimeinMins = 60;
                break;
            case "20 minutes":
                prepTimeinMins = 20;
                break;
            case "35 minutes":
                prepTimeinMins = 35;
                break;
            case "6 hours":
                prepTimeinMins = 360;
                break;
            case "1 hour and 20 minutes":
                prepTimeinMins = 80;
                break;
            case "40 minutes":
                prepTimeinMins = 40;
                break;
            case "50 minutes":
                prepTimeinMins = 50;
                break;
            case "3 hours and 15 minutes":
                prepTimeinMins = 195;
                break;
            case "15 minutes":
                prepTimeinMins = 15;
                break;
            default:
                prepTimeinMins = 0;
        }
        return prepTimeinMins;
    }
    private List<Recipe> filterByDiet(List<Recipe> list, String dietChoice){
        List<Recipe> recipes = list;
        Iterator<Recipe> iter = recipes.iterator();
        while (iter.hasNext()) {
            Recipe r = iter.next();
            String diet = r.getDietLabel();
            if (!diet.equalsIgnoreCase(dietChoice)) {
                iter.remove();
            }
        }
        return recipes;
    }

}
