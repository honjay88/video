package com.example.honjaychen.dagger.gallery.gallery;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.honjaychen.dagger.R;
import com.example.honjaychen.dagger.media.PlayerActivity;
import com.mindorks.placeholderview.Animation;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Animate;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.LongClick;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

/**
 * Created by honjayChen on 2017/4/29.
 */

@Animate(Animation.CARD_TOP_IN_DESC)
@NonReusable
@Layout(R.layout.gallery_item_small)
public class ImageTypeSmall {


    @View(R.id.imageView)
    private ImageView imageView;
    @View(R.id.ImageViewText)
    private TextView mImageViewText;
    @View(R.id.btn_more)
    private Button mbtn_more;


    private Image mImg;
    private Context mContext;
    private PlaceHolderView mPlaceHolderView;

    public ImageTypeSmall(Context context, PlaceHolderView placeHolderView, Image img) {
        mContext = context;
        mPlaceHolderView = placeHolderView;
        mImg = img;
    }

    @Resolve
    private void onResolved() {
        Glide.with(mContext).load(mImg.getImageUrl()).into(imageView);
        mImageViewText.setText(mImg.getName());
        mbtn_more.setText(mImg.getDesc());
    }

    @LongClick(R.id.imageView)
    private void onLongClick(){
        mPlaceHolderView.removeView(this);
    }

    @Click(R.id.imageView)
    public void onImageViewClick() {
        try {
            startActivity(PlayerActivity.class);
          //  startActivity(PreferenceActivityCompat.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Click(R.id.btn_more)
    private void onMoreClick(){
        try {
            startActivity(PreferenceActivityCompat.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void startActivity(Class<?> cls) {
        Intent intent = new Intent(mContext, cls);
     //   intent.putExtra("vedio_url",this.mImg.getVedioUrl());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }
}