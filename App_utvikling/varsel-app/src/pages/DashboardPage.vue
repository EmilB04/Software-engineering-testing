<template>
  <!-- TODO:
   - Make CAR IMG centered at all times
   -->
  <q-page class="q-pa-none column bg-grey-2" style="font-size: 1rem; font-family: Arial, Helvetica, sans-serif;">
    <div class="q-mx-md">
      <section class="q-mt-lg" id="userHeader"> <!-- Bruker-->
        <article class="q-ma-none q-pa-none">
          <h1 class="text-left q-ma-none" style="font-size: 20px;">Hei {{firstName}}!</h1>
          <h1 class="text-left" style="font-size: 18px;">Det lønner seg å lade<br> senere i kveld.</h1>
        </article>
        <q-btn
          class="q-ma-none q-pa-none"
          style="align-self: baseline;"
          flat
          to="/profile"
        >
          <q-img style="height: 60px; width: 60px;" src="/src/assets/c_icons/profile.svg"/>
        </q-btn>
      </section>
      <section> <!-- Ørepris-->
        <h2 class="text-left q-ma-none">2.34 øre/kWh</h2>
        <h3 class="text-left q-my-sm">Nåværende strømpris</h3>
      </section>
      <hr class="bg-black">
      <section> <!-- Tidligere og fremtidlig priser-->
        <article id="p_f_prices">
          <div :class="checkTargetPriceDiff(previousKwhValue) ? 'text-negative' : 'text-positive'">
            <h3 class="text-center">{{ previousKwhValue }}</h3>
            <h3 class="text-center">øre/kWh</h3>
          </div>
          <div :class="checkTargetPriceDiff(futureKwhValue) ? 'text-negative' : 'text-positive'">
            <h3 class="text-center">{{ futureKwhValue }}</h3>
            <h3 class="text-center">øre/kWh</h3>
          </div>
        </article>
        <hr class="verticalHR">
        <article id="p_f_text">
          <h3>Tidligere</h3>
          <h3>Fremtidige</h3>
        </article>
      </section>
      <hr style="background-color: black;">
      <section id="carInfo"> <!-- Bil informasjon-->
        <h2 class="q-mb-none">Volkswagen ID.3</h2>
        <article class="row">
          <h3>Batterinivå: </h3>
          <h3>67%</h3>
        </article>
        <article class="row q-mt-md">
          <h4>Lokasjon: </h4>
          <h4>Hjemme</h4>
        </article>
        <article class="row">
          <h4>Status: </h4>
          <h4>Lader ikke</h4>
        </article>
        <div style="display: flex; flex-direction: column;">
          <q-img>
            <img
              src="/src/assets/c_icons/VW_ID3.svg"
              alt="car"
              style="height: auto; max-width: 350px;"
            >
          </q-img>
          <q-btn
            label="Se alle kjøretøy"
            flat
            no-caps
            style="color: #000; text-decoration: underline;"
          >
          </q-btn>
        </div>
      </section>
      <nav>
        <ul>
          <li>
            <q-btn
              class="q-pa-sm"
              flat
              no-caps
              id="active-nav"
              @click="() => {}"
            ><img src="/src/assets/c_icons/home.svg" alt="Hjemknapp"></q-btn>
          </li>
          <li>
            <q-btn
              class="q-pa-sm"
              flat
              no-caps
              @click="() => {}"
            >
              <img src="/src/assets/c_icons/history.svg" alt="Historikk">
            </q-btn>
          </li>
          <li>
            <q-btn
              class="q-pa-sm"
              flat
              no-caps
              @click="() => {}"
            >
              <img src="/src/assets/c_icons/chart.svg" alt="Diagram">
            </q-btn>
          </li>
          <li>
            <q-btn
              class="q-pa-sm"
              flat
              no-caps
              @click="() => {}"
            >
              <img src="/src/assets/c_icons/settings.svg" alt="Innstillinger">
            </q-btn>
          </li>
        </ul>
      </nav>
    </div>
  </q-page>
</template>

<style>
  h1:last-child {
    margin: 0 0 30px 0;
    font-weight: 500;
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
  #p_f_prices h3{
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
  #p_f_prices div {
    margin: 0 auto;
  }
  #p_f_prices>div h3:last-child {
    margin-top: 0;
  }
  #p_f_prices>div h3:first-child {
    margin-bottom: 0;
  }
  #carInfo h3, h4 {
    margin: 0 2px 0 0;
  }
  nav {
    position: fixed;
    bottom: 0;
    left: 0;
    width: 100%;
    border-radius: 20px 20px 5px 5px;
    background: #F2F2F2;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25) inset;
    height: 74px;
    flex-shrink: 0;
  }
  nav>ul {
    display: flex;
    justify-content: space-around;
    align-items: center;
    flex-wrap: nowrap;
    list-style-type: none;
    padding: 0;
  }
  ul img {
    filter: invert(0) sepia(0) saturate(0) hue-rotate(0deg) brightness(0) contrast(1); /* black */
  }
  #active-nav {
    filter: invert(66%) sepia(82%) saturate(703%) hue-rotate(117deg) brightness(97%) contrast(92%); /* #32E4C3 */
  }
  .verticalHR{
    width: 54.5px;
    height: 0px;
    transform: rotate(-90deg);
    position: absolute;
    top: 250px;
    left: 50%;
    transform: translate(-50%, -50%) rotate(-90deg);
    padding: 0;
    background-color: black;
  }
</style>

<script setup lang="ts">
import { getAuth } from 'firebase/auth'
import { ref } from 'vue'

const auth = getAuth()
const firstName = ref(auth.currentUser?.displayName?.split(' ')[0])
// const lastName = ref(auth.currentUser?.displayName?.split(' ').slice(1).join(' '))

const targetPrice = ref(3.60)
const previousKwhValue = ref(3.64)
const futureKwhValue = ref(2.12)

// previousKwhValue.value = 4.32 // Eksempel

// Change color of kWh based on value
const checkTargetPriceDiff = (kwhValue: number) => {
  return kwhValue > targetPrice.value
}
</script>
