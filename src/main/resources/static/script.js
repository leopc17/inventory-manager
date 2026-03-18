const api = "http://localhost:8080/products";

function $(id) {
    return document.getElementById(id);
}

// Guarda as instâncias dos Modais para serem reutilizadas
let productModalInstance;
let deleteModalInstance;

document.addEventListener('DOMContentLoaded', function () {
    // Inicializa o modal de detalhes do produto
    const productModalElement = $('productDetailsModal');
    if (productModalElement) {
        productModalInstance = new bootstrap.Modal(productModalElement);
    }

    // Inicializa o modal de confirmação de exclusão
    const deleteModalElement = $('deleteConfirmModal');
    if (deleteModalElement) {
        deleteModalInstance = new bootstrap.Modal(deleteModalElement);
    }
});


/* =========================
CRIAÇÃO DE PRODUTO
========================= */

async function createProduct() {
    const product = {
        name: $("name").value,
        price: parseFloat($("price").value),
        quantity: parseInt($("quantity").value),
        shortDescription: $("shortDescription").value,
        longDescription: $("longDescription").value,
        category: $("category").value
    };

    if (!product.name || isNaN(product.price) || isNaN(product.quantity)) {
        showNotification("Preencha nome, preço e quantidade.", "warning");
        return;
    }

    try {
        const response = await fetch(api, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(product)
        });

        if (response.ok) {
            showNotification("Produto criado com sucesso!", "success");
            clearForm();
            loadProducts();
        } else {
            const error = await response.json();
            showNotification(`Erro ao criar produto: ${error.message}`, "danger");
        }
    } catch (error) {
        console.error("Erro ao criar produto:", error);
        showNotification("Erro de conexão ao criar produto.", "danger");
    }
}

/* =========================
CARREGAMENTO DE PRODUTOS
========================= */

async function loadProducts() {
    try {
        const response = await fetch(api);
        if (!response.ok) throw new Error("Erro ao buscar produtos");
        const products = await response.json();
        renderProducts(products);
    } catch (error) {
        console.error("Erro ao carregar produtos:", error);
        showNotification("Erro ao carregar produtos.", "danger");
    }
}

/* =========================
RENDERIZAÇÃO DE PRODUTOS
========================= */

function renderProducts(products) {
    const table = $("productTable");
    table.innerHTML = "";

    if (!products || products.length === 0) {
        table.innerHTML = `<tr><td colspan="7" class="text-center">Nenhum produto encontrado</td></tr>`;
        return;
    }

    products.forEach(p => {
        const row = table.insertRow();
        row.innerHTML = `
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td class="d-none d-md-table-cell">${p.shortDescription || '-'}</td>
            <td>R$ ${p.price.toFixed(2)}</td>
            <td>${p.quantity}</td>
            <td>${p.category}</td>
            <td>
                <button class="btn btn-info btn-sm me-2" onclick="showProductDetails(${p.id})">Ver</button>
                <button class="btn btn-danger btn-sm" onclick="confirmDeleteProduct(${p.id})">Excluir</button>
            </td>
        `;
    });
}

/* =========================
EXIBIR DETALHES DO PRODUTO (MODAL)
========================= */

async function showProductDetails(id) {
    try {
        const response = await fetch(`${api}/${id}`);
        if (!response.ok) throw new Error('Produto não encontrado');

        const p = await response.json();

        $('modalProductId').textContent = p.id;
        $('modalProductName').textContent = p.name;
        $('modalProductCategory').textContent = p.category;
        $('modalProductPrice').textContent = p.price.toFixed(2);
        $('modalProductQuantity').textContent = p.quantity;
        $('modalProductShortDescription').textContent = p.shortDescription || 'Não informada.';
        $('modalProductLongDescription').textContent = p.longDescription || 'Não informada.';

        if (productModalInstance) {
            productModalInstance.show();
        }

    } catch (error) {
        console.error('Erro ao buscar detalhes do produto:', error);
        showNotification('Não foi possível carregar os detalhes do produto.', 'danger');
    }
}


/* =========================
AÇÕES DE FILTRO E ORDENAÇÃO
========================= */

async function orderProducts(order) {
    try {
        const response = await fetch(`${api}/order?order=${order}`);
        if (!response.ok) throw new Error("Erro ao ordenar produtos");
        const products = await response.json();
        renderProducts(products);
        showNotification(`Produtos ordenados por preço (${order === 'asc' ? 'crescente' : 'decrescente'}).`, "info");
    } catch (error) {
        console.error(error);
        showNotification("Erro ao ordenar produtos.", "danger");
    }
}

async function filterProducts() {
    try {
        const category = $("filterCategory").value;
        const minPrice = $("minPrice").value;
        const maxPrice = $("maxPrice").value;

        let params = new URLSearchParams();
        if (category) params.append('category', category);
        if (minPrice) params.append('minprice', minPrice);
        if (maxPrice) params.append('maxprice', maxPrice);

        const url = `${api}?${params.toString()}`;
        const response = await fetch(url);

        if (!response.ok) throw new Error("Erro ao filtrar produtos");

        const products = await response.json();
        renderProducts(products);
        showNotification("Filtro aplicado.", "info");

    } catch (error) {
        console.error(error);
        showNotification("Erro ao aplicar filtro.", "danger");
    }
}

async function lowStock() {
    try {
        const quantity = 10;
        const response = await fetch(`${api}/below?quantity=${quantity}`);
        if (!response.ok) throw new Error("Erro ao buscar produtos com estoque baixo");
        const products = await response.json();
        renderProducts(products);
        showNotification("Exibindo produtos com estoque baixo.", "warning");
    } catch (error) {
        console.error(error);
        showNotification("Erro ao buscar produtos com estoque baixo.", "danger");
    }
}

/* =========================
DELETAR PRODUTO
========================= */

function confirmDeleteProduct(id) {
    const confirmBtn = $('confirmDeleteBtn');

    // Passa o ID para o botão de confirmação
    confirmBtn.onclick = () => {
        deleteProduct(id);
    };

    if (deleteModalInstance) {
        deleteModalInstance.show();
    }
}

async function deleteProduct(id) {
    // Esconde o modal antes de processar
    if (deleteModalInstance) {
        deleteModalInstance.hide();
    }

    try {
        const response = await fetch(`${api}/${id}`, { method: "DELETE" });
        if (response.ok) {
            showNotification("Produto excluído com sucesso.", "success");
            loadProducts();
        } else {
            throw new Error("Falha ao excluir");
        }
    } catch (error) {
        console.error("Erro ao excluir produto:", error);
        showNotification("Erro ao excluir produto.", "danger");
    }
}

/* =========================
FUNÇÕES UTILITÁRIAS
========================= */

function clearForm() {
    $("name").value = "";
    $("price").value = "";
    $("quantity").value = "";
    $("shortDescription").value = "";
    $("longDescription").value = "";
}

function showNotification(message, type = 'info') {
    const notificationArea = $('notification-area');
    const notificationId = `notification-${Date.now()}`;

    const toastHTML = `
        <div id="${notificationId}" class="toast align-items-center text-white bg-${type} border-0" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="d-flex">
                <div class="toast-body">
                    ${message}
                </div>
                <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        </div>
    `;

    notificationArea.insertAdjacentHTML('beforeend', toastHTML);

    const toastElement = $(notificationId);
    const toast = new bootstrap.Toast(toastElement, { delay: 4000 });
    toast.show();

    toastElement.addEventListener('hidden.bs.toast', () => {
        toastElement.remove();
    });
}


/* =========================
INICIALIZAÇÃO
========================= */

// Expondo funções para o escopo global para que possam ser chamadas pelo HTML
window.createProduct = createProduct;
window.loadProducts = loadProducts;
window.orderProducts = orderProducts;
window.filterProducts = filterProducts;
window.lowStock = lowStock;
window.confirmDeleteProduct = confirmDeleteProduct;
window.showProductDetails = showProductDetails;

// Carregamento inicial
window.onload = loadProducts;