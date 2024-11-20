<template>
  <q-page class="q-pa-none column bg-grey-2">
    <section class="row" style="justify-content: space-between;">
      <BackButton to="/dashboard" />
    </section>
    <header class="column q-mt-none">
      <h1 class="self-center q-mb-lg">Min Side</h1>
      <h2 class="q-ml-md q-ma-none">{{firstName}} {{lastName}}</h2>
      <h3 class="q-ml-md q-mt-sm q-ma-none" style="font-weight: 400;">{{ email }}</h3>
    </header>
    <main>
      <q-card class="q-ma-md q-mt-lg">
        <q-card-section class="q-py-sm">
          <q-item>
            <q-item-section>
              <q-item-label>Endre PIN-kode</q-item-label>
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
              <q-item-label>Endre passord</q-item-label>
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
              <q-item-label>Endre mobilnummer</q-item-label>
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
              <q-item-label>Vilkår</q-item-label>
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
              <q-item-label>Slett konto</q-item-label>
            </q-item-section>
            <q-item-section side>
              <q-icon name="chevron_right" />
            </q-item-section>
          </q-item>
        </q-card-section>
      </q-card>
    </main>
    <q-btn
      @click="logOut"
      no-caps
      style="height: 50px;"
      class="bg-black text-white self-center q-px-lg q-ma-xl"
    >
      Logg ut
    </q-btn>
  </q-page>
</template>

<script setup>
import { getAuth, signOut } from 'firebase/auth'
import { ref } from 'vue'
import { useRouter } from 'vue-router'

import BackButton from 'src/components/BackButton.vue'

const auth = getAuth()
const router = useRouter()

const logOut = () => {
  signOut(auth)
  router.push('/auth/welcome')
}
const firstName = ref(auth.currentUser?.displayName?.split(' ')[0])
const lastName = ref(auth.currentUser?.displayName?.split(' ').slice(1).join(' '))
const email = ref(auth.currentUser?.email)
</script>
