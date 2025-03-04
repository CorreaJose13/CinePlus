<script setup lang="ts">
import { useRouter } from 'vue-router'
const router = useRouter()
const verDetalles = () => {
  router.push({ name: 'moviePurchase' })
}
</script>
<template>
  <v-card class="mt-8" width="50vw" min-height="40vh" elevation="8">
    <v-button @click="verDetalles">Comprar</v-button>
  </v-card>
</template>