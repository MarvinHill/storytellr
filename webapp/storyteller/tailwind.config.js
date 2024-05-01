/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: {},
    fontFamily: {
      playfair: ['Playfair Display', 'serif'],
      handwriter: ["Delicious Handrawn", 'cursive'],
      courier: ['Courier New', 'monospace'],
    }
  },
  plugins: [],
}
