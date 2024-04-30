<template>
  <div>
   <!-- <h1>{{massage}}</h1> -->
   <!-- <element-view></element-view> -->
   <!-- <emp-view></emp-view> -->
   <!-- 调用router路由对视图进行动态展示组件 -->
   <router-view></router-view>
  </div>
</template>

<script>
// import EmpView from './views/tlias/EmpView.vue'
// import ElementView from './views/element/ElementView.vue'
export default {
  // components: { EmpView },
  // components: { ElementView },
  data() {
    return {
      massage:"Hello Vue"
    }
  },
  methods: {
    
  },
}
</script>
<style>

</style>
