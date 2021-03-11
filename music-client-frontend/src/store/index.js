import configure from './configure'
import user from './user'
import song from './song'
import Vuex from 'vuex'
import Vue from 'vue'

Vue.use(Vuex)

const store = {
  modules: {
    configure,
    user,
    song
  }
}

export default new Vuex.Store(store)

