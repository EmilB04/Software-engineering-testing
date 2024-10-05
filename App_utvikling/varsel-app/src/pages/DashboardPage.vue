<template>
  <q-page>
    Dashboard Page
    <q-btn @click="logOut">
      Log Out
    </q-btn>
  </q-page>
</template>

<script setup lang="ts">
import { getAuth, signOut } from 'firebase/auth'

const logOut = () => {
  const auth = getAuth()
  signOut(auth)
}
</script>
