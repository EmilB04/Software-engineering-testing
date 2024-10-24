<template>
  <q-page class="q-pa-none column bg-grey-2">
    <section class="row" style="justify-content: space-between;">
      <BackButton to="/dashboard" />
    </section>
    <header class="column q-mt-none">
      <h1 class="self-center q-mb-lg">Innstillinger</h1>
      <q-icon>
        <img src="/src/assets/c_icons/settings.svg" alt="Språk">
        <img src="/src/assets/c_icons/settings.svg" alt="Dark Mode">
      </q-icon>
    </header>
    <main>
      <q-card class="q-ma-md q-mt-lg">
        <q-card-section class="q-py-sm">
          <q-item>
            <q-item-section>
              <q-item-label>Kjøretøyinnstillinger</q-item-label>
            </q-item-section>
            <q-item-section side>
              <q-icon name="chevron_right" />
            </q-item-section>
          </q-item>
        </q-card-section>
      </q-card>
      <q-card class="q-ma-md">
        <q-card-section class="q-py-sm">
          <q-item>
            <q-item-section>
              <q-item-label>Ladepreferanser</q-item-label>
            </q-item-section>
            <q-item-section side>
              <q-icon name="chevron_right" />
            </q-item-section>
          </q-item>
        </q-card-section>
      </q-card>
      <q-card class="q-ma-md">
        <q-card-section class="q-py-sm">
          <q-item>
            <q-item-section>
              <q-item-label>Varsler</q-item-label>
            </q-item-section>
            <q-item-section side>
              <q-icon name="chevron_right" />
            </q-item-section>
          </q-item>
        </q-card-section>
      </q-card>
      <q-card class="q-ma-md">
        <q-card-section class="q-py-sm">
          <q-item>
            <q-item-section>
              <q-item-label>Oppdateringer</q-item-label>
            </q-item-section>
            <q-item-section side>
              <q-icon name="chevron_right" />
            </q-item-section>
          </q-item>
        </q-card-section>
      </q-card>
      <q-card class="q-ma-md">
        <q-card-section class="q-py-sm">
          <q-item>
            <q-item-section>
              <q-item-label>Versjon</q-item-label>
            </q-item-section>
            <q-item-section side>
              <q-icon name="chevron_right" />
            </q-item-section>
          </q-item>
        </q-card-section>
      </q-card>
    </main>
  </q-page>
</template>

<script setup>
import { getAuth, signOut } from 'firebase/auth'
import { ref } from 'vue'
import { useRouter } from 'vue-router'

import BackButton from 'src/components/BackButton.vue'

const auth = getAuth()
const router = useRouter()

</script>
