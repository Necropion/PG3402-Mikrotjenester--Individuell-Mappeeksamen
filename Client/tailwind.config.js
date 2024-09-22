/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./View/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    extend: {
      fontFamily: {
        poppins: ['Poppins', 'sans-serif'],
        merriweather: ['Merriweather', 'serif'],
        roboto: ['Roboto', 'sans-serif']
      },
    },
  },
  plugins: [],
}

