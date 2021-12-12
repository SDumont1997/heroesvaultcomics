const app = Vue.createApp({

    created(){
        this.loadComics();
        this.loadMerch();
    },

    data() {
        return {
            comics: [],
            merch: [],
            comicsVisible: true,
            totalItems: 0,
            cartItems: [],
            totalAmount: 0,
        }
    },

    methods: {
        loadComics(){
            axios.get('/api/comics')
            .then(resp => {
                this.comics = resp.data;
            })
        },
        loadMerch(){
            axios.get('/api/merch')
            .then(resp => {
                this.merch = resp.data;
            })
        },
        showComics(visible){
            this.comicsVisible = visible;
        },
        addComicToCart(event){
            let quantity = event.target.elements.quantity.value;
            let comicId = event.target.elements.addComicItem.id;

            this.totalItems += parseInt(quantity);
            let comicInfo = this.comics.filter(c => comicId == c.id)[0];
            this.cartItems.push({productId:comicId, productQuantity:parseInt(quantity), productType:'comic', productImg: comicInfo.coverImgUrl, productPrice: comicInfo.price})
            this.totalAmount = this.cartItems.reduce(function(total, item){return total + item.productQuantity * item.productPrice;},0);
        },
        addMerchToCart(event){
            let quantity = event.target.elements.quantity.value;
            let merchId = event.target.elements.addMerchItem.id;

            this.totalItems += parseInt(quantity);
            let merchInfo = this.merch.filter(c => merchId == c.id)[0];
            this.cartItems.push({productId:merchId, productQuantity:parseInt(quantity), productType:'merch', productImg: merchInfo.imgUrl, productPrice: merchInfo.price})
            this.totalAmount = this.cartItems.reduce(function(total, item){return total + item.productQuantity * item.productPrice;},0);
        },
    },

})
app.mount("#app")