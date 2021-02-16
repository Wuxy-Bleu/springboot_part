import router from './router'
import store from './store'
import { Message } from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import { getToken } from '@/utils/auth' // get token from cookie
import getPageTitle from '@/utils/get-page-title'

import Cookies from 'js-cookie'
import http from '@/wuxyUtils'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

const whiteList = ['/login', '/auth-redirect'] // no redirect whitelist

router.beforeEach(async (to, from, next) => {
  // start progress bar
  NProgress.start()

  // set page title
  document.title = getPageTitle(to.meta.title)

  // determine whether the user has logged in
  const hasToken = getToken()

  console.log(hasToken == null)

  if (hasToken) {

    var isLogin = ""
    await http
      .get("auth/redis/isLogin", {
        params: {
          token: hasToken
        }
      })
      .then(res => {
        // console.log(res.data)
        isLogin = res.data;
      })
      
    if (isLogin.isLogin == 'true') {
      // if (isLogin.isLogin == "true")

      // console.log('怎么在这里访问store的token数据啊')
      // console.log(store.getters.token)
      // console.log(from)
      // console.log(to)
      // console.log(next)

      if (to.path === '/login') {
        // if is logged in, redirect to the home page
        next({ path: '/' })
        NProgress.done() // hack: https://github.com/PanJiaChen/vue-element-admin/pull/2939
      } else {
        // determine whether the user has obtained his permission roles through getInfo
        const hasRoles = store.getters.roles && store.getters.roles.length > 0
        // console.log("进入系统了, 是否有角色信息   " + hasRoles)

        if (hasRoles) {
          next()
        } else {
          try {
            // console.log("当没有用户身份信息，会发送请求获取用户的角色信息")

            // get user info
            // note: roles must be a object array! such as: ['admin'] or ,['developer','editor']



            const { roles } = await store.dispatch('user/getInfo')

            // generate accessible routes map based on roles
            const accessRoutes = await store.dispatch('permission/generateRoutes', roles)

            // dynamically add accessible routes
            router.addRoutes(accessRoutes)

            // hack method to ensure that addRoutes is complete
            // set the replace: true, so the navigation will not leave a history record
            next({ ...to, replace: true })
          } catch (error) {
            // remove token and go to login page to re-login
            await store.dispatch('user/resetToken')
            Message.error(error || 'Has Error')
            next(`/login?redirect=${to.path}`)
            NProgress.done()
          }
        }
      }
    }

    else {
      console.log('redis中没有记录')

      if (whiteList.indexOf(to.path) !== -1) {
        // in the free login whitelist, go directly
        next()
      } else {
        // other pages that do not have permission to access are redirected to the login page.
        next(`/login?redirect=${to.path}`)
        NProgress.done()
      }
    }
  }
  else {
    if (whiteList.indexOf(to.path) !== -1) {
      // in the free login whitelist, go directly
      next()
    } else {
      // other pages that do not have permission to access are redirected to the login page.
      next(`/login?redirect=${to.path}`)
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  // finish progress bar
  NProgress.done()
})
