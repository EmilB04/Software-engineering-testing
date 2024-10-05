<template>
  <q-page
    class="column items-center q-pa-none bg-grey-2"
  >
    <div class="column items-center text-center q-mt-lg col">
      <!-- override this picture to be on the left-->
      <BackButton to="/auth/welcome" />
      <h1 class="text-h4 text-weight-medium">Registrer deg</h1>
    </div>
    Register Page
  </q-page>
</template>

<script setup lang="ts">
import BackButton from 'src/components/BackButton.vue'

console.log('Register Page')
</script>
