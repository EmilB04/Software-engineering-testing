<template>
  <img
    src="/c_icons/backButton.svg"
    :to="props.to"
    style="width: 55px; height: 55px; margin: 69px 0px 0px 44px;"
  />
</template>

<script setup lang="ts">

const props = defineProps<{
    to: string
  }>()
</script>
