import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PageMessageBoxComponent } from './page-message-box.component';

describe('PageMessageBoxComponent', () => {
  let component: PageMessageBoxComponent;
  let fixture: ComponentFixture<PageMessageBoxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PageMessageBoxComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PageMessageBoxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
