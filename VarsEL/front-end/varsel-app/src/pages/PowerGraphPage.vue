<template>
  <q-page class="q-pa-none column bg-grey-2">
    <BackButton to="/dashboard" />
    <header class="column q-mt-none text-center">
      <h1>Strømpris</h1>
    </header>
    <main class="text-center">
      <h2>{{ currentKwhPrice }} kWh</h2>

      <!-- Chart Section -->
      <section class="q-pa-sm">
        <div class="chart-container">
          <Line :data="chartData" :options="chartOptions" />
        </div>
        <q-separator />
        <q-btn-group spread>
          <q-btn
            flat
            :class="buttonClass('year')"
            @click="() => select('year')"
          >
            År
          </q-btn>
          <q-btn
            flat
            :class="buttonClass('month')"
            @click="() => select('month')"
          >
            Måned
          </q-btn>
          <q-btn
            flat
            :class="buttonClass('today')"
            @click="() => select('today')"
          >
            I dag
          </q-btn>
          <q-btn
            flat
            :class="buttonClass('tomorrow')"
            @click="() => select('tomorrow')"
          >
            I morgen
          </q-btn>
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

// Registering all chart components
Chart.register(...registerables)

// Define types for the chart data structure
type ChartData = {
  labels: string[]
  datasets: {
    label: string
    backgroundColor: string
    borderColor: string
    data: number[]
    fill: boolean
  }[]
}

// Reactive references for dynamic data
const currentKwhPrice = ref(1.25) // To be replaced with API data
const selected = ref<'year' | 'month' | 'today' | 'tomorrow'>('today')

// Default chart data (empty initially)
const chartData = ref<ChartData>({
  labels: [],
  datasets: [
    {
      label: 'Strømpris (øre/kWh)',
      backgroundColor: 'rgba(75, 192, 152, 0.1)',
      borderColor: '#32E4C3',
      data: [],
      fill: true,
    },
  ],
})

// Chart options configuration
const chartOptions = ref({
  responsive: true,
  maintainAspectRatio: false,
  scales: {
    x: {
      title: {
        display: true,
        text: 'Tid',
      },
    },
    y: {
      title: {
        display: false,
        text: 'Strømpris (øre/kWh)',
      },
    },
  },
})

// Data for each period
const periodData = {
  year: {
    labels: ['Jan', 'Feb', 'Mar', 'Apr', 'Mai', 'Jun', 'Jul', 'Aug', 'Sep', 'Okt', 'Nov', 'Des'],
    data: [300, 320, 350, 370, 340, 360, 380, 400, 370, 360, 350, 340],
  },
  month: {
    labels: Array.from({ length: 31 }, (_, i) => `${i + 1}`),
    data: Array.from({ length: 31 }, () => Math.floor(Math.random() * 100)),
  },
  today: {
    labels: Array.from({ length: 24 }, (_, i) => `${i}:00`),
    data: Array.from({ length: 24 }, () => Math.floor(Math.random() * 10)),
  },
  tomorrow: {
    labels: Array.from({ length: 24 }, (_, i) => `${i}:00`),
    data: Array.from({ length: 24 }, () => Math.floor(Math.random() * 10)),
  },
}

// Function to select the period and update chart data
function select(period: 'year' | 'month' | 'today' | 'tomorrow') {
  selected.value = period
  const periodInfo = periodData[period]
  chartData.value.labels = periodInfo.labels
  chartData.value.datasets[0].data = periodInfo.data
}

// Set initial data (for "today")
select('today')

// Function to determine the class of the button based on the selected period
const buttonClass = (period: 'year' | 'month' | 'today' | 'tomorrow') => {
  return selected.value === period ? 'selected-button' : ''
}
</script>

<style scoped>
.chart-container {
  height: 400px;
  width: 100%;
  margin: 0 auto;
}

.q-btn {
  font-weight: bold;
}
.selected-button {
  background-color: #43E4C3;
  color: white;
}
</style>
