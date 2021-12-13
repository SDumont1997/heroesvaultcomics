const app = Vue.createApp({
    data(){
        return{
            personajes:[],
            appUser: {},
            loggedIn: false
        }
    },
    created(){
        axios.get("/api/characters")
        .then(response => {
            console.log(response.data)
            this.personajes = response.data
            this.personajes.sort((a,b) => a.alias>b.alias ? 1 : -1)//ASC
        })
        .catch(e => console.log(e.message));
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