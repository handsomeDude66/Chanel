<script setup lang="ts">
import Vue from 'vue'
import router from './components/router.js'
import { ref } from 'vue'
import ContainerShopping from './components/shoppingContent/ContainerShopping.vue'
import ContainerLogin from './components/loginContent/ContianerLogin.vue'
const index = ref(true)
const a = function() {
  index.value = !index.value
}


</script>

<template>
  <ContainerShopping></ContainerShopping>
    <!-- <ContainerLogin></ContainerLogin> -->
</template>

<style scoped>
.a {
  background-color: #f00;
}
.f-container {
  width: 100%;
  height: 100%;
  display: grid;
  background-color: aqua;
}
</style>
