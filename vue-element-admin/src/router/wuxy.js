import Layout from '@/layout'

const wuxyRoute =
{
  path: '/music',
  component: Layout,
  redirect: '/music/music-menu',
  name: 'music',
  meta: {
    title: 'music',
    icon: 'el-icon-coffee-cup'
  },
  children: [
    {
      path: 'music-menu',
      component: () => import('@/views/table/inline-edit-table'),
      name: 'music-menu',
      meta: { title: 'music-menu', icon: 'el-icon-cold-drink' }
    },
    {
      path: 'playlist-menu',
      component: () => import('@/views/table/complex-table'),
      name: 'playlist-menu',
      meta: { title: 'playlist-menu', icon: 'el-icon-goblet-square' }
    }
  ]
}

export default wuxyRoute

  //   path: '/permission',
  //   component: Layout,
  //   redirect: '/permission/page',
  //   alwaysShow: true, // will always show the root menu
  //   name: 'Permission',
  //   meta: {
  //     title: 'Permission',
  //     icon: 'lock',
  //     roles: ['admin', 'editor'] // you can set roles in root nav
  //   },
  //   children: [
  //     {
  //       path: 'page',
  //       component: () => import('@/views/permission/page'),
  //       name: 'PagePermission',
  //       meta: {
  //         title: 'Page Permission',
  //         roles: ['admin'] // or you can only set roles in sub nav
  //       }
  //     },
  //     {
  //       path: 'directive',
  //       component: () => import('@/views/permission/directive'),
  //       name: 'DirectivePermission',
  //       meta: {
  //         title: 'Directive Permission'
  //         // if do not set roles, means: this page does not require permission
  //       }
  //     },
  //     {
  //       path: 'role',
  //       component: () => import('@/views/permission/role'),
  //       name: 'RolePermission',
  //       meta: {
  //         title: 'Role Permission',
  //         roles: ['admin']
  //       }
  //     }