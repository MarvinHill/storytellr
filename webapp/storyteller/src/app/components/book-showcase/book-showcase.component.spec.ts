import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookShowcaseComponent } from './book-showcase.component';

describe('BookShowcaseComponent', () => {
  let component: BookShowcaseComponent;
  let fixture: ComponentFixture<BookShowcaseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BookShowcaseComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BookShowcaseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
