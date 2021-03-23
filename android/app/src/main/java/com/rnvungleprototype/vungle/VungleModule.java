package com.rnvungleprototype.vungle;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import com.vungle.warren.*;
import com.vungle.warren.error.VungleException;

public class VungleModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public VungleModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }


    @Override
    public String getName() {
        return "RNVungle";
    }


    @ReactMethod
    public void loadAds(final String placementId) {
        if (Vungle.isInitialized()) {
            Vungle.loadAd(placementId, new LoadAdCallback() {
                @Override
                public void onAdLoad(String placementReferenceId) {
                }

                @Override
                public void onError(String placementReferenceId, VungleException e) {
                }
            });
        }
    }

    @ReactMethod
    public void loadBannerAds(final String placementId) {
        if (Vungle.isInitialized()) {
            Banners.loadBanner(placementId, AdConfig.AdSize.BANNER,
                    new LoadAdCallback() {
                        @Override
                        public void onAdLoad(String placementReferenceId) {
                        }

                        @Override
                        public void onError(String placementReferenceId, VungleException e) {
                        }
                    });
        }
    }

    @ReactMethod
    public void init(final String appid) {
        Vungle.init(appid, reactContext.getApplicationContext(), new InitCallback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError(VungleException e) {
            }

            @Override
            public void onAutoCacheAdAvailable(String placementId) {
            }
        }, new VungleSettings.Builder().setAndroidIdOptOut(true).build());
    }


    @ReactMethod
    public void showVideoAds(String placementId) {
        if (Vungle.canPlayAd(placementId)) {
            Vungle.playAd(placementId, null, new PlayAdCallback() {
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
            });
        }
    }
}