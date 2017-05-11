/*
 * MIT License
 *
 * Copyright (c) 2017 Jan Heinrich Reimer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.heinrichreimersoftware.materialdrawer.structure;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.heinrichreimersoftware.materialdrawer.DrawerView;
import com.heinrichreimersoftware.materialdrawer.drawable.RoundedAvatarDrawable;
import com.heinrichreimersoftware.materialdrawer.theme.DrawerTheme;

/**
 * Object to be used with {@link com.heinrichreimersoftware.materialdrawer.DrawerView} to display a profile in the drawer.
 * Can hold an image, a primary text, a secondary text and a listener.
 */
public class DrawerProfile {

    private DrawerTheme mDrawerTheme;

    private long mId = -1;

    private Drawable mAvatar = null;
    private Drawable mBackground = null;
    private String mName;
    private String mDescription;

    private OnProfileClickListener mOnClickListener;
    private DrawerView mDrawerView;


    /**
     * Sets the drawer profile theme
     *
     * @param theme Theme to set
     */
    public DrawerProfile setDrawerTheme(DrawerTheme theme) {
        mDrawerTheme = theme;
        notifyDataChanged();
        return this;
    }

    /**
     * Resets the drawer profile theme
     */
    public DrawerProfile resetDrawerTheme(Context context) {
        mDrawerTheme = new DrawerTheme(context);
        notifyDataChanged();
        return this;
    }

    /**
     * Gets the drawer profile theme
     */
    public DrawerTheme getDrawerTheme() {
        return mDrawerTheme;
    }

    /**
     * Gets whether the drawer profile has its own theme
     */
    public boolean hasDrawerTheme() {
        return mDrawerTheme != null;
    }


    /**
     * Sets an ID the drawer profile
     *
     * @param id ID to set
     */
    public DrawerProfile setId(long id) {
        mId = id;
        return this;
    }

    /**
     * Gets the ID of the drawer profile
     *
     * @return ID of the drawer profile
     */
    public long getId() {
        return mId;
    }


    /**
     * Sets an avatar image to the drawer profile
     *
     * @param avatar Avatar image to set
     */
    public DrawerProfile setAvatar(Drawable avatar) {
        mAvatar = avatar;
        notifyDataChanged();
        return this;
    }

    /**
     * Sets an avatar image to the drawer profile
     *
     * @param avatar Avatar image to set
     */
    public DrawerProfile setAvatar(Context context, Bitmap avatar) {
        mAvatar = new BitmapDrawable(context.getResources(), avatar);
        notifyDataChanged();
        return this;
    }

    /**
     * Sets a rounded avatar image to the drawer profile
     *
     * @param image Avatar image to set
     */
    public DrawerProfile setRoundedAvatar(BitmapDrawable image) {
        return setAvatar(new RoundedAvatarDrawable(image.getBitmap()));
    }

    /**
     * Sets a rounded avatar image to the drawer profile
     *
     * @param image Avatar image to set
     */
    public DrawerProfile setRoundedAvatar(Context context, Bitmap image) {
        return setAvatar(new RoundedAvatarDrawable(new BitmapDrawable(context.getResources(), image).getBitmap()));
    }

    /**
     * Gets the avatar image of the drawer profile
     *
     * @return Avatar image of the drawer profile
     */
    public Drawable getAvatar() {
        return mAvatar;
    }

    /**
     * Gets whether the drawer profile has an avatar image set to it
     *
     * @return True if the drawer profile has an avatar image set to it, false otherwise.
     */
    public boolean hasAvatar() {
        return mAvatar != null;
    }

    /**
     * Removes the avatar image from the drawer profile
     */
    public DrawerProfile removeAvatar() {
        mAvatar = null;
        notifyDataChanged();
        return this;
    }


    /**
     * Sets a background to the drawer profile
     *
     * @param background Background to set
     */
    public DrawerProfile setBackground(Drawable background) {
        mBackground = background;
        notifyDataChanged();
        return this;
    }

    /**
     * Sets a background to the drawer profile
     *
     * @param background Background to set
     */
    public DrawerProfile setBackground(Context context, Bitmap background) {
        mBackground = new BitmapDrawable(context.getResources(), background);
        notifyDataChanged();
        return this;
    }

    /**
     * Gets the background of the drawer profile
     *
     * @return Background of the drawer profile
     */
    public Drawable getBackground() {
        return mBackground;
    }

    /**
     * Gets whether the drawer profile has a background set to it
     *
     * @return True if the drawer profile has a background set to it, false otherwise.
     */
    public boolean hasBackground() {
        return mBackground != null;
    }

    /**
     * Removes the background from the drawer profile
     */
    public DrawerProfile removeBackground() {
        mBackground = null;
        notifyDataChanged();
        return this;
    }


    /**
     * Sets a name to the drawer profile
     *
     * @param name Name to set
     */
    public DrawerProfile setName(String name) {
        mName = name;
        notifyDataChanged();
        return this;
    }

    /**
     * Gets the name of the drawer profile
     *
     * @return Name of the drawer profile
     */
    public String getName() {
        return mName;
    }

    /**
     * Gets whether the drawer profile has a name set to it
     *
     * @return True if the drawer profile has a name set to it, false otherwise.
     */
    public boolean hasName() {
        return mName != null && !mName.equals("");
    }

    /**
     * Removes the name from the drawer profile
     */
    public DrawerProfile removeName() {
        mName = null;
        notifyDataChanged();
        return this;
    }


    /**
     * Sets a description to the drawer profile
     *
     * @param description Description to set
     */
    public DrawerProfile setDescription(String description) {
        mDescription = description;
        notifyDataChanged();
        return this;
    }

    /**
     * Gets the description of the drawer profile
     *
     * @return Description of the drawer profile
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * Gets whether the drawer profile has a description set to it
     *
     * @return True if the drawer profile has a description set to it, false otherwise.
     */
    public boolean hasDescription() {
        return mDescription != null && !mDescription.equals("");
    }

    /**
     * Removes the description from the drawer profile
     */
    public DrawerProfile removeDescription() {
        mDescription = null;
        notifyDataChanged();
        return this;
    }


    /**
     * Sets a click listener to the drawer profile
     *
     * @param listener Listener to set
     */
    public DrawerProfile setOnProfileClickListener(OnProfileClickListener listener) {
        mOnClickListener = listener;
        notifyDataChanged();
        return this;
    }

    /**
     * Gets the click listener of the drawer profile
     *
     * @return Click listener of the drawer profile
     */
    public OnProfileClickListener getOnProfileClickListener() {
        return mOnClickListener;
    }

    /**
     * Gets whether the drawer profile has a click listener set to it
     *
     * @return True if the drawer profile has a click listener set to it, false otherwise.
     */
    public boolean hasOnProfileClickListener() {
        return mOnClickListener != null;
    }

    /**
     * Removes the click listener from the drawer profile
     */
    public DrawerProfile removeOnProfileClickListener() {
        mOnClickListener = null;
        notifyDataChanged();
        return this;
    }


    /**
     * Attaches the drawer item to a drawer
     *
     * @param drawerView drawer to attach to
     */
    public DrawerProfile attachTo(DrawerView drawerView) {
        mDrawerView = drawerView;
        return this;
    }

    /**
     * Detaches the drawer item from its drawer
     */
    public DrawerProfile detach() {
        mDrawerView = null;
        return this;
    }

    protected void notifyDataChanged() {
        if (mDrawerView != null) {
            mDrawerView.selectProfile(this);
        }
    }


    public interface OnProfileClickListener {
        void onClick(DrawerProfile profile, long id);
    }

    public interface OnProfileSwitchListener {
        void onSwitch(DrawerProfile oldProfile, long oldId, DrawerProfile newProfile, long newId);
    }
}
