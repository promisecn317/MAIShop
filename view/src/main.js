import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
// import './utils/request.js'
import axios from 'axios'

Vue.prototype.$https = axios;
axios.defaults.baseURL = 'http://localhost:8088/'
axios.defaults.headers.post['Content-Type']='application/json'

axios.interceptors.response.use(
  data => {
    return data;
  },
  error => {
    console.log(error.response.status)
    if (error.response) {
      switch (error.response.status) {
        case 400:
          ElementUI.Message({
            showClose: true,
            message: 'Failed. Please try again',
            type: 'error'
          })
          break
        case 416:
          ElementUI.Message({
            showClose: true,
            message: 'You are a new user.Go and register first',
            type: 'error'
          })
          break
        case 409:
          ElementUI.Message({
              showClose: true,
              message: 'Already registered, log in!',
              type: 'error'
            })
          break
      }
    }
  }
)
// Vue.prototype.$https.interceptors.response.use(
//   response => {
//     const code = response.status
//     if (code < 200 || code > 300) {
//       Notification.error({
//         title: response.message
//       })
//       return Promise.reject('error')
//     } else {
//       return response.data
//     }
//   },
//   error => {
//         Notification.error({
//           title:'请求超时',
//           duration: 5000,
//           offset:50
//         })
//         return Promise.reject(error)
//       }
// )



Vue.use(router)
Vue.use(ElementUI)



new Vue({
  el: '#app',
  router,
  render: h => h(App),
})
