<template>
  <q-page class="column q-pa-none bg-grey-2">
    <q-stepper
      class="bg-grey-2"
      v-model="step"
      ref="stepper"
      color="primary"
      animated
      flat
    >
      <q-step :name="1" :done="step > 1">
        <q-btn :to="'/auth/welcome'" flat>
          <img src="/public/c_icons/backButton.svg" alt="Tilbake">
        </q-btn>
        <q-linear-progress
          rounded
          size="20px"
          :value="progress"
          color="primary"
          class="q-mt-sm justify-center"
          style="width: 50%; border-radius: 20px;"
          :style="{ background: 'linear-gradient(to right, #fff, #fff)' }"
        />
      </q-step>

      <q-step :name="2" :done="step > 2">
        <q-btn @click="step--" flat>
          <img src="/public/c_icons/backButton.svg" alt="Tilbake">
        </q-btn>
        <q-linear-progress
          rounded
          size="20px"
          :value="progress"
          color="primary"
          class="q-mt-sm justify-center"
          style="width: 50%; border-radius: 20px;"
          :style="{ background: 'linear-gradient(to right, #fff, #fff)' }"
        />
        <div>
          GPS position: <strong>{{ position }}</strong>
        </div>
      </q-step>

      <q-step :name="3">
        <q-btn @click="step--" flat>
          <img src="/public/c_icons/backButton.svg" alt="Tilbake">
        </q-btn>
        <q-linear-progress
          rounded
          size="20px"
          :value="progress"
          color="primary"
          class="q-mt-sm justify-center"
          style="width: 50%; border-radius: 20px;"
          :style="{ background: 'linear-gradient(to right, #fff, #fff)' }"
        />
      </q-step>

      <q-step :name="4">
        <q-btn @click="step--" flat>
          <img src="/public/c_icons/backButton.svg" alt="Tilbake">
        </q-btn>
        <q-linear-progress
          rounded
          size="20px"
          :value="progress"
          color="primary"
          class="q-mt-sm justify-center"
          style="width: 50%; border-radius: 20px;"
          :style="{ background: 'linear-gradient(to right, #fff, #fff)' }"
        />
      </q-step>

      <template v-slot:navigation>
        <q-stepper-navigation>
          <q-btn
            @click="step++"
            color="primary"
            :label="step === 4 ? 'Fullfør' : 'Fortsett'"
            class="q-mt-xl q-mb-md"
          />
        </q-stepper-navigation>
      </template>
    </q-stepper>
  </q-page>
</template>

<script setup>
import { Geolocation } from '@capacitor/geolocation'
import { PushNotifications } from '@capacitor/push-notifications'
import { computed, ref, onMounted, onBeforeUnmount } from 'vue'

const step = ref(1)
const progress = computed(() => step.value * 0.25)

const stepInfo = [
  {
    title: 'Velg din strømleverandør',
    description: 'Velg din nåværende strømleverandør for å få den mest nøyaktige oversikten over strømpriser.',
  },
  {
    title: 'Lokaliserings-innstillinger',
    description: 'Aktiver posisjonsinnstillinger for å få varsler når du ankommer hjemmet, slik at ladingen kan optimaliseres basert på strømpriser og din plassering.',
    icon: 'gps',
  },
  {
    title: 'Varslings-innstillinger',
    description: 'Få varsler når strømprisene er lave eller når du ankommer hjemmet for optimal lading',
    icon: 'notifications',
  },
  {
    title: 'Oppsummering',
    description: 'Gå gjennom innstillingene dine og bekreft at alt er riktig før du går videre.',
  },
]

const position = ref('determining...')

function getCurrentPosition() {
  Geolocation.getCurrentPosition().then(newPosition => {
    console.log('Current', newPosition)
    position.value = newPosition
  })
}
const registerNotifications = async () => {
  let permStatus = await PushNotifications.checkPermissions()

  if (permStatus.receive === 'prompt') {
    permStatus = await PushNotifications.requestPermissions()
  }

  if (permStatus.receive !== 'granted') {
    throw new Error('User denied permissions!')
  }

  await PushNotifications.register()
}
let geoId

onMounted(() => {
  getCurrentPosition()
  registerNotifications()

  // we start listening
  geoId = Geolocation.watchPosition({}, (newPosition, err) => {
    console.log('New GPS position')
    position.value = newPosition
  })
})

onBeforeUnmount(() => {
  // we do cleanup
  Geolocation.clearWatch(geoId)
})
</script>

<style lang="scss" scoped>
.q-stepper::v-deep {
  .q-stepper__header {
    display: none;
  }
}
</style>
