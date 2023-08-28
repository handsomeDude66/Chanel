<script setup lang="ts">
import axios from 'axios'
import { ref } from 'vue'

const txt = ref('')
const list = ref<
  {
    id: number
    path: string
    price: number
    commodity: string
  }[]
>([])

function search() {
  axios
    .get('search.do', {
      params: {
        txt: txt.value
      },
    })
    .then((response) => {
      list.value = response.data
      list.value = list.value.map((x) => ({...x, path: `/shopping${x.path.slice(1)}`}))
    })
    .catch(() => {
      console.log("error");
      
    })
}

// list.value = [
//   {
//     id: 1,
//     path: 'src/assets/image/look1.jpg',
//     price: 20.0,
//     commodity: '潮流大妹子1号'
//   },
//   {
//     id: 2,
//     path: 'src/assets/image/look2.jpg',
//     price: 30.0,
//     commodity: '潮流大妹子2号'
//   },
//   {
//     id: 3,
//     path: 'src/assets/image/look3.jpg',
//     price: 40.0,
//     commodity: '潮流大妹子3号'
//   },
//   {
//     id: 4,
//     path: 'src/assets/image/look4.jpg',
//     price: 50.0,
//     commodity: '潮流大妹子4号'
//   },
//   {
//     id: 5,
//     path: 'src/assets/image/look5.jpg',
//     price: 60.0,
//     commodity: '潮流大妹子5号'
//   },
//   {
//     id: 6,
//     path: 'src/assets/image/look6.jpg',
//     price: 70.0,
//     commodity: '潮流大妹子6号'
//   }
// ]
</script>

<template>
  <div class="content2">
    <div class="search-box">
      <input
        v-model="txt"
        @input="search"
        type="text"
        name="search"
        class="search-txt"
        id="search-p-txt"
      />
      <input type="button" value="search" class="search-btn text-sm p-2" onclick="search()" />
    </div>
    <div class="search-show">
      <div v-if="list.length === 0" style="font-size: 14px">
        ·请输入你要搜的内容 如果不知道搜索什么请输入"潮流"~·
      </div>
      <div v-else>
        <div v-for="i in list" :key="i.id">
          <img :src="i.path" alt="" />
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.content2 {
  display: grid;
  grid-template-rows: 0.5fr 5fr auto;
  width: 100%;
  height: 100%;
  text-align: center;
}
.search-box {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
}

.search-txt {
  width: 80%;
  height: 100%;
  border: 2px solid #000;
  outline: none;
}
.search-btn {
  border: 2px solid #000;
  background-color: #fff;
  border-left: 0;
  height: 100%;
}
.search-show {
  overflow: auto;
  margin-top: 5%;
}
</style>
