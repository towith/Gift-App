import {Component, EventEmitter, Input, OnInit, Output, SimpleChanges} from '@angular/core';
import 'rxjs/add/observable/throw';
import {Router} from "@angular/router";
import {MessageService} from "../../service/message.service";
import {App_Service} from "../../entities/app_/app_.service";
import {MatDialog} from "@angular/material";
import {DataTable, LazyLoadEvent} from "primeng/primeng";
import {PageResponse} from "../../support/paging";
import {ConfirmDeleteDialogComponent} from "../../support/confirm-delete-dialog.component";
import {App_} from "../../entities/app_/app_";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ApplicationScopeServiceService} from "../../support/application-scope-service.service";
import {AppWidget} from "../appWidget/appWidget";
import {AppWidgetService} from "../appWidget/appWidget.service";

@Component({
    selector: 'app-bench',
    templateUrl: './bench.component.html',
    styleUrls: ['./bench.component.css']
})
export class BenchComponent implements OnInit {
    ngOnInit(): void {
        if (this.chosen) {
            for (let aw of this.chosen) {
                var ctrlName = 'control' + aw.id;
                var controlsConfig = {};
                controlsConfig[ctrlName] = [''];
                // controlsConfig[ctrlName] = ['', Validators.required];
                this.formGroups['' + aw.id] = this._formBuilder.group(controlsConfig);
            }
        }
    }

    formGroups: { [key: string]: FormGroup } = {};

    @Input() header = "App_s...";

    // When 'sub' is true, it means this list is used as a one-to-many list.
    // It belongs to a parent entity, as a result the addNew operation
    // must prefill the parent entity. The prefill is not done here, instead we
    // emit an event.
    // When 'sub' is false, we display basic search criterias
    @Input() sub: boolean;
    @Output() onAddNewClicked = new EventEmitter();

    app_ToDelete: App_;

    // basic search criterias (visible if not in 'sub' mode)
    example: App_ = new App_();
    chosen: AppWidget[];

    // list is paginated
    currentPage: PageResponse<App_> = new PageResponse<App_>(0, 0, []);


    constructor(private router: Router,
                private app_Service: App_Service,
                private messageService: MessageService,
                private confirmDeleteDialog: MatDialog,
                private _formBuilder: FormBuilder,
                private applicationScope: ApplicationScopeServiceService,
                private appWidgetService:AppWidgetService) {
        this.chosen = this.applicationScope.chosenWidget;
    }

    /**
     * When used as a 'sub' component (to display one-to-many list), refreshes the table
     * content when the input changes.
     */
    ngOnChanges(changes: SimpleChanges) {
        this.loadPage({first: 0, rows: 10, sortField: null, sortOrder: null, filters: null, multiSortMeta: null});
    }

    /**
     * Invoked when user presses the search button.
     */
    search(dt: DataTable) {
        if (!this.sub) {
            dt.reset();
            this.loadPage({
                first: 0,
                rows: dt.rows,
                sortField: dt.sortField,
                sortOrder: dt.sortOrder,
                filters: null,
                multiSortMeta: dt.multiSortMeta
            });
        }
    }

    /**
     * Invoked automatically by primeng datatable.
     */
    loadPage(event: LazyLoadEvent) {
        this.app_Service.getPage(this.example, event).subscribe(
            pageResponse => this.currentPage = pageResponse,
            error => this.messageService.error('Could not get the results', error)
        );
    }

    onRowSelect(event: any) {
        let id = event.data.id;
        this.router.navigate(['/app_', id]);
    }

    addNew() {
        if (this.sub) {
            this.onAddNewClicked.emit("addNew");
        } else {
            this.router.navigate(['/app_', 'new']);
        }
    }

    showDeleteDialog(rowData: any) {
        let app_ToDelete: App_ = <App_> rowData;

        let dialogRef = this.confirmDeleteDialog.open(ConfirmDeleteDialogComponent);
        dialogRef.afterClosed().subscribe(result => {
            if (result === 'delete') {
                this.delete(app_ToDelete);
            }
        });
    }

    private delete(app_ToDelete: App_) {
        let id = app_ToDelete.id;

        this.app_Service.delete(id).subscribe(
            response => {
                this.currentPage.remove(app_ToDelete);
                this.messageService.info('Deleted OK', 'Angular Rocks!');
            },
            error => this.messageService.error('Could not delete!', error)
        );
    }

    ok4config() {
        this.appWidgetService.saveConfigAndGenApp(this.chosen).subscribe(
            pageResponse => this.currentPage = pageResponse,
            error => this.messageService.error('Could not get the results', error)
        );

    }
}
