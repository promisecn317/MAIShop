import axios from 'axios';
import router from '../router';
import {Notification, MessageBox} from 'element-ui'


const service = axios.create({
  baseURL: "http://localhost:8080",
  timeout: 10000, //请求超时时间
})

//request拦截器
service.interceptors.request.use(
  config => {
    config.headers['Content-Type'] = 'application/json'
    return config
  },
  error => {
    console.load(error)  //for debug
    Promise.reject(error)
  }
);

//response拦截器
service.interceptors.response.use(
  response => {
    const code = response.status
    if (code < 200 || code > 300) {
      Notification.error({
        title: response.message
      })
      return Promise.reject('error')
    } else {
      return response.data
    }
  },
  error => {
        Notification.error({
          title:'请求超时',
          duration: 5000,
          offset:50
        })
        return Promise.reject(error)
      }
)

export default service;


