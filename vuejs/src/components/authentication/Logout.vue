<template>
  <div></div>
</template>

<script>
export default {
  name: "Logout",

  data () {
    return {};
  },
  mounted () {
    localStorage.removeItem("token");

    localStorage.removeItem("logged");

    localStorage.clear();
  },
  methods: {}
};
</script>

<style lang="scss" scoped></style>
