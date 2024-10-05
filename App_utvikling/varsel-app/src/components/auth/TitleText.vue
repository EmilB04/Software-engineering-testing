<template>
  <h1
    :label="props.label"
    class="text-h4 text-weight-medium"
  />
</template>

<script setup lang="ts">

const props = defineProps<{
    label: string
    to: string
  }>()
</script>
