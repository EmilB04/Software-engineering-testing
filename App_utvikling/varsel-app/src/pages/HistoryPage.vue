<template>
  <q-page class="q-pa-none column bg-grey-2">
    <section class="row" style="justify-content: space-between;">
      <BackButton to="/dashboard" />
    </section>
    <header class="column q-mt-none">
      <h1 class="self-center q-mb-lg">Ladehistorikk</h1>
    </header>

    <main class="q-mx-md">
      <section>
        <h2>August 2024</h2>
        <q-card class="q-mb-md" >
          <q-card-section class="row justify-between items-center" >
            <article class="column">
              <div class="row items-center">
                <img :src="CalendarIconSVG">
                <h3 class="q-my-none">Lørdag 17. aug 2024 09:04</h3>
              </div>
              <div class="row items-center">
                <img :src="PlugIconSVG">
                <h4 class="q-my-none">Ladet til: 80% </h4>
              </div>
              <div class="row items-center">
                <img :src="ChargedIconSVG">
                <h5 class="q-my-none">20.7 kWh</h5>
              </div>
            </article>
            <q-item-label>
              <h2>kr. 111.26</h2>
            </q-item-label>
          </q-card-section>
        </q-card>

        <q-card class="q-mb-md" >
          <q-card-section class="row justify-between items-center" >
            <article class="column">
              <div class="row items-center">
                <img :src="CalendarIconSVG">
                <h3 class="q-my-none">Lørdag 17. aug 2024 09:04</h3>
              </div>
              <div class="row items-center">
                <img :src="PlugIconSVG">
                <h4 class="q-my-none">Ladet til: 80% </h4>
              </div>
              <div class="row items-center">
                <img :src="ChargedIconSVG">
                <h5 class="q-my-none">20.7 kWh</h5>
              </div>
            </article>
            <q-item-label>
              <h2>kr. 111.26</h2>
            </q-item-label>
          </q-card-section>
        </q-card>

        <q-card class="q-mb-md" >
          <q-card-section class="row justify-between items-center" >
            <article class="column">
              <div class="row items-center">
                <img :src="CalendarIconSVG">
                <h3 class="q-my-none">Lørdag 17. aug 2024 09:04</h3>
              </div>
              <div class="row items-center">
                <img :src="PlugIconSVG">
                <h4 class="q-my-none">Ladet til: 80% </h4>
              </div>
              <div class="row items-center">
                <img :src="ChargedIconSVG">
                <h5 class="q-my-none">20.7 kWh</h5>
              </div>
            </article>
            <q-item-label>
              <h2>kr. 111.26</h2>
            </q-item-label>
          </q-card-section>
        </q-card>

        <q-card class="q-mb-md" >
          <q-card-section class="row justify-between items-center" >
            <article class="column">
              <div class="row items-center">
                <img :src="CalendarIconSVG">
                <h3 class="q-my-none">Lørdag 17. aug 2024 09:04</h3>
              </div>
              <div class="row items-center">
                <img :src="PlugIconSVG">
                <h4 class="q-my-none">Ladet til: 80% </h4>
              </div>
              <div class="row items-center">
                <img :src="ChargedIconSVG">
                <h5 class="q-my-none">20.7 kWh</h5>
              </div>
            </article>
            <q-item-label>
              <h2>kr. 111.26</h2>
            </q-item-label>
          </q-card-section>
        </q-card>
      </section>
      <section>
        <h2>Juli 2024</h2>
        <q-card class="q-mb-md" >
          <q-card-section class="row justify-between items-center" >
            <article class="column">
              <div class="row items-center">
                <img :src="CalendarIconSVG">
                <h3 class="q-my-none">Lørdag 17. aug 2024 09:04</h3>
              </div>
              <div class="row items-center">
                <img :src="PlugIconSVG">
                <h4 class="q-my-none">Ladet til: 80% </h4>
              </div>
              <div class="row items-center">
                <img :src="ChargedIconSVG">
                <h5 class="q-my-none">20.7 kWh</h5>
              </div>
            </article>
            <q-item-label>
              <h2>kr. 111.26</h2>
            </q-item-label>
          </q-card-section>
        </q-card>
      </section>
    </main>

  </q-page>
</template>

<script setup>
import BackButton from 'src/components/BackButton.vue'

import CalendarIconSVG from 'src/assets/c_icons/calendar.svg'
import ChargedIconSVG from 'src/assets/c_icons/charged.svg'
import PlugIconSVG from 'src/assets/c_icons/plug.svg'
</script>

<style>
div > h2 {
  font-size: 20px;
  font-weight: bolder;
  line-height: normal;
  background-color: rgb(228, 228, 228);
  padding: 10px;
  border-radius: 3px;
  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.25);
}
h3, h4, h5 {
  font-size: 16px;
  font-weight: normal;
  line-height: normal;
}
article {
  display: flex;
  gap: 2vh;
  flex-direction: column;
}

</style>
