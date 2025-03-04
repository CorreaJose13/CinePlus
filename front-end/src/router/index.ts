import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import LoginView from '@/views/LoginView.vue'
import RegisterView from '@/views/RegisterView.vue'
import AdminView from '@/views/AdminView.vue'
import ListMovies from '@/components/ListMovies.vue'
import ListUsers from '@/components/ListUsers.vue'
import ListPurchases from '@/components/ListPurchases.vue'
import BillBoardView from '@/views/BillBoardView.vue'
import MovieFunction from '@/components/MovieFunction.vue'
import MoviePurchase from '@/components/MoviePurchase.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      redirect: 'billboard',
      component: HomeView,
      children: [
        {
          path: '',
          name: 'billboard',
          component: BillBoardView,
        },
        {
          path: 'movie',
          name: 'movieDetail',
          redirect: 'function',
          children: [
            {
              path: '/function',
              name: 'movieFunction',
              component: MovieFunction,
            },
            {
              path: '/purchase',
              name: 'moviePurchase',
              component: MoviePurchase,
            },
          ],
        },
        {
          path: 'login',
          name: 'login',
          component: LoginView,
        },
        {
          path: 'register',
          name: 'register',
          component: RegisterView,
        },
        {
          path: 'admin',
          name: 'admin',
          component: AdminView,
          children: [
            {
              path: '/movies',
              name: 'movies',
              component: ListMovies,
            },
            {
              path: '/users',
              name: 'users',
              component: ListUsers,
            },
            {
              path: '/purchases',
              name: 'purchases',
              component: ListPurchases,
            },
          ],
        },
      ],
    },
    {
      path: '/:pathMatch(.*)*',
      name: 'redirect',
      redirect: 'billboard',
    },
  ],
})

export default router
