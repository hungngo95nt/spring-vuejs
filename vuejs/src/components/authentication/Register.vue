<template>
   <div class="container">
    
    <b-form @submit="onSubmit" @reset="onReset" v-if="show">
      <b-form-group
        id="input-group-1"
        label="Your email :"
        label-for="input-1"
      >
        <b-form-input
          id="input-1"
          v-model="form.email"
          type="email"
          placeholder="Enter email"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Your Name:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.name"
          placeholder="Enter name"
          required
        ></b-form-input>
      </b-form-group>

       <b-form-group id="input-group-2" label="Your Password:" label-for="input-3">
        <b-form-input
          id="input-3"
          v-model="form.password"
          placeholder="Enter password"
          type="password"
          required
        ></b-form-input>
      </b-form-group>
      <b-form-group id="input-group-4" label="Role " label-for="input-4">
         <b-form-select v-model="form.role" :options="options"></b-form-select>
      </b-form-group>
       <b-form-group>
            <b-button type="submit" variant="primary">Submit</b-button>
            <b-button type="reset" variant="danger">Reset</b-button>
         </b-form-group>
    </b-form>
    
  </div>
</template>

<script>
//import Menu from './components/Menu.vue'
  export default {
     name: 'Register',
     components: {
     
    },
    data() {
      return {
        form: {
          email: '',
          name: '',
          password: '',
          role: '',
        },
        options: [
          { value: null, text: 'Please select an option' },
          { value: 'Admin', text: 'Admin' },
          { value: 'Writer', text: 'Writer'},
          { value: 'Reader', text: 'Reader'},
        ],
        show: true,
        message : '',
      }
    },
    methods: {
      onSubmit(event) {
        event.preventDefault()
        alert(JSON.stringify(this.form));
        console.log(" conmit");
        console.log(this.form);
        this.$http.post("http://localhost:8090/users/api/signup", this.form).then(response => {
          console.log(response.data);
      });

      },
      onReset(event) {
        event.preventDefault()
        // Reset our form values
        this.form.email = ''
        this.form.name = ''
        this.form.role = ''
        this.form.password = ''
      }
    }
  }
</script>
