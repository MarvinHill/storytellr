/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: {},
    fontFamily: {
      playfair: ['Playfair Display', 'serif'],
      courier: ['Courier New', 'monospace'],
    }
  },
  plugins: [],
}
