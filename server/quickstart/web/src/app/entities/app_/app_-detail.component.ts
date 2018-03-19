//
// Project home: https://github.com/jaxio/celerio-angular-quickstart
//
// Source code generated by Celerio, an Open Source code generator by Jaxio.
// Documentation: http://www.jaxio.com/documentation/celerio/
// Source code: https://github.com/jaxio/celerio/
// Follow us on twitter: @jaxiosoft
// This header can be customized in Celerio conf...
// Template pack-angular:web/src/app/entities/entity-detail.component.ts.e.vm
//
import {Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MessageService} from '../../service/message.service';
import {App_} from './app_';
import {App_Service} from './app_.service';

@Component({
    moduleId: module.id,
	templateUrl: 'app_-detail.component.html',
	selector: 'app_-detail',
})
export class App_DetailComponent implements OnInit, OnDestroy {
    app_ : App_;

    private params_subscription: any;

    showAppWidgets : boolean = true;

    @Input() sub : boolean = false;
    @Output() onSaveClicked = new EventEmitter<App_>();
    @Output() onCancelClicked = new EventEmitter();

    constructor(private route: ActivatedRoute, private router: Router, private messageService: MessageService, private app_Service: App_Service) {
    }

    ngOnInit() {
        if (this.sub) {
            return;
        }

        this.params_subscription = this.route.params.subscribe(params => {
            let id = params['id'];
            console.log('ngOnInit for app_-detail ' + id);

            if (id === 'new') {
                this.app_ = new App_();
            } else {
                this.app_Service.getApp_(id)
                    .subscribe(app_ => {
                            this.app_ = app_;
                        },
                        error =>  this.messageService.error('ngOnInit error', error)
                    );
            }
        });
    }

    ngOnDestroy() {
        if (!this.sub) {
            this.params_subscription.unsubscribe();
        }
    }

    onSave() {
        this.app_Service.update(this.app_).
            subscribe(
                app_ => {
                    this.app_ = app_;
                    if (this.sub) {
                        this.onSaveClicked.emit(this.app_);
                        this.messageService.info('Saved OK and msg emitted', 'Angular Rocks!')
                    } else {
                        this.messageService.info('Saved OK', 'Angular Rocks!')
                    }
                },
                error =>  this.messageService.error('Could not save', error)
            );
    }

    onCancel() {
        if (this.sub) {
            this.onCancelClicked.emit("cancel");
            this.messageService.info('Cancel clicked and msg emitted', 'Angular Rocks!')
        }
    }

}
