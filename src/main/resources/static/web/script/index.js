const app = Vue.createApp({
    data(){
        return {
            appUser: {},
            loggedIn: false
        }
    },
    created(){
        this.loadData();
    },
    methods: {
        loadData(){
            axios.get("/api/appUsers/current")
            .then(response => {
                this.appUser = response.data
                this.loggedIn = true
            })
            .catch(error => {
                console.log("No user currently active")
            })
        },
        logOut(){
            axios.post("/api/logout")
            .then(response => {
                window.location.replace("/web/index.html")
            })
        }
    },
    computed: {
        
    }
})

const consola = app.mount("#app")