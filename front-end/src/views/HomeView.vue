<script setup lang="ts">
  import { useRouter } from "vue-router";
import NavBar from '@/components/NavBar.vue';
  const router = useRouter();
  const redirectToAdminPage = () => {
    router.push("/admin");
  };
</script>
<template>
  <NavBar></NavBar>
  <router-view @admin-login="redirectToAdminPage"></router-view>
</template>
