package com.scleroidtech.gatepass.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Inject;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class ImageUtils {

    @Inject
    Context context;

    @Inject
    public ImageUtils(Context context) {
        this.context = context;
    }

    /**
     * Sets profile picture of user if exists
     * if doesn't, uses the default profile picture of the user
     */
    public void loadImageIntoImageView(ImageView imageView, String imageUrl, int placeHolder) {
        /*if (MainActivity.session.getUser().isUserImageExists()) {*/

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(placeHolder).fitCenter().circleCrop().diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(imageUrl)
                .transition(withCrossFade())
                .thumbnail(0.5f)
                .apply(requestOptions)
                .into(imageView);

       /* } else {
            Drawable d = context.getResources().getDrawable(R.drawable.ic_person);
            profilePicture.setImageDrawable(d);
        }*/
    }
}