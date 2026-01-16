<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <div class="app-container">
    <header>
      <div class="logo">MiniERP</div>
      
      <nav>
        <RouterLink to="/">Home</RouterLink>
        
        <RouterLink to="/users">Users</RouterLink>
      </nav>
    </header>

    <main>
      <RouterView />
    </main>
  </div>
</template>

<style scoped>
header {
  background-color: #2c3e50;
  color: white;
  padding: 1rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.logo {
  font-weight: bold;
  font-size: 1.5rem;
}

nav a {
  color: white;
  text-decoration: none;
  margin-left: 1rem;
  padding: 0.5rem 1rem;
  border-radius: 4px;
}

nav a:hover {
  background-color: #34495e;
}

nav a.router-link-active {
  background-color: #42b883; 
  font-weight: bold;
}

main {
  padding: 2rem;
}
</style>