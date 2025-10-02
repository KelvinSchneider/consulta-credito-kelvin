export interface Credito {
  id: number
  numeroCredito: string
  numeroNfse: string
  dataConstituicao: string
  valorIssqn: number
  tipoCredito: string
  simplesNacional: boolean
  aliquota: number
  valorFaturado: number
  valorDeducao: number
  baseCalculo: number
}

export interface CreditoSearchParams {
  numeroNfse?: string
  numeroCredito?: string
}

export enum SearchType {
  NFSE = "nfse",
  CREDITO = "credito",
}
