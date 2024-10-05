<template>
  <q-page
    class="column q-pa-none bg-grey-2"
  >
    <BackButton to="/auth/welcome"/>
    <div class="column items-center text-center q-mt-lg col">
      <h1 class="text-h4 text-weight-medium">Registrer deg</h1>
    </div>
    Register Page
  </q-page>
</template>

<script setup lang="ts">
import BackButton from 'src/components/BackButton.vue'

console.log('Register Page')
</script>
