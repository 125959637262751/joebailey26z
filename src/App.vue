<style>
* {
  box-sizing: border-box
}
body {
  margin: 0;
  background: white;
  display: grid;
  place-content: center;
  place-items: center;
  min-height: 100vh
}
#app {
  color: var(--secondaryColor);
  max-width: 1200px;
  padding: 0 2rem;
  margin: 40px auto 60px;
  font-family: var(--secondaryFont);
  text-align: left;
  font-size: var(--fontSize)
}
h1, h2, h3, h4, h5, h6 {
  color: var(--primaryColor);
  font-family: var(--primaryFont)
}
.container {
  display: grid;
  gap: 2rem;
  width: 100%;
  place-content: start;
  place-items: start
}
h1, h2 {
  width: 100%;
  margin-bottom: 0
}
.container.column {
  grid-auto-flow: column;
  grid-auto-columns: max-content
}
label {
  width: min-content
}
input {
  font-size: 1.2rem;
  padding: 6px;
  border: 1px solid var(--primaryColor);
  border-radius: var(--borderRadius)
}
.btn {
  padding: 1rem;
  border-radius: var(--borderRadius);
  transition: .5s;
  cursor: pointer;
  min-width: 26ch
}
.primary-btn {
  border: 1px solid var(--secondaryColor);
  background-color: var(--primaryColor);
  color: var(--tertiaryColor)
}
.primary-btn:hover {
  background-color: var(--secondaryColor)
}
.secondary-btn {
  border: 1px solid var(--primaryColor);
  background-color: var(--tertiaryColor);
  color: var(--primaryColor)
}
.secondary-btn:hover {
  background-color: var(--primaryColor);
  color: var(--tertiaryColor)
}
.fonts {
  align-items: start
}
.vm--modal {
  overflow-y: auto!important;
  padding: 2rem;
  height: 100%
}
.vm--modal h2 {
  margin-bottom: .83em
}
pre {
  background-color: #EFEFEF;
  width: 100%;
  display: block;
  padding: 1rem;
  user-select: all;
  border-radius: 8px;
  white-space: pre-wrap;
  word-wrap: break-word
}
div[id^='font-picker'], .vc-chrome {
  box-shadow: none!important;
  border: 1px solid #AFAFAF
}
</style>

<template>
  <div id="app" class="container" :style="variables">
    <div>
    <h1>
      Style Guide Creator
    </h1>
    <h2>Colors</h2>
    </div>
    <div class="colors container column">
      <color :primary="true" color="#1a237e"/>
      <color :secondary="true" color="#212121"/>
      <color :tertiary="true" color="#ffffff"/>
    </div>
    <h2>Typography</h2>
    <div class="fonts">
      <div class="container column">
        <label>
          Heading Font
          <font-picker :api-key="apiKey" :options="options1" :active-font="fontHeading.family" @change="headingFont"></font-picker>
        </label>
        <label>
          Body Font
          <font-picker :api-key="apiKey" :options="options2" :active-font="fontBody.family" @change="bodyFont"></font-picker>
        </label>
        <label>
          Font Size
          <input v-model.number="fontSize" type="number">
        </label>
      </div>
      <h1>Heading 1</h1>
      <h2>Heading 2</h2>
      <h3>Heading 3</h3>
      <h4>Heading 4</h4>
      <h5>Heading 5</h5>
      <h6>Heading 6</h6>
      <p>I show my fluffy belly but it's a trap! if you pet it i will tear up your hand. Check cat door for ambush 10 times before coming in cat fur is the new black and i hate cucumber pls dont throw it at me jump on counter removed by human jump on counter again removed by human meow before jumping on counter this time to let the human know am coming back.</p>
    </div>
    <h2>Variables</h2>
    <div class="inputs container column">
      <label>
        Border Radius
        <input v-model.number="borderRadius" type="number">
      </label>
    </div>
    <h2>Download / Buttons</h2>
    <div class="btns container column">
      <button class="btn primary-btn" @click="$modal.show('nuxt')">Download for Vue/Nuxt</button>
      <button class="btn secondary-btn" @click="$modal.show('wp')">Download for WordPress</button>
    </div>
    <modal name="nuxt" height="80%" width="80%" :adaptive="true" :click-to-close="true">
      <div class="modal">
        <h2>
          CSS
        </h2>
        <pre>
          {{this.nuxt.css}}
        </pre>
        <h2>nuxt.config.js</h2>
        <pre>
          {{this.nuxt.js}}
        </pre>
      </div>
    </modal>
    <modal name="wp" height="80%" width="80%" :adaptive="true" :click-to-close="true">
      <div class="modal">
        <h2>
          CSS
        </h2>
        <pre>
          {{this.wp.css}}
        </pre>
        <h2>header.php</h2>
        <pre>
          {{this.wp.php}}
        </pre>
      </div>
    </modal>
  </div>
</template>

<script>
import Color from "./components/color"

export default {
  name: "App",
  data () {
    return {
      apiKey: 'AIzaSyCYTib-0NYDlyJQbJACMdIGoP2qgw3zXNc',
      borderRadius: 0,
      primaryColor: '#1a237e',
      secondaryColor: '#212121',
      tertiaryColor: '#ffffff',
      fontHeading: {
        family: 'Open Sans',
        category: 'sans-serif'
      },
      fontBody: {
        family: 'Open Sans',
        category: 'sans-serif'
      },
      fontSize: 16,
      options1: {
        name: 'headings'
      },
      options2: {
        name: 'body'
      }
    }
  },
  computed: {
    variables () {
      return `
--primaryColor: ${this.primaryColor};
--secondaryColor: ${this.secondaryColor};
--tertiaryColor: ${this.tertiaryColor};
--primaryFont: ${this.fontHeading.family}, ${this.fontHeading.category};
--secondaryFont: ${this.fontBody.family}, ${this.fontBody.category};
--borderRadius:${this.borderRadius}px;
--fontSize: ${this.fontSize}px`
    },
    nuxt () {
      return {
        css: `
:root {
  ${this.variables}
}
h1, h2, h3, h4, h5, h6 {
  font-family: var(--primaryFont);
  color: var(--primaryColor)
}
body {
  font-family: var(--secondaryFont);
  color: var(--secondaryColor)
}`,
        js: `
export default {
  link: [
    { rel: 'preconnect', href: 'https://fonts.gstatic.com', crossorigin: true },
    { rel: 'preload', as: 'style', href: 'https://fonts.googleapis.com/css2?family=${this.fontHeading.family}&family=${this.fontBody.family}&display=swap' },
    { rel: 'stylesheet', media: 'print', onload: 'this.media="all"', href: 'https://fonts.googleapis.com/css2?family=${this.fontHeading.family}&family=${this.fontBody.family}&display=swap' }
  ],
  __dangerouslyDisableSanitizers: ['script', 'noscript'],
  noscript: [
    { innerHTML: '<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=${this.fontHeading.family}&family=${this.fontBody.family}&display=swap" />' }
  ]
}`
      }
    },
    wp () {
      return {
        css: `
:root {
  ${this.variables}
}
h1, h2, h3, h4, h5, h6 {
  font-family: var(--primaryFont);
  color: var(--primaryColor)
}
body {
  font-family: var(--secondaryFont);
  color: var(--secondaryColor)
}`,
        php: `
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin="true">
<link rel="preload" as="style" href="https://fonts.googleapis.com/css2?family=${this.fontHeading.family}&family=${this.fontBody.family}&display=swap">
<link rel="stylesheet" media="all" onload="this.media=&quot;all&quot;" href="https://fonts.googleapis.com/css2?family=${this.fontHeading.family}&family=${this.fontBody.family}&display=swap">
<noscript><link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=${this.fontHeading.family}&family=${this.fontBody.family}&display=swap"></noscript>`
      }
    }
  },
  components: {
    Color
  },
  methods: {
    headingFont (font) {
      console.log(font)
			this.fontHeading = font
    },
    bodyFont (font) {
			this.fontBody = font
    }
  }
}
</script>
