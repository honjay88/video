package com.example.honjaychen.dagger.gallery.gallery;

import android.content.Context;
import com.mindorks.placeholderview.Animation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.honjaychen.dagger.R;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Animate;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.LongClick;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

/**
 * Created by honjayChen on 2017/4/29.
 */

@Animate(Animation.ENTER_LEFT_DESC)
@NonReusable
@Layout(R.layout.gallery_item_big)
public class ImageTypeBig {

    @View(R.id.imageView)
    private ImageView imageView;

    private String mUlr;
    private Context mContext;
    private PlaceHolderView mPlaceHolderView;

    public ImageTypeBig(Context context, PlaceHolderView placeHolderView, String ulr) {
        mContext = context;
        mPlaceHolderView = placeHolderView;
        mUlr = ulr;
    }

    @Resolve
    private void onResolved() {
        Glide.with(mContext).load(mUlr).into(imageView);
    }

    @LongClick(R.id.imageView)
    private void onLongClick(){
        mPlaceHolderView.removeView(this);
    }
}