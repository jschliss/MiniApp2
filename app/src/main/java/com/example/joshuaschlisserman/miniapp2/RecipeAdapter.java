package com.example.joshuaschlisserman.miniapp2;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Recipe> mRecipeList;
    Context mContext;
    LayoutInflater mLayoutInflater;

    RecipeAdapter(Context context, List<Recipe> list){
        mRecipeList = list;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    //RecyclerView ViewHolder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.activity_result, parent, false);
        return new RecipeViewHolder(itemView);
    }

    //OnBindViewHolder
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Recipe currentRecipe = mRecipeList.get(position);
        RecipeViewHolder viewHolder = (RecipeViewHolder)holder;
        viewHolder.recipeNameTextview.setText(currentRecipe.getTitle());
        viewHolder.servingsTextView.setText(Integer.toString(currentRecipe.getServings()));
        viewHolder.prepTimeTextView.setText(currentRecipe.getPrepTime());
        Picasso.with(mContext).load(currentRecipe.getImage()).into(viewHolder.recipeImageView);
        viewHolder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(currentRecipe.getUrl()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext, "Channel_ID")
                        .setSmallIcon(R.drawable.ic_create_black_24dp)
                        .setContentTitle(currentRecipe.getTitle())
                        .setContentIntent(pendingIntent)
                        .setContentText("The instructions for " + currentRecipe.getTitle() + " can be found here!")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("The instructions for " + currentRecipe.getTitle() + " can be found here!"))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mContext);
                notificationManager.notify(2, mBuilder.build());
            }

        });
    }

    //Items from Recipe List
    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }

    //Recipe ViewHolder
    static class RecipeViewHolder extends RecyclerView.ViewHolder {

        private TextView recipeNameTextview;
        private TextView servingsTextView;
        private TextView prepTimeTextView;
        private ImageView recipeImageView;
        private ImageButton imageButton;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            recipeNameTextview = (TextView) itemView.findViewById(R.id.recipe_list_title);
            servingsTextView = (TextView) itemView.findViewById(R.id.recipe_list_serving);
            prepTimeTextView = (TextView) itemView.findViewById(R.id.recipe_list_preptime);
            recipeImageView = (ImageView) itemView.findViewById(R.id.recipe_list_thumbnail);
            imageButton = (ImageButton) itemView.findViewById(R.id.want_to_cook_button);
        }
    }
}
