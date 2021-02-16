import request from '@/utils/request'

import http from '@/wuxyUtils'

// export function login (data) {
//   return request({
//     url: '/vue-element-admin/user/login',
//     method: 'post',
//     data
//   })
// }

export function login (data) {
  return http({
    url: '/auth/user/login02',
    method: 'post',
    data
  })
}

export function getInfo (token) {
  return http({
    url: '/auth/user/info',
    method: 'post',
    params: { token }
  })
}

export function register (data) {
  return http({
    url: '/auth/user/register',
    method: 'post',
    data
  })
}


export function logout () {
  return request({
    url: '/vue-element-admin/user/logout',
    method: 'post'
  })
}
