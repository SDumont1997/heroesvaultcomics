const app = Vue.createApp({
    data(){
        return {
            firstName: "",
            lastName: "",
            birthDate: "",
            username: "",
            email : "",
            password: "",
            signUpErrorMessage: ""
        }
    },
    created(){
    },
    methods: {
        signUp(){
            axios.post("/api/appUsers", {username: this.username, email: this.email, password: this.password, firstName: this.firstName, lastName: this.lastName, birthDate: this.birthDate})
            .then(response=> {
                this.logIn(this.email, this.password)
            })
            .catch(error => {
                this.signUpErrorMessage = error.response.data
            })
        },
        logIn(email, password){
            axios.post("/api/login", `email=${email}&password=${password}`)
            .then(response=> {
                window.location.replace("./index.html")
            })
        }
    },
    computed: {
        
    }
})

app.mount("#app")