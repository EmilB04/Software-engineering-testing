<template>
  <q-page
    class="column q-pa-none bg-grey-2"
  >
    <BackButton to="/auth/login"/>
    <div class="column items-center text-center q-mt-lg col">
      <TitleText label="Velkommen til oss!<br>Registrer deg"/>

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
      <p class="q-ma-none">Eller fortsett med:</p>
      <LoginGoogleButton
        @click="onSignInWithGoogle()"
        :loading="busy"
        class="q-ma-none q-pa-none"
      />
    </div>
  </q-page>
</template>

<script setup lang="ts">
import { getAuth, GoogleAuthProvider, signInWithPopup } from 'firebase/auth'
import { ref } from 'vue'
import { useRouter } from 'vue-router'

// import LoginBrandButton from 'components/auth/LoginBrandButton.vue'
import LoginGoogleButton from 'components/auth/LoginGoogleButton.vue'
import TitleText from 'components/auth/TitleText.vue'
import MainButton from 'components/MainButton.vue'
import BackButton from 'src/components/BackButton.vue'

const name = ref('')
const email = ref('')
const password = ref('')
const busy = ref(false)
const router = useRouter()

const onSignInWithGoogle = () => {
  busy.value = true
  const provider = new GoogleAuthProvider()
  signInWithPopup(getAuth(), provider)
    .then((result: any) => {
      if (result._tokenResponse.isNewUser) {
        router.push('/auth/register')
      }
      else {
        router.push('/dashboard')
      }
    })
    .catch((error) => {
      console.warn('Error: ', error)
      busy.value = false
    })
}

</script>
