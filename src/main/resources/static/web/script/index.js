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
            axios.get("/api/clients/current")
            .then(response => {
                this.appUser = response.data
                this.loggedIn = true
            })
            .catch(error => {
                console.log("No user currently active")
            })
        }
    },
    computed: {
        
    }
})

app.mount("#app")