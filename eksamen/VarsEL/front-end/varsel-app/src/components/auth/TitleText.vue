<template>
  <h1
    class="text-h1 text-weight-medium q-mt-sm"
    style="color: black;"
    v-html="props.label"
  >
  </h1>
</template>

<script setup lang="ts">

const props = defineProps<{
    label: string
  }>()
</script>
