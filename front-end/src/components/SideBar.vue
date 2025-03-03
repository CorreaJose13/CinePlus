<script setup lang="ts">
import { ref } from 'vue'

const items = ref<{ path: string; icon: string; title: string }[]>([
  {
    path: '/movies',
    icon: 'mdi-movie-roll',
    title: 'Lista de peliculas',
  },
  {
    path: '/movies/add',
    icon: 'mdi-movie-plus',
    title: 'Añadir pelicula',
  },
  {
    path: '/movies/edit',
    icon: 'mdi-movie-open-edit',
    title: 'Editar pelicula',
  },
  {
    path: '/users',
    icon: 'mdi-account-plus',
    title: 'Lista de usuarios',
  },
  {
    path: '/users/edit',
    icon: 'mdi-account-edit',
    title: 'Editar usuario',
  },
  {
    path: '/purchases',
    icon: 'mdi-cash',
    title: 'Lista de compras',
  },
])
</script>

<template>
  <v-navigation-drawer permanent border="end" class="sidebar-container">
    <div class="px-4">
      <div class="text-subtitle-1 font-weight-bold mb-3">Menu</div>

      <v-list nav density="compact">
        <v-list-item
          v-for="item in items"
          :key="item.path"
          :to="item.path"
          :prepend-icon="item.icon"
          :title="item.title"
          class="mb-1 rounded custom-list-item"
          color="primary"
          link
        />
      </v-list>
    </div>
  </v-navigation-drawer>
</template>

<style scoped>

.sidebar-container :deep(.custom-list-item) {
  background-color: #f7f7f7;
}

.sidebar-container :deep(.v-list-item--active) {
  background-color: rgb(214, 232, 255);
}

.sidebar-container :deep(.v-list-item:hover) {
  background-color: #f1f1f1;
}

.sidebar-container :deep(.v-list-item--active .v-list-item__content) {
  color: black;
}
</style>
