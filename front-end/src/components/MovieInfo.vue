<script setup lang="ts">
import { onMounted } from 'vue'
import { useMovieStore } from '@/stores/movieStore'

const movieStore = useMovieStore()
const movie = movieStore.getMovie || null
const titulo = movie?.titulo || ''
const imagen = movie?.imagen || ''
const descripcion = movie?.descripcion || ''
const duracion = movie?.duracion || 0
const genero = movie?.genero || ''
const año = movie?.año || 0

onMounted(() => {
  movieStore.loadMovie()
})
</script>
<template>
  <v-card class="mt-8" max-width="20vw" min-height="60vh" elevation="8">
    <v-img :src="imagen" class="movie-image"> </v-img>

    <v-card-text class="pa-0">
      <div class="pa-4">
        <div>Titulo</div>
        <div>{{ titulo }}</div>
        <v-divider class="my-2"></v-divider>
        <div>Sinopsis</div>
        <div>{{ descripcion }}</div>
        <v-divider class="my-2"></v-divider>
        <div>Género</div>
        <div>{{ genero }}</div>
        <v-divider class="my-2"></v-divider>
        <div>Año</div>
        <div>{{ año }}</div>
        <v-divider class="my-2"></v-divider>
        <div>Duración</div>
        <div>{{ duracion }} min</div>
      </div>
      <div class="text-center mb-2">
        <v-icon icon="mdi-barcode" size="x-large"></v-icon>
      </div>
    </v-card-text>
  </v-card>
</template>
<style scoped>
.movie-image :deep(.v-img__img--contain) {
  object-fit: fill;
}
</style>
