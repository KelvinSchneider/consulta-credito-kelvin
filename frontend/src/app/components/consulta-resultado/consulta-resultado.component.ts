import { Component, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

// Material Imports
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatTableModule } from '@angular/material/table';

import { Credito } from '../../models/credito.model';

@Component({
  selector: 'app-consulta-resultado',
  standalone: true,
  imports: [CommonModule, MatIconModule, MatProgressSpinnerModule, MatTableModule],
  templateUrl: './consulta-resultado.component.html',
  styleUrls: ['./consulta-resultado.component.scss'],
})
export class ConsultaResultadoComponent {
  @Input() creditos: Credito[] | null = null;
  @Input() isLoading: boolean = false;
  @Input() errorMessage: string | null = null;

  public readonly instructionIcon = 'receipt_long';

  displayedColumns: string[] = [
    'id',
    'numeroCredito',
    'numeroNfse',
    'dataConstituicao',
    'valorIssqn',
    'tipoCredito',
    'simplesNacional',
    'aliquota',
    'valorFaturado',
    'valorDeducao'
  ];
}
