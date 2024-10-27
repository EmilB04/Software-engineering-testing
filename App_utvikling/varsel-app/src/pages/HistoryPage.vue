<template>
  <q-page class="q-pa-none column bg-grey-2">
    <section class="row" style="justify-content: space-between;">
      <BackButton to="/dashboard" />
    </section>
    <header class="column q-mt-none">
      <h1 class="self-center q-mb-lg">Ladehistorikk</h1>
    </header>
    <main>
      <section class="column q-mx-md">
        <h2>August 2024</h2>
        <div class="row" style="justify-content: space-between;">
          <div class="column">
            <article class="row">
              <img src="/src/assets/c_icons/calendar.svg" alt="Kalender">
              <h3>Lørdag 17. august 2024, 09:04</h3>
            </article>
            <article class="row">
              <img src="/src/assets/c_icons/plug.svg" alt="Lade">
              <h3>Ladet til 80%</h3>
            </article>
            <article class="row">
              <img src="/src/assets/c_icons/charged.svg" alt="Pris">
              <h3>20 kWh</h3>
            </article>
          </div>
          <h3 class="self-center bg-grey-4 q-pa-sm q-ma-none">kr. 111,26</h3>
        </div>
      </section>
      <hr class="self-center q-my-sm" style="width: 90%;">
      <section class="column q-mx-md" style="width: 90%;">
        <div class="row" style="justify-content: space-between;">
          <div class="column">
            <article class="row">
              <img src="/src/assets/c_icons/calendar.svg" alt="Kalender">
              <h3>Onsdag 14. august 2024, 08:50</h3>
            </article>
            <article class="row">
              <img src="/src/assets/c_icons/plug.svg" alt="Lade">
              <h3>20 kWh</h3>
            </article>
            <article class="row">
              <img src="/src/assets/c_icons/charged.svg" alt="Pris">
              <h3>15/kWh</h3>
            </article>
          </div>
          <h3 class="self-center bg-grey-4 q-pa-sm q-ma-none">kr. 81,26</h3>
        </div>
      </section>
      <section class="column q-mx-md">
        <h2>Juli 2024</h2>
        <div class="row" style="justify-content: space-between;">
          <div class="column">
            <article class="row">
              <img src="/src/assets/c_icons/calendar.svg" alt="Kalender">
              <h3>Lørdag 17. august 2024, 09:04</h3>
            </article>
            <article class="row">
              <img src="/src/assets/c_icons/plug.svg" alt="Lade">
              <h3>Ladet til 85%</h3>
            </article>
            <article class="row">
              <img src="/src/assets/c_icons/charged.svg" alt="Pris">
              <h3>27 kWh</h3>
            </article>
          </div>
          <h3 class="self-center bg-grey-4 q-pa-sm q-ma-none">kr. 181,26</h3>
        </div>
      </section>
    </main>

  </q-page>
</template>

<script setup>
import BackButton from 'src/components/BackButton.vue'

</script>
