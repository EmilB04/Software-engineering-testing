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
        <q-btn
          :to="'/auth/welcome'"
          class="q-mt-lg"
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
          class="q-mt-sm q-mx-auto"
          style="width: 50%; border-radius: 20px;"
          :style="{ background: 'linear-gradient(to right, #fff, #fff)' }"
        />
      </q-step>

      <q-step :name="2" :done="step > 2">
        <q-btn
          @click="step--"
          class="q-mt-lg"
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
          class="q-mt-sm q-mx-auto"
          style="width: 50%; border-radius: 20px;"
          :style="{ background: 'linear-gradient(to right, #fff, #fff)' }"
        />
        <div>
          GPS position: <strong>{{ position }}</strong>
        </div>
      </q-step>

      <q-step :name="3">
        <q-btn
          @click="step--"
          class="q-mt-lg"
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
          class="q-mt-sm q-mx-auto"
          style="width: 50%; border-radius: 20px;"
          :style="{ background: 'linear-gradient(to right, #fff, #fff)' }"
        />
      </q-step>

      <q-step :name="4">
        <q-btn
          @click="step--"
          class="q-mt-lg"
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
          class="q-mt-sm q-mx-auto"
          style="width: 50%; border-radius: 20px;"
          :style="{ background: 'linear-gradient(to right, #fff, #fff)' }"
        />
      </q-step>

      <template v-slot:navigation>
        <q-stepper-navigation class="flex flex-center">
          <q-btn
            @click="step++"
            color="primary"
            :label="step === 4 ? 'Fullfør' : 'Fortsett'"
            class="custom-btn text-black"
            no-caps
          />
        </q-stepper-navigation>
      </template>
    </q-stepper>
  </q-page>
</template>
<style scoped>
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

<script setup>

import { Geolocation } from '@capacitor/geolocation'
import { PushNotifications } from '@capacitor/push-notifications'
import backButtonImg from 'assets/c_icons/backButton.svg'
import { computed, ref, onBeforeUnmount, onMounted } from 'vue'

const step = ref(1)
const progress = computed(() => step.value * 0.25)

/* const stepInfo = [
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
  */

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
  geoId = Geolocation.watchPosition({}, newPosition => {
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
