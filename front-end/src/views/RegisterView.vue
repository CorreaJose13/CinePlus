<script lang="ts" setup>
import { ref, computed } from 'vue'
import { VForm } from 'vuetify/components'
import { useRouter } from 'vue-router'

const router = useRouter()
const show1 = ref(false)
const show2 = ref(false)

const form = ref<InstanceType<typeof VForm> | null>(null)

const name = ref('')
const lastName = ref('')
const emailUser = ref('')
const phone = ref('')
const password = ref('')
const passwordConfirm = ref('')

const nameRules = [(v: string) => !!v || 'El nombre es requerido']

const lastNameRules = [(v: string) => !!v || 'El apellido es requerido']

const emailRules: ((v: string) => boolean | string)[] = [
  (v) => !!v || 'El correo electrónico es requerido',
  (v) => /.+@.+\..+/.test(v) || 'El correo electrónico debe ser válido',
]

const phoneRules = [(v: string) => !!v || 'El teléfono es requerido']

const passwordRules = [
  (v: string) => !!v || 'La contraseña es requerida',
  (v: string) => v.length >= 8 || 'La contraseña debe tener al menos 8 caracteres',
  (v: string) => /[A-Z]/.test(v) || 'La contraseña debe tener al menos una letra mayúscula',
  (v: string) => /[a-z]/.test(v) || 'La contraseña debe tener al menos una letra minúscula',
  (v: string) => /[0-9]/.test(v) || 'La contraseña debe tener al menos un número',
  (v: string) => /[!@#$%^&*(),.?":{}|<>]/.test(v) || 'La contraseña debe tener al menos un carácter especial',
]

const passwordConfirmRules = [
  (v: string) => !!v || 'Debe confirmar la contraseña',
  (v: string) => v === password.value || 'Las contraseñas no coinciden',
]

async function validateAndSubmit() {
  const { valid } = await form.value!.validate() || { valid: false }
  
  if (valid) {
    router.push('/login')
  }
}

const items = computed(() => {
  const RegisterTexts = {
    titleForm: 'Crea una cuenta',
    nameLabel: 'Nombre',
    namePlaceHolder: 'Ingresa tu nombre',
    lastNameLabel: 'Apellido',
    lastNamePlaceHolder: 'Ingresa tu apellido',
    emailLabel: 'Email',
    emailPlaceHolder: 'Ingresa tu email',
    phoneLabel: 'Telefono',
    phonePlaceHolder: 'Ingresa tu telefono',
    passwordRules: '8 caracteres como mínimo, distingue mayúsculas de minúsculas',
    passwLabel: 'Contraseña',
    passwPlaceHolder: 'Ingresa tu contraseña',
    repPasswLabel: 'Repetir contraseña',
    repPasswPlaceHolder: 'Confirma tu contraseña',
    allRequired: '*Todos los campos son obligatorios',
    registerButton: 'Registrarme',
  }
  return RegisterTexts
})
</script>

<template>
  <v-container fluid class="fill-height">
    <v-row class="fill-height" align="center" justify="center">
      <v-col cols="12" sm="8" md="6" lg="4">
        <v-card class="form-container">
          <v-card-title class="text-center text-h5 font-weight-bold pt-4">
            {{ items.titleForm }}
          </v-card-title>
          <v-card-text>
            <v-form ref="form" @submit.prevent="validateAndSubmit">
              <div>
                <p>{{ items.nameLabel }}</p>
                <v-text-field
                  v-model="name"
                  variant="outlined"
                  type="text"
                  :placeholder="items.namePlaceHolder"
                  :rules="nameRules"
                  class="mb-4"
                  required
                />
              </div>
              <div>
                <p>{{ items.lastNameLabel }}</p>
                <v-text-field
                  v-model="lastName"
                  variant="outlined"
                  type="text"
                  :placeholder="items.lastNamePlaceHolder"
                  :rules="lastNameRules"
                  class="mb-4"
                  required
                />
              </div>
              <div>
                <p>{{ items.emailLabel }}</p>
                <v-text-field
                  v-model="emailUser"
                  variant="outlined"
                  type="email"
                  :placeholder="items.emailPlaceHolder"
                  :rules="emailRules"
                  class="mb-4"
                  required
                />
              </div>
              <div>
                <p>{{ items.phoneLabel }}</p>
                <v-phone-input
                  v-model="phone"
                  variant="outlined"
                  :placeholder="items.phonePlaceHolder"
                  :label="items.phoneLabel"
                  :rules="phoneRules"
                  default-country="co"
                  class="mb-4"
                />
              </div>
              <div>
                <p>
                  {{ items.passwLabel }}
                  <v-tooltip>
                    <template #activator="{ props }">
                      <v-icon v-bind="props" icon="mdi-help-circle-outline" />
                    </template>
                    {{ items.passwordRules }}
                  </v-tooltip>
                </p>
                <v-text-field
                  v-model="password"
                  variant="outlined"
                  :placeholder="items.passwPlaceHolder"
                  :append-inner-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                  :type="show1 ? 'text' : 'password'"
                  :rules="passwordRules"
                  autocomplete="new-password"
                  class="mb-4"
                  required
                  @click:append-inner="show1 = !show1"
                />
              </div>
              <div>
                <p>{{ items.repPasswLabel }}</p>
                <v-text-field
                  v-model="passwordConfirm"
                  variant="outlined"
                  :placeholder="items.repPasswPlaceHolder"
                  :append-inner-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
                  :type="show2 ? 'text' : 'password'"
                  :rules="passwordConfirmRules"
                  autocomplete="new-password"
                  required
                  class="mb-4"
                  @click:append-inner="show2 = !show2"
                />
              </div>
              <div>
                <p>{{ items.allRequired }}</p>
              </div>
              <div class="d-flex justify-center mt-4">
                <v-btn
                  type="submit"
                  variant="elevated"
                  color="primary"
                  size="large"
                  min-width="150"
                >
                  {{ items.registerButton }}
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
</style>
