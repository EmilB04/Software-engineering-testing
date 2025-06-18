<template>
  <q-btn
    :label="props.label"
    :to="props.to"
    class="q-pa-md"
    color="primary"
    text-color="black"
    style="border-radius: 20px; padding: 20px 36px; font-size: 16px; box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);"
    no-caps
  />
</template>

<script setup lang="ts">

const props = defineProps<{
    label: string
    to: string
  }>()
</script>
