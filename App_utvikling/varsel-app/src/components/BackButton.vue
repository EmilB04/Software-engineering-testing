<template>
  <img
    src="public/c_icons/Keyboard arrow left.svg"
    :to="props.to"
    style="width: 55px; height: 55px;"
    class="q-mt-xl"
  />
</template>

<script setup lang="ts">

const props = defineProps<{
    to: string
  }>()
</script>
