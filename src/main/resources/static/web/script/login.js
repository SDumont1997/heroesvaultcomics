const app = Vue.createApp({
    data(){
        return {
           email : "",
           password: "",
           logInErrorMessage: ""
        }
    },
    created(){
    },
    methods: {
        logIn(){
            axios.post("/api/login", `email=${this.email}&password=${this.password}`)
            .then(response=> {
                window.location.replace("./index.html")
            })
            .catch(error => {
                if(error.response.status === 409){
                    this.logInErrorMessage = "User already has an ongoing session"
                } else {
                    this.logInErrorMessage = "Invalid username or password"
                }
            })
        }
    },
    computed: {
        
    }
})

app.mount("#app")