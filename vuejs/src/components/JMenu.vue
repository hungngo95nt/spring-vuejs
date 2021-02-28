<template>
    <div class="container" id="jmenu">
       <div>
        <b-navbar type="dark" variant="dark">
            <b-navbar-nav>
            <b-nav-item><router-link to="/home">Home</router-link></b-nav-item>
            <b-nav-item ><router-link to="/company/search">Search </router-link></b-nav-item>
            <b-nav-item><router-link to="/company/upload/excel">Search</router-link> </b-nav-item>
            <!-- Navbar dropdowns -->
            <b-nav-item-dropdown text="Account" left>
                <b-dropdown-item><router-link to="/author/login">Login</router-link></b-dropdown-item>
                <b-dropdown-item ><router-link to="/author/logout">Logout</router-link></b-dropdown-item>
                <b-dropdown-item ><router-link to="/author/register">Register</router-link></b-dropdown-item>
            </b-nav-item-dropdown>
            </b-navbar-nav>
        </b-navbar>
        </div>
        </div>
</template>

<script>
export default {
    name: 'JMenu'
};
</script>
<style scoped>
b-nav-item-dropdown {
  padding-top: 50px;
  padding-right: 50px;
  padding-bottom: 50px;
  padding-left: 10px;
} 
#jmenu{
    margin-bottom: 5%;

}
</style>
