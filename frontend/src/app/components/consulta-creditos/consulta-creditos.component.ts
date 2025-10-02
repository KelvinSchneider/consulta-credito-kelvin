import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { finalize } from 'rxjs/operators';
import { ConsultaHeaderComponent } from '../consulta-header/consulta-header.component';
import { ConsultaFormComponent } from '../consulta-form/consulta-form.component';
import { ConsultaResultadoComponent } from '../consulta-resultado/consulta-resultado.component';
import { CreditoService } from '../../services/credito.service';
import { Credito, CreditoSearchParams, SearchType } from '../../models/credito.model';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-consulta-creditos',
  standalone: true,
  imports: [
    CommonModule,
    MatSnackBarModule,
    MatCardModule,
    ConsultaHeaderComponent,
    ConsultaFormComponent,
    ConsultaResultadoComponent,
  ],
  templateUrl: './consulta-creditos.component.html',
  styleUrls: ['./consulta-creditos.component.scss'],
})
export class ConsultaCreditosComponent implements OnInit {
  public creditos: Credito[] | null = null;
  public isLoading: boolean = false;
  public errorMessage: string | null = null;

  constructor(
    private creditoService: CreditoService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {}

  /**
   * Trata o evento de busca emitido pelo ConsultaFormComponent.
   * @param event Dados da busca (params e type)
   */
  handleSearch(event: { params: CreditoSearchParams; type: SearchType }): void {
    this.resetResult();
    this.isLoading = true;

    this.creditoService.searchCreditos(event.params, event.type)
      .pipe(
        finalize(() => (this.isLoading = false))
      )
      .subscribe({
        next: (data) => {
          this.creditos = data;
          if (data.length === 0) {
             this.snackBar.open('Nenhum crédito encontrado.', 'Fechar', { duration: 5000 });
          }
        },
        error: (err) => {
          this.errorMessage = err.message;
          this.snackBar.open(err.message, 'Fechar', { duration: 8000 });
        },
      });
  }

  private resetResult(): void {
    this.creditos = null;
    this.errorMessage = null;
  }
}
