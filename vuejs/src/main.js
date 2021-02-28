import Vue from "vue";
import App from "./App.vue";
import Home from "./components/Home.vue";
import Login from "./components/authentication/Login.vue";
import Logout from "./components/authentication/Logout.vue";
import Register from "./components/authentication/Register.vue";
import VueRouter from "vue-router";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import Vuelidate from "vuelidate";
import DataTable from "./components/DataTable.vue";
import ExcelFile from "./components/ExcelFile.vue";
import VueGoodTablePlugin from "vue-good-table";
// import the styles
import "vue-good-table/dist/vue-good-table.css";

import VueExcelEditor from 'vue-excel-editor'
Vue.use(VueExcelEditor)

Vue.use(VueGoodTablePlugin);
axios.defaults.baseURL = "http://localhost:8080/";
axios.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
Vue.prototype.$http = axios;
Vue.use(axios);
Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
Vue.use(VueRouter);
Vue.use(Vuelidate);
import { VuejsDatatableFactory } from "vuejs-datatable";
Vue.use(VuejsDatatableFactory);
axios.interceptors.request.use(function (config) {
  // Do something before request is sent
  console.log(" interceptor ");
  if( localStorage.getItem('token')!= null){
    config.headers = { 
      'Authorization': 'Bearer ' + localStorage.getItem('token') ,
      'Accept': 'application/json',
    }
  }
  
  return config;
}, function (error) {
  // Do something with request error
  localStorage.removeItem('token');
  return Promise.reject(error);
});

// Add a response interceptor
axios.interceptors.response.use(function (response) {
  // Any status code that lie within the range of 2xx cause this function to trigger
  // Do something with response data
  console.log(" interceptor ");
  return response;
}, function (error) {
  localStorage.removeItem('token');
  // Any status codes that falls outside the range of 2xx cause this function to trigger
  // Do something with response error
  return Promise.reject(error);
});

const routes = [
  { path: "/home", component: Home },
  { path: "/author/login", component: Login },
  { path: "/author/logout", component: Logout },
  { path: "/author/register", component: Register },
  { path: "/company/search", component: DataTable },
  { path: "/company/upload/excel", component: ExcelFile },
  
];

Vue.config.productionTip = false;
const router = new VueRouter({
  routes, // short for `routes: routes`
  mode: "history"
});

new Vue({
  router,
  render: h => h(App)
}).$mount("#app");
