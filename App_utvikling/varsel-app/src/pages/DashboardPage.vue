<template>
  <q-page class="q-pa-none column bg-grey-2" style="font-size: 1rem; font-family: Arial, Helvetica, sans-serif;">
    <div class="q-mx-md">
      <section class="q-mt-lg" id="userHeader"> <!-- Bruker-->
        <article class="q-ma-none q-pa-none">
          <h1 class="text-left">Hei Bruker!</h1>
          <h1 class="text-left">Det lønner seg å lade<br> senere i kveld</h1>
        </article>
        <figure class="q-ma-none">
          <q-img style="height: 60px; width: 60px;" src="/public/c_icons/profile.svg" />
        </figure>
      </section>
      <section> <!-- Ørepris-->
        <h2 class="text-left">2,34 øre/kWh</h2>
        <h3 class="text-left q-my-sm">Nåværende strømpris</h3>
      </section>
      <hr style="background-color: black;">
      <section> <!-- ToDo! Add flex row here hr is to be vertical-->
        <article id="p_f_prices">
          <h3 id="previous_kWh" class="text-center">3,64<br>øre/kWh</h3>
          <h3 id="future_kWh" class="text-center">2,34<br>øre/kWh</h3>
        </article>
        <hr style="width: 54.5px; height: 0px; transform: rotate(-90deg); position: absolute; top: 250px; left: 50%; transform: translate(-50%, -50%) rotate(-90deg); padding: 0; background-color: black">
        <article id="p_f_text">
          <h3>Tidligere</h3>
          <h3>Fremtidige</h3>
        </article>
      </section>
      <div style="margin-top: 50px;">
        Dashboard Page
        <q-btn @click="logOut">
          Log Out
        </q-btn>
      </div>
    </div>
  </q-page>
</template>

<style>
  h1 {
    color: #000;
    font-size: 18px;
    font-style: normal;
    font-weight: 600;
    line-height: 22px;
    margin: 0
  }
  h1:last-child {
    margin: 0 0 30px 0;
    font-weight: 500;
  }
  h2 {
    color: #000;
    font-size: 26px;
    font-style: normal;
    font-weight: 600;
    line-height: 22px;
    margin: 0;
  }
  h3 {
    color: #000;
    font-size: 14px;
    font-style: normal;
    font-weight: 500;
    line-height: normal;
  }
  #userHeader {
    display: flex;
    justify-content:space-between;
    flex-wrap: nowrap;
    margin-top: 70px;
  }
  #p_f_prices { /* previous and future prices*/
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    flex-wrap: nowrap;
  }
  #p_f_prices>h3{
    margin-inline: auto;
    font-size: 1rem;
  }
  #p_f_text {
    display: flex;
    justify-content: space-around;
    align-items: center;
    flex-wrap: nowrap;
    border-radius: 5px 0px 0px 5px;
    background: #CBC9C9;
  }
</style>

<script setup lang="ts">
import { getAuth, signOut } from 'firebase/auth'

const logOut = () => {
  const auth = getAuth()
  signOut(auth)
}

// Change color of previous kWh based on value
const previousKWh = document.getElementById('previous_kWh')

if (previousKWh) {
  if (parseFloat(previousKWh.innerText) > 3.60) {
    previousKWh.style.color = 'red'
  }
  else {
    previousKWh.style.color = 'green'
  }
}

// Change color of future kWh based on value
const futureKWh = document.getElementById('future_kWh')

if (futureKWh) {
  if (parseFloat(futureKWh.innerText) > 2.30) {
    futureKWh.style.color = 'red'
  }
  else {
    futureKWh.style.color = 'green'
  }
}
</script>
