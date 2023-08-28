import { createRouter, createWebHistory } from 'vue-router'

import ContainerShopping from './shoppingContent/ContainerShopping.vue'
import ContainerLogin from './loginContent/ContianerLogin.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/login', component: ContainerLogin },
    { path: '/home', component: ContainerShopping }
  ]
})

export default router
