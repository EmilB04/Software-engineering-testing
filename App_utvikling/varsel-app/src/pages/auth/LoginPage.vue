<template>
  <q-page
    class="column q-pa-none bg-grey-2"
  >
    <BackButton to="/auth/welcome"/>
    <div class="column items-center text-center q-mt-lg col">
      <TitleText label="Velkommen tilbake!<br>Logg inn"/>

      <div style="display: flex; flex-direction: column; gap: 1vh; width: 270px;;">
        <q-input
          v-model="email"
          label="E-post"
          outlined
          class="q-mt-xl"
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
        <q-btn
          label="Glemt passord?"
          flat
          no-caps
          class="q-mt-md q-pa-none text-body1 text-weight-medium"
          style="text-decoration: underline; align-self: flex-start;"
        />
      </div>

    </div>
    <div class="column items-center text-center">
      <MainButton
        label="Logg inn"
        to="/"
        class="q-mt-md"
      />
    </div>
    <div>
      <LoginGoogleButton
        @click="onSignInWithGoogle()"
        :loading="busy"
      />
    </div>
    <div
      class="q-pb-xl"
      style="display: flex; justify-content: center; gap: 2px;"
    >
      <p class="q-pa-none q-ma-none">Har du ikke konto?</p>
      <router-link to="/auth/register" class="q-pa-none q-ma-none text-weight-medium underline">Registrer deg </router-link>
    </div>
  </q-page>
</template>

<script setup lang="ts">
import { getAuth, GoogleAuthProvider, signInWithPopup } from 'firebase/auth'
import { ref } from 'vue'
import { useRouter, RouterLink } from 'vue-router'

// import LoginBrandButton from 'components/auth/LoginBrandButton.vue'
import LoginGoogleButton from 'components/auth/LoginGoogleButton.vue'
import TitleText from 'components/auth/TitleText.vue'
import MainButton from 'components/MainButton.vue'
import BackButton from 'src/components/BackButton.vue'

const router = useRouter()
const busy = ref(false)
const email = ref('')
const password = ref('')

const onSignInWithGoogle = () => {
  busy.value = true
  const provider = new GoogleAuthProvider()
  signInWithPopup(getAuth(), provider)
    .then((result: any) => {
      if (result._tokenResponse.isNewUser) {
        router.push('/auth/register')
      }
      else {
        router.push('/')
      }
    })
    .catch((error) => {
      console.warn('Error: ', error)
      busy.value = false
    })
}
</script>

<script lang="ts">
export default {
  components: {
    RouterLink,
  },
}
</script>
