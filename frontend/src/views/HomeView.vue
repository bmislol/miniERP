<script setup>
</script>

<template>
  <div class="dashboard">
    <h1>Dashboard</h1>
    <p>Welcome to the MiniERP System.</p>
    
    <div class="stats-grid">
      <div class="card">
        <h3>System Status</h3>
        <p class="online">Online</p>
      </div>
      </div>
  </div>
</template>

<style scoped>
.dashboard {
  padding: 20px;
}

.stats-grid {
  display: flex;
  gap: 20px;
  margin-top: 20px;
}

.card {
  background: #f4f4f4;
  color: #333;         
  padding: 20px;
  border-radius: 8px;
  width: 200px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.online {
  color: green;
  font-weight: bold;
}
</style>