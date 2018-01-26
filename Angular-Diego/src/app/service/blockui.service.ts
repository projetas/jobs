import { Injectable, EventEmitter } from '@angular/core';

@Injectable()
export class BlockUIService {

    public event: EventEmitter<any>;

    constructor() {
        this.event = new EventEmitter();
    }

    public startBlock() {
        this.event.emit(true);
    }

    public stopBlock() {
        this.event.emit(false);
    }

}