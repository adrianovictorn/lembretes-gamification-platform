import { TestBed } from '@angular/core/testing';

import { GameProfileServices } from './game-profile-services';

describe('GameProfileServices', () => {
  let service: GameProfileServices;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GameProfileServices);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
