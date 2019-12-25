/**
 * @format
 */

import { AppRegistry, } from 'react-native';
import App from './react/my/MyStackRoute';
import multServer from './react/multServer';
import Battery_business from './react/Battery_business/Battery_business';

AppRegistry.registerComponent("Move_App_My", () => App);//我的
AppRegistry.registerComponent("multServer", () => multServer);//多服务
AppRegistry.registerComponent("Battery_business", () => Battery_business);//电池业务
console.disableYellowBox = true;
