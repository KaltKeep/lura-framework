import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import {ElementPlusResolver} from 'unplugin-vue-components/resolvers'
import {defineConfig} from "vite";

const path = require('path')
const pathResolve = (dir) => path.resolve(__dirname, dir)

export default defineConfig({
    plugins: [
        vue(),
        // ...
        AutoImport({
            resolvers: [ElementPlusResolver()],
        }),
        Components({
            resolvers: [ElementPlusResolver({
                importStyle: 'sass',
            })],
        }),
    ],
    css: {
        preprocessorOptions: {
            scss: {
                additionalData: `@use "~/styles/index.scss" as *;`,
            },
        },
    },


    resolve: {
        alias: {
            '@': pathResolve('src') + '/',
            '~': pathResolve('src') + '/'
        },
        // 忽略后缀名的配置选项, 添加 .vue 选项时要记得原本默认忽略的选项也要手动写入
        extensions: ['.mjs', '.js', '.ts', '.jsx', '.tsx', '.json', '.vue']
    }

})
