import {inject, TestBed} from '@angular/core/testing';

import {ApplicationScopeServiceService} from './application-scope-service.service';

describe('ApplicationScopeServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ApplicationScopeServiceService]
    });
  });

  it('should be created', inject([ApplicationScopeServiceService], (service: ApplicationScopeServiceService) => {
    expect(service).toBeTruthy();
  }));
});
