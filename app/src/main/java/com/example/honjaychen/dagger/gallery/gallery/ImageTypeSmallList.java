package com.example.honjaychen.dagger.gallery.gallery;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.beardedhen.androidbootstrap.AwesomeTextView;
import com.example.honjaychen.dagger.R;
import com.mindorks.placeholderview.Animation;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Animate;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import java.util.List;

//import android.view.animation.Animation;

/**
 * Created by honjayChen on 2017/4/29.
 */

@Animate(Animation.CARD_TOP_IN_DESC)
@NonReusable
@Layout(R.layout.gallery_item_small_list)
public class ImageTypeSmallList {

    @View(R.id.placeholderview)
    private PlaceHolderView mPlaceHolderView;

    @View(R.id.titleID)
    private AwesomeTextView mTitle;

    private Context mContext;
    private List<Image> mImageList;

    public ImageTypeSmallList(Context context, List<Image> imageList) {
        mContext = context;
        mImageList = imageList;
    }

    @Resolve
    private void onResolved() {
        mPlaceHolderView.getBuilder()
                .setHasFixedSize(false)
                .setItemViewCacheSize(10)
                .setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        this.setTitle(this.mImageList.get(0).getCategory());
        for(Image image : mImageList) {
            mPlaceHolderView.addView(new ImageTypeSmall(mContext, mPlaceHolderView, image));
        }
    }

    private void setTitle(String sTiteNmae)
    {
        mTitle.setText(sTiteNmae);
    }
}