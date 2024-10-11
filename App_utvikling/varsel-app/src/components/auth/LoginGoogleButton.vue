<template>
  <q-btn
    flat
    outline
    @click="redirectToGoogle"
    class="google-btn"
  >
    <img
      class="google-icon"
      src="/public/c_icons/continue_wgoogle_light.svg"
      alt="Google innlogging"
    />
  </q-btn>
</template>

<script setup lang="ts">
const redirectToGoogle = () => {
  // Redirect to Google sign-in or any other desired URL
  window.location.href = 'https://accounts.google.com/signin'
}
</script>

<style lang="scss">
.google-btn {
  display: block;
  width: 100%;
  margin: 10px auto;
  max-width: max-content;
}
.google-icon {
  width: 36px;
  height: auto;
  margin: 12px;
  padding: 0;
}
</style>
