<template>
<!--  <logo :collapse="isCollapse" />-->
  <el-menu
      router
      class="el-menu-vertical-demo"
      :collapse="isCollapse"
      @open="handleOpen"
      @close="handleClose"
  >
    <el-menu-item index="/app/gateway" >
      <el-icon><location /></el-icon>
      <template #title>网关配置</template>
    </el-menu-item>
    <el-menu-item index="/app/zipkin" >
        <el-icon><location /></el-icon>
        <template #title>Zipkin</template>
    </el-menu-item>
    <el-menu-item index="/app/nacos" >
      <el-icon><icon-menu /></el-icon>
      <template #title>Nacos</template>
    </el-menu-item>
    <el-menu-item index="/app/sentinel" >
      <el-icon><document /></el-icon>
      <template #title>Sentinel</template>
    </el-menu-item>
  </el-menu>
</template>

<script>
import {
  Location,
  Document,
  Menu as IconMenu,
  Setting,
} from '@element-plus/icons'
import logo from './Logo.vue'
export default {
  name: "sidebar",
  components: {
    Location,
    Document,
    Setting,
    IconMenu,
    logo
  },
  data() {
    const isCollapse = false
    const handleOpen = (key, keyPath) => {
      console.log(key, keyPath)
    }
    const handleClose = (key, keyPath) => {
      console.log(key, keyPath)
    }
    return {
      isCollapse,
      handleOpen,
      handleClose,
    }
  },

}
</script>

<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>
