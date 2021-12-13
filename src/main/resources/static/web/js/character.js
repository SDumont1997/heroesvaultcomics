const app = Vue.createApp({
    data(){
        return{
            personaje:[],
            apariciones:[]
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
        })
        .catch(error => console.log(error.message))
    },
    methods:{

    }
})
app.mount("#app")