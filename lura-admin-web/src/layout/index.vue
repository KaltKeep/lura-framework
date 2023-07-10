<template>
  <div style="height: 100%;display: flex;">
    <sidebar></sidebar>
    <app-main></app-main>
  </div>
  <div :class="classObj" class="app__wrap">
    <!-- Classic -->
    <div v-if="showMenuTab" class="menu__tab">
      <menu-tab/>
    </div>
    <div
        id="sidebar__wrap"
        class="sidebar__wrap"
        :class="{
        'sidebar__wrap--collapsed': collapsed,
        'sidebar__wrap--tab': showMenuTab
      }"
    >
      <logo v-if="showLogo && layout === 'Classic'" :collapsed="collapsed"/>
      <sider :layout="layout" mode="vertical"/>
    </div>

    <div
        class="main__wrap"
        :class="{
        'main__wrap--collapsed': collapsed,
        'main__wrap--tab': showMenuTab,
        'main__wrap--tab--collapsed': showMenuTab && collapsed
      }"
    >
      <el-scrollbar
          class="main__wrap--content"
          :class="{
          'main__wrap--fixed--all': fixedHeader && showNavbar && showTags,
          'main__wrap--fixed--nav': fixedHeader && showNavbar && !showTags,
          'main__wrap--fixed--tags': fixedHeader && !showNavbar && showTags
        }"
      >
        <div
            class="header__wrap"
            :class="{
            'header__wrap--fixed': fixedHeader,
            'header__wrap--tab--fixed': fixedHeader && showMenuTab,
            'header__wrap--collapsed': fixedHeader && collapsed,
            'header__wrap--tab': showMenuTab,
            'header__wrap--tab--collapsed': showMenuTab && collapsed
          }"
        >
          <div v-if="showNavbar" class="navbar__wrap">
            <hamburger
                v-if="showHamburger"
                id="hamburger-container"
                :collapsed="collapsed"
                class="hover-container"
                @toggleClick="setCollapsed"
            />
            <breadcrumb v-if="showBreadcrumb" id="breadcrumb-container"/>
            <div v-if="showScreenfull || showUserInfo" class="navbar__wrap--right">
              <screenfull v-if="showScreenfull" class="hover-container screenfull-container"/>
              <user-info v-if="showUserInfo" class="hover-container user-container"/>
            </div>
          </div>
          <div v-if="showTags" id="tag-container" class="tags__wrap">
            <tags-view/>
          </div>
        </div>
      </el-scrollbar>
    </div>

    <!-- setting -->
    <setting/>
    <!-- setting -->

    <backtop v-if="showBackTop"/>
  </div>
</template>

<script setup>
import sidebar from "@/components/sidebar/index.vue";
import AppMain from "./app-main.vue";
import Logo from '@/components/sidebar/Logo.vue'


</script>

<style scoped>

</style>
