<template>
  <el-card>
    <el-table :data="tableData" stripe height="600">
      <!-- <el-table-column prop="id" label="ID"></el-table-column> -->
      <el-table-column prop="targetServer" label="目标服务" />
      <el-table-column prop="requestPath" label="地址" />
      <el-table-column
        prop="requestMethod"
        label="Method"
        width="100"
        show-overflow-tooltip
      >
        <template #default="{ row }">
          <el-tag>{{ row.requestMethod }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="url"
        label="URL"
        width="200"
        show-overflow-tooltip
      />
      <el-table-column prop="requestBody" label="请求体" />
      <el-table-column
        prop="responseData"
        label="响应数据"
        width="100"
        show-overflow-tooltip
      />
      <el-table-column prop="ip" label="IP" />
      <el-table-column prop="requestTime" label="请求时间" />
      <el-table-column prop="responseTime" label="响应时间" />
      <el-table-column prop="executeTime" label="花费时间（ms）" />
    </el-table>
  </el-card>
</template>

<script lang="ts" setup>
import { type GatewayLog, getGateWayLogs } from "@/api/gateway";
defineOptions({
  name: "log"
});

const tableData = ref([]);

const getTableData = async () => {
  const res: GatewayLog[] = await getGateWayLogs();
  tableData.value = res;
};

getTableData();
</script>
