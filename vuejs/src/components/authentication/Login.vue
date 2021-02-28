<template>
<b-container>
  <b-row>
       <div class="col-sm-8 offset-sm-2">
                    <div>
                        <h2>Login Form - Form Validation</h2>
                        <form @submit.prevent="handleSubmit">
                            <div class="form-group">
                                <label for="firstName">First Name</label>
                                <input type="text" v-model="user.firstName" id="firstName" name="firstName" class="form-control" :class="{ 'is-invalid': submitted && $v.user.firstName.$error }" />
                                <div v-if="submitted && !$v.user.firstName.required" class="invalid-feedback">First Name is required</div>
                            </div>
                         
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="password" v-model="user.password" id="password" name="password" class="form-control" :class="{ 'is-invalid': submitted && $v.user.password.$error }" />
                                <div v-if="submitted && $v.user.password.$error" class="invalid-feedback">
                                    <span v-if="!$v.user.password.required">Password is required</span>
                                    <span v-if="!$v.user.password.minLength">Password must be at least 6 characters</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <button class="btn btn-primary">Login Now</button>
                            </div>
                        </form>
            </div>
        </div>
  </b-row>
</b-container>
</template>

<script>
    import { required, minLength } from "vuelidate/lib/validators";
    export default {
        name: "Login",
        data() {
            return {
                user: {
                    firstName: "",
                    password: "",
                },
                submitted: false
            };
        },
        validations: {
            user: {
                firstName: { required },
                password: { required, minLength: minLength(6) },
            }
        },
        methods: {
             handleSubmit() {
                
                this.submitted = true;
                this.$v.$touch();
                if (this.$v.$invalid) {
                    return;
                }
                alert("SUCCESS!! :-)\n\n" + JSON.stringify(this.user));
                this.$http.post('http://localhost:8090/users/signin?username='+this.user.firstName + '&password=' + this.user.password)
                .then(response => {
                           localStorage.setItem('token',response.data);
                           console.log("step 1 " + localStorage.getItem('token'));
                }).catch(error =>{
                         localStorage.removeItem('token');
                         localStorage.clear();
                         console.log(error);
                     });
            }
        }
    };
</script>