import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WriteOverviewComponent } from './write-overview.component';

describe('WriteOverviewComponent', () => {
  let component: WriteOverviewComponent;
  let fixture: ComponentFixture<WriteOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [WriteOverviewComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(WriteOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
