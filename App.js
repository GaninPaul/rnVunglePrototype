/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React, {useState, useEffect} from 'react';

import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  View,
  TouchableOpacity,
} from 'react-native';

import RNVungle, {VungleView} from './src/VungleView';

const App = () => {
  const [banner, shoWBanner] = useState(true);
  const appID = '604fa258bf7860d226647665';
  const placementId = 'TEST2-1451823';
  const placementId2 = 'BANNER-6722133';

  RNVungle.init(appID);
  RNVungle.loadAds(placementId);
  RNVungle.loadBannerAds(placementId2);

  const backgroundStyle = {
    backgroundColor: '#fff',
  };

  return (
    <SafeAreaView style={backgroundStyle}>
      <StatusBar barStyle={'dark-content'} />
      <ScrollView
        contentInsetAdjustmentBehavior="automatic"
        style={backgroundStyle}>
        <View
          style={{
            backgroundColor: '#fff',
          }}>
          <TouchableOpacity onPress={() => shoWBanner(!banner)}>
            <Text>Show Banner</Text>
          </TouchableOpacity>
          <TouchableOpacity onPress={() => RNVungle.showVideoAds(placementId)}>
            <Text>Show Video</Text>
          </TouchableOpacity>
          {!banner ? (
            <VungleView style={styles.vungle} placementId={placementId2} />
          ) : null}
        </View>
      </ScrollView>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  vungle: {
    height: 100,
    width: 100,
  },
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
  },
  sectionTitle: {
    fontSize: 24,
    fontWeight: '600',
  },
  sectionDescription: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
  },
  highlight: {
    fontWeight: '700',
  },
});

export default App;
