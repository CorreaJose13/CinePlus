import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import LoginView from '@/views/LoginView.vue'
import RegisterView from '@/views/RegisterView.vue'
import AdminView from '@/views/AdminView.vue'
import ListMovies from '@/components/ListMovies.vue'
import ListUsers from '@/components/ListUsers.vue'
import ListPurchases from '@/components/ListPurchases.vue'
import AddMovie from '@/components/AddMovie.vue'
import EditMovie from '@/components/EditMovie.vue'
import EditUser from '@/components/EditUser.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/home',
      name: 'home',
      component: HomeView,
      children: [
        {
          path: '/login',
          name: 'login',
          component: LoginView,
        },
        {
          path: '/register',
          name: 'register',
          component: RegisterView,
        },
        {
          path: '/admin',
          name: 'admin',
          component: AdminView,
          children: [
            {
              path: '/movies',
              name: 'movies',
              component: ListMovies,
            },
            {
              path: '/movies/add',
              name: 'add movie',
              component: AddMovie,
            },
            {
              path: '/movies/edit',
              name: 'edit movie',
              component: EditMovie,
            },
            {
              path: '/users',
              name: 'users',
              component: ListUsers,
            },
            {
              path: '/users/edit',
              name: 'edit user',
              component: EditUser,
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
      redirect: '/home',
    },
  ],
})

export default router
