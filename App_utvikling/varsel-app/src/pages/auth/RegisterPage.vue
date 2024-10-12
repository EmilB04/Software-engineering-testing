<template>
  <q-page
    class="column q-pa-none bg-grey-2"
  >
    <BackButton to="/auth/login"/>
    <div class="column items-center text-center q-mt-lg col">
      <TitleText label="Velkommen!<br>Registrer deg"/>

      <div style="display: flex; flex-direction: column; gap: 1vh; width: 270px;">
        <q-input
          v-model="name"
          label="Navn"
          outlined
          class="q-mt-xl"
          style="width: 100%;"
        />
        <q-input
          v-model="email"
          label="E-post"
          outlined
          class="q-mt-md"
          style="width: 100%;"
        />
        <q-input
          v-model="password"
          label="Passord"
          type="password"
          outlined
          class="q-mt-md"
          style="width: 100%;"
        />
        <q-input
          v-model="password"
          label="Bekreft passord"
          type="password"
          outlined
          class="q-mt-md"
          style="width: 100%;"
        />
      </div>

    </div>
    <div class="column items-center text-center">
      <MainButton
        label="Registrer deg"
        to="/"
        class="q-mt-md"
      />
    </div>
    <div class="q-mt-xl column items-center text-center">
      <p>Eller fortsett med:</p>
      <!-- Google innlogging-->
    </div>
  </q-page>
</template>

<script setup lang="ts">
import { ref } from 'vue'

// import LoginGoogleButton from 'src/components/auth/LoginGoogleButton.vue'
import TitleText from 'src/components/auth/TitleText.vue'
import BackButton from 'src/components/BackButton.vue'
import MainButton from 'src/components/MainButton.vue'

const name = ref('')
const email = ref('')
const password = ref('')

</script>
