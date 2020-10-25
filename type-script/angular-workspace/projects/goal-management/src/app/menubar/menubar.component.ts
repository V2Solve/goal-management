import { Component, OnInit } from '@angular/core';
import {MenuItem} from 'primeng/api';

@Component({
  selector: 'app-menubar',
  templateUrl: './menubar.component.html',
  styleUrls: ['./menubar.component.scss']
})
export class MenubarComponent implements OnInit {

  constructor() { 

  }

  items: MenuItem[];

  ngOnInit() {
    this.items = [
        {
            label:'Defining',
            icon:'pi pi-fw pi-circle-on',
            items:[
                {
                    label:'Goal-Domains',
                    icon:'pi pi-fw pi-th-large',
                    routerLink: '/domain-management'
                },
                {
                    label:'Goal-Definitions',
                    icon:'pi pi-fw pi-file',
                    routerLink: '/goal-definitions'
                },
                {
                    label:'Goal-Value-Types',
                    icon:'pi pi-fw pi-list',
                    routerLink: '/goal-value-types'
                }
            ]
        },
        {
            label:'Tracking',
            icon:'pi pi-fw pi-chart-line',
            items:[
                {
                    label:'Card',
                    icon:'pi pi-fw pi-table',
                    routerLink: '/goal-card-management'
                },
                {
                    label:'Add Items',
                    icon:'pi pi-fw pi-plus',
                    routerLink: '/item-management'
                },
            ]
        },
        {
            label:'Audit Logs',
            icon:'pi pi-fw pi-user-edit',
            routerLink: '/audit-logs'
        },
    ];
  }

}
