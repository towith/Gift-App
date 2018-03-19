//
// Project home: https://github.com/jaxio/celerio-angular-quickstart
// 
// Source code generated by Celerio, an Open Source code generator by Jaxio.
// Documentation: http://www.jaxio.com/documentation/celerio/
// Source code: https://github.com/jaxio/celerio/
// Follow us on twitter: @jaxiosoft
// This header can be customized in Celerio conf...
// Template pack-angular:web/src/app/entities/entity.ts.e.vm
//
import {User_} from '../user_/user_';
import {App_} from '../app_/app_';

export class Config_ {
    // Raw attributes
    id : number;
    // x-to-one
    user : User_;
    app : App_;


    constructor(json? : any) {
        if (json != null) {
            this.id = json.id;

            if (json.user != null) {
                this.user = new User_(json.user);
            }

            if (json.app != null) {
                this.app = new App_(json.app);
            }
        }
    }

    // Utils

    static toArray(jsons : any[]) : Config_[] {
        let config_s : Config_[] = [];
        if (jsons != null) {
            for (let json of jsons) {
                config_s.push(new Config_(json));
            }
        }
        return config_s;
    }
}
