<template>
  <b-row>
  </b-row>
</template>

<script>
export default {
  name : 'Home',
  data: function() {
    return {
      post: ""
    };
  },
  created() {
    let token = localStorage.getItem('token');
    console.log(token);
    if (token === null || token === undefined){
      this.push('http://localhost:8080/author/login');
    }
  }
};
</script>