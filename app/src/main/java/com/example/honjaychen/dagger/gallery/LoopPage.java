package com.example.honjaychen.dagger.gallery;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.example.honjaychen.dagger.R;
import com.itheima.loopviewpager.LoopViewPager;
import com.itheima.loopviewpager.listener.OnItemClickListener;
import com.mindorks.placeholderview.Animation;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Animate;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by honjayChen on 2017/4/29.
 */

@Animate(Animation.CARD_TOP_IN_DESC)
@NonReusable
@Layout(R.layout.looppage_image)
public class LoopPage  {

    private PlaceHolderView mPlaceHolderView;
    /**
     * 全局上下文
     */
    private final Context mContext;
    /**
     * 依附的容器Activity
     */
    private final Activity mActivity;


    @View(R.id.lvp_pager)
    LoopViewPager loopViewPager;

    public LoopPage(Activity activity,PlaceHolderView placeHolderView) {
        this(activity, null,placeHolderView);
    }

    public LoopPage(Activity activity, android.view.View rootView,PlaceHolderView placeHolderView) {
        mPlaceHolderView = placeHolderView;
        this.mActivity = activity;
        this.mContext = activity;
       /* if (rootView == null) {
            loopViewPager = (LoopViewPager) mActivity.findViewById(R.id.lvp_pager);
        }else {
            loopViewPager = (LoopViewPager) rootView.findViewById(R.id.lvp_pager);
        }*/

    }

    public void  setHeight(int height)
    {

      //  loopViewPager.setLayoutParams(Lay);
    }
    @Resolve
    private void onResolved() {
        //处理点击事件
        loopViewPager.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(android.view.View view, int position) {
                Toast.makeText(mContext, "position=" + position, Toast.LENGTH_SHORT).show();
            }
        });
        loopViewPager.setImgData(imgListString());
        loopViewPager.setTitleData(titleListString());
        loopViewPager.start();
    }

    private List<String> imgListString() {
        List<String> imageData = new ArrayList<>();
        imageData.add("http://d.hiphotos.baidu.com/image/h%3D200/sign=72b32dc4b719ebc4df787199b227cf79/58ee3d6d55fbb2fb48944ab34b4a20a44723dcd7.jpg");
        imageData.add("http://pic.4j4j.cn/upload/pic/20130815/31e652fe2d.jpg");
        imageData.add("http://pic.4j4j.cn/upload/pic/20130815/5e604404fe.jpg");
        imageData.add("http://pic.4j4j.cn/upload/pic/20130909/681ebf9d64.jpg");
        imageData.add("http://d.hiphotos.baidu.com/image/pic/item/54fbb2fb43166d22dc28839a442309f79052d265.jpg");
        return imageData;
    }

    private List<String> titleListString() {
        List<String> titleData = new ArrayList<>();
        titleData.add("1、在这里等着你");
        titleData.add("2、在你身边");
        titleData.add("3、打电话给你就是想说声“嗨”");
        titleData.add("4、不介意你对我大喊大叫");
        titleData.add("5、期待你总是尽全力");
        return titleData;
    }
}