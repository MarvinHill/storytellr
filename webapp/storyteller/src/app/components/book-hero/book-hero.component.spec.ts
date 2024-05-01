import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookHeroComponent } from './book-hero.component';

describe('BookHeroComponent', () => {
  let component: BookHeroComponent;
  let fixture: ComponentFixture<BookHeroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BookHeroComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BookHeroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
