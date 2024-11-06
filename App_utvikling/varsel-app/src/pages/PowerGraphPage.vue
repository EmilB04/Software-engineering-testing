<template>
  <q-page class="q-pa-none column bg-grey-2">
    <BackButton to="/dashboard" />
    <header class="column q-mt-none text-center">
      <h1>Strømpris</h1>
    </header>
    <main class="text-center">
      <h2>{{ currentKwhPrice }} kr/kWh</h2>

      <!-- Chart Section -->
      <section class="q-pa-sm">
        <div class="chart-container">
          <Line :data="chartData" :options="chartOptions" />
        </div>
        <q-separator />
        <q-btn-group spread>
          <q-btn
            flat
            :outline="selected !== 'year'"
            @click="select('year')"
            class="q-px-sm q-py-md"
          >År</q-btn>
          <q-btn
            flat
            :outline="selected !== 'month'"
            @click="select('month')"
          >Måned</q-btn>
          <q-btn
            flat
            :outline="selected !== 'today'"
            @click="select('today')"
          >I dag</q-btn>
          <q-btn
            flat
            :outline="selected !== 'tomorrow'"
            @click="select('tomorrow')"
          >I morgen</q-btn>
        </q-btn-group>
      </section>
    </main>
  </q-page>
</template>

<script setup lang="ts">
import { Chart, registerables } from 'chart.js'
import { ref } from 'vue'
import { Line } from 'vue-chartjs'

import BackButton from 'components/BackButton.vue'

Chart.register(...registerables)

const currentKwhPrice = 1.25

const selected = ref('today')

const chartData = ref({
  labels: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],
  datasets: [
    {
      label: 'Strømpris',
      backgroundColor: '#4CAF50',
      borderColor: '#4CAF50',
      data: [350, 320, 370, 350, 320, 350, 280, 300, 370, 350, 320, 350], // Hent data fra API, bytt ut med variabler
      fill: true,
    },
  ],
})

const chartOptions = ref({
  responsive: true,
  maintainAspectRatio: false,
  scales: {
    x: {
      title: {
        display: true,
        text: 'Periode', // Title for X-axis
      },
    },
    y: {
      title: {
        display: true,
        text: 'øre/kWh', // Title for Y-axis
      },
    },
  },
})

function select(period: string) {
  selected.value = period
  // Add logic here to update `chartData` based on `period`
}
</script>

<style scoped>
.chart-container {
  height: 300px; /* Set the maximum height for the chart */
  margin: 0 auto;
}

.q-btn {
  font-weight: bold;
}
</style>
