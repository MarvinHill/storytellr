import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DiscoveryPageComponent } from './discovery-page.component';

describe('DiscoveryPageComponent', () => {
  let component: DiscoveryPageComponent;
  let fixture: ComponentFixture<DiscoveryPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DiscoveryPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DiscoveryPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
