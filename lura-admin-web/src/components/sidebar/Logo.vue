<template>
  <img v-if="logo" :src="logo" class="sidebar-logo">
</template>

<script>
import Logo from '@/assets/logo.png'

export default {
  name: 'SidebarLogo',
  props: {
    collapse: {
      type: Boolean,
      required: true
    }
  },
  data() {
    return {
      title: 'LuraFramework',
      logo: Logo
    }
  }
}
</script>

<style lang="scss" scoped>


.sidebar-logo {
  margin-top: 16px;
  margin-right: 0px;
  width: 50px;
  height: 50px;
  display: block;
}
</style>
