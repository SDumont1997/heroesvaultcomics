const app = Vue.createApp({
    data(){
        return{
            personajes:[]
        }
    },
    created(){
        axios.get("/api/characters")
        .then(response => {
            console.log(response.data)
            this.personajes = response.data
            this.personajes.sort((a,b) => a.alias>b.alias ? 1 : -1)//ASC
        })
        .catch(e => console.log(e.message))
    },
    methods:{

    }
})
app.mount("#app")