webpackJsonp([4],{"7Fuv":function(t,e){},A82U:function(t,e,n){"use strict";var s=n("BO1k"),i=n.n(s),a=n("Dd8w"),r=n.n(a),c=n("NYxO"),o=n("msXN"),l=n("gyMJ"),u={name:"comment",mixins:[o.a],props:{playId:Number,type:Number},data:function(){return{commentList:[],userPic:[],userName:[],textarea:""}},computed:r()({},Object(c.b)(["id","userId","index","loginIn","avator"])),watch:{id:function(){this.getComment()}},mounted:function(){this.getComment()},methods:{getComment:function(){var t=this;Object(l.c)(this.type,this.playId).then(function(e){t.commentList=e.data;var n=!0,s=!1,a=void 0;try{for(var r,c=i()(e.data);!(n=(r=c.next()).done);n=!0){var o=r.value;t.getUsers(o.userId)}}catch(t){s=!0,a=t}finally{try{!n&&c.return&&c.return()}finally{if(s)throw a}}}).catch(function(t){console.log(t)})},getUsers:function(t){var e=this;Object(l.p)(t).then(function(t){e.userPic.push(t.data.avator),e.userName.push(t.data.name)}).catch(function(t){console.log(t)})},postComment:function(){var t=this;if(this.loginIn){var e=new URLSearchParams;1===this.type?e.append("songListId",this.playId):0===this.type&&e.append("songId",this.playId),e.append("userId",this.userId),e.append("type",this.type),e.append("content",this.textarea),Object(l.t)(e).then(function(e){1===e.data.code?(t.textarea="",t.getComment(),t.notify("评论成功","success")):t.notify("评论失败","error")}).catch(function(t){console.log(t)})}else this.notify("请先登录","warning")},postUp:function(t,e,n){var s=this;if(this.loginIn){var i=new URLSearchParams;i.append("id",t),i.append("up",e+1),Object(l.u)(i).then(function(t){1===t.data.code&&(s.$refs.up[n].children[0].style.color="#2796dd",s.getComment())}).catch(function(t){console.log(t)})}else this.notify("请先登录","warning")}}},d={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("div",{staticClass:"comment"},[n("h2",[n("span",[t._v("评论")]),t._v(" "),n("span",{staticClass:"part__tit_desc"},[t._v("共 "+t._s(t.commentList.length)+" 条评论")])]),t._v(" "),n("div",{staticClass:"comment-msg"},[n("el-input",{staticClass:"comment-input",attrs:{type:"textarea",placeholder:"期待您的精彩评论...",rows:2},model:{value:t.textarea,callback:function(e){t.textarea=e},expression:"textarea"}})],1),t._v(" "),n("el-button",{staticClass:"sub-btn",attrs:{type:"primary"},on:{click:function(e){return t.postComment()}}},[t._v("发表评论")])],1),t._v(" "),t._l(t.commentList,function(e,s){return n("ul",{key:s,staticClass:"popular"},[n("li",[n("div",{staticClass:"popular-img"},[n("img",{attrs:{src:t.userPic[s],alt:""}})]),t._v(" "),n("div",{staticClass:"popular-msg"},[n("ul",[n("li",{staticClass:"name"},[t._v(t._s(t.userName[s]))]),t._v(" "),n("li",{staticClass:"content"},[t._v(t._s(e.content))]),t._v(" "),n("li",{staticClass:"time"},[t._v(t._s(e.createTime))])])]),t._v(" "),n("div",{ref:"up",refInFor:!0,staticClass:"up",on:{click:function(n){return t.postUp(e.id,e.up,s)}}},[n("svg",{staticClass:"icon",attrs:{"aria-hidden":"true"}},[n("use",{attrs:{"xlink:href":"#icon-zan"}})]),t._v("\n        "+t._s(e.up)+"\n      ")])])])})],2)},staticRenderFns:[]};var p=n("VU/8")(u,d,!1,function(t){n("7Fuv")},"data-v-8d106738",null);e.a=p.exports},YTXR:function(t,e){},cOmO:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s=n("Dd8w"),i=n.n(s),a=n("NYxO"),r=n("msXN"),c=n("A82U"),o={name:"lyric",mixins:[r.a],components:{Comment:c.a},data:function(){return{lrcTop:"200px",showLrc:!1,lyr:[]}},computed:i()({},Object(a.b)(["curTime","id","lyric","listOfSongs","listIndex"])),watch:{id:function(){this.lyr=this.parseLyric(this.listOfSongs[this.listIndex].lyric)},curTime:function(){if(0!==this.lyr.length)for(var t=0;t<this.lyr.length;t++)if(this.curTime>=this.lyr[t][0]){for(var e=0;e<this.lyr.length;e++)document.querySelectorAll(".has-lyric li")[e].style.color="#000",document.querySelectorAll(".has-lyric li")[e].style.fontSize="15px";t>=0&&(document.querySelectorAll(".has-lyric li")[t].style.color="#95d2f6",document.querySelectorAll(".has-lyric li")[t].style.fontSize="25px")}}},created:function(){this.lyr=this.lyric?this.lyric:[]}},l={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"song-lyric"},[n("h1",{staticClass:"lyric-title"},[t._v("歌词")]),t._v(" "),n("transition-group",{attrs:{name:"lyric-fade"}},[t.lyr.length?n("ul",{key:"has-lyric",staticClass:"has-lyric",style:{top:t.lrcTop}},t._l(t.lyr,function(e,s){return n("li",{key:s},[t._v("\n        "+t._s(e[1])+"\n      ")])}),0):n("div",{key:"no-lyric",staticClass:"no-lyric"},[n("span",[t._v("暂无歌词")])])]),t._v(" "),n("comment",{attrs:{playId:t.id,type:0}})],1)},staticRenderFns:[]};var u=n("VU/8")(o,l,!1,function(t){n("YTXR")},"data-v-6e43636e",null);e.default=u.exports}});
//# sourceMappingURL=4.5b92e185831681eb5772.js.map