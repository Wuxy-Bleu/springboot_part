// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
// import Router from 'vue-router'
// import Vuex from 'vuex'
import App from './App'
import router from './router'
import store from './store'
import './assets/css/index.scss'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import '@/assets/js/iconfont.js'
import '@/assets/js/iconfont1.js'
import '@/assets/js/iconfont2.js'
import '@/assets/js/iconfont3.js'
import './VueRouter' // permission control
import Router from '@/router'

// Vue.use(Router)
Vue.use(ElementUI)

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router: router,
  store: store,
  components: { App },
  template: '<App/>'
})
