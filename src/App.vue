<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>

<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png" width="25%" />
    <button :click="add_color">Add another color!</button>
    <div class="color-container">
      <color v-for="color in colors" :key="color" />
    </div>
  </div>
</template>

<script>
import Color from "./components/color";

export default {
  name: "App",
  data: function () {
    return {
      colors: [1],
    };
  },
  components: {
    Color,
  },
  methods: {
    add_color() {
      this.colors.push(1);
      console.log(this.colors);
    },
  },
};
</script>
