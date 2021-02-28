<template>
  <div id="app">
    <JMenu></JMenu>
     <router-view></router-view>
 </div>
</template>

<script>
import JMenu from './components/JMenu.vue'
export default {
  name: 'App', 
  components: {
    JMenu
  }

}
</script>
