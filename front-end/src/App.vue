<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <v-app>
    <v-app-bar color="primary" app dark>
      <div class="d-flex align-center">
        <v-img alt="Logo" class="mr-2" contain src="@/assets/logo.svg" width="40" height="40" />
      </div>

      <v-spacer></v-spacer>

      <div class="navigation-links">
        <v-btn v-for="item in navigationItems" :key="item.title" :to="item.to" text class="mx-2">
          {{ item.title }}
        </v-btn>
      </div>
    </v-app-bar>

    <v-main>
      <v-container fluid>
        <RouterView />
      </v-container>
    </v-main>
  </v-app>
</template>

<script lang="ts">
export default {
  data() {
    return {
      navigationItems: [
        { title: 'Cartelera', to: '/cartelera' },
        { title: 'Iniciar Sesión', to: '/login' },
        { title: 'Registrarse', to: '/register' }
      ]
    }
  }
}
</script>

<style scoped>
.navigation-links {
  display: flex;
  align-items: center;
}

@media (max-width: 600px) {
  .navigation-links {
    display: none;
  }
}
</style>
