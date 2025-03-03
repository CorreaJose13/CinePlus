<script setup lang="ts">
import { ref, computed } from 'vue'
import { useDisplay } from 'vuetify'
const navigationItems = ref<{ title: string; to: string; icon?: string }[]>([
  { title: 'Cartelera', to: '/billboard', icon: 'mdi-movie' },
  { title: 'Iniciar Sesión', to: '/login', icon: 'mdi-login' },
  { title: 'Registrarse', to: '/register', icon: 'mdi-account-plus' },
])
const drawer = ref(false)

const { mobile } = useDisplay()
const isMobile = computed(() => mobile.value)
</script>

<template>
  <v-app-bar color="primary" :elevation="10">
    <v-spacer></v-spacer>

    <div v-if="!isMobile" class="navigation-links">
      <v-btn
        v-for="item in navigationItems"
        :key="item.title"
        :to="item.to"
        variant="text"
        :prepend-icon="item.icon"
        rounded
        class="ms-1"
      >
        {{ item.title }}
      </v-btn>
    </div>

    <v-app-bar-nav-icon v-if="isMobile" @click="drawer = !drawer"></v-app-bar-nav-icon>
  </v-app-bar>

  <v-navigation-drawer v-model="drawer" temporary location="left">
      <v-btn
        v-for="item in navigationItems"
        :key="item.title"
        :to="item.to"
        variant="text"
        :prepend-icon="item.icon"
        rounded
        class="ms-1"
      >
        {{ item.title }}
      </v-btn>
  </v-navigation-drawer>
</template>

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
