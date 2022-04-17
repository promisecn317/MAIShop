import Vue from 'vue'
import Router from 'vue-router'
import open from "../views/open";
import login from "../views/login";
import register from "../views/register";
// import goods from "../components/goods";
import sc from "../views/sc";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect:'/open',
    },
    {
      path: '/open',
      component:open,
    },
    {
      path: '/login',
      component:login,
    },
    {
      path:'/register',
      component:register,
    },
    // {
    //   path:'/goods',
    //   component:goods,
    // },
    {
      path:'/sc',
      component:sc,
    },
  ]
})
