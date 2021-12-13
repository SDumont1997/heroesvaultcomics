const app = Vue.createApp({
    data(){
        return {
            appUser: {},
            loggenIn: false
        }
    },
    created(){
        this.loadData();
    },
    methods: {
        loadData(){
            axios.get("/api/appUsers/current")
            .then(response => {
                console.log(response)
                // this.appUser = response.data
                // this.loggedIn = true
            })
            .catch(error => {
                console.log("No user currently active")
            })
        }
    },
    computed: {
        
    }
})

const consola = app.mount("#app")