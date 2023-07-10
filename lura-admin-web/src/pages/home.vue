<template>
  <el-row>
    <el-col :span="12" style="padding: 0 12px;">
      <el-card style="padding: 14px 26px;">
        <div class="j-b-flex">
          <div class="item">
            <div class="label">微服务个数</div>
            <div class="info">
              <div class="num">10</div>
              <div class="compare">
                <span>较昨日</span>
                <span style="color: red"><i>▲</i>0</span>
              </div>
            </div>
          </div>
          <div>sdfads</div>
          <div>sdfads</div>
        </div>
      </el-card>
      <el-card style="padding: 14px 26px; margin-top: 24px" shadow="never">
        <div class="title">快捷操作</div>
        <div class="link-box">
          <div class="link-mode">
            <el-icon style="color: rgb(255, 159, 50); font-size: 36px;"><Platform /></el-icon>
            <div class="label">网关</div>
          </div>
          <div class="link-mode">
            <el-icon style="color: rgb(255, 159, 50); font-size: 36px;"><Platform /></el-icon>
            <div class="label">Nacos</div>
          </div>
          <div class="link-mode">
            <el-icon style="color: rgb(255, 159, 50); font-size: 36px;"><Platform /></el-icon>
            <div class="label">Sentinel</div>
          </div>
          <div class="link-mode">
            <el-icon style="color: rgb(255, 159, 50); font-size: 36px;"><Platform /></el-icon>
            <div class="label">Zipkin</div>
          </div>
        </div>
      </el-card>
    </el-col>
    <el-col :span="12">
      <el-card>
        dfsda
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: "home"
}
</script>

<style scoped lang="scss">
.title {
  width: 100%;
  height: 28px;
  font-size: 18px;
  font-weight: 600;
  color: #000000e0;
  line-height: 28px;
}
.j-b-flex {
  display: flex;
  align-items: center;
  justify-content: space-between;


  .item {
    display: flex;
    flex-direction: column;
    height: 94px;

    .label {
      font-size: 14px;
      font-weight: 400;
      color: #000000e0;
      line-height: 24px;
    }

    .info {
      margin-top: 24px;
      display: flex;
      align-items: center;
      .num {
        width: auto;
        height: 46px;
        font-size: 30px;
        font-weight: 600;
        color: #000000e0;
        line-height: 46px;
      }
      .compare {
        width: auto;
        height: 24px;
        font-size: 14px;
        font-weight: 400;
        color: #00000073;
        line-height: 24px;
        margin-left: 24px;
        display: flex;
        flex-direction: row;
        align-items: center;
      }
    }
  }


}
.link-box {
  display: grid;
  column-gap: 24px;
  grid-template-columns: repeat(4,minmax(0,1fr));
  .link-mode {
    width: auto;
    height: 76px;
    border-radius: 7px;
    display: flex;
    align-items: center;
    box-sizing: border-box;
    padding: 8%;
    cursor: pointer;
    i {
      margin-right: 16px;
    }
  }
}

</style>
