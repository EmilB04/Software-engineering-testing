<template>
  <q-page class="row items-center justify-evenly">
    <example-component
      title="Example component"
      active
      :todos="todos"
      :meta="meta"
    ></example-component>
    <q-btn @click="logOut">

    </q-btn>
  </q-page>
</template>

<script setup lang="ts">
import { getAuth, signOut } from 'firebase/auth'
import { ref } from 'vue'

import ExampleComponent from 'components/ExampleComponent.vue'
import { Todo, Meta } from 'components/models'

defineOptions({
  name: 'IndexPage',
})

const logOut = () => {
  const auth = getAuth()
  signOut(auth)
}

const todos = ref<Todo[]>([
  {
    id: 1,
    content: 'ct1',
  },
  {
    id: 2,
    content: 'ct2',
  },
  {
    id: 3,
    content: 'ct3',
  },
  {
    id: 4,
    content: 'ct4',
  },
  {
    id: 5,
    content: 'ct5',
  },
])

const meta = ref<Meta>({
  totalCount: 1200,
})
</script>
