<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const users = ref([]);
const error = ref('');

const fetchUsers = async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/users');
    users.value = response.data;
  } catch (err) {
    error.value = 'Error fetching users: ' + err.message;
    console.error(err);
  }
};

onMounted(() => {
  fetchUsers();
});
</script>

<template>
  <div class="user-list">
    <h1>User List</h1>
    <p v-if="error" class="error">{{ error }}</p>
    <ul v-else>
      <li v-for="user in users" :key="user.id">
        <strong>{{ user.username }}</strong> - {{ user.role }}
      </li>
    </ul>
  </div>
</template>

<style scoped>
.error { color: red; font-weight: bold; }
.user-list { padding: 20px; }
</style>