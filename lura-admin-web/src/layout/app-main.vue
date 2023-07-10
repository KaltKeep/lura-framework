<template>
  <div class="app-main">
    <router-view :key="key" />
  </div>
</template>

<script>
export default {
  name: "app-main",

  computed: {
    key() {
      return this.$route.path
    }
  }

}
</script>

<style scoped>
  .app-main {
    padding: 20px;
    height: 100%;
    overflow: auto;
    box-sizing: border-box;
    background-color: #f6f6f6;
    flex: 1;
  }
</style>
