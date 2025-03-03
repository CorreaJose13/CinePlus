/**
 * plugins/index.ts
 *
 * Automatically included in `./src/main.ts`
 */

// Plugins
import vuetify from '@/plugins/vuetify'
import vPhoneInput from '@/plugins/vphone'
import pinia from '@/store'
import router from '@/router'

// Types
import type { App } from 'vue'

export function registerPlugins(app: App) {
  app.use(vuetify).use(vPhoneInput).use(router).use(pinia)
}
