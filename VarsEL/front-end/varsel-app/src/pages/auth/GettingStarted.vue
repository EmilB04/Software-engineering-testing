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
        </main>
        <div class="text-center q-mt-xl">
          <q-btn-dropdown
            split
            flat
            no-caps
            color="black"
            label="Velg strømleverandør"
            style="border: 1px solid black;"
            class="q-pa-md"
            v-close-popup
          >
            <q-list>
              <q-item clickable v-close-popup>
                <q-item-section>
                  <q-item-label>Tibber</q-item-label>
                </q-item-section>
              </q-item>

              <q-item clickable v-close-popup>
                <q-item-section>
                  <q-item-label>Vibb</q-item-label>
                </q-item-section>
              </q-item>

              <q-item clickable v-close-popup>
                <q-item-section>
                  <q-item-label>Norges Energi</q-item-label>
                </q-item-section>
              </q-item>

              <q-item clickable v-close-popup>
                <q-item-section>
                  <q-item-label>Fjordkraft</q-item-label>
                </q-item-section>
              </q-item>

              <q-item clickable v-close-popup>
                <q-item-section>
                  <q-item-label>Klarkraft</q-item-label>
                </q-item-section>
              </q-item>
            </q-list>
          </q-btn-dropdown>
        </div>
      </q-step>

      <q-step
        :name="2"
        :done="step > 2"
        title="Lokaliseringsinnstillinger"
      >
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
          <h3 class="text-center q-mx-lg q-mt-lg">Aktiver posisjonsinnstillinger får å få tilpassede varslet basert på din posisjon,
            slik at ladingen kan optimaliseres basert på strømpriser og din plassering.</h3>
          <MainButton
            @click="getCurrentPosition"
            class="text-black align-center"
            no-caps
            label="Tillat posisjon"
            color="primary"
          />
        </main>
      </q-step>

      <q-step :name="3" title="Varslingsinnstillinger">
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
          <h3 class="text-center q-mt-lg q-mx-lg">Få varsler når strømprisene er lave eller når du ankommer hjemmet for optimal lading</h3>
        </main>
        <div>
          <!-- When user lands here, ask for notification access, with button-->
          <MainButton
            @click="requestNotificationPermission"
            class="text-black align-center"
            no-caps
            label="Tillat varsler"
            color="primary"
          />
        </div>
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
        <main>
          <h3 class="text-center q-mt-lg q-mx-lg">Her er en oppsummering av dine valg. Trykk på fullfør for å gå videre til registrering.</h3>
          <!-- Show which option the user selected from the drop-down-menu-->
          <p class="text-center q-mt-lg">Valgt strømleverandør: Tibber</p>

          <!-- Show if the user accepted or denied gps services-->
          <p class="text-center q-mt-lg">Posisjon: {{ city }}</p>

          <!-- Show if the user accepted or denied notifications-->
        </main>
      </q-step>

      <template v-slot:navigation>
        <q-stepper-navigation class="flex flex-center">
          <MainButton
            @click="step++"
            color="primary"
            :label="step === 4 ? 'Fullfør' : 'Fortsett'"
            title="Fortsett"
            class=""
            no-caps
          />
        </q-stepper-navigation>
      </template>
    </q-stepper>
  </q-page>
</template>
<style scoped>

</style>

<script setup>

import { Geolocation } from '@capacitor/geolocation'
// import { PushNotifications } from '@capacitor/push-notifications'
import backButtonImg from 'assets/c_icons/backButton.svg'
import { computed, ref, onBeforeUnmount, watch } from 'vue'
import { useRouter } from 'vue-router'

import MainButton from 'components/MainButton.vue'

const router = useRouter()
const step = ref(1)
const progress = computed(() => step.value * 0.25)

const position = ref('determining...')
const city = ref('Unknown')

function getCurrentPosition() {
  Geolocation.getCurrentPosition().then(newPosition => {
    console.log('Current', newPosition)
    position.value = newPosition
    getCityName(newPosition.coords.latitude, newPosition.coords.longitude)
  })
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
        console.log('City:', city.value)
      }
      else {
        console.log('No results found')
      }
    })
    .catch(error => {
      console.error('Error fetching city name:', error)
    })
}

let geoId

watch(step, (step) => {
  if (step === 5) {
    router.push('/auth/register')
  }
})

onBeforeUnmount(() => {
  // we do cleanup
  Geolocation.clearWatch(geoId)
})

function requestNotificationPermission() {
  if (Notification.permission === 'default') {
    Notification.requestPermission().then(permission => {
      if (permission === 'granted') {
        console.log('Notification permission granted.')
      }
      else {
        console.log('Notification permission denied.')
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
</style>
