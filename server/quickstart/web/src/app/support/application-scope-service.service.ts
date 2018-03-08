import {Injectable} from '@angular/core';
import {AppWidget} from "../entities/appWidget/appWidget";

@Injectable()
export class ApplicationScopeServiceService {
    chosenWidget: AppWidget[];

    constructor() {
    }

}
