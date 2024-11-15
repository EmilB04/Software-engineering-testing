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
          <!-- When user lands here, ask for notification access, no button-->

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
      </q-step>

      <template v-slot:navigation>
        <q-stepper-navigation class="flex flex-center">
          <q-btn
            @click="step++"
            color="primary"
            :label="step === 4 ? 'Fullfør' : 'Fortsett'"
            title="Fortsett"
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
// import { PushNotifications } from '@capacitor/push-notifications'
import backButtonImg from 'assets/c_icons/backButton.svg'
import { computed, ref, onBeforeUnmount, watch } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const step = ref(1)
const progress = computed(() => step.value * 0.25)

const position = ref('determining...')

function getCurrentPosition() {
  Geolocation.getCurrentPosition().then(newPosition => {
    console.log('Current', newPosition)
    position.value = newPosition
    if (position.value) {
      console.log('Position determined: ', position.value)
    }
  })
}

/* const registerNotifications = async () => {
  let permStatus = await PushNotifications.checkPermissions()

  if (permStatus.receive === 'prompt') {
    permStatus = await PushNotifications.requestPermissions()
  }

  if (permStatus.receive !== 'granted') {
    throw new Error('User denied permissions!')
  }

  await PushNotifications.register()
}
  */
let geoId

watch(step, (step) => {
  console.log('Step', step)
  if (step === 2) {
    getCurrentPosition()
    // we start listening
    geoId = Geolocation.watchPosition({}, newPosition => {
      console.log('New GPS position')
      position.value = newPosition
    })
  }
  if (step === 5) {
    router.push('/auth/register')
  }
  if (step === 3) {
    requestNotificationPermission()
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
