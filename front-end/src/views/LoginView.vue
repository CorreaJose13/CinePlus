<script setup lang="ts">
import { ref, computed } from 'vue'

const show = ref(false)

const items = computed(() => {
  const LoginTexts = {
    titleForm: 'Inicia sesión',
    emailLabel: 'Correo electrónico',
    emailPlaceHolder: 'Ingresa tu correo',
    passwLabel: 'Contraseña',
    passwPlaceHolder: 'Ingresa tu contraseña',
    continue: 'Continuar',
  }
  return LoginTexts
})

const email = ref('')
const password = ref('')

const emailRules: ((v: string) => boolean | string)[] = [
  (v) => !!v || 'El correo electrónico es requerido',
  (v) => /.+@.+\..+/.test(v) || 'El correo electrónico debe ser válido',
]

const passwordRules = [
  (v: string) => !!v || 'La contraseña es requerida',
]

const emits = defineEmits(['admin-login'])

const login = () => {
  if (email.value === 'jocorreamoreno@gmail.com' && password.value === 'password') {
    emits('admin-login')
  } else {
    alert('Invalid username or password')
  }
}
</script>

<template>
  <v-container class="fill-height">
    <v-row class="fill-height" align="center" justify="center">
      <v-col cols="12" sm="8" md="6" lg="4">
        <v-card class="form-container elevation-5">
          <v-card-title class="text-center text-h5 font-weight-bold pt-4">
            {{ items.titleForm }}
          </v-card-title>
          <v-card-text>
            <v-form @submit.prevent="login">
              <div>
                <p>{{ items.emailLabel }}</p>
                <v-text-field
                  v-model="email"
                  variant="outlined"
                  type="email"
                  :placeholder="items.emailPlaceHolder"
                  :rules="emailRules"
                  class="mb-4"
                  required
                />
              </div>
              <div>
                <p>{{ items.passwLabel }}</p>
                <v-text-field
                  v-model="password"
                  variant="outlined"
                  :placeholder="items.passwPlaceHolder"
                  :append-inner-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                  :type="show ? 'text' : 'password'"
                  :rules="passwordRules"
                  class="mb-4"
                  required 
                  @click:append-inner="show = !show"
                />
              </div>
              <div class="d-flex justify-center mt-4">
                <v-btn
                  type="submit"
                  variant="elevated"
                  color="primary"
                  size="large"
                  min-width="150"
                >
                  {{ items.continue }}
                </v-btn>
              </div>
            </v-form>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>
.form-container {
  padding: 16px;
  border-radius: 8px;
}
p {
  margin-bottom: 4px;
  font-size: 14px;
  color: rgba(0, 0, 0, 0.6);
}
.v-container {
  min-height: calc(100vh - 64px);
}
</style>
