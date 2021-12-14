const app = Vue.createApp({
    data(){
        return{
            personaje:[],
            apariciones:[],
            appUser: {},
            loggedIn: false
        }
    },
    created(){
        const urlSearchParams = new URLSearchParams(window.location.search);
        const params = Object.fromEntries(urlSearchParams.entries());

        axios.get("/api/characters/" + params.id)
        .then(response => {
            this.personaje = response.data
            console.log(response.data)
            this.apariciones = response.data.appearances
            console.log(response.data.appearances)
        })
        .catch(error => console.log(error.message));
        this.loadData();
    },
    methods:{
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
    }
})
app.mount("#app")