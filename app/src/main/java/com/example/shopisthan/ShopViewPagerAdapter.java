package com.example.shopisthan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ShopViewPagerAdapter extends PagerAdapter
{
    Context ctx;

    public ShopViewPagerAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object)
    {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.silde_screen,container,false);


        ImageView logo = view.findViewById(R.id.shopLogo);
        ImageView ind1 = view.findViewById(R.id.ind1);
        ImageView ind2 = view.findViewById(R.id.ind2);
        ImageView ind3 = view.findViewById(R.id.ind3);


        Animation top = AnimationUtils.loadAnimation(ctx,R.anim.top_animation);





        TextView title = view.findViewById(R.id.introduction_description);
        TextView desc = view.findViewById(R.id.desc);



        Button btn = view.findViewById(R.id.goBtn);

        ind1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SlideActivity.viewPager.setCurrentItem(position-2);
            }
        });
        ind2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SlideActivity.viewPager.setCurrentItem(position+1);
            }
        });
        ind3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SlideActivity.viewPager.setCurrentItem(position+2);
            }
        });
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(ctx,MainActivity.class);
////                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
////                ctx.startActivity(intent);
//
//                AlertDialog.Builder  dialogBuilder= new AlertDialog.Builder(ctx);
//                final View contactPopupView = getLayoutInflater().inflate(R.layout.sign_up,null);
//                AlertDialog dialog;


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(ctx, Shop_register.class);
                ctx.startActivity(intent);
            }
        });
        switch (position)
        {
            case  0:
                logo.setImageResource(R.drawable.shoplogo);
                logo.setAnimation(top);
                ind1.setImageResource(R.drawable.selected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.unselected);

                title.setText("Shopisthan");
                desc.setText("Explore New Store,\nFollow Your Favorite\nStore.");

                btn.setVisibility(View.INVISIBLE);

                break;

            case 1:
                logo.setImageResource(R.drawable.shoplogo);
                logo.setAnimation(top);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.selected);
                ind3.setImageResource(R.drawable.unselected);

                title.setText("Shopisthan");
                desc.setText("Sell Products From\nAnywhere, Home,\nStore Or Multilocation.");
                btn.setVisibility(View.INVISIBLE);

                break;

            case 2:
                logo.setImageResource(R.drawable.shoplogo);
                logo.setAnimation(top);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.selected);

                title.setText("Shopisthan");
                desc.setText("Enjoy Window\nShopping Or Build\nYour Community By\nSelling Best Products");
                btn.setVisibility(View.VISIBLE);
                btn.setAnimation(top);
                break;

        }





        container.addView(view);

        return view;
    }




    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
