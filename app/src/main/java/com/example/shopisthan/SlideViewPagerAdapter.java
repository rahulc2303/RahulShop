package com.example.shopisthan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SlideViewPagerAdapter  extends PagerAdapter {

    Context ctx;

    public SlideViewPagerAdapter(Context ctx)
    {
        this.ctx = ctx;
    }

    @Override
    public int getCount()
    {
        return 3;
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






        TextView title = view.findViewById(R.id.shopisthan);
        TextView desc = view.findViewById(R.id.desc);

        ImageView next = view.findViewById(R.id.next);
        ImageView back = view.findViewById(R.id.back);

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

                Intent intent = new Intent(ctx, SignInActivity.class);
                ctx.startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                SlideActivity.viewPager.setCurrentItem(position+1);
            }
        });

        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                SlideActivity.viewPager.setCurrentItem(position - 1);
            }
        });
        switch (position)
        {
            case  0:
                logo.setImageResource(R.drawable.shoplogo);
                ind1.setImageResource(R.drawable.selected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.unselected);

                title.setText("Shopisthan");
                desc.setText("Shopping on the way");
                back.setVisibility(View.GONE);
                next.setVisibility(View.VISIBLE);

                break;

            case 1:
                logo.setImageResource(R.drawable.shoplogo);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.selected);
                ind3.setImageResource(R.drawable.unselected);

                title.setText("Shopisthan 1231");
                desc.setText("Shopping on the1231 way");
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);

                break;

            case 2:
                logo.setImageResource(R.drawable.shoplogo);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.selected);

                title.setText("Shopisthan 3e 1231");
                desc.setText("Shopping of 14f14fn the1231 way");
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.GONE);
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
