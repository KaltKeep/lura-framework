<template>
  <router-link class="app-logo" to="/" >
    <img :src="logoImg" />
    <div v-if="show" class="sidebar-title">{{ title }}</div>
  </router-link>
</template>

<script setup>
import logoImg from '@/assets/img/logo.png'
import { ref } from 'vue'

const show = ref(true)
const title = ref("luraFramework");

</script>

<style scoped>

</style>
