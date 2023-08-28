<script setup lang="ts">
import { reactive, ref } from 'vue'
import axios from 'axios'

const index = ref(true)

const formValue = reactive({
  name: '',
  age: '',
  inv: ''
})

const formValidate = reactive({
  name: true,
  age: true,
  inv: true,

  validateName() {
    if (!formValue.name) {
      formInvalidText.name = '不可以为空值'
    } else if (formValue.name.length < 4) {
      formInvalidText.name = '用户名要大于 4 个字符'
    } else {
      formInvalidText.name = ''
      return true
    }
    return false
  },

  validateAge() {
    if (!formValue.age) {
      formInvalidText.age = '不可以为空值'
    } else {
      formInvalidText.age = ''
      return true
    }
    return false
  },

  validateInv() {
    if (!formValue.inv) {
      formInvalidText.inv = '不可以为空值'
    } else {
      formInvalidText.inv = ''
      return true
    }
    return false
  }
})

const formInvalidText = reactive({
  name: '',
  age: '',
  inv: ''
})

function login() {
  if (formValidate.validateName() && formValidate.validateAge() && formValidate.validateInv()) {
    // 提交表单
    axios
      .get('/loginShoping.do', {
        params: { name: formValue.name, age: formValue.age, inv: formValue.inv }
      })
      .then((response) => {
        // 重定向会解析成200
      })
      .catch((err) => {
        
      })
  }
}
</script>
<template>
  <div class="grid w-full h-full   ">
    <div class="phone-msg-box min-h-[375px] ">
      <div class="input-data ">
        <input
          v-model="formValue.name"
          type="text"
          name="myname"
          id="myname"
          required
          @blur="formValidate.validateName"
        />
        <label>Name</label>
        <div>{{ formInvalidText.name }}</div>
      </div>
      <div class="input-data">
        <input
          v-model="formValue.age"
          type="text"
          name="myage"
          id="myage"
          required 
          @blur="formValidate.validateAge"
        />
        <label>Age</label>
        <div>{{ formInvalidText.age }}</div>
      </div>
      <div class="input-data flex-1">
        <input
          v-model="formValue.inv"
          type="text"
          name="inv"
          id="inv"
          required
          @blur="formValidate.validateInv"
        />
        <label>VIPID</label>
        <div>{{ formInvalidText.inv }}</div>
      </div>
      <div >
        <button class="btn-s-i">submit</button>
      </div>
    </div>
  </div>
</template>
<style scoped>
.input-data > label {
  @apply absolute left-0;
}

.input-data input:focus + label,
.input-data input:valid + label {
  @apply translate-y-[-26px] text-[10px];
}

.input-data label + div {
  @apply bottom-[16px] text-sm text-red-500;
}

.input-data label + div:empty {
  @apply hidden;
}

input {
  width: 100%;
  outline: none;
  border: 0;
  border-bottom: 1px solid #b4b4b4;
}
label {
  color: #b4b4b4;
  transition: all 1s;
}
.show {
  transform: translateY(-26px);
  font-size: 10px;
}
.phone-msg-box {
  display: grid;
  align-items: center;
  width: 100%;
  height: 100%;
}
.phone-msg-box > div {
  width: 80%;  
  display: flex;
  flex-direction: column;
  justify-self: center;
  position: relative;
}

.btn-s-i {
  width: 100%;
  background-color: #fff;
  box-shadow: 1px 1px 5px #000;
  height: 2rem;
  color: #959595;
}
@keyframes moveUp {
  0% {
    transform: translateY(0px);
  }
  100% {
    transform: translateY(10px);
  }
}
</style>
