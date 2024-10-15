<template>
  <q-page class="column q-pa-none bg-grey-2">
    <div class="column q-ml-md text-center q-mt-lg col">
      <h1 class="text-h5 text-left">Hei Bruker!<br>Det lønner seg å lade<br>senere i kveld.</h1>
    </div>
    <div>
      
    </div>
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
