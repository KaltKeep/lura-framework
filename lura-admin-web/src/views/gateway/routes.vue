<template>
  <el-card>
    <h1>白名单配置</h1>
    <el-table :data="tableData">
      <!-- <el-table-column prop="id" label="ID"></el-table-column> -->
      <el-table-column prop="predicates" label="predicates">
        <template #default="{ row }">
          <span v-for="(item, index) of row.predicates" :key="index">
            {{ item.name }}
          </span>
        </template>
      </el-table-column>
      <el-table-column prop="filters" label="filters">
        <template #default="{ row }">
          <span v-for="(item, index) of row.predicates" :key="index">
            {{ item.name }}
          </span>
        </template>
      </el-table-column>
      <el-table-column prop="uri" label="uri" />
      <el-table-column prop="metadata" label="metadata">
        <template #default="{ row }">
          <span v-for="(item, index) of row.predicates" :key="index">
            {{ item.name }}
          </span>
        </template>
      </el-table-column>
      <el-table-column prop="order" label="order" />
    </el-table>
  </el-card>
</template>

<script lang="ts" setup>
import { type GatewayRoute, getGateWayRoutes } from "@/api/gateway";
defineOptions({
  name: "routes"
});
const tableData = ref([]);

const getTableData = async () => {
  const res: GatewayRoute[] = await getGateWayRoutes();
  tableData.value = res;
};

getTableData();
</script>
