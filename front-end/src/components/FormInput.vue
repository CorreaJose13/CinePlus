<script setup lang="ts">
import { ref, reactive } from 'vue';
import type { FormProps } from '@/types/types';

const props = defineProps<FormProps>();

const emit = defineEmits<{
  (e: 'submit', formData: Record<string, string | number | boolean>): void;
  (e: 'cancel'): void;
}>();

const formValues = reactive<Record<string, string | number | boolean>>({});

props.fields.forEach(field => {
  formValues[field.id] = '';
});

const formRef = ref();

const isFormValid = async (): Promise<boolean> => {
  const { valid } = await formRef.value?.validate() || { valid: false };
  return valid;
};

const onSubmit = async () => {
  const valid = await isFormValid();
  if (valid) {
    emit('submit', formValues);
  }
};

const onCancel = () => {
  emit('cancel');
};
</script>

<template>
  <v-container class="fill-height">
    <v-row class="fill-height" align="center" justify="center">
      <v-col cols="12" sm="8" md="6" lg="4">
        <v-card class="form-container elevation-5">
          <v-card-title class="text-center text-h5 font-weight-bold pt-4">
            {{ titleForm }}
          </v-card-title>
          <v-card-text>
            <v-form ref="formRef" @submit.prevent="onSubmit">
              <div v-for="field in fields" :key="field.id">
                <v-text-field
                  v-if="['text', 'email', 'password', 'number', 'tel', 'url'].includes(field.type)"
                  v-model="formValues[field.id]"
                  :label="field.label"
                  :placeholder="field.placeholder"
                  :type="field.type"
                  :rules="field.rules"
                  :required="field.required"
                  :clearable="field.clearable"
                  :prepend-icon="field.prependIcon"
                  :append-icon="field.appendIcon"
                  variant="outlined"
                  class="mb-4"
                />
                
                <v-textarea
                  v-else-if="field.type === 'textarea'"
                  v-model="formValues[field.id]"
                  :label="field.label"
                  :placeholder="field.placeholder"
                  :rules="field.rules"
                  :required="field.required"
                  :clearable="field.clearable"
                  :prepend-icon="field.prependIcon"
                  variant="outlined"
                  class="mb-4"
                />
              </div>
              
              <div class="d-flex justify-space-between mt-6">
                <v-btn
                  v-if="showCancelButton"
                  variant="outlined"
                  color="error"
                  @click="onCancel"
                >
                  {{ cancelButtonText || 'Cancelar' }}
                </v-btn>
                
                <v-spacer v-if="showCancelButton" />
                
                <v-btn
                  color="primary"
                  type="submit"
                  variant="elevated"
                  :loading="loading"
                >
                  {{ submitButtonText || 'Enviar' }}
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
</style>