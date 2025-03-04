import { defineStore } from 'pinia'
import { ref, computed, watch } from 'vue'

interface Movie {
  id: number
  titulo: string
  imagen: string
  descripcion: string
  duracion: number
  genero: string
  año: number
}


export const useMovieStore = defineStore('movieStore', () => {
  const movie = ref<Movie | null>(null)

  const loadMovie = () => {
    const storedMovie = localStorage.getItem('movie')
    if (storedMovie) {
      movie.value = JSON.parse(storedMovie)
    }
  }

  watch(
    movie,
    (newValue) => {
      if (newValue) {
        localStorage.setItem('movie', JSON.stringify(newValue))
      }
    },
    { deep: true },
  )

  const getMovie = computed(() => movie.value)

  return { movie, loadMovie, getMovie }
})
