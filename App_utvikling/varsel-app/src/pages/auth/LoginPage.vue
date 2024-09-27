<template>
  <q-page
    class="column items-center c-page"
    style="width: clamp(320px, 50vw, 380px);"
  >
    <q-space />
    <div class="full-width flex justify-center">
      logo
    </div>
    <q-space />
    <div>
      <LoginBrandButton
        brand="google"
        @click="onSignInWithGoogle()"
        :loading="busy"
        class="q-mb-md"
      />
    </div>
  </q-page>
</template>

<script setup lang="ts">
import { getAuth, GoogleAuthProvider, signInWithPopup } from 'firebase/auth'
import { ref } from 'vue'
import { useRouter } from 'vue-router'

import LoginBrandButton from 'components/auth/LoginBrandButton.vue'

const router = useRouter()
const busy = ref(false)

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
