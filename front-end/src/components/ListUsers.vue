<script setup lang="ts">
import { ref, onMounted } from 'vue';
import GeneralTable from '@/components/GeneralTable.vue';
import type { TableHeader } from '@/types/types';

interface Product {
  id: number;
  name: string;
  category: string;
  price: number;
  stock: number;
  status: string;
  lastUpdated: string;
}

const loading = ref<boolean>(true);

const headers = ref<TableHeader[]>([
  { title: 'ID', key: 'id', sortable: true },
  { title: 'Producto', key: 'name', sortable: true },
  { title: 'Categoría', key: 'category', sortable: true },
  { title: 'Precio', key: 'price', sortable: true, align: 'end' },
  { title: 'Stock', key: 'stock', sortable: true, align: 'end' },
  { title: 'Estado', key: 'status', sortable: true },
  { title: 'Última Actualización', key: 'lastUpdated', sortable: true },
]);

const products = ref<Product[]>([]);

onMounted(async () => {
  try {
    // Simular llamada a API
    await new Promise(resolve => setTimeout(resolve, 1200));
    
    // Datos de ejemplo
    products.value = [
      {
        id: 1001,
        name: 'Laptop HP ProBook',
        category: 'Electrónicos',
        price: 899.99,
        stock: 15,
        status: 'Disponible',
        lastUpdated: '2025-02-28',
      },
      {
        id: 1002,
        name: 'Monitor Samsung 27"',
        category: 'Periféricos',
        price: 249.99,
        stock: 23,
        status: 'Disponible',
        lastUpdated: '2025-02-15',
      },
      {
        id: 1003,
        name: 'Teclado Mecánico Logitech',
        category: 'Periféricos',
        price: 119.99,
        stock: 8,
        status: 'Pocas unidades',
        lastUpdated: '2025-03-01',
      },
      {
        id: 1004,
        name: 'Mouse Inalámbrico Microsoft',
        category: 'Periféricos',
        price: 45.99,
        stock: 32,
        status: 'Disponible',
        lastUpdated: '2025-02-20',
      },
      {
        id: 1005,
        name: 'Disco Duro SSD 1TB',
        category: 'Almacenamiento',
        price: 129.99,
        stock: 0,
        status: 'Agotado',
        lastUpdated: '2025-01-30',
      },
      {
        id: 1006,
        name: 'Memoria RAM 16GB DDR4',
        category: 'Componentes',
        price: 89.99,
        stock: 14,
        status: 'Disponible',
        lastUpdated: '2025-02-10',
      },
      {
        id: 1007,
        name: 'Tarjeta Gráfica NVIDIA RTX 4060',
        category: 'Componentes',
        price: 599.99,
        stock: 5,
        status: 'Pocas unidades',
        lastUpdated: '2025-02-25',
      },
    ];
  } catch (error) {
    console.error('Error al cargar productos:', error);
  } finally {
    loading.value = false;
  }
});
</script>
<template>
  <div>
    <GeneralTable
      title="Inventario de Productos"
      search-label="Buscar producto"
      :headers="headers"
      :items="products"
      :loading="loading"
      :items-per-page="5"
    />
  </div>
</template>