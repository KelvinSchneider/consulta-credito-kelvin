import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

// Material Imports
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatRadioModule } from '@angular/material/radio';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';

import { CreditoSearchParams, SearchType } from '../../models/credito.model';

@Component({
  selector: 'app-consulta-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatIconModule,
    MatRadioModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
  ],
  templateUrl: './consulta-form.component.html',
  styleUrls: ['./consulta-form.component.scss'],
})
export class ConsultaFormComponent implements OnInit {
  @Output() search = new EventEmitter<{ params: CreditoSearchParams; type: SearchType }>();
  @Input() isLoading: boolean = false;

  public SearchType = SearchType;
  public consultaForm!: FormGroup;
  public readonly searchIcon = 'search';

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.consultaForm = this.fb.group({
      searchType: [SearchType.NFSE, Validators.required],
      searchValue: ['', Validators.required],
    });

    // Resetar o campo quando o tipo de busca muda
    this.consultaForm.get('searchType')?.valueChanges.subscribe(() => {
      this.consultaForm.get('searchValue')?.setValue('');
    });
  }

  get inputPlaceholder(): string {
    const type = this.consultaForm.get('searchType')?.value;
    return type === SearchType.NFSE
      ? '7891011'
      : '123456';
  }

  get inputLabel(): string {
    const type = this.consultaForm.get('searchType')?.value;
    return type === SearchType.NFSE ? 'Número da NFS-e' : 'Número do Crédito';
  }

  consultarCreditos(): void {
    if (this.consultaForm.invalid) {
      this.consultaForm.markAllAsTouched();
      return;
    }

    const { searchType, searchValue } = this.consultaForm.value;

    const params: CreditoSearchParams = {
      numeroNfse: searchType === SearchType.NFSE ? searchValue : undefined,
      numeroCredito: searchType === SearchType.CREDITO ? searchValue : undefined,
    };

    this.search.emit({ params, type: searchType });
  }
}
