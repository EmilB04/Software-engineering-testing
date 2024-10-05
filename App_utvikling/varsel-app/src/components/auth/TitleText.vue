<template>
  <h1
    :label="props.label"
    class="text-h4 text-weight-medium q-mt-sm"
    style="color: black;"
  >
    {{ props.label }}
  </h1>
</template>

<script setup lang="ts">

const props = defineProps<{
    label: string
  }>()
</script>
