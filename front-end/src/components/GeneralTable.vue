<script setup lang="ts">
import { ref, defineProps } from 'vue'
import type { GenericItem, TableHeader } from '@/types/types'

const props = defineProps({
  // Título del componente
  title: {
    type: String,
    default: 'Tabla',
  },
  // Etiqueta para el campo de búsqueda
  searchLabel: {
    type: String,
    default: 'Search',
  },
  // Encabezados de la tabla
  headers: {
    type: Array as () => TableHeader[],
    required: true,
  },
  // Elementos a mostrar en la tabla
  items: {
    type: Array as () => GenericItem[],
    required: true,
  },
  // Estado de carga
  loading: {
    type: Boolean,
    default: false,
  },
  // Elementos por página
  itemsPerPage: {
    type: Number,
    default: 10,
  },
  // Propiedades del pie de página
  footerProps: {
    type: Object,
    default: () => ({
      'items-per-page-options': [5, 10, 15, 20, -1],
      'show-current-page': true,
      'show-first-last-page': true,
    }),
  },
})
const search = ref('')
</script>

<template>
  <v-card :title="props.title" flat>
    <template v-slot:text>
      <v-text-field
        v-model="search"
        :label="props.searchLabel"
        prepend-inner-icon="mdi-magnify"
        variant="outlined"
        hide-details
        single-line
      ></v-text-field>
    </template>

    <v-data-table
      :headers="props.headers"
      :items="props.items"
      :search="search"
      :loading="props.loading"
      :items-per-page="props.itemsPerPage"
      :footer-props="props.footerProps"
    ></v-data-table>
  </v-card>
</template>
