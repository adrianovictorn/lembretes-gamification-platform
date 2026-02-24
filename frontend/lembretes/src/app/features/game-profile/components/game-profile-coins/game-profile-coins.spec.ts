import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GameProfileCoins } from './game-profile-coins';

describe('GameProfileCoins', () => {
  let component: GameProfileCoins;
  let fixture: ComponentFixture<GameProfileCoins>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GameProfileCoins]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GameProfileCoins);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
