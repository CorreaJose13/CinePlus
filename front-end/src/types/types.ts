export interface GenericItem {
  [key: string]: string | number | boolean
}

export interface Movie {
  text: string
}

export interface User {
  text: string
}

export interface Purchase {
  text: string
}

export interface MenuItem {
  text: string
  icon: string
  action: string
}

export interface CustomStyle {
  fieldName: string
  fieldValue: string | number
  class: (value: string | number | boolean) => string
}

export type FormField = {
  id: string
  label: string
  placeholder: string
  type: string
  rules?: ((value: string) => boolean | string)[]
  required?: boolean
  dense?: boolean
  clearable?: boolean
  prependIcon?: string
  appendIcon?: string
}

export type FormProps = {
  titleForm: string
  fields: FormField[]
  submitButtonText?: string
  cancelButtonText?: string
  showCancelButton?: boolean
  loading?: boolean
}

export type TableHeader = {
  align?: 'start' | 'center' | 'end'
  key: string
  sortable?: boolean
  title: string
}
