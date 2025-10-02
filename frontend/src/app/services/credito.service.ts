import { Injectable } from "@angular/core";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Observable, throwError } from "rxjs";
import { catchError, map } from "rxjs/operators";
import {
  Credito,
  CreditoSearchParams,
  SearchType,
} from "../models/credito.model";

@Injectable({
  providedIn: "root",
})
export class CreditoService {
  private readonly baseUrl = `http://localhost:8080/api/creditos`;

  constructor(private http: HttpClient) {}

  /**
   * Busca créditos por número da NFS-e
   * @param numeroNfse Número da NFS-e
   * @returns Observable com lista de créditos
   */
  getCreditosByNfse(numeroNfse: string): Observable<Credito[]> {
    if (!numeroNfse?.trim()) {
      return throwError(() => new Error("Número da NFS-e é obrigatório"));
    }

    return this.http.get<Credito[]>(`${this.baseUrl}/${numeroNfse}`).pipe(
      map((creditos) => creditos || []),
      catchError(this.handleError)
    );
  }

  /**
   * Busca crédito por número do crédito
   * @param numeroCredito Número do crédito
   * @returns Observable com o crédito encontrado
   */
  getCreditoByNumero(numeroCredito: string): Observable<Credito> {
    if (!numeroCredito?.trim()) {
      return throwError(() => new Error("Número do crédito é obrigatório"));
    }

    return this.http
      .get<Credito>(`${this.baseUrl}/credito/${numeroCredito}`)
      .pipe(catchError(this.handleError));
  }

  /**
   * Busca créditos baseado nos parâmetros fornecidos
   * @param params Parâmetros de busca
   * @param searchType Tipo de busca (NFS-e ou Crédito)
   * @returns Observable com os resultados
   */
  searchCreditos(
    params: CreditoSearchParams,
    searchType: SearchType
  ): Observable<Credito[]> {
    if (searchType === SearchType.NFSE && params.numeroNfse) {
      return this.getCreditosByNfse(params.numeroNfse);
    } else if (searchType === SearchType.CREDITO && params.numeroCredito) {
      return this.getCreditoByNumero(params.numeroCredito).pipe(
        map((credito) => (credito ? [credito] : []))
      );
    }

    return throwError(() => new Error("Parâmetros de busca inválidos"));
  }

  /**
   * Trata erros das requisições HTTP
   * @param error Erro da requisição
   * @returns Observable com erro tratado
   */
  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = "Erro desconhecido";

    if (error.error instanceof ErrorEvent) {
      // Erro do lado do cliente
      errorMessage = `Erro: ${error.error.message}`;
    } else if (error.status === 0) {
      // Erro de CORS ou conexão
      errorMessage =
        "Erro de conexão com o servidor. Verifique se a API está rodando e se há problemas de CORS.";
    } else {
      // Erro do lado do servidor
      switch (error.status) {
        case 404:
          errorMessage = "Nenhum crédito encontrado com os dados informados";
          break;
        case 400:
          errorMessage = "Dados de consulta inválidos";
          break;
        case 500:
          errorMessage = "Erro interno do servidor. Tente novamente mais tarde";
          break;
        default:
          errorMessage = `Erro ${error.status}: ${error.message}`;
      }
    }

    console.error("Erro na requisição:", error);
    return throwError(() => new Error(errorMessage));
  }
}
