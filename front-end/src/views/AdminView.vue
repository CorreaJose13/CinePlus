<script setup lang="ts">
import SideBar from '@/components/SideBar.vue'
</script>

<template>
  <v-container class="d-flex ma-0 pa-0" max-width="100%">
    <v-row no-gutters>
      <v-col cols="12">
        <v-sheet class="pa-0"> <SideBar /> </v-sheet>
        <v-sheet class="pa-0 ml-2">
          <router-view></router-view>
        </v-sheet>
      </v-col>
    </v-row>
  </v-container>
</template>
