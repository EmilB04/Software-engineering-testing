<template>
  <q-btn
    :label="props.label"
    :to="props.to"
    class="q-pa-md"
    color="primary"
    text-color="black"
    style="border-radius: 15px;"
  />
</template>

<script setup lang="ts">

const props = defineProps<{
    label: string
    to: string
  }>()
</script>
