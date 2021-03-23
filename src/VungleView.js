import {requireNativeComponent, NativeModules} from 'react-native';

export const VungleView = requireNativeComponent('RNVungleView');

const {RNVungle} = NativeModules;

export default RNVungle;
