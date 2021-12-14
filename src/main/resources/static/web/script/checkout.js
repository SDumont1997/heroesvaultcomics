const app = Vue.createApp({
    data(){
        return {
            paymentOption: "",
            cardNumber: "",
            cvv: "",
            amount: 0,
            storeName: "Heroes Vault Comics",
            detail: "Purchase",
            errorMessage: "",
            comicIds: [],
            merchIds: [],
            appUser: {},
            loggedIn: false,
            cart: []

        }
    },
    created(){
        this.loadData()
        
    },
    methods: {
        confirmPurchase(){
            axios.post("https://mindhub-brothers-bank.herokuapp.com/api/cards/purchase", {cardNumber: this.cardNumber, cvv: this.cvv, amount: this.amount, storeName: this.storeName, detail: this.detail})
            .then(response => {
                this.registerPurchase()
            })
            .catch(error => {
                this.errorMessage = error.response.data
            })
        },
        registerPurchase(){
            axios.post("/api/purchases", {comicIds: this.comicIds, merchIds: this.merchIds, amount: this.amount, paymentOption: this.paymentOption, cardNumber: this.cardNumber, cardCvv: this.cvv})
            .then(response => {
                localStorage.removeItem("cart")
                window.location.replace("./index.html")
            })
            .catch(error => {
                this.errorMessage = error.response.data
            })
        },
        loadData(){
            axios.get("/api/appUsers/current")
            .then(response => {
                this.appUser = response.data
                this.loggedIn = true
                this.cart = JSON.parse(localStorage.getItem("cart"))
                this.splitCart()
            })
            .catch(error => {
                console.log("No user currently active")
            })
        },
        splitCart(){
            let comics = [...this.cart].filter(function(cartItem){
               return cartItem.productType == "comic"
            })
            this.comicIds = comics.map(function(comic){
                return comic.productId
            })
            console.log(this.comicIds)
            let merch = [...this.cart].filter(function(cartItem){
                return cartItem.productType == "merch"
            })
            this.merchIds = merch.map(function(merchItem){
                return merchItem.productId
            })
            console.log(this.merchIds)
            this.amount = this.cart.reduce(function(total, item){return total + item.productQuantity * item.productPrice;},0);
            console.log(this.amount)

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

let asd = app.mount("#app")