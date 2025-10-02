import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaHeaderComponent } from './consulta-header.component';

describe('ConsultaHeaderComponent', () => {
  let component: ConsultaHeaderComponent;
  let fixture: ComponentFixture<ConsultaHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConsultaHeaderComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ConsultaHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
