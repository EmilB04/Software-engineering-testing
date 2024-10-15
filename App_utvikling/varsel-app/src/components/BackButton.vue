<template>
  <router-link :to="props.to" style="display: inline-block; width: 55px; height: 55px; margin: 69px 0px 0px 44px;">
    <img
      src="/c_icons/backButton.svg"
      style="width: 100%; height: 100%;"
    />
  </router-link>
</template>

<script setup lang="ts">
import { RouterLink } from 'vue-router'

const props = defineProps<{
  to: string
}>()
</script>
