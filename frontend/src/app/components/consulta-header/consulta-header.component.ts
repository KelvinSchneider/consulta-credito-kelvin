import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-consulta-header',
  standalone: true,
  imports: [CommonModule, MatToolbarModule, MatIconModule],
  templateUrl: './consulta-header.component.html',
  styleUrls: ['./consulta-header.component.scss'],
})
export class ConsultaHeaderComponent {
  @Input() toolbarIcon: string = 'description';
}
