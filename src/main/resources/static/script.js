const api = "http://localhost:8080/products";

function createProduct() {

  const product = {
    name: document.getElementById("name").value,
    price: parseFloat(document.getElementById("price").value),
    quantity: parseInt(document.getElementById("quantity").value),
    shortDescription: document.getElementById("shortDescription").value,
    longDescription: document.getElementById("longDescription").value,
    category: document.getElementById("category").value
  };

  fetch(api, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(product)
  })
  .then(response => {
    if (!response.ok) {
      throw new Error("Erro ao criar produto");
    }
    return response.json();
  })
  .then(() => {
    alert("Produto criado com sucesso");
    clearForm();
    loadProducts();
  })
  .catch(error => {
    console.error(error);
    alert("Erro ao criar produto");
  });

}

function loadProducts() {

  fetch(api)
    .then(response => response.json())
    .then(data => {

      const table = document.getElementById("productTable");
      table.innerHTML = "";

      data.forEach(p => {

        table.innerHTML += `
        <tr>
          <td>${p.id}</td>
          <td>${p.name}</td>
          <td>R$ ${p.price}</td>
          <td>${p.quantity}</td>
          <td>${p.category}</td>
          <td>
            <button class="btn btn-danger btn-sm" onclick="deleteProduct(${p.id})">
              Excluir
            </button>
          </td>
        </tr>
        `;

      });

    })
    .catch(error => {
      console.error("Erro ao carregar produtos:", error);
    });

}

function deleteProduct(id) {

  fetch(`${api}/${id}`, {
    method: "DELETE"
  })
  .then(() => {
    loadProducts();
  })
  .catch(error => {
    console.error("Erro ao excluir:", error);
  });

}

function clearForm() {
  document.getElementById("name").value = "";
  document.getElementById("price").value = "";
  document.getElementById("quantity").value = "";
  document.getElementById("shortDescription").value = "";
  document.getElementById("longDescription").value = "";
}

loadProducts();