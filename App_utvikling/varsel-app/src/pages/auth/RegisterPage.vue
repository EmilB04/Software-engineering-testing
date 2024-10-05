<template>
  <q-page
    class="column q-pa-none bg-grey-2"
  >
    <BackButton to="/auth/welcome"/>
    <div class="column items-center text-center q-mt-lg col">
      <TitleText label="Registrer deg"/>
    </div>
    Register Page
  </q-page>
</template>

<script setup lang="ts">
import TitleText from 'src/components/auth/TitleText.vue'
import BackButton from 'src/components/BackButton.vue'

console.log('Register Page')
</script>
