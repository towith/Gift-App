import {Component, OnInit} from '@angular/core';
import {AppWidget} from "../../appWidget/appWidget";
import {AppWidgetService} from "../../appWidget/appWidget.service";
import {PageResponse} from "../../../support/paging";
import {MessageService} from "../../../service/message.service";
import {Router} from "@angular/router";
import {ApplicationScopeServiceService} from "../../../support/application-scope-service.service";

@Component({
    selector: 'app-widget-chooser',
    templateUrl: './widget-chooser.component.html',
    styleUrls: ['./widget-chooser.component.css']
})
export class WidgetChooserComponent implements OnInit {
    example: AppWidget;
    currentPage: PageResponse<AppWidget> = new PageResponse<AppWidget>(0, 0, []);

    constructor(private appWidgetService: AppWidgetService, private messageService: MessageService
        , private router: Router, private applicationScope: ApplicationScopeServiceService) {
    }

    ngOnInit() {
        this.appWidgetService.getPage(this.example, null).subscribe(
            pageResponse => this.currentPage = pageResponse,
            error => this.messageService.error('Could not get the results', error)
        );
    }

    ok4select() {
        var widgetList: AppWidget[] = this.currentPage.content;
        var chosen = [];
        for (let widget of widgetList) {
            if (widget.selected) {
                chosen.push(widget);
            }
        }
        this.applicationScope.chosenWidget = chosen;
        this.router.navigate(['/bench', '___']);
    }
}
