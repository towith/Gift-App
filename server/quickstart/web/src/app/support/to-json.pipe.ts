import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
    name: 'toJson'
})
export class ToJsonPipe implements PipeTransform {

    transform(value: any, args?: any): any {
        return eval('(' + value + ')');
    }

}
