<script setup lang="ts">
import ShoppingHome from './ShoppingHome.vue'
import ShoppingConfig from './ShoppingConfig.vue'
import ShoppingSearch from './ShoppingSearch.vue'
import { ref } from 'vue'
const index = ref<number>(0)
function navClick(num: number) {
  index.value = num
}
</script>
<template>
  <div class="my_container  min-w-[320px]"  >
    <div v-if="index != 0" class=" text-3xl text-center font-bold p-2 text-black"  >CHANEL</div>
    <div class="content">
      <ShoppingHome  v-if="index == 0"></ShoppingHome>
      <ShoppingSearch v-else-if="index == 1" ></ShoppingSearch>
      <ShoppingConfig v-else></ShoppingConfig>
    </div>
    <div>
      <!-- <ul class="nav-p-s flex justify-center items-center p-0 h-12 bg-white">
        <li @click="navClick(0)" class="text-[#808080]" :class="{'focus-class' : index == 0}" >首页</li>
        <li @click="navClick(1)" class="text-[#808080]" :class="{'focus-class' : index == 1}" >搜索</li>
        <li @click="navClick(2)" class="text-[#808080]" :class="{'focus-class' : index == 2}">我的</li>
      </ul> -->
    </div>
  </div>
</template>
<style scoped>
.focus-class {
  @apply border-b-2 border-[#000] w-full h-full text-[#000000];
}
.my_container {
  position: fixed;
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-rows: auto 1fr auto;
}
h1 {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0;
}
.content {
  overflow: auto;
}
.nav-p-s > * {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
