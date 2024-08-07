package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String itemName = getIntent().getStringExtra("ITEM_NAME");
        String itemArtist = getIntent().getStringExtra("ITEM_ARTIST");
        String itemDescription = getIntent().getStringExtra("ITEM_DESCRIPTION");
        int itemImage = getIntent().getIntExtra("ITEM_IMAGE", -1);
        String itemGenre = getIntent().getStringExtra("ITEM_GENRE");
        double itemPrice = getIntent().getDoubleExtra("ITEM_PRICE", 0.0);
        double itemRating = getIntent().getDoubleExtra("ITEM_RATING", 0.0);
        LinearLayout layout = findViewById(R.id.detailLayout);

        ImageView detailImage = findViewById(R.id.detailImage);
        TextView detailName = findViewById(R.id.detailName);
        TextView detailArtist = findViewById(R.id.detailArtist);
        TextView detailDescription = findViewById(R.id.detailDescription);
        TextView detailGenre = findViewById(R.id.detailGenre);
        TextView detailPrice = findViewById(R.id.detailPrice);
        TextView detailRating = findViewById(R.id.detailRating);
        EditText quantity = findViewById(R.id.quantity);
        Button addToCartBtn = findViewById(R.id.addToCartBtn);
        TextView successMessage = findViewById(R.id.successMessage);
        ImageView backChevron = findViewById(R.id.backChevron);

        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        String formattedPrice = format.format(itemPrice);

        detailImage.setImageResource(itemImage);
        detailName.setText(itemName);
        detailArtist.setText(itemArtist);
        detailDescription.setText(itemDescription);
        detailGenre.setText(itemGenre);
        detailPrice.setText(formattedPrice);
        detailRating.setText(String.valueOf(itemRating));

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), itemImage);
        Drawable drawable = new BitmapDrawable(getResources(), bitmap);
        drawable.setAlpha(70);
        layout.setBackground(drawable);

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantityText = quantity.getText().toString();

                if(quantityText.isEmpty()){
                    showAlertDialog("Error", "Quantity must be filled.");
                }else{
                    int quantityValue = Integer.parseInt(quantityText);

                    if(quantityValue < 1){
                        showAlertDialog("Error", "Quantity minimum 1.");
                    }else{
                        successMessage.setVisibility(View.VISIBLE);
                        successMessage.setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        ));
                    }

                }
            }
        });

        backChevron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void showAlertDialog(String title, String message){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Try Again", null)
                .show();
    }
}