<template>
  <el-card>
    <h1>白名单配置</h1>
    <el-table :data="data" >
      <!-- <el-table-column prop="id" label="ID"></el-table-column> -->
      <el-table-column prop="targetServer" label="目标服务"></el-table-column>
      <el-table-column prop="requestPath" label="地址"></el-table-column>
      <el-table-column prop="requestMethod" label="类型">
        <template #default="{row}">
          <el-tag>{{ row.requestMethod }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="url" label="URL"></el-table-column>
      <el-table-column prop="requestBody" label="请求体"></el-table-column>
      <el-table-column prop="responseData" label="响应数据"></el-table-column>
      <el-table-column prop="ip" label="IP"></el-table-column>
      <el-table-column prop="requestTime" label="请求时间"></el-table-column>
      <el-table-column prop="responseTime" label="响应时间"></el-table-column>
      <el-table-column prop="executeTime" label="花费时间"></el-table-column>
    </el-table>
  </el-card>
</template>

<script setup>
const data = [
  {
    id: 2,
    targetServer: "auth-service",
    requestPath: "/auth/code",
    requestMethod: "GET",
    url: "https://lura-gateway-server.sit.yumimiao.cn/auth/code",
    requestBody: null,
    responseData: null,
    ip: "123",
    requestTime: "2023-07-11T08:58:08.514",
    responseTime: null,
    executeTime: 0,
  },
];
</script>

<style scoped></style>
