import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import store from './store'
import './plugins/element.js'
import axios from 'axios'
 

Vue.config.productionTip = false
//axios.defaults.baseURL = '';  
//Vue.prototype.$axios = axios
Vue. use(ElementUI)
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
