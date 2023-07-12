export default {
  path: "/gateway",
  redirect: "/gateway/log",
  meta: {
    icon: "network",
    title: "网关",
    // showLink: false,
    rank: 9
  },
  children: [
    {
      path: "/gateway/403",
      name: "log",
      component: () => import("@/views/gateway/log.vue"),
      meta: {
        title: "日志"
      }
    },
    {
      path: "/gateway/routes",
      name: "routes",
      component: () => import("@/views/gateway/routes.vue"),
      meta: {
        title: "接口"
      }
    }
  ]
} as RouteConfigsTable;
