//PARTE DE BOTON COMPRAR PARA CADA PRODUCTO EN PARTICULAR
// Seleccionamos el contenedor de productos en el HTML
const shopContent = document.getElementById("shopContent");

// Generamos el contenido dinámico
productos.forEach((product) => {
    const content = document.createElement("div");
    content.className = "product-card"; // Clase para estilo opcional
    content.innerHTML= `
        <section class="product-gallery"> 
            <div class="product">
                <img src="${product.imagen}" alt="${product.nombre}">
                <h3>${product.nombre}</h3>
                <p>${product.descripcion}</p>
                <h4>$${product.precio.toFixed(2)}</h4>
                <a href="detalle.html?id=${product.id}"><button>Comprar</button></a>
            </div>
        </section>
    `;
    shopContent.append(content);
});