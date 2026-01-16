<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const products = ref([]);
const error = ref('');
const showForm = ref(false);
const isEditing = ref(false);

const newProduct = ref({
  id: null, 
  name: '',
  sku: '',
  price: '',
  stockQuantity: ''
});

const fetchProducts = async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/products');
    products.value = response.data;
  } catch (err) {
    error.value = 'Error fetching products: ' + err.message;
  }
};
const startEdit = (product) => {
  newProduct.value = { ...product }; 
  isEditing.value = true;
  showForm.value = true;
};

const startAdd = () => {
  newProduct.value = { id: null, name: '', sku: '', price: '', stockQuantity: '' };
  isEditing.value = false;
  showForm.value = !showForm.value; 
};

const saveProduct = async () => {
  try {
    const payload = {
      ...newProduct.value,
      price: parseFloat(newProduct.value.price),
      stockQuantity: parseInt(newProduct.value.stockQuantity),
      createdByUserId: 1 
    };

    if (isEditing.value) {
      await axios.put(`http://localhost:8080/api/products/${newProduct.value.id}`, payload);
    } else {
      await axios.post('http://localhost:8080/api/products', payload);
    }
    
    await fetchProducts();
    showForm.value = false;
    isEditing.value = false;
    newProduct.value = { id: null, name: '', sku: '', price: '', stockQuantity: '' };
    
  } catch (err) {
    console.error(err);
    alert('Failed to save: ' + (err.response?.data?.message || err.message));
  }
};

const deleteProduct = async (id) => {
  if (!confirm('Are you sure you want to delete this product?')) return;
  
  try {
    await axios.delete(`http://localhost:8080/api/products/${id}`);
    await fetchProducts(); 
  } catch (err) {
    console.error(err);
    alert('Failed to delete: ' + (err.response?.data?.message || err.message));
  }
};

onMounted(() => {
  fetchProducts();
});
</script>

<template>
  <div class="product-list">
    <div class="header">
      <h1>Product Inventory</h1>
      <button class="add-btn" @click="startAdd">
        {{ showForm && !isEditing ? 'Cancel' : 'Add Product' }}
      </button>
    </div>

    <div v-if="showForm" class="add-form">
      <h3>New Product</h3>
      <div class="form-grid">
        <input v-model="newProduct.name" placeholder="Product Name" />
        <input v-model="newProduct.sku" placeholder="SKU (e.g. LAP-001)" />
        <input v-model="newProduct.price" type="number" placeholder="Price" />
        <input v-model="newProduct.stockQuantity" type="number" placeholder="Stock Qty" />
      </div>
      <button class="save-btn" @click="saveProduct">
        {{ isEditing ? 'Update Product' : 'Save Product' }}
      </button>
    </div>

    <p v-if="error" class="error">{{ error }}</p>

    <table v-else class="inventory-table">
      <thead>
        <tr>
          <th>SKU</th>
          <th>Name</th>
          <th>Price</th>
          <th>Stock</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="product in products" :key="product.id">
          <td>{{ product.sku }}</td>
          <td>{{ product.name }}</td> 
          <td>${{ product.price }}</td>
          <td :class="{ 'low-stock': product.stockQuantity < 5 }">
            {{ product.stockQuantity }}
          </td>
          <td>
            <button class="small-btn" @click="startEdit(product)">Edit</button>
            <button class="delete-btn" @click="deleteProduct(product.id)">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.add-form {
  background: #f8f9fa;
  padding: 15px;
  border-radius: 8px;
  margin-bottom: 20px;
  border: 1px solid #ddd;
}
.form-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 10px;
  margin-bottom: 10px;
}
.full-width { grid-column: span 2; }
input {
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.add-btn {
  background-color: #42b883;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 4px;
  cursor: pointer;
}
.save-btn {
  background-color: #2c3e50;
  color: white;
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
}

.delete-btn {
  background-color: #e74c3c; 
  color: white;
  border: none;
  padding: 5px 10px;
  border-radius: 4px;
  cursor: pointer;
  margin-left: 5px; 
}
.delete-btn:hover {
  background-color: #c0392b;
}
.inventory-table { width: 100%; border-collapse: collapse; }
.inventory-table th, .inventory-table td { border: 1px solid #ddd; padding: 12px; text-align: left; }
.inventory-table th { background-color: #f4f4f4; font-weight: bold; }
.low-stock { color: red; font-weight: bold; }
.small-btn { background-color: #34495e; color: white; border: none; padding: 5px 10px; border-radius: 4px; cursor: pointer; }
</style>