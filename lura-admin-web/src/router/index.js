import {createRouter, createWebHashHistory} from 'vue-router'


const routes = [
    {
        path: '/',
        name: 'Home',
        component: () => import('../layout/index.vue'),
        children: [
            {
                path: '/app/gateway',
                name: 'Gateway',
                component: () => import('../pages/gateway/index.vue')
            },
            {
                path: '/app/:app',
                name: 'Home',
                component: () => import('../components/app-frame.vue')
            },

            {
                path: '/',
                component: ()=> import('../pages/home.vue')
            }
        ]
    }
]

export default createRouter({
    history: createWebHashHistory(),
    routes: routes
})
