import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from"../views/Layout.vue"
import Home from'../components/Home.vue'
import MyView from'../views/main/MyView.vue'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    children:[
    {
      path: '',
      name: 'Home',
      component:Home
    },
    {
      path:"/Cart",
      name:"Cart",
      component:()=>import("@/views/main/CartView.vue")
    },
    {
      path:"/Chat",
      name:"Chat",
      component:()=>import("@/views/main/ChatView.vue")
    },
    {
      path:"/MyView",
      name:"MyView",
      component:()=>import("../views/main/MyView.vue")
    },
    {
      path:"/Newproduct",
      name:"Newproduct",
      component:()=>import("../views/main/NewproductView.vue")
    },
    {
      path:"/productlist",
      name:"productlist",
      component:()=>import("../views/main/productlist.vue")
    },
    {
      path:"/edit",
      name:"edit",
      component:()=>import("../views/main/edit information.vue")
    }
  ]
  
  },
  {
    path:"/open",
    name:"open",
    component:()=>import("../views/open.vue")
  },
  {
    path:"/login",
    name:"login",
    component:()=>import("../views/login.vue")
  },
  {
    path:"/register",
    name:"register",
    component:()=>import("../views/register.vue")
  }
]

const router = new VueRouter({
  routes
})

export default router
