import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GoalDefinitionManagementComponent } from './goal-definition-management.component';

describe('GoalDefinitionManagementComponent', () => {
  let component: GoalDefinitionManagementComponent;
  let fixture: ComponentFixture<GoalDefinitionManagementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GoalDefinitionManagementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GoalDefinitionManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
