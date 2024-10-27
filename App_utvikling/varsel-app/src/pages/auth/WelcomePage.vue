<template>
  <q-page
    class="column items-center q-pa-none bg-grey-2"
  >
    <div class="column items-center text-center q-mt-lg col">
      <img :src="welcomeImage" style="width: 375px;">
      <h1>Velkommen til<br>VarsEL!</h1>
      <p class="text-body1 text-weight-regular">Din smart assistent for<br>lading av din EL-bil.</p>
      <MainButton
        label="Kom i gang"
        to="/auth/getting-started"
        class="q-mt-md"
      />
    </div>
    <div class="q-pb-lg q-pt-lg full-width bg-white flex justify-center" style="border-radius: 10px;">
      <q-btn
        class="q-pa-md text-body1 text-weight-medium"
        @click="login"
        flat
        no-caps
      >Logg inn</q-btn>
    </div>
  </q-page>
</template>

<script setup lang="ts">
import welcomeImage from 'assets/Illustrasjonsbilde.png'
import { useRouter } from 'vue-router'

import MainButton from 'components/MainButton.vue'

const router = useRouter()

const login = () => {
  router.push('/auth/login')
}

</script>
