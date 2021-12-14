const app = Vue.createApp({

    created(){
        this.loadData();
        this.loadComics();
    },

    data() {
        return {
            comics: [],
            merch: [],
            comicsVisible: true,
            cartItems: [],
            totalItems: 0,
            totalAmount: 0,
            appUser: {},
            loggedIn: false
        }
    },

    methods: {
        loadCartItems(){
            let items = JSON.parse(localStorage.getItem("cart"));
            this.cartItems = items != null ? items : [];
            this.calculateTotals();
            for(var i = 0; i < this.cartItems.length; i++){
                let item = this.cartItems[i];
                if(item.productType == 'comic'){
                    let comic = this.comics.filter(c => c.id == item.productId);
                    comic[0].stock -= item.productQuantity;
                }else{
                    this.merch.filter(m => m.id == item.productId)[0].stock -= item.productQuantity;
                }
            }
        },

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
        loadComics(){
            axios.get('/api/comics')
            .then(resp => {
                this.comics = resp.data;          
                this.loadMerch()      
            })
        },
        loadMerch(){
            axios.get('/api/merch')
            .then(resp => {
                this.merch = resp.data;
                this.loadCartItems();
            })
        },
        showComics(visible){
            this.comicsVisible = visible;
        },

        addProductToCart(event){
            let quantity = parseInt(event.target.elements.quantity.value);
            let productId = parseInt(event.target.elements.addProductItem.id);
            let productType = event.target.elements.productType.value;

            let products = this.cartItems.filter(p => productId == p.productId && productType == p.productType);            
            
            if(products.length == 0){
                let productInfo = {};

                if (productType == 'comic'){
                    let comicInfo = this.comics.filter(c => productId == c.id)[0];
                    productInfo = {productId:productId, productQuantity:quantity, productType:'comic', productImg: comicInfo.coverImgUrl, productPrice: comicInfo.price};
                }  else {
                    let merchInfo = this.merch.filter(c => productId == c.id)[0];
                    productInfo = {productId:productId, productQuantity:quantity, productType:'merch', productImg: merchInfo.imgUrl, productPrice: merchInfo.price};
                }                
                
                this.cartItems.push(productInfo);

            }else{
                products[0].productQuantity += quantity;
            }

            this.calculateTotals();
            this.updateStock(productId, productType, quantity);
            localStorage.setItem("cart", JSON.stringify(this.cartItems))
        },
        calculateTotals(){
            this.totalAmount = this.cartItems.reduce(function(total, item){return total + item.productQuantity * item.productPrice;},0);
            this.totalItems = this.cartItems.reduce(function(total, item){return total + item.productQuantity}, 0);
        },
        removeItem(productId, productType){
            let product = this.cartItems.filter(p => productId == p.productId && productType == p.productType)[0];
            if(product.productQuantity > 1){
                product.productQuantity -= 1;
                this.updateStock(productId, productType, -1);
                localStorage.setItem("cart", JSON.stringify(this.cartItems));
            }
        },
        addItem(productId, productType){
            let product = this.cartItems.filter(p => productId == p.productId && productType == p.productType)[0];
            if(productType == 'comic'){
                let comic = this.comics.filter(c => productId == c.id)[0];
                if(comic.stock >= 1){
                    product.productQuantity += 1;
                    this.updateStock(productId, productType, 1);        
                    localStorage.setItem("cart", JSON.stringify(this.cartItems));
                }
            }else{
                let merch = this.merch.filter(m => productId == m.id)[0];
                if(merch.stock >= 1){
                    product.productQuantity += 1;
                    this.updateStock(productId, productType, 1);
                    localStorage.setItem("cart", JSON.stringify(this.cartItems));
                }
            }
        },
        updateStock(productId, productType, productQuantity){
            if(productType == 'comic'){
                let comic = this.comics.filter(c => productId == c.id)[0];
                comic.stock -= productQuantity;
            }else{
                let merch = this.merch.filter(m => productId == m.id)[0];
                merch.stock -= productQuantity;
            }
        },
        removeCartItem(productId, productType){
            let product = this.cartItems.filter(p => productId == p.productId && productType == p.productType)[0];
            let index = this.cartItems.indexOf(product);
            this.cartItems.splice(index, 1);
            this.calculateTotals();
            this.updateStock(productId, productType, -product.productQuantity);
            localStorage.setItem("cart", JSON.stringify(this.cartItems))
        },
        checkout(){
            // localStorage.setItem("cartProducts", JSON.stringify(this.cartItems));
            // window.location.replace("/web/checkout.html")
        },
        logOut(){
            axios.post("/api/logout")
            .then(response => {
                window.location.replace("/web/index.html")
            })
        }
    },

})
let asd = app.mount("#app")