import { RouteRecordRaw } from 'vue-router'

import { blockUnAuth, blockAuth } from './routerGuards'

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    beforeEnter: blockUnAuth,
    children: [
      { path: '', component: () => import('pages/DashboardPage.vue') },
      { path: 'power-graph', component: () => import('pages/PowerGraphPage.vue') },
    ],
  },
  {
    path: '/auth',
    component: () => import('layouts/MainLayout.vue'),
    beforeEnter: blockAuth,
    children: [
      { path: 'login', component: () => import('pages/auth/LoginPage.vue') },
      { path: 'getting-started', component: () => import('pages/auth/GettingStarted.vue') },
      { path: 'register', component: () => import('pages/auth/RegisterPage.vue') },
      { path: 'welcome', component: () => import('pages/auth/WelcomePage.vue') },
    ],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue'),
  },
]

export default routes
