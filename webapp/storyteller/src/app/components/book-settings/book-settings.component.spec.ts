import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookSettingsComponent } from './book-settings.component';

describe('BookSettingsComponent', () => {
  let component: BookSettingsComponent;
  let fixture: ComponentFixture<BookSettingsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BookSettingsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BookSettingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
