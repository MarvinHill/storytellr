import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvatarMenuItemComponent } from './avatar-menu-item.component';

describe('AvatarMenuItemComponent', () => {
  let component: AvatarMenuItemComponent;
  let fixture: ComponentFixture<AvatarMenuItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AvatarMenuItemComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AvatarMenuItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
