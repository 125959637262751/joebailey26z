module.exports = {
  root: true,
  env: {
    browser: true,
    node: true
  },
  parserOptions: {
    parser: 'babel-eslint'
  },
  extends: [
    "plugin:vue/essential",
    "eslint:recommended"
  ],
  // add your custom rules here
  rules: {
    "vue/comment-directive": 0,
    "vue/no-v-html": 0
  }
}
