<script setup lang="ts">
import SeatLayout from './SeatLayout.vue';

</script>

<template>
  <SeatLayout
      :number-columns="10"
      :number-rows="10"
      :available-seats="['A1', 'A2', 'A3', 'A4', 'A5', 'A6', 'A7', 'A8', 'A9', 'A10']"
      :max-seat-to-select="3"
    />
</template>