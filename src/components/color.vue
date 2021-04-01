<template>
  <chrome-picker @input="updateColor" v-model="actual" />
</template>

<script>
import { Chrome } from "vue-color";

export default {
  components: {
    "chrome-picker": Chrome,
  },
  props: {
    primary: Boolean,
    secondary: Boolean,
    tertiary: Boolean,
    color: {
      type: String,
      default: '#000000'
    }
  },
  name: "color",
  data () {
    return {
      actual: null
    }
  },
  beforeMount () {
    this.actual = this.color
  },
  methods: {
    updateColor () {
      if (this.primary) {
        this.$parent.primaryColor = this.actual.hex
      } else if (this.secondary) {
        this.$parent.secondaryColor = this.actual.hex
      } else if (this.tertiary) {
        this.$parent.tertiaryColor = this.actual.hex
      }
    }
  }
}
</script>
