const API = "http://localhost:8080/products"

async function loadProducts(){

const res = await fetch(API)

const data = await res.json()

renderTable(data)

}

function renderTable(products){

const table = document.getElementById("tableProducts")

table.innerHTML = ""

products.forEach(p => {

table.innerHTML += `

<tr>

<td>${p.id}</td>
<td>${p.name}</td>
<td>${p.price}</td>
<td>${p.quantity}</td>
<td>${p.category}</td>

<td>

<button onclick="deleteProduct(${p.id})">Excluir</button>

<button onclick="addStock(${p.id})">+ Estoque</button>

</td>

</tr>

`

})

}

async function createProduct(){

const product = {

name: document.getElementById("name").value,

price: document.getElementById("price").value,

quantity: document.getElementById("quantity").value,

category: document.getElementById("category").value

}

await fetch(API, {

method: "POST",

headers: {

"Content-Type":"application/json"

},

body: JSON.stringify(product)

})

loadProducts()

}

async function deleteProduct(id){

await fetch(`${API}/${id}`, {

method:"DELETE"

})

loadProducts()

}

async function addStock(id){

const quantity = prompt("Quantidade para adicionar ou remover:")

await fetch(`${API}/${id}/inventory?quantity=${quantity}`, {

method:"PATCH"

})

loadProducts()

}

async function orderProducts(){

const res = await fetch(`${API}/order?order=desc`)

const data = await res.json()

renderTable(data)

}

loadProducts()