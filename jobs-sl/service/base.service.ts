import {Logger} from '../lib/logger';

export abstract class BaseService {

    private _logger: Logger;

    constructor(level: string = 'info') {
        this._logger = new Logger({level: level});
    }

    get logger(): Logger {
        return this._logger;
    }

}
