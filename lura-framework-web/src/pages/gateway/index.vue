<template>
  <h1>白名单配置</h1>
  <el-table :data="data">
    <el-table-column prop="url" label="url"></el-table-column>
    <el-table-column prop="method" label="method">
      <template #default="scope">
        <el-tag>{{scope.row.method}}</el-tag>
      </template>
    </el-table-column>
  </el-table>
</template>

<script setup>

const data = [
  {url: '/product/**', method: 'GET'}
]
</script>

<style scoped>

</style>
