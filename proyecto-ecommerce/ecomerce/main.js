// Cambio de cantidad de articulos ingresado por el usuario.

let minusBtn = document.querySelector('.input__minus');
let plusBtn = document.querySelector('.input__plus');
let userInput = document.querySelector('.input__number');

let userInputNumber = 0;

plusBtn.addEventListener('click', ()=>{
    userInputNumber++;
    userInput.value = userInputNumber;
    console.log(userInputNumber);
});

minusBtn.addEventListener('click', ()=>{
    userInputNumber--;
    if(userInputNumber <=0){
        userInputNumber = 0;
    }
    userInput.value = userInputNumber;
    console.log(userInputNumber);
});

// Agregar el total de productos al carrito cuando se presiona el boton "Add to cart".

const addToCartBtn = document.querySelector('.details__button');
let cartNotification = document.querySelector('.header__cart--notification');
let lastValue = parseInt(cartNotification.innerText);

addToCartBtn.addEventListener('click', ()=>{

    lastValue = lastValue + userInputNumber;
  
    cartNotification.innerText = lastValue;
    cartNotification.style.display = 'block';
    drawProductinModal()
    priceModal.innerHTML = `$250 x${lastValue} <span>$${lastValue*250}</span>`;
});

//Mostrar modal con el detalle del carrito.

const cartIconBtn = document.querySelector('.header__cart');
const cartModal = document.querySelector('.cart-modal');
//let priceModal = document.querySelector('.cart-modal__price');
const productContainer = document.querySelector('.cart-modal__checkout-container');

cartIconBtn.addEventListener('click', ()=>{
    cartModal.classList.toggle('show');     //funcion 'toggle' funciona como add si no existe una clase y como remove si existe
    
    if(lastValue === 0){
        productContainer.innerHTML = '<p class="cart-empty">Your cart is empty</p>'
    }else{
        drawProductinModal();
    }
});

//Borrar el contenido del carrito.
function deleteProduct(){
    const deleteProductBtn = document.querySelector('.cart-modal__delete');
    deleteProductBtn.addEventListener('click', ()=>{
        productContainer.innerHTML = '<p class="cart-empty">Your cart is empty</p>'
        lastValue = 0;
        cartNotification.innerText = lastValue;
    })
}


//Cambiar imagenes cuando se presione los botones flecha.
const imageContainer = document.querySelector('.gallery__image-container');
const previusGalleryBtn = document.querySelector('.gallery__previus');
const nextGalleryBtn = document.querySelector('.gallery__next');

let imgIndex = 1;

nextGalleryBtn.addEventListener('click', ()=>{
    changeNextImage(imageContainer);
});

previusGalleryBtn.addEventListener('click', ()=>{
    changePreviusImage(imageContainer);
});

//Mostrar el modal de imagenes cuando hago click en imagen principal.
const imageModal = document.querySelector('.modal-gallery__background');
const closeModalBtn = document.querySelector('.modal-gallery__close');

imageContainer.addEventListener('click', ()=>{
    if(window.innerWidth >= 1115){
        imageModal.style.display = 'grid';
    }
});

closeModalBtn.addEventListener('click', ()=>{
    imageModal.style.display = 'none';
})

//Cambiar las imagenes principales desde los thumbnails;

let thumbnails = document.querySelectorAll('.gallery__thumnail');
thumbnails = [...thumbnails]

thumbnails.forEach(thumbnail => {
    thumbnail.addEventListener('click', event=>{
        console.log(event.target.id)
        imageContainer.style.backgroundImage = `url('./images/nike-${event.target.id}.jpg')`

    });
});

//Cambiar las imagenes principales desde los thumbnails en el modal;
let modalThumbnails = document.querySelectorAll('.modal-gallery__thumnail');
const modalImageContainer = document.querySelector('.modal-gallery__image-container');
modalThumbnails = [...modalThumbnails]

modalThumbnails.forEach(modalthumbnail =>{
    modalthumbnail.addEventListener('click', event=>{
        console.log(event.target.id.slice(-1))
        modalImageContainer.style.backgroundImage = `url('./images/nike-${event.target.id.slice(-1)}.jpg')`

    });
});

//Cambiar imagen principal del modal con las flechas del modal
const previusModalBtn = document.querySelector('.modal-gallery__previus');
const nextModalBtn = document.querySelector('.modal-gallery__next');

nextModalBtn.addEventListener('click', ()=>{
    changeNextImage(modalImageContainer);
});

previusModalBtn.addEventListener('click', ()=>{
    changePreviusImage(modalImageContainer);
});

// Mostrar el navbar cuando presiono el menu de hamburgesa
const hamburgerMenu = document.querySelector('.header__menu');
const modalNavbar = document.querySelector('.modal-navbar__background');
const closeModalNavbar = document.querySelector('.modal-navbar__close');

modalNavbar.style.display = 'none';

hamburgerMenu.addEventListener('click', ()=>{
    console.log('abrir modal');
    modalNavbar.style.display = 'block';
});

closeModalNavbar.addEventListener('click', ()=>{
    modalNavbar.style.display = 'none';
});


function drawProductinModal(){
    productContainer.innerHTML = `.
    <div class="cart-modal__details-container">
        <img class="cart-modal__image" src="./images/nike-1.jpg" alt="image-thumnail">
        <div>
        <p class="cart-modal__product">Shoes Limited Edition</p>
        <p class="cart-modal__price">$250 x3 <span>$750</span></p>
        </div>
        <img class="cart-modal__delete" src="./images/trash.png" alt="delete">
    </div>      
    `;
    
    const modalFooter = document.createElement("div")
    modalFooter.className = "modal-footer";
    modalFooter.innerHTML = `
    <button class="btn-primary" id = "checkout-btn">checkout</button>
    <div id="wallet_container"></div>
    `
    productContainer.append(modalFooter);

    const mp = new MercadoPago('TEST-ca749cf9-9041-46c4-bc1c-c8d578c8f0c9', {
        locale: "es-AR",
      });
  
  
  
    document.getElementById("checkout-btn").addEventListener("click", async () => {
        try {
          const orderData = {
            title: 'compra',
            quantity: 1,
            price: 100,
          };

          const response = await fetch("http://localhost:3000/create_preference", {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(orderData),
          });
          const preference = await response.json();
          createCheckoutButton(preference.id);
        } catch (error) {
          alert("error :(");
        }
        
    });
  
    const createCheckoutButton = (preferenceId) => {
        console.log(preferenceId);
        const bricksBuilder = mp.bricks();
  
        const renderComponent = async () => {
            if (window.CheckoutButton) window.checkoutButton.unmount();
            
            await bricksBuilder.create("wallet", "wallet_container", {
                initialization: {
                    preferenceId: preferenceId
                },
            });      
        };
        
        renderComponent();
    };

    deleteProduct()
    let priceModal = document.querySelector('.cart-modal__price');
    priceModal.innerHTML = `$250 x${lastValue} <span>$${lastValue*250}</span>`;  

};



function changeNextImage(imgContainer){
    if(imgIndex === 4){
        imgIndex = 1;
    }else{
        imgIndex++;
    }
    imgContainer.style.backgroundImage = `url('./images/nike-${imgIndex}.jpg')`
}

function changePreviusImage(imgContainer){
    if(imgIndex === 1){
        imgIndex = 4;
    }else{
        imgIndex--;
    }
    imgContainer.style.backgroundImage = `url('./images/nike-${imgIndex}.jpg')`
}


