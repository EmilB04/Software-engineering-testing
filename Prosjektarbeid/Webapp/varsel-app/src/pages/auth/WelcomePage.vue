<template>
  <q-page
    class="column items-center q-pa-none bg-grey-2"
  >
    <div class="column items-center text-center q-mt-xl col">
      <img :src="welcomeImage" style="width: 300px;">
      <h1 class="text-h4">Velkommen til<br>VarsEL</h1>
      <p class="text-body2 q-mb-xl">Din smart assistent for lading av din EL-bil.</p>
      <MainButton
        label="Kom i"
        to="/auth/getting-started"
      />
    </div>
    <div class="q-pb-xl q-pt-lg full-width bg-white flex justify-center">
      <q-btn @click="login" flat>Logg inn</q-btn>
    </div>
  </q-page>
</template>

<script setup lang="ts">
import welcomeImage from 'assets/illustrasjonsbilde.png'
import { useRouter } from 'vue-router'

import MainButton from 'components/MainButton.vue'

const router = useRouter()

const login = () => {
  router.push('/auth/login')
}

</script>
