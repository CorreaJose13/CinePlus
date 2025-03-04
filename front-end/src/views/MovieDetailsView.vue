<script setup lang="ts">
import MovieInfo from '@/components/MovieInfo.vue'
</script>

<template>
  <v-container>
    <v-row no-gutters justify="center" class="d-flex">
      <v-col>
        <v-sheet class="pa-2 ma-2"> <MovieInfo /> </v-sheet>
      </v-col>
      <v-col cols="8">
        <v-sheet class="pa-2 ma-2">
          <router-view></router-view>
        </v-sheet>
      </v-col>
    </v-row>
  </v-container>
</template>
