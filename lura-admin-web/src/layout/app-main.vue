<template>
  <div class="app-right">
    <div style="height: 48px; background-color: white; position: sticky">sdfa</div>
    <div class="app-main">
      <router-view :key="key"/>
    </div>
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

<style scoped lang="scss">
.app-right {
  flex: 1;

  .app-main {
    padding: 20px;
    height: 100%;
    overflow: auto;
    box-sizing: border-box;
    background-color: #f6f6f6;
  }
}

</style>
