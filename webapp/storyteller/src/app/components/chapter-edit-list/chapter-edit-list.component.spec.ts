import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChapterEditListComponent } from './chapter-edit-list.component';

describe('ChapterEditListComponent', () => {
  let component: ChapterEditListComponent;
  let fixture: ComponentFixture<ChapterEditListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ChapterEditListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ChapterEditListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
