<template>
  <q-btn
    v-bind="attrs"
    padding="1rem 3rem 1rem 1rem"
    class="q-my-sm brand-button-text"
    :flat="opts.flat"
    outline
    :style="{ background: opts.bgcolor, color: opts.textcolor }"
  >
    <q-icon
      :name="opts.icon"
      :style="{ color: opts.textcolor }"
      class="q-ml-md q-mr-md"
    />
    <span>{{ opts.label }}</span>
  </q-btn>
</template>

<script setup lang="ts">
import { computed, useAttrs } from 'vue'

const attrs = useAttrs()
const props = defineProps<{
  brand: 'google'
  label?: string
}>()

const opts = computed(() => {
  let icon, bgcolor, textcolor, label, outline, flat
  switch (props.brand) {
    case 'google':
      icon = 'fab fa-google'
      bgcolor = '#FFFFFF'
      textcolor = '#4B5565'
      label = 'Sign in with Google'
      outline = true
      flat = true
      break
  }
  return {
    icon,
    bgcolor,
    textcolor,
    label: props.label || label,
    outline,
    flat
  }
})
</script>
<style lang="scss" scoped>
  .brand-button-text {
    font-weight: 600;
    font-size: 16px;
    width: 100%;
    max-width: 380px;
    border: 1px solid currentColor;
    border-radius: 8px;
  }
</style>
