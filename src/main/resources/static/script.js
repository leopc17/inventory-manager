const api = "http://localhost:8080/products";

function $(id){
return document.getElementById(id);
}

/* =========================
CRIAR PRODUTO
========================= */

async function createProduct(){

const product = {
name: $("name").value,
price: parseFloat($("price").value),
quantity: parseInt($("quantity").value),
shortDescription: $("shortDescription").value,
longDescription: $("longDescription").value,
category: $("category").value
};

if(!product.name || isNaN(product.price) || isNaN(product.quantity)){
alert("Preencha nome, preço e quantidade");
return;
}

try{

const response = await fetch(api,{
method:"POST",
headers:{
"Content-Type":"application/json"
},
body:JSON.stringify(product)
});

if(response.ok){
alert("Produto criado");
clearForm();
loadProducts();
}else{
alert("Erro ao criar produto");
}

}catch(error){
console.error(error);
alert("Erro ao criar produto");
}

}

/* =========================
CARREGAR PRODUTOS
========================= */

async function loadProducts(){

try{

const response = await fetch(api);

if(!response.ok){
throw new Error("Erro ao buscar produtos");
}

const products = await response.json();

renderProducts(products);

}catch(error){

console.error("Erro ao carregar produtos:", error);
alert("Erro ao carregar produtos");

}

}

/* =========================
RENDERIZAR PRODUTOS
========================= */

function renderProducts(products){

const table = $("productTable");
table.innerHTML="";

if(!products || products.length === 0){

table.innerHTML = `
<tr>
<td colspan="6" class="text-center">
Nenhum produto encontrado
</td>
</tr>
`;

return;

}

products.forEach(p=>{

const row = document.createElement("tr");

row.innerHTML = `
<td>${p.id}</td>
<td>${p.name}</td>
<td>${p.price}</td>
<td>${p.quantity}</td>
<td>${p.category}</td>
<td>
<button class="btn btn-danger btn-sm">Excluir</button>
</td>
`;

row.querySelector("button").onclick = ()=>deleteProduct(p.id);

table.appendChild(row);

});

}

/* =========================
ORDENAR PRODUTOS
========================= */

async function orderProducts(order){

try{

const response = await fetch(`${api}/order?order=${order}`);

if(!response.ok){
throw new Error("Erro ao ordenar produtos");
}

const products = await response.json();

renderProducts(products);

}catch(error){

console.error(error);
alert("Erro ao ordenar produtos");

}

}

/* =========================
FILTRAR PRODUTOS
========================= */

async function filterProducts(){

try{

const category = $("filterCategory").value;
const minPrice = $("minPrice").value;
const maxPrice = $("maxPrice").value;

let params = [];

if(category){
params.push(`category=${category}`);
}

if(minPrice){
params.push(`minprice=${minPrice}`);
}

if(maxPrice){
params.push(`maxprice=${maxPrice}`);
}

let url = api;

if(params.length > 0){
url += "?" + params.join("&");
}

const response = await fetch(url);

if(!response.ok){
throw new Error("Erro ao filtrar produtos");
}

const products = await response.json();

renderProducts(products);

}catch(error){

console.error(error);
alert("Erro ao filtrar produtos");

}

}

/* =========================
ESTOQUE BAIXO
========================= */

async function lowStock(){

try{

const quantity = 10;

const response = await fetch(`${api}/below?quantity=${quantity}`);

if(!response.ok){
throw new Error("Erro ao buscar produtos com estoque baixo");
}

const products = await response.json();

renderProducts(products);

}catch(error){

console.error(error);
alert("Erro ao buscar produtos com estoque baixo");

}

}

/* =========================
DELETAR
========================= */

async function deleteProduct(id){

if(!confirm("Excluir produto?")) return;

await fetch(api+"/"+id,{
method:"DELETE"
});

loadProducts();

}

/* =========================
UTIL
========================= */

function clearForm(){

$("name").value="";
$("price").value="";
$("quantity").value="";
$("shortDescription").value="";
$("longDescription").value="";

}

/* =========================
INIT
========================= */

window.createProduct = createProduct;
window.loadProducts = loadProducts;
window.orderProducts = orderProducts;
window.filterProducts = filterProducts;
window.lowStock = lowStock;

window.onload = function(){
loadProducts();
};