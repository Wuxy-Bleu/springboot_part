import axios from 'axios'

// create an axios instance
const http = axios.create({
  baseURL: 'http://localhost:81/',
  timeout: 5000 // request timeout
})

//没用到
// export function wuxyHttp (query) {
//   return request({
//     url: '/backend/music',
//     method: 'get',
//     params: null
//   })
// }

export function getToken () {
  return Cookies.get(TokenKey)
}

export default http;