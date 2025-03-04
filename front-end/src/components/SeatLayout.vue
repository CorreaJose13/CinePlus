<script setup lang="ts">
import { ref, computed } from 'vue'
import { VForm } from 'vuetify/components'

interface Props {
  numberColumns: number
  numberRows: number
  availableSeats: string[]
  maxSeatToSelect: number
}

const props = defineProps<Props>()

const getRowLabel = (index: number) => String.fromCharCode(65 + index)

const isSeatAvailable = (seatId: string) => props.availableSeats.includes(seatId)

const clickedSeats = ref<string[]>([])

const showModal = ref(false)
const cardNumber = ref('')
const cardName = ref('')
const expirationDate = ref('')
const cvv = ref('')
const isLoading = ref(false)
const showSuccess = ref(false)
const showCvv = ref(false)

// Form validation
const valid = ref(false)
const formRef = ref<InstanceType<typeof VForm> | null>(null)

// Rules for form validation
const cardNumberRules = [
  (v: string) => !!v || 'Número de tarjeta es requerido',
  (v: string) => {
    const digits = v.replace(/\s/g, '')
    return /^\d{16}$/.test(digits) || 'Debe contener 16 dígitos'
  },
]

const cardNameRules = [(v: string) => !!v || 'Nombre del titular es requerido']

const expirationDateRules = [
  (v: string) => !!v || 'Fecha de vencimiento es requerida',
  (v: string) => {
    const digits = v.replace(/\D/g, '')
    if (digits.length !== 4) return 'Ingresa una fecha completa (MM/AA)'

    const month = parseInt(digits.substring(0, 2))
    if (month === 0 || month > 12) return 'El mes debe estar entre 01 y 12'

    const year = parseInt(digits.substring(2, 4))
    const currentYear = new Date().getFullYear() % 100
    const currentMonth = new Date().getMonth() + 1

    if (year < currentYear) return 'La tarjeta ha expirado 😳'
    if (year === currentYear && month < currentMonth) return 'La tarjeta ha expirado 😳'

    return true
  },
]

const cvvRules = [
  (v: string) => !!v || 'CVV es requerido',
  (v: string) => /^\d{3}$/.test(v) || 'CVV debe tener 3 dígitos',
]

const maxCardNumberLength = 16

const handleSeatClick = (seatId: string) => {
  if (!isSeatAvailable(seatId)) {
    return
  }

  if (clickedSeats.value.includes(seatId)) {
    clickedSeats.value = clickedSeats.value.filter((id) => id !== seatId)
    return
  }

  if (clickedSeats.value.length >= props.maxSeatToSelect) {
    // Using Vuetify snackbar instead of alert
    snackbarText.value = `Solo puedes seleccionar un máximo de ${props.maxSeatToSelect} asientos`
    showSnackbar.value = true
    return
  }

  clickedSeats.value.push(seatId)
}

const formatCardNumber = (value: string) => {
  const digits = value.replace(/[^\d]/g, '')
  const formatted = digits.match(/.{1,4}/g)?.join(' ') || digits

  if (digits.length <= maxCardNumberLength) {
    cardNumber.value = formatted
  }
}

const formatExpirationDate = (value: string) => {
  const digits = value.replace(/\D/g, '')

  if (digits.length >= 2) {
    expirationDate.value = digits.substring(0, 2) + '/' + digits.substring(2, 4)
  } else {
    expirationDate.value = digits
  }
}

const formatCvv = (value: string) => {
  const digits = value.replace(/\D/g, '')

  if (digits.length <= 3) {
    cvv.value = digits
  }
}

const redirectToHome = () => {
  // showModal.value = false
  // showSuccess.value = false
  // window.location.href = 'https://www.google.com'
}

const resetForm = () => {
  cardNumber.value = ''
  cardName.value = ''
  expirationDate.value = ''
  cvv.value = ''
}

const handleCloseModal = () => {
  showModal.value = false
  resetForm()
}

const handlePayment = async () => {
  if (!formRef.value) return

  const { valid } = await formRef.value.validate()

  if (!valid) return

  isLoading.value = true

  try {
    await new Promise((resolve) => setTimeout(resolve, 1500))
    showSuccess.value = true

    setTimeout(() => {
      redirectToHome()
    }, 5000)
  } catch (error) {
    console.error('Error en el pago:', error)
    isLoading.value = false
    // Mostrar un mensaje de error con snackbar
    snackbarText.value = 'Hubo un error al procesar el pago'
    showSnackbar.value = true
  }
}

const maskCardNumber = (number: string) => {
  const cleanNumber = number.replace(/\s/g, '')
  const lastFourDigits = cleanNumber.slice(-4)
  const maskedPart = '*'.repeat(cleanNumber.length - 4)
  const formatted = (maskedPart + lastFourDigits).match(/.{1,4}/g)?.join(' ') || ''

  return formatted
}

// Snackbar para mensajes de error
const showSnackbar = ref(false)
const snackbarText = ref('')

const seatsSelected = computed(() => {
  return clickedSeats.value.length
})

const progressPercentage = computed(() => {
  // Convertimos explícitamente a número para evitar errores de tipo
  return (seatsSelected.value * 100) / props.maxSeatToSelect
})
</script>

<template>
  <div class="cinema-container">
    <v-card class="mx-auto px-4 py-4">
      <v-card-title class="text-center mb-4">Selección de Asientos</v-card-title>

      <!-- Pantalla -->
      <v-sheet color="amber-lighten-2" class="text-center py-2 rounded mb-8"> Pantalla </v-sheet>

      <!-- Grid de asientos -->
      <div class="seating-grid" :style="{ gridTemplateColumns: `repeat(${numberColumns}, 1fr)` }">
        <template v-for="row in numberRows" :key="row">
          <v-btn
            v-for="col in numberColumns"
            :key="`${row}-${col}`"
            :disabled="!isSeatAvailable(`${getRowLabel(row - 1)}${col}`)"
            :color="
              clickedSeats.includes(`${getRowLabel(row - 1)}${col}`)
                ? 'primary'
                : isSeatAvailable(`${getRowLabel(row - 1)}${col}`)
                  ? 'success'
                  : 'grey-lighten-2'
            "
            variant="elevated"
            size="small"
            class="seat-btn"
            @click="handleSeatClick(`${getRowLabel(row - 1)}${col}`)"
          >
            {{ getRowLabel(row - 1) }}{{ col }}
          </v-btn>
        </template>
      </div>

      <!-- Información de asientos seleccionados -->
      <v-card v-if="clickedSeats.length > 0" variant="outlined" class="mt-4 pa-4">
        <v-card-title class="text-subtitle-1">Asientos Seleccionados:</v-card-title>
        <v-card-text class="text-body-1">
          {{ clickedSeats.join(', ') }}
        </v-card-text>
      </v-card>

      <!-- Información de límite de asientos -->
      <v-card v-if="maxSeatToSelect > 0" variant="tonal" color="info" class="mt-4 pa-4">
        <v-card-text>
          <div>Puedes seleccionar hasta {{ maxSeatToSelect }} asientos</div>
          <div>Has seleccionado {{ seatsSelected }} de {{ maxSeatToSelect }}</div>
          <v-progress-linear
            v-model="progressPercentage"
            color="primary"
            height="10"
            rounded
            class="mt-2"
          ></v-progress-linear>
        </v-card-text>
      </v-card>

      <!-- Botón de pago -->
      <v-btn
        v-if="clickedSeats.length > 0"
        color="success"
        size="large"
        block
        class="mt-6"
        @click="showModal = true"
      >
        Proceder al Pago
      </v-btn>
    </v-card>

    <!-- Modal de pago -->
    <v-dialog v-model="showModal" max-width="500">
      <v-card v-if="!showSuccess">
        <v-card-title class="text-h5 pa-4"> Detalles del Pago </v-card-title>

        <v-card-text>
          <v-form ref="formRef" v-model="valid" lazy-validation>
            <v-text-field
              v-model="cardNumber"
              :rules="cardNumberRules"
              label="Número de Tarjeta"
              prepend-inner-icon="mdi-credit-card-outline"
              variant="outlined"
              placeholder="1234 5678 9012 3456"
              @input="formatCardNumber($event.target.value)"
              maxlength="19"
              required
            ></v-text-field>

            <v-text-field
              v-model="cardName"
              :rules="cardNameRules"
              label="Nombre del titular"
              variant="outlined"
              placeholder="Pepe"
              required
            ></v-text-field>

            <v-row>
              <v-col cols="7">
                <v-text-field
                  v-model="expirationDate"
                  :rules="expirationDateRules"
                  label="Fecha de vencimiento"
                  variant="outlined"
                  placeholder="MM/AA"
                  maxlength="5"
                  @input="formatExpirationDate($event.target.value)"
                  required
                ></v-text-field>
              </v-col>
              <v-col cols="5">
                <v-text-field
                  v-model="cvv"
                  :rules="cvvRules"
                  label="CVV"
                  variant="outlined"
                  placeholder="123"
                  maxlength="3"
                  :append-inner-icon="showCvv ? 'mdi-eye' : 'mdi-eye-off'"
                  :type="showCvv ? 'text' : 'password'"
                  @click:append-inner="showCvv = !showCvv"
                  @input="formatCvv($event.target.value)"
                  required
                ></v-text-field>
              </v-col>
            </v-row>
          </v-form>
        </v-card-text>

        <v-card-actions class="pa-4">
          <v-spacer></v-spacer>
          <v-btn variant="tonal" @click="handleCloseModal" :disabled="isLoading"> Cancelar </v-btn>
          <v-btn color="success" @click="handlePayment" :loading="isLoading" :disabled="!valid">
            Pagar
          </v-btn>
        </v-card-actions>
      </v-card>

      <!-- Modal de éxito -->
      <v-card v-else>
        <v-btn icon variant="text" position="absolute" top right @click="redirectToHome">
          <v-icon>mdi-close</v-icon>
        </v-btn>

        <v-card-text class="text-center pa-4">
          <v-img
            src="https://media1.giphy.com/media/v1.Y2lkPTc5MGI3NjExbjhvc2Z6M2tuZTBhYTA3dW5rZHNwOG8wNzZ1MzNuNGxsc21rMXVqdCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/uZksGEVjUU9C8/giphy.gif"
            alt="Animación de Éxito"
            width="200"
            class="mx-auto mb-4"
          ></v-img>

          <h3 class="text-h5 mb-4">Nos fuimos a ver Mufasa mi papacho!</h3>

          <v-card variant="outlined" class="pa-4 text-left">
            <h4 class="text-h6 mb-4 text-primary">Detalles de la compra</h4>
            <v-list density="compact" class="bg-transparent pa-0">
              <v-list-item>
                <template v-slot:prepend>
                  <v-icon color="primary" size="small">mdi-movie</v-icon>
                </template>
                <v-list-item-title>Película: PELÍCULA</v-list-item-title>
              </v-list-item>

              <v-list-item>
                <template v-slot:prepend>
                  <v-icon color="primary" size="small">mdi-calendar</v-icon>
                </template>
                <v-list-item-title>Fecha: FECHA</v-list-item-title>
              </v-list-item>

              <v-list-item>
                <template v-slot:prepend>
                  <v-icon color="primary" size="small">mdi-clock</v-icon>
                </template>
                <v-list-item-title>Hora: HORA</v-list-item-title>
              </v-list-item>

              <v-list-item>
                <template v-slot:prepend>
                  <v-icon color="primary" size="small">mdi-seat</v-icon>
                </template>
                <v-list-item-title>Asientos: {{ clickedSeats.join(', ') }}</v-list-item-title>
              </v-list-item>

              <v-list-item>
                <template v-slot:prepend>
                  <v-icon color="primary" size="small">mdi-cash</v-icon>
                </template>
                <v-list-item-title>Total: $100</v-list-item-title>
              </v-list-item>

              <v-list-item>
                <template v-slot:prepend>
                  <v-icon color="primary" size="small">mdi-credit-card</v-icon>
                </template>
                <v-list-item-title>Tarjeta: {{ maskCardNumber(cardNumber) }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-card>

          <div class="text-h6 mt-6 text-success">¡Gracias por tu compra!</div>
        </v-card-text>
      </v-card>
    </v-dialog>

    <!-- Snackbar para mensajes -->
    <v-snackbar v-model="showSnackbar" :timeout="3000" color="error">
      {{ snackbarText }}
      <template v-slot:actions>
        <v-btn variant="text" @click="showSnackbar = false"> Cerrar </v-btn>
      </template>
    </v-snackbar>
  </div>
</template>

<style scoped>
.cinema-container {
  width: 100%;
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.seating-grid {
  display: grid;
  gap: 8px;
  justify-content: center;
}

.seat-btn {
  min-width: 40px !important;
  height: 40px !important;
}
</style>
