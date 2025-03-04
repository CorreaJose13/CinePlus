<script lang="ts" setup>
import type { MenuItem } from '@/types/types';

const props = defineProps<{
  menuItems: MenuItem[];
}>();

const emit = defineEmits<{
  (e: 'menu-action', action: string): void;
}>();

const handleAction = (action: string) => {
  emit('menu-action', action);
};
</script>

<template>
  <v-menu location="end">
    <template #activator="{ props }">
      <v-btn
        icon
        variant="text"
        density="comfortable"
        v-bind="props"
      >
        <v-icon>mdi-dots-vertical</v-icon>
      </v-btn>
    </template>
    <v-list>
      <v-list-item
        v-for="(item, index) in props.menuItems"
        :key="index"
        @click="handleAction(item.action || item.text)"
      >
        <template v-slot:prepend>
          <v-icon :icon="item.icon"></v-icon>
        </template>
        <v-list-item-title>{{ item.text }}</v-list-item-title>
      </v-list-item>
    </v-list>
  </v-menu>
</template>