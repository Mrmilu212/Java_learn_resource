import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

const routes = [
  {
    path: '/emp',
    name: 'emp',
    component: () => import('../views/tlias/EmpView.vue')
  },
  {
    path: '/dept',
    name: 'dept',
    component: () => import('../views/tlias/DeptView.vue')
  },
  {
    // 因为vue项目启动时默认访问'/'组件，这里需要利用重定向
    // 当访问的是'/'的时候，会自动跳转到'/dept'
    path: '/',
    // 重定向
    redirect: '/dept'
  }
]

const router = new VueRouter({
  routes
})

export default router
