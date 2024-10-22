<template>
  <q-page class="q-pa-none column bg-grey-2">
    <section class="row" style="justify-content: space-between;">
      <BackButton to="/dashboard" />
      <q-btn
        @click="logOut"
        no-caps
        style="height: 50px; margin: 69px 20px 0 0;"
        class="bg-black text-white self-center q-px-lg"
      >
        Logg ut
      </q-btn>
    </section>
    <header class="column q-mt-md">
      <h1 class="self-center">Min Side</h1>
      <h2 class="q-ml-md q-ma-none">{{firstName}} {{lastName}}</h2>
      <h3 class="q-ml-md q-mt-sm q-ma-none">{{ email }}</h3>
    </header>
  </q-page>
</template>

<script setup>
import { getAuth, signOut } from 'firebase/auth'
import { ref } from 'vue'
import { useRouter } from 'vue-router'

import BackButton from 'src/components/BackButton.vue'

const auth = getAuth()
const router = useRouter()

const logOut = () => {
  signOut(auth)
  router.push('/auth/welcome')
}
const firstName = ref(auth.currentUser?.displayName?.split(' ')[0])
const lastName = ref(auth.currentUser?.displayName?.split(' ').slice(1).join(' '))
const email = ref(auth.currentUser?.email)
</script>
