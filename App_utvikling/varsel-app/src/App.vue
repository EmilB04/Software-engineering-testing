<template>
  <router-view />
</template>

<script setup lang="ts">

//Spør om tilatelse til å sende push-notifikasjoner
import { onMounted } from 'vue';
import { Plugins } from '@capacitor/core';
const { PushNotifications } = Plugins;

// Denne koden kjører når appen åpnes
onMounted(() => {
  requestPushNotifications();
});

// Denne funksjonen ber brukeren om tillatelse til å sende notifikasjoner
function requestPushNotifications() {
  PushNotifications.requestPermission().then(permission => {
    // Hvis brukeren gir tillatelse
    if (permission.granted) {
      PushNotifications.register().then(() => {
        PushNotifications.addListener('registration', (token) => {
          console.log('Registreringstoken:', token);
        });

        // håndterer om det oppstår en feil ved registrering
        PushNotifications.addListener('registrationError', (error) => {
          console.error('Registreringsfeil:', error);
        });

        // Når appen mottar en varsel
        PushNotifications.addListener('pushNotificationReceived', (notification) => {
          console.log('Varsel mottatt:', notification);
        });
      }).catch(error => {
        console.error('Feil ved registrering:', error);
      });
    }
  }).catch(error => {
    console.error('Feil ved forespørsel om tillatelse:', error);
  });
}

defineOptions({
  name: 'App',
})
</script>
