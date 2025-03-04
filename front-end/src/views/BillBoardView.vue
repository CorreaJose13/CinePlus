<script setup lang="ts">
import { computed, ref } from 'vue'
import { useDisplay } from 'vuetify'
import { useRouter } from 'vue-router'
import { useMovieStore } from '@/stores/movieStore'

interface Pelicula {
  id: number
  titulo: string
  imagen: string
  descripcion: string
  duracion: number
  genero: string
  año: number
}

const { md, lg, xl } = useDisplay()
const router = useRouter()
const movieStore = useMovieStore()

const peliculas = ref<Pelicula[]>([
  {
    id: 1,
    titulo: 'Dune: Parte Dos',
    imagen: 'https://pics.filmaffinity.com/dune_part_two-802143593-large.jpg',
    descripcion:
      'Paul Atreides se une a los Fremen y busca venganza contra quienes destruyeron a su familia.',
    duracion: 166,
    genero: 'Ciencia Ficción',
    año: 2024,
  },
  {
    id: 2,
    titulo: 'Oppenheimer',
    imagen: 'https://pics.filmaffinity.com/oppenheimer-828933592-large.jpg',
    descripcion:
      'La historia de J. Robert Oppenheimer y su papel en el desarrollo de la bomba atómica.',
    duracion: 180,
    genero: 'Drama Histórico',
    año: 2023,
  },
  {
    id: 3,
    titulo: 'Barbie',
    imagen: 'https://pics.filmaffinity.com/barbie-295661697-large.jpg',
    descripcion: 'Barbie y Ken descubren el mundo real en una divertida y colorida aventura.',
    duracion: 114,
    genero: 'Comedia',
    año: 2023,
  },
  {
    id: 4,
    titulo: 'Everything Everywhere All at Once',
    imagen: 'https://pics.filmaffinity.com/everything_everywhere_all_at_once-897692218-large.jpg',
    descripcion:
      'Una mujer descubre que puede acceder a múltiples versiones de sí misma en diferentes universos.',
    duracion: 139,
    genero: 'Ciencia Ficción',
    año: 2022,
  },
  {
    id: 5,
    titulo: 'Joker',
    imagen: 'https://pics.filmaffinity.com/joker-790658206-large.jpg',
    descripcion:
      'Arthur Fleck, un comediante fallido, desciende lentamente a la locura y se convierte en el Joker.',
    duracion: 122,
    genero: 'Drama',
    año: 2019,
  },
  {
    id: 6,
    titulo: 'The Batman',
    imagen: 'https://pics.filmaffinity.com/the_batman-449856406-large.jpg',
    descripcion:
      'Batman investiga una serie de asesinatos y enfrenta al enigmático Acertijo en Gotham City.',
    duracion: 176,
    genero: 'Acción',
    año: 2022,
  },
  {
    id: 7,
    titulo: 'Interstellar',
    imagen:
      'https://m.media-amazon.com/images/M/MV5BMjIxNTU4MzY4MF5BMl5BanBnXkFtZTgwMzM4ODI3MjE@._V1_FMjpg_UX1000_.jpg',
    descripcion:
      'Un grupo de astronautas viaja a través de un agujero de gusano en busca de un nuevo hogar para la humanidad.',
    duracion: 169,
    genero: 'Ciencia Ficción',
    año: 2014,
  },
  {
    id: 8,
    titulo: 'Inception',
    imagen: 'https://pics.filmaffinity.com/inception-652954101-large.jpg',
    descripcion:
      'Un ladrón que roba secretos a través de los sueños debe realizar una misión casi imposible: implantar una idea.',
    duracion: 148,
    genero: 'Ciencia Ficción',
    año: 2010,
  },
])

const peliculasAgrupadas = computed(() => {
  let tamañoGrupo = 1
  if (md.value) tamañoGrupo = 2
  if (lg.value) tamañoGrupo = 3
  if (xl.value) tamañoGrupo = 4

  const grupos: Pelicula[][] = []
  for (let i = 0; i < peliculas.value.length; i += tamañoGrupo) {
    grupos.push(peliculas.value.slice(i, i + tamañoGrupo))
  }

  return grupos
})

const saveMovie = (pelicula: Pelicula) => {
  movieStore.movie = pelicula 
}

const verDetalles = (pelicula: Pelicula) => {
  saveMovie(pelicula)
  router.push({ name: 'movieDetail' })
}
</script>

<template>
  <v-container fluid class="fill-height">
    <v-row class="fill-height" align="center" justify="center">
      <v-carousel
        show-arrows="hover"
        hide-delimiter-background
        height="100%"
        cycle
        class="flex-grow-1"
      >
        <v-carousel-item v-for="(grupo, i) in peliculasAgrupadas" :key="i" class="fill-height">
          <v-row class="fill-height" align="center" justify="center">
            <v-col
              v-for="pelicula in grupo"
              :key="pelicula.id"
              cols="12"
              :sm="12 / 2"
              :md="12 / 3"
              :lg="12 / 4"
              :xl="12 / 5"
            >
              <v-card height="60vh" class="clickable-card" @click="verDetalles(pelicula)">
                <v-img :src="pelicula.imagen" class="movie-image">
                  <div class="d-flex flex-column fill-height justify-space-between pa-4">
                    <div>
                      <h2 class="text-h4 text-white font-weight-bold mb-2 text-outline">
                        {{ pelicula.titulo }}
                      </h2>
                      <div class="d-flex flex-wrap gap-2 mb-2">
                        <v-chip
                          color="white"
                          size="md"
                          class="mr-2 pa-2 border-black"
                          variant="flat"
                          label
                          >{{ pelicula.año }}</v-chip
                        >
                        <v-chip
                          color="red"
                          size="md"
                          class="pa-2 border-black"
                          variant="flat"
                          label
                          >{{ pelicula.genero }}</v-chip
                        >
                      </div>
                    </div>
                    <div class="text-right">
                      <v-btn
                        variant="flat"
                        color="white"
                        size="small"
                        class="text-black border-black"
                      >
                        {{ pelicula.duracion }} min
                      </v-btn>
                    </div>
                  </div>
                </v-img>
              </v-card>
            </v-col>
          </v-row>
        </v-carousel-item>
      </v-carousel>
    </v-row>
  </v-container>
</template>
<style scoped>
.clickable-card {
  cursor: pointer;
  transition: transform 0.2s ease-in-out;
}
.clickable-card:hover {
  transform: scale(1.05);
}
.movie-image :deep(.v-img__img--contain) {
  object-fit: fill;
}
.text-outline {
  -webkit-text-stroke: 1px black;
}
.border-black {
  border: 1px solid black;
}
</style>
