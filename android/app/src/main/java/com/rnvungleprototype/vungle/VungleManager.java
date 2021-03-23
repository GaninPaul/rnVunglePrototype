package com.rnvungleprototype.vungle;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.image.ReactImageView;
import com.vungle.warren.AdConfig;
import com.vungle.warren.Banners;
import com.vungle.warren.InitCallback;
import com.vungle.warren.LoadAdCallback;
import com.vungle.warren.PlayAdCallback;
import com.vungle.warren.Vungle;
import com.vungle.warren.VungleBanner;
import com.vungle.warren.VungleNativeAd;
import com.vungle.warren.VungleSettings;
import com.vungle.warren.error.VungleException;

public class VungleManager extends SimpleViewManager<LinearLayout> {

    public static final String REACT_CLASS = "RNVungleView";
    String placementId;
    LinearLayout linearLayout;
    PlayAdCallback vunglePlayAdCallback;


    public VungleManager(ReactApplicationContext _) {
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(name = "placementId")
    public void placementId(LinearLayout _, String placementId) {
       this.placementId = placementId;
       Log.println(Log.ERROR, " *******","  this.placementId"+  this.placementId);
    }

    @NonNull
    @Override
    public LinearLayout createViewInstance(@NonNull ThemedReactContext context) {
        linearLayout = new LinearLayout(context);
        vunglePlayAdCallback = new PlayAdCallback() {
            @Override
            public void onAdStart(String placementReferenceId) {
            }

            @Override
            public void onAdEnd(String placementReferenceId, boolean completed, boolean isCTAClicked) {
            }

            @Override
            public void onAdEnd(String id) {
            }

            @Override
            public void onAdClick(String id) {
            }

            @Override
            public void onAdRewarded(String id) {
            }

            @Override
            public void onAdLeftApplication(String id) {
            }

            @Override
            public void onError(String placementReferenceId, VungleException exception) {
            }
        };

        if (Banners.canPlayAd(placementId, AdConfig.AdSize.BANNER)) {
            VungleBanner vungleBanner = Banners.getBanner(placementId, AdConfig.AdSize.BANNER_SHORT, vunglePlayAdCallback);
            linearLayout.addView(vungleBanner);
        }
        return linearLayout;
    }
}
