<script setup lang="ts">
import { ref, onUnmounted, onMounted } from 'vue'

import Video from 'video.js'
// import 'video.js/dist/video-js.css'

import axios from 'axios'



// 页面加载完后
onMounted(() => {
  window.addEventListener('resize', handleResize)
  document.addEventListener('fullscreenchange', handleFullscreenChange)
  handleResize()
})
const index = ref(false)
const volumeIndex = ref(false)
const playIndex = ref(false)
const videosrc = ref('/public/assets/main.mp4')
const videoref = ref(null) as any
const jennie = ref('/src/assets/image/JENNIE.webp')
const lily_rose = ref('/src/assets/image/LILY-ROSE.webp')
const whitney = ref('/src/assets/image/WHITNEY.webp')
const margaret = ref('/src/assets/image/MARGARET.webp')
// 点击事件 全屏切换
function toggleFullscreen() {
  if (document.fullscreenElement) {
    document.exitFullscreen()
  } else {
    videoref.value.requestFullscreen()
  }
}
// 全屏改变 切换静音
function handleFullscreenChange() {
  if (document.fullscreenElement) {
    videoref.value.muted = false
  } else {
    videoref.value.muted = true
  }
}
// 用户 浏览器大小发生变化触发函数
function handleResize() {
  // 第二次检查宽度
  if (window.innerWidth < 900) {
    videosrc.value = '/public/assets/main.mp4'
    jennie.value = '/src/assets/image/JENNIE.webp'
    lily_rose.value = '/src/assets/image/LILY-ROSE.webp'
    whitney.value = '/src/assets/image/WHITNEY.webp'
    margaret.value = '/src/assets/image/MARGARET.webp'
    index.value = false
  } else {
    videosrc.value = '/public/assets/mainComputer.mp4'
    jennie.value = '/src/assets/image/JENNIE-computer3.webp'
    lily_rose.value = '/src/assets/image/LILY-ROSE-computer.webp'
    whitney.value = '/src/assets/image/WHITNEY-computer.webp'
    margaret.value = '/src/assets/image/MARGARET-computer.webp'
    index.value = true
  }
  // 重新加载视频
  videoref.value.load()
}
function volumeToggle() {
  videoref.value.muted = !videoref.value.muted
  volumeIndex.value = !volumeIndex.value;
}
function playToggle() {
  if (!playIndex.value) {
    videoref.value.pause()
  } else {
    videoref.value.play()
  }
  playIndex.value = !playIndex.value
}

// 页面消失调用
onUnmounted(() => {
  window.removeEventListener('resize', handleResize)
  document.removeEventListener('fullscreenchange', handleFullscreenChange)
})
</script>

<template>
  <div class="content1">
    <div
      class="text-3xl text-center font-bold p-2 bg-transparent fixed top-0 text-white z-10 w-full"
    >
      DIK
    </div>
    <div class="h-full w-full relative bg-black">
      <div class="h-full overflow-hidden max-h-[650px] relative" id="main">
        <div class="text-stone-50 w-full h-full relative">
          <video ref="videoref" autoplay loop muted id="main-video" class="h-full w-full">
            <source :src="videosrc" type="video/mp4" />
          </video>
          <div class="flex justify-between w-full absolute bottom-2 video-icon">
              <i class="fa fa-volume-high  ml-10" v-if="volumeIndex" @click="volumeToggle"></i>
              <i class="fa-solid fa-volume-xmark ml-10" v-else @click="volumeToggle"></i>
              <i class="fa-solid fa-play mr-10" @click="playToggle" v-if="playIndex"></i>
              <i class="fa fa-pause mr-10" @click="playToggle" v-else></i>
            </div>
          <div class="flex justify-center items-center">
            <div class="text-[2vh] text p-[2rem_2rem_0_2rem]">
              <div class="text-[20px] font-extrabold">THE CHANEL 22 BAG</div>
              <span class="max-w-[481px] w-full h-full">
                In the sagacity of cities, the plenitude of open spaces or the daydream of a villa,
                CHANEL ambassadors Whitney Peak, Margaret Qualley, Lily-Rose Depp and JENNIE play
                the game of autobiographical fiction. Like a page of a diary written in an affable,
                imaginary ink, they tell their stories, and ours.<br />
                <br />
                On their arm, the CHANEL 22 bag, a genuine ally for daily life captured<br />
                by Inez &amp; Vinoodh through four films.<br />
                <br />Music « L’Amour et la Violence » written and composed by Sébastien Tellier.
              </span>

              <div class="mt-[10vh]">
                <span
                  @click="toggleFullscreen"
                  class="border-solid border-white border-[1px] p-[10px_50px]"
                >
                  See the Film!
                </span>
              </div>
            </div>
            

          </div>
        </div>
      </div>
    </div>

    <div class="w-full">
      <div class="h-full text-white bg-black" id="jennie">
        <div class="text-[40px] font-bold h-[65vh] height-vh">
          <p class="fixed_top text-center font-extrabold">JENNIE</p>
        </div>
        <div>
          <img class="z-2 relative" :src="jennie" alt="" srcset="" />
        </div>
        <div class="gril-grounp">
          <div>
            <video loop autoplay>
              <source src="/public/assets/JENNIE.mp4" type="video/mp4" />
            </video>
          </div>
          <div v-if="index">
            <img src="@/assets/image/JENNIE-computer1.webp" alt="" />
          </div>
        </div>
        <div class="w-full h-full">
          <img src="@/assets/image/JENNIE-computer2.webp" alt="" />
        </div>

        <div class="bg-black h-[50%] flex items-center justify-center">
          <p class="text-center font-mono h-full text-[14px] text-computer max-w-[500px]">
            “my first solo came out when I was 22 and we started our new world tour with blackpink
            in 2022. To me, 22 means a new beginning.”
          </p>
        </div>
        <div class="bg-black">
          <div class="p-5 flex justify-center items-center text-black">
            <a href="#" class="block p-[10px_20px] bg-white font-extrabold">
              SEE MORE CHANEL 22 BAGS
            </a>
          </div>
        </div>
      </div>
    </div>

    <div class="w-full">
      <div class="h-full text-white bg-black" id="lily-rose">
        <div class="text-[40px] font-bold h-[65vh] height-vh">
          <p class="fixed_top text-center font-extrabold">LILY-ROSE</p>
        </div>
        <div class="bg-black">
          <img class="z-2 relative" :src="lily_rose" alt="" srcset="" />
        </div>
        <div class="gril-grounp">
          <div>
            <video loop autoplay>
              <source src="/public/assets/LILY_ROSE.mp4" type="video/mp4" />
            </video>
          </div>
          <div>
            <img src="@/assets/image/LILY-ROSE2.webp" alt="" />
          </div>
        </div>
        <div class="bg-black h-[50%] flex items-center justify-center">
          <p
            class="text-center font-mono flex items-center justify-center h-full text-computer max-w-[500px]"
          >
            “I feel like I could bring this bag anywhere! To work, to stroll around alone, to sit
            and read in a park, on a date, even to the beach!”
          </p>
        </div>
        <div class="bg-black">
          <div class="p-5 flex justify-center items-center text-black">
            <a href="#" class="block p-[10px_20px] bg-white font-extrabold">
              SEE MORE CHANEL 22 BAGS
            </a>
          </div>
        </div>
      </div>
    </div>

    <div class="w-full">
      <div class="h-full text-white bg-black" id="whitney">
        <div class="text-[40px] font-bold h-[65vh] height-vh">
          <p class="fixed_top text-center font-extrabold">WHITNEY</p>
        </div>
        <div class="bg-black">
          <img class="z-2 relative" :src="whitney" alt="" srcset="" />
        </div>
        <div class="gril-grounp">
          <div>
            <video loop autoplay>
              <source src="/public/assets/WHITNEY.mp4" type="video/mp4" />
            </video>
          </div>
          <div>
            <img src="@/assets/image/WHITNEY2.webp" alt="" />
          </div>
        </div>
        <div class="bg-black h-[50%] flex items-center justify-center">
          <p
            class="text-center font-mono flex items-center justify-center h-full text-computer max-w-[500px]"
          >
            “This might be my favourite CHANEL bag so far. I'm a huge fan of functional pieces and
            tend to travel with my life in my bag.”
          </p>
        </div>
        <div class="bg-black">
          <div class="p-5 flex justify-center items-center text-black">
            <a href="#" class="block p-[10px_20px] bg-white font-extrabold">
              SEE MORE CHANEL 22 BAGS
            </a>
          </div>
        </div>
      </div>
    </div>

    <div class="w-full">
      <div class="h-full text-white bg-black" id="margaret">
        <div class="text-[40px] font-bold h-[65vh] height-vh">
          <p class="fixed_top text-center font-extrabold">MARGARET</p>
        </div>
        <div class="bg-black">
          <img class="z-2 relative" :src="margaret" alt="" srcset="" />
        </div>
        <div class="gril-grounp">
          <div>
            <video loop autoplay>
              <source src="/public/assets/MARGARET.mp4" type="video/mp4" />
            </video>
          </div>
          <div>
            <img src="@/assets/image/MARGARET2.webp" alt="" />
          </div>
        </div>

        <div class="bg-black h-[50%] flex items-center justify-center">
          <p
            class="text-center font-mono flex items-center justify-center h-full text-computer max-w-[500px]"
          >
            “The CHANEL 22 bag is a perfect example of Virginie viard’s natural ability to create
            pieces that empower women. This is a bag that accommodates a woman’s every need.”
          </p>
        </div>
        <div class="bg-black">
          <div class="p-5 flex justify-center items-center text-black">
            <a href="#" class="block p-[10px_20px] bg-white font-extrabold">
              SEE MORE CHANEL 22 BAGS
            </a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
img {
  width: 100%;
}
video {
  width: 100%;
  /* max-height: 650px; */
  height: max-content;
  /* z-index: -10; */
  object-fit: cover;
}
.content1 {
  width: 100%;
  height: 100%;
}
.text {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
  position: absolute;
  top: 15vh;
  z-index: 30;
}
.fixed_top {
  position: sticky;
  top: 50%;
  transform: translateY(160%);
}
i {
  cursor: pointer;
}
/* >900px */
@media (min-width: 900px) {
  .gril-grounp {
    display: grid;
    grid-template-columns: 1fr 1fr;
  }
  #main {
    overflow: hidden;
    max-height: none;
  }
  .fixed_top {
    font-size: 150px;
    top: 20%;
    transform: translateY(100%);
  }
  .height-vh {
    height: 80vh;
  }
  .text-computer {
    font-size: 30px;
    max-width: 850px;
  }
  
}
@media (max-width: 350px) {
  #main {
    height: auto;
  }
  
  .video-icon {
    bottom: 2vh;
  }
}
</style>
