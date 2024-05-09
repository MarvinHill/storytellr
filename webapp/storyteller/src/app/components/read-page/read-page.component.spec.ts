import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReadPageComponent } from './read-page.component';

describe('ReadPageComponent', () => {
  let component: ReadPageComponent;
  let fixture: ComponentFixture<ReadPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ReadPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ReadPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
