<style scoped>
.color {
  padding: 20px;
  background-color: var(--color);
}
</style>

<template>
  <div v-if="color" class="color" :style="'--color:' + color.hex">
    <chrome-picker v-model="color" />
  </div>
</template>

<script>
import { Chrome } from "vue-color";

export default {
  components: {
    "chrome-picker": Chrome,
  },
  name: "color",
  data: function () {
    return {
      color: "#000000",
    };
  },
};
</script>
