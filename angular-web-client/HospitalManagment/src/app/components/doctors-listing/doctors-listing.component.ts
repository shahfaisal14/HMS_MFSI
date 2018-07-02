import { Component, OnInit } from '@angular/core';
import { NgGridItemConfig, NgGridItemEvent, NgGridConfig } from 'angular2-grid';

interface Box {
  id: number;
  config: any;
}

@Component({
  selector: 'app-doctors-listing',
  templateUrl: './doctors-listing.component.html',
  styleUrls: ['./doctors-listing.component.less']
})

export class DoctorsListingComponent implements OnInit {

  private  dataSource;

  // Draggable Grid
  private boxes: Array<Box> = [];
	private rgb: string = '#efefef';
	private curNum;
	private gridConfig: NgGridConfig = <NgGridConfig>{
		'margins': [5],
		'draggable': true,
		'resizable': true,
		'max_cols': 0,
		'max_rows': 0,
		'visible_cols': 0,
		'visible_rows': 0,
		'min_cols': 1,
		'min_rows': 1,
		'col_width': 2,
		'row_height': 2,
		'cascade': 'up',
		'min_width': 50,
		'min_height': 50,
		'fix_to_grid': false,
		'auto_style': true,
		'auto_resize': false,
		'maintain_ratio': false,
		'prefer_new': false,
		'zoom_on_drag': false,
		'limit_to_screen': true
	};
	private itemPositions: Array<any> = [];

  constructor() { 

    // Chart
    this.dataSource = {
      "chart": {
          "caption": "Harry's SuperMart",
          "subCaption": "Top 5 stores in last month by revenue",
          "numberprefix": "$",
          "theme": "fint"
      },
      "data": [
          {
              "label": "Bakersfield Central",
              "value": "880000"
          },
          {
              "label": "Garden Groove harbour",
              "value": "730000"
          },
          {
              "label": "Los Angeles Topanga",
              "value": "590000"
          },
          {
              "label": "Compton-Rancho Dom",
              "value": "520000"
          },
          {
              "label": "Daly City Serramonte",
              "value": "330000"
          }
      ]
    }

    // Draggable Grid
    const dashconf = this._generateDefaultDashConfig();
    for (var i = 0; i < dashconf.length; i++) {
        const conf = dashconf[i];
        conf.payload = 1 + i;
        this.boxes[i] = { id: i + 1, config: conf };
    }
    this.curNum = dashconf.length + 1;
  }

  ngOnInit() {
  }

  // Draggable Grid
  addBox(): void {
    const conf: NgGridItemConfig = this._generateDefaultItemConfig();
    conf.payload = this.curNum++;
    this.boxes.push({ id: conf.payload, config: conf });
    }

    removeWidget(index: number): void {
        if (this.boxes[index]) {
            this.boxes.splice(index, 1);
        }
    }

    updateItem(index: number, event: NgGridItemEvent): void {
        // Do something here
    }

    onDrag(index: number, event: NgGridItemEvent): void {
        // Do something here
    }

    onResize(index: number, event: NgGridItemEvent): void {
        // Do something here
    }

    private _generateDefaultItemConfig(): NgGridItemConfig {
        return { 'dragHandle': '.handle', 'col': 1, 'row': 1, 'sizex': 1, 'sizey': 1 };
    }

    private _generateDefaultDashConfig(): NgGridItemConfig[] {
        return [{ 'dragHandle': '.handle', 'col': 1, 'row': 1, 'sizex': 50, 'sizey': 40 },
        { 'dragHandle': '.handle', 'col': 1, 'row': 1, 'sizex': 1, 'sizey': 1 },
        { 'dragHandle': '.handle', 'col': 26, 'row': 1, 'sizex': 1, 'sizey': 1 },
        { 'dragHandle': '.handle', 'col': 51, 'row': 1, 'sizex': 75, 'sizey': 1 },
        { 'dragHandle': '.handle', 'col': 51, 'row': 26, 'sizex': 32, 'sizey': 40 },
        { 'dragHandle': '.handle', 'col': 83, 'row': 26, 'sizex': 1, 'sizey': 1 }];
    }
}
