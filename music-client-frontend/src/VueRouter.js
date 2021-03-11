import router from './router'
import { isLogin, getInfo } from './api/index'
import store from './store'
import Cookies from 'js-cookie'

router.beforeEach(async (to, from, next) => {

  console.log('============================to &  from==========================================')
  console.log(to)
  console.log(from)
  console.log('=========================================================================')


  if (to.path === '/' && typeof to.query.token != 'undefined') {
    console.log('from vue-element-admin and token =')
    console.log(to.query.token)

    isLogin(to.query.token).then(res => {
      //json中的true false貌似是字符串   所以我后端之后返回的true false的字符串
      if (res.data.isLogin == 'true') {
        console.log('已登录')
        store.commit('setLoginIn', true)
        //将token存在cookie中
        Cookies.set('User-Token', to.query.token)
        //获取当前用户的一些基本信息，存放在vuex中
        getInfo(to.query.token).then(res => {
          store.commit('setUserId', res.data.id)
          store.commit('setUsername', res.data.name)
          store.commit('setAvator', res.data.avatar)
        }).catch(err => {
          console.log(err)
        })
      }
    }).catch(err => {
      console.log(err)
    })
  }



  next()
})