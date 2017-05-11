package com.example.honjaychen.dagger.gallery;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.honjaychen.dagger.R;
import com.example.honjaychen.dagger.gallery.gallery.Image;
import com.example.honjaychen.dagger.gallery.gallery.ImageTypeSmallList;
import com.heinrichreimersoftware.materialdrawer.DrawerView;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerItem;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerProfile;
import com.itheima.loopviewpager.LoopViewPager;
import com.mindorks.placeholderview.PlaceHolderView;
import com.test.pkg.ModelManager;

import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;


//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//import java.util.stream.Collectors;
/*import com.example.honjaychen.dagger.media.ijkplayer.listener.OnShowThumbnailListener;
import com.example.honjaychen.dagger.media.ijkplayer.widget.PlayStateParams;
import com.example.honjaychen.dagger.media.ijkplayer.widget.PlayerView;*/

/**
 * Created by honjayChen on 2017/4/29.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {//,SurfaceHolder.Callback {

    private PlaceHolderView mGalleryView;
    private PlaceHolderView mViewholder;
    // private Button playButton;
    private Context mContext;
    private LoopViewPager loopViewPager;
    private boolean direction;
    private DrawerView drawer;
    private ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        try {
            mContext = getBaseContext();

            // playButton = (Button) findViewById(R.id.playButton);
            //  playButton.setOnClickListener(this);

            DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
            drawer = (DrawerView) findViewById(R.id.drawer);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

            setSupportActionBar(toolbar);

       /* setDrawerTheme(
                new DrawerTheme(this)
                        .setBackgroundColorRes(R.color.window_background_3)
                        .setTextColorPrimaryRes(R.color.text_color_primary_3)
                        .setTextColorSecondaryRes(R.color.text_color_secondary_3)
        );*/


            drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close) {
                public void onDrawerClosed(View view) {
                    invalidateOptionsMenu();
                }

                public void onDrawerOpened(View drawerView) {
                    invalidateOptionsMenu();
                }
            };

            drawerLayout.setStatusBarBackgroundColor(ContextCompat.getColor(this, R.color.bootstrap_brand_danger));
            drawerLayout.addDrawerListener(drawerToggle);
            drawerLayout.closeDrawer(drawer);
            drawer.addProfile(new DrawerProfile()
                    .setId(1)
                    .setRoundedAvatar((BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.cat_2))
                    .setBackground(ContextCompat.getDrawable(this, R.drawable.b1))
                    .setName("設定")
            );

            drawer.addItem(new DrawerItem()
                    .setImage(ContextCompat.getDrawable(this, R.drawable.checkbox))
                    .setTextPrimary("近期瀏覽"));
            drawer.addDivider();
            drawer.addItem(new DrawerItem()
                    .setImage(ContextCompat.getDrawable(this, R.drawable.favorite))
                    .setTextPrimary("我的最愛")
            );
            drawer.addDivider();
            drawer.addItem(new DrawerItem()
                    .setImage(ContextCompat.getDrawable(this, R.drawable.research))
                    .setTextPrimary("搜索")
            );
            drawer.addDivider();

            drawer.addItem(new DrawerItem()
                    .setImage(ContextCompat.getDrawable(this, R.drawable.smartphone))
                    .setTextPrimary("說明")
            );
            drawer.addDivider();
            drawer.addFixedItem(new DrawerItem()
                    .setRoundedImage((BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.logout_sign), DrawerItem.SMALL_AVATAR)
                    .setTextPrimary("離開")
            );

            mGalleryView = (PlaceHolderView) findViewById(R.id.galleryView);
            mGalleryView.addView(new LoopPage(this, mGalleryView));
            mViewholder = (PlaceHolderView) findViewById(R.id.viewholder);
            setupGallery();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.d("My-Log-Msg", e.toString());
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onClick(View view) {
       /* switch (view.getId()) {
            case R.id.playButton:
                //RtspStream("rtsp://mpv.cdn3.bigCDN.com:554/bigCDN/definst/mp4:bigbuckbunnyiphone_400.mp4");
                try {
                    //openVideo();
                   // player.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }*/
    }

    private void setupGallery() {

        List<Image> imageList = ModelManager.provideImageModel(this.getApplicationContext());//Utils.loadImages(this.getApplicationContext());

        List<String> ls = Observable.fromIterable(imageList)
                .map(p -> p.getCategory())
                .distinct().toList().blockingGet();
        for (String s : ls) {
            List<Image> Images = Observable.fromIterable(imageList)
                    .filter(p -> p.getCategory().equals(s))
                    .sorted(new Comparator<Image>() {
                        @Override
                        public int compare(Image o1, Image o2) {
                            return o1.getUpdate().compareTo(o2.getUpdate());
                        }
                    })
                    .take(10)
                    .toList().blockingGet();

            mViewholder.addView(new ImageTypeSmallList(this.getApplicationContext(), Images));
        }
               /* .map(p ->
                        // a 2nd map operation is called with the List<Message> emitted upstream
                        messages.stream()
                                // filter to a sub-list containing only "our" messages
                                .filter(message -> message.isFor(recipient))
                                // turn sub-list stream back into a List<Message>
                                .collect(Collectors.toList()))*/
         /*       .subscribe(messages -> {
                    for (Message message : messages) {
                        log.info(message.toString())
                    }
                });*/


      /* List<String> cats = Stream.of(imageList)
                .map(p -> p.getCategory())
                .distinct()
                .collect(Collectors.<String>toList());

        for(int i=0;i<cats.size();i++)
        {
            String str = cats.get(i);
            List<Image> Images = Stream.of(imageList)
                            .filter(p -> p.getCategory().equals(str))
                            .sorted(new Comparator<Image>() {
                                        @Override
                                        public int compare(Image o1, Image o2) {
                                            return o1.getUpdate().compareTo(o2.getUpdate());
                                        }})
                            .limit(10)
                            .collect(Collectors.<Image>toList());

            mViewholder.addView(new ImageTypeSmallList(this.getApplicationContext(), Images));
        }
*/

     /*  List<Image> newImageList = new ArrayList<>();
        for (int i = 0; i <  (imageList.size() > 10 ? 10 : imageList.size()); i++) {
            newImageList.add(imageList.get(i));
        }
        mViewholder.addView(new ImageTypeSmallList(this.getApplicationContext(), newImageList));
        mViewholder.addView(new ImageTypeSmallList(this.getApplicationContext(), newImageList));
        mViewholder.addView(new ImageTypeSmallList(this.getApplicationContext(), newImageList));
        mViewholder.addView(new ImageTypeSmallList(this.getApplicationContext(), newImageList));
        mViewholder.addView(new ImageTypeSmallList(this.getApplicationContext(), newImageList));
        for (int i = imageList.size() - 1; i >= 0; i--) {
           // mGalleryView.addView(new ImageTypeBig(this.getApplicationContext(), mGalleryView, imageList.get(i).getImageUrl()));
        }*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loopViewPager.stop();
    }

}