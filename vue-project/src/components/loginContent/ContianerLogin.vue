<script setup lang="ts">
import axios from 'axios'
import { reactive } from 'vue'

const checkValue = reactive({
  name: '',
  inv: '',
  wrongName: '',
  wrongInv: '',
  checkName() {
    this.wrongName = ''
    if (!this.name) {
      this.wrongName = '不能为空值'
    } else if (this.name.length > 10) {
      this.wrongName = 'name不能超过10'
    }
  },
  checkInv() {
    this.wrongInv = ''
    if (!this.inv) {
      this.wrongInv = '不能为空值'
    } else if (this.inv.length != 4) {
      this.wrongInv = 'inv必须是四位'
    }
  }
})
function login() {
  if (!checkValue.wrongInv && !checkValue.wrongName) {
    axios
      .post('/loginIn.do', { params: { name: checkValue.name, inv: checkValue.inv } })
      .then((response) => {
        if (!response.data) {
          window.location.href = ""
        }
      })
      .catch((e) => {
        console.log(e)
      })
  }
}
</script>
<template>
  <div class="container">
    <div class="text-2xl text-black font-bold mb-5">login</div>
    <div class="input-div">
      <div>
        <input
          type="text"
          name="name"
          id="name"
          v-model="checkValue.name"
          @blur="checkValue.checkName"
          required
        />
        <label>name</label>
        <div class="text-red-400 text-sm">{{ checkValue.wrongName }}</div>
      </div>
      <div>
        <input
          type="password"
          name="inv"
          id="inv"
          v-model="checkValue.inv"
          @blur="checkValue.checkInv"
          required
        />
        <label>inv</label>
        <div class="text-red-400 text-sm">{{ checkValue.wrongInv }}</div>
      </div>
    </div>

    <div class="btn-div">
      <button>提交</button>
    </div>
  </div>
</template>
<style scoped>
input {
  outline: none;
  border-bottom: 1px solid #c4c4c4;
  width: 100%;
}
label {
  color: #c4c4c4;
  font-size: 14px;
}
.container {
  width: 100%;
  height: 100%;
  position: fixed;
  display: grid;
  grid-template-rows: auto 1fr auto;
  padding: 10%;
}
.input-div {
  display: flex;
  align-items: center;
  flex-direction: column;
  width: 100%;
}
.input-div > div {
  position: relative;
  width: 100%;
  margin-bottom: 20px;
}
.input-div > div > label {
  transition: all 0.5s;
  position: absolute;
  left: 0;
}
.input-div > div > input:focus ~ label {
  transform: translateY(-16px);
  font-size: 13px;
}

.input-div > div > input:valid ~ label {
  font-size: 13px;
  transform: translateY(-16px);
}

.container > div:not(.input-div, :first-child) {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
button {
  padding: 10px;
  background-color: skyblue;
  width: 100%;
}
</style>
