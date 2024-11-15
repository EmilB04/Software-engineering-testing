<template>
  <q-page class="column q-pa-none bg-grey-2">
    <q-stepper
      class="bg-grey-2"
      v-model="step"
      ref="stepper"
      title="Getting Started"
      color="primary"
      animated
      flat
    >
      <q-step
        :name="1"
        :done="step > 1"
        title="Velg strømleverandør"
      >
        <q-btn
          :to="'/auth/welcome'"
          class="q-mt-lg"
          title="Tilbake"
          flat
        >
          <img :src="backButtonImg" alt="Tilbake">
        </q-btn>
        <h1 class="text-center q-mx-auto">Velg din <br> strømleverandør</h1>
        <q-linear-progress
          rounded
          size="20px"
          :value="progress"
          color="primary"
          class="q-mt-lg q-mx-auto"
          style="width: 50%; border-radius: 20px;"
          :style="{ background: 'linear-gradient(to right, #fff, #fff)' }"
        />

        <main>
          <h3 class="text-center q-mt-lg q-mx-lg">Velg din nåværende strømleverandør <br> for å få den mest nøyaktige oversikten <br> over strømpriser.</h3>

          <!-- Dropdown for strømleverandør -->
          <div class="text-center q-mt-xl">
            <q-select
              outlined
              v-model="selectedSupplier"
              :options="suppliers"
              label="Velg strømleverandør"
              class="q-pa-md"
              style="max-width: 400px; margin: 0 auto;"
            />
          </div>
        </main>
      </q-step>

      <q-step
        :name="2"
        :done="step > 2"
        title="Varslingsinnstillinger"
      >
        <q-btn
          @click="step--"
          class="q-mt-lg"
          title="Tilbake"
          flat
        >
          <img :src="backButtonImg" alt="Tilbake">
        </q-btn>
        <h1 class="text-center q-mx-auto">Varslings- <br> instillinger</h1>
        <q-linear-progress
          rounded
          size="20px"
          :value="progress"
          color="primary"
          class="q-mt-lg q-mx-auto"
          style="width: 50%; border-radius: 20px;"
          :style="{ background: 'linear-gradient(to right, #fff, #fff)' }"
        />
        <main>
          <h3 class="text-center q-mt-lg q-mx-lg">Aktiver varslinger for å holde deg oppdatert på når du burde lade. <br>
            Få tips og anbefalinger basert på strømpriser og din posisjon.
          </h3>
          <div class="row justify-center q-mt-lg">
            <!-- When user lands here, ask for notification access, with button-->
            <q-btn
              @click="requestNotificationPermission"
              class="text-black align-center q-mt-lg q-pa-md"
              no-caps
              label="Tillat varsler"
              color="transparent"
              style="border: 1px solid black;"
            />
          </div>
        </main>
      </q-step>

      <q-step :name="3" title="Lokalsiseringsinnstillinger">
        <q-btn
          @click="step--"
          class="q-mt-lg"
          title="Tilbake"
          flat
        >
          <img :src="backButtonImg" alt="Tilbake">
        </q-btn>
        <h1 class="text-center q-mx-auto">Lokaliserings- <br> innstillinger</h1>
        <q-linear-progress
          rounded
          size="20px"
          :value="progress"
          color="primary"
          class="q-mt-lg q-mx-auto"
          style="width: 50%; border-radius: 20px;"
          :style="{ background: 'linear-gradient(to right, #fff, #fff)' }"
        />
        <main>
          <h3 class="text-center q-mx-lg q-mt-lg">Aktiver posisjonsinnstillinger for å få tilpassede varslet basert <br> på din posisjon,
            slik at ladingen kan optimaliseres basert på <br> strømpriser og din plassering.</h3>
          <div class="row justify-center q-mt-lg">
            <q-btn
              @click="getCurrentPosition"
              class="text-black text-center q-mt-lg q-pa-md"
              no-caps
              label="Tillat posisjon"
              color="transparent"
              style="border: 1px solid black;"
            />
          </div>
        </main>
      </q-step>

      <q-step :name="4" title="Oppsummering">
        <q-btn
          @click="step--"
          class="q-mt-lg"
          title="Tilbake"
          flat
        >
          <img :src="backButtonImg" alt="Tilbake">
        </q-btn>
        <h1 class="text-center q-mx-auto">Oppsummering</h1>
        <q-linear-progress
          rounded
          size="20px"
          :value="progress"
          color="primary"
          class="q-mt-lg q-mx-auto"
          style="width: 50%; border-radius: 20px;"
          :style="{ background: 'linear-gradient(to right, #fff, #fff)' }"
        />
        <main class="flex column content-center q-mx-auto">
          <h3 class="text-center q-mt-lg q-mx-lg">Her er en oppsummering av dine valg. Trykk på fullfør for å gå videre til registrering.</h3>
          <!-- Show which option the user selected from the drop-down-menu-->
          <p class="q-mt-lg"><strong>Valgt strømleverandør:</strong> {{ getSupplierLabel(selectedSupplier) }}</p>

          <!-- Show if the user accepted or denied notifications-->
          <p class="q-mt-lg"><strong>Varsler:</strong> {{ notificationPermission }}</p>

          <!-- Show if the user accepted or denied gps services-->
          <p class="q-mt-lg"> <strong>Posisjon:</strong> {{ city }}, {{ country }}</p>
        </main>
      </q-step>

      <template v-slot:navigation>
        <q-stepper-navigation class="flex flex-center">
          <q-btn
            @click="step++"
            color="primary"
            :label="step === 4 ? 'Fullfør' : 'Fortsett'"
            title="Fortsett"
            class="custom-btn"
            text-color="black"
            no-caps
          />
        </q-stepper-navigation>
      </template>
    </q-stepper>
  </q-page>
</template>

<script setup>

// import { Geolocation } from '@capacitor/geolocation'
// import { PushNotifications } from '@capacitor/push-notifications'
import backButtonImg from 'assets/c_icons/backButton.svg'
import { computed, ref, watch } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const step = ref(1)
const progress = computed(() => step.value * 0.25)

const position = ref('Ukjent')
const city = ref('Ukjent')
const country = ref('Ukjent')
const notificationPermission = ref('Ukjent')

const suppliers = [
  { label: 'Tibber', value: 'tibber' },
  { label: 'Vibb', value: 'vibb' },
  { label: 'Norges Energi', value: 'norges-energi' },
  { label: 'Fjordkraft', value: 'fjordkraft' },
  { label: 'Klarkraft', value: 'klarkraft' },
  { label: 'Agva Kraft', value: 'agva-kraft' },
]

const selectedSupplier = ref('') // Holder verdien til valgt strømleverandør

// Function for only getting label from suppliers
function getSupplierLabel(supplier) {
  return supplier.label
}

// Function for getting current position using browser's Geolocation API
function getCurrentPosition() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(
      (newPosition) => {
        console.log('Current position', newPosition)
        position.value = newPosition
        getCityName(newPosition.coords.latitude, newPosition.coords.longitude)
      },
      (error) => {
        console.error('Error getting position:', error)
      },
    )
  }
  else {
    console.error('Geolocation is not supported by this browser.')
  }
}
// Function for getting city name from coordinates
function getCityName(lat, lng) {
  const apiKey = '0156a3ba4e2042e0a237b949ab6a87b7' // personal key
  const url = `https://api.opencagedata.com/geocode/v1/json?q=${lat}+${lng}&key=${apiKey}`

  fetch(url)
    .then(response => response.json())
    .then(data => {
      if (data.results && data.results.length > 0) {
        city.value = data.results[0].components.city || data.results[0].components.town || data.results[0].components.village || 'Unknown'
        country.value = data.results[0].components.country || 'Unknown'
        console.log('City:', city.value, 'Country:', country.value)
      }
      else {
        console.log('No results found')
      }
    })
    .catch(error => {
      console.error('Error fetching city name:', error)
    })
}

// Watcher for stepper
watch(step, (step) => {
  if (step === 5) {
    router.push('/auth/register')
  }
})

// Function for requesting notification permission
function requestNotificationPermission() {
  if (Notification.permission === 'default') {
    Notification.requestPermission().then(permission => {
      if (permission === 'granted') {
        console.log('Notification permission granted.')
        notificationPermission.value = 'Godkjent'
      }
      else {
        console.log('Notification permission denied.')
        notificationPermission.value = 'Avslått'
      }
    })
  }
}

</script>

<style lang="scss" scoped>
.q-stepper::v-deep {
  .q-stepper__header {
    display: none;
  }
}
.custom-btn {
  position: absolute;
  bottom: 106px;
  left: 50%;
  transform: translateX(-50%);
  border-radius: 20px;
  padding: 20px 36px;
  font-size: 16px;
  box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
}
</style>
