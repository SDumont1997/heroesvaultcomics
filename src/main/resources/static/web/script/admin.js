const app = Vue.createApp({
    data(){
        return {
            adminClass: "",
            publisherCreationForm: {
                name: "",
                date: ""
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
            },
            comicCreationForm: {
                title: "",
                authorId: "",
                publicationDate: "",
                publisherId: "",
                price: "",
                stock: ""
            },
            merchCreationForm: {
                name: "",
                merchType: "",
                characterId: "",
                price: "",
                stock: "" 
            },
            appUserUpdateForm: {
                userId: "",
                admin: ""
            },
            comicUpdateForm: {
                comicId: "",
                price: "",
                stock: ""
            },
            merchUpdateForm: {
                merchId: "",
                price: "",
                stock: ""
            }

        }
    },
    created(){
    },
    methods: {
        changeAdminClass(event){
            this.adminClass = event.target.value
        }
        
    },
    computed: {
        
    }
})

app.mount("#app")