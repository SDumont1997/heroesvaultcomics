const app = Vue.createApp({
    data(){
        return {
            appUser: {},
            loggedIn: false,
            purchases:[]
        }
    },
    created(){
        this.loadData();
        this.getPurchase();
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
        },
        getPurchase(){
            axios.get("/api/purchases")
            .then(response => {
                this.purchases = response.data
            })
            .catch(err => {
                console.log(err.data.response)
            })
        },
        getPurchasePdf(e){
            axios.post("/api/purchases/export/pdf", `purchaseId=${e.target.value}`, {responseType: 'blob'})
            .then(response=> {
                let file = response.headers['content-disposition']
                let fileName = decodeURI(file.substring(20))
                let link = document.createElement('a')
                link.href= URL.createObjectURL(response.data)
                link.download = fileName
                link.click()
                link.remove()
            })
        }
    },
    computed: {
        
    }
})

let asd = app.mount("#app")