const app = Vue.createApp({
    data(){
        return {
            adminClass: "",
            publisherCreationForm: {
                name: "",
                creationDate: ""
            },
            authorCreationForm: {
                firstName: "",
                lastName: "",
                birthDate: "",
                birthPlace: ""
            },
            characterCreationForm: {
                name: "",
                alias: "",
                firstAppearance: "",
                birthPlace: "",
                authorId: "",
                publisherId: "",
                imgUrl: ""
            },
            comicCreationForm: {
                title: "",
                authorId: "",
                publicationDate: "",
                publisherId: "",
                price: "",
                stock: "",
                coverImgUrl: ""
            },
            merchCreationForm: {
                name: "",
                merchType: "",
                characterId: "",
                price: "",
                stock: "",
                imgUrl: ""
            },
            appUserUpdateForm: {
                userEmail: "",
                admin: ""
            },
            comicUpdateForm: {
                comicId: "",
                addedProtagonistId: "",
                price: "",
                stock: ""
            },
            merchUpdateForm: {
                merchId: "",
                price: "",
                stock: ""
            },
            publishers: [],
            authors: [],
            characters : [],
            comics: [],
            merch: []
        

        }
    },
    created(){
        this.loadPublishers()
        this.loadAuthors()
        this.loadCharacters()
        this.loadComics()
        this.loadMerch()
    },
    methods: {
        changeAdminClass(event){
            this.adminClass = event.target.value
        },
        loadPublishers(){
            axios.get("/api/publishers")
            .then(response => {
                this.publishers = response.data
            })
            .catch(error => {
                console.log(error)
            })
        },
        createPublisher(){
            axios.post("/api/publishers", this.publisherCreationForm)
            .then(response => {
                window.location.reload()
            })
            .catch(error =>{
                console.log(error.response.data)
            })
        },
        loadAuthors(){
            axios.get("/api/authors")
            .then(response => {
                this.authors = response.data
            })
            .catch(error => {
                console.log(error)
            })
        },
        createAuthor(){
            axios.post("/api/authors", this.authorCreationForm)
            .then(response => {
                window.location.reload()
            })
            .catch(error => {
                console.log(error)
            })
        },
        loadCharacters(){
            axios.get("/api/characters")
            .then(response => {
                this.characters = response.data
            })
            .catch(error => {
                console.log(error)
            })
        },
        createCharacter(){
            axios.post("/api/characters", this.characterCreationForm)
            .then(response => {
                window.location.reload()
            })
            .catch(error => {
                console.log(error)
            })
        },
        loadComics(){
            axios.get("/api/comics")
            .then(response => {
                this.comics = response.data
            })
            .catch(error => {
                console.log(error)
            })
        },
        createComic(){
            axios.post("/api/comics", this.comicCreationForm)
            .then(response => {
                window.location.reload()
            })
            .catch(error => {
                console.log(error)
            })
        },
        updateComic(){
            axios.post("/api/comics/update", `id=${this.comicUpdateForm.comicId}&characterId=${this.comicUpdateForm.addedProtagonistId}&price=${this.comicUpdateForm.price}&stock=${this.comicUpdateForm.stock}`)
            .then(response => {
                window.location.reload()
            })
            .catch(error => {
                console.log(error.response)
            })
        },
        loadMerch(){
            axios.get("/api/merch")
            .then(response => {
                this.merch = response.data
            })
            .catch(error => {
                console.log(error)
            })
        },
        createMerch(){
            axios.post("/api/merch", this.merchCreationForm)
            .then(response => {
                window.location.reload()
            })
            .catch(error => {
                console.log(error)
            })
        },
        updateMerch(){
            axios.post("/api/merch/update", `id=${this.merchUpdateForm.merchId}&price=${this.merchUpdateForm.price}&stock=${this.merchUpdateForm.stock}`)
            .then(response => {
                window.location.reload()
            })
            .catch(error => {
                console.log(error)
            })
        },
        updateAppUser(){
            if(this.appUserUpdateForm.admin === "false"){
                axios.post("/api/appUsers/revokeAdmin", `email=${this.appUserUpdateForm.userEmail}`)
                .then(response => {
                    window.location.reload()
                })
                .catch(error => {
                    console.log(error)
                })
            }
            if(this.appUserUpdateForm.admin === "true"){
                axios.post("/api/appUsers/setAdmin", `email=${this.appUserUpdateForm.userEmail}`)
                .then(response => {
                    window.location.reload()
                })
                .catch(error => {
                    console.log(error)
                })
            }
        }
        
    },
    computed: {
        
    }
})

app.mount("#app")