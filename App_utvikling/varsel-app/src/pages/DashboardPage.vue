<template>
  <q-page class="q-pa-none column bg-grey-2">
    <div class="q-mx-md text-center q-mt-lg"> <!-- Bruker-->
      <h1 class="text-h5 text-left">Hei Bruker!<br>Det lønner seg å lade<br>senere i kveld.</h1>
    </div>
    <div class="q-mx-md"> <!-- Ørepris-->
      <h2 class="text-left">2,34 øre/kWh</h2>
      <h3 class="text-left">Nåværende strømpris</h3>
    </div>
    <div>
      
    </div>
    Dashboard Page
    <q-btn @click="logOut">
      Log Out
    </q-btn>
  </q-page>
</template>

<style>
  h1 {
    color: #000;
    font-family: Arial, Helvetica, sans-serif;
    font-size: 18px;
    font-style: normal;
    font-weight: 600;
    line-height: 22px;
    margin-top: 70px;
  }
  h2 {
    color: #000;
    font-family: Arial, Helvetica, sans-serif;
    font-size: 26px;
    font-style: normal;
    font-weight: 600;
    line-height: 22px; /* 84.615% */
    margin: 0;
  }
  h3 {
    color: #000;
    font-family: Arial, Helvetica, sans-serif;
    font-size: 14px;
    font-style: normal;
    font-weight: 500;
    line-height: normal;
  }
</style>

<script setup lang="ts">
import { getAuth, signOut } from 'firebase/auth'

const logOut = () => {
  const auth = getAuth()
  signOut(auth)
}
</script>
