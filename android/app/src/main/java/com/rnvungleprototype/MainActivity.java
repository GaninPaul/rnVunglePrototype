package com.rnvungleprototype;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.facebook.react.ReactActivity;
import com.vungle.warren.AdConfig;
import com.vungle.warren.InitCallback;
import com.vungle.warren.LoadAdCallback;
import com.vungle.warren.PlayAdCallback;
import com.vungle.warren.Vungle;
import com.vungle.warren.VungleSettings;
import com.vungle.warren.error.VungleException;

public class MainActivity extends ReactActivity {

  VungleSettings vungleSettings = new VungleSettings.Builder().setAndroidIdOptOut(true).build();
  String placementId = "DEFAULT-8783530";
  String appId = "604fa258bf7860d226647665";
  AdConfig adConfig = new AdConfig();
  PlayAdCallback vunglePlayAdCallback;
  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */
  @Override
  protected String getMainComponentName() {
    return "rnVunglePrototype";
  }


  private void loadAds(){
    if (Vungle.isInitialized()) {
      Vungle.loadAd(placementId, new LoadAdCallback() {
        @Override
        public void onAdLoad(String placementReferenceId) {
          Log.println(Log.ERROR, "@@@@@@", "onAdLoad " + placementReferenceId);
        }

        @Override
        public void onError(String placementReferenceId, VungleException e) {
          Log.println(Log.ERROR, "@@@@@@", "onAdLoad " + placementReferenceId + " error " + e.getMessage());
        }
      });
    }
  }
  private void playAds(){
    vunglePlayAdCallback = new PlayAdCallback() {
      @Override
      public void onAdStart(String placementReferenceId) {
        Log.println(Log.ERROR, "@@@@@@", "onAdStart " + placementReferenceId  );

      }

      @Override
      public void onAdEnd(String placementReferenceId, boolean completed, boolean isCTAClicked) {
        Log.println(Log.ERROR, "@@@@@@", "onAdEnd " + placementReferenceId + " completed " + completed);
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
      public void onError(String placementReferenceId, VungleException e) {
        if (e.getExceptionCode() == VungleException.VUNGLE_NOT_INTIALIZED) {
        }
      }
    };
//        Vungle.setIncentivizedFields("604f903d5a93950016a64081", "RewardedTitle", "RewardedBody", "RewardedKeepWatching", "RewardedClose");
    Log.println(Log.ERROR, "@@@@@@", "Play can ? " + Vungle.canPlayAd(placementId) );
    if (Vungle.canPlayAd(placementId)) {
      Vungle.playAd(placementId, adConfig, vunglePlayAdCallback);
    }
  }


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
    super.onCreate(savedInstanceState, persistentState);
    Vungle.init(appId, this, new InitCallback() {
      @Override
      public void onSuccess() {
        Log.println(Log.ERROR, "@@@@@@", "onSuccess");
        loadAds();
        playAds();
      }

      @Override
      public void onError(VungleException e) {
        Log.println(Log.ERROR, "@@@@@@", "onError " + e);
      }

      @Override
      public void onAutoCacheAdAvailable(String placementId) {
        Log.println(Log.ERROR, "@@@@@@", "onAutoCacheAdAvailable " + placementId);

      }
    }, vungleSettings);
  }
}
