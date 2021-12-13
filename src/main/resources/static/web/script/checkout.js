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
           comics: [],
           merch: [],
           
        }
    },
    created(){
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
            axios.post("/api/purchases")
        }
        
    },
    computed: {
        
    }
})

app.mount("#app")