import Vue from "vue";
import App from "./App.vue";
import FontPicker from 'font-picker-vue'
import VModal from 'vue-js-modal'

Vue.component('FontPicker', FontPicker)
Vue.use(VModal)

Vue.config.productionTip = false;

new Vue({
  render: (h) => h(App)
}).$mount("#app");
