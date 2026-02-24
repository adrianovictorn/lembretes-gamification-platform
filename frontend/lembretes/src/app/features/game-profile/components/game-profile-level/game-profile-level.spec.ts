import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GameProfileLevel } from './game-profile-level';

describe('GameProfileLevel', () => {
  let component: GameProfileLevel;
  let fixture: ComponentFixture<GameProfileLevel>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GameProfileLevel]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GameProfileLevel);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
