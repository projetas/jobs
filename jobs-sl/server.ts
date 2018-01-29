import 'reflect-metadata';
import {BusinessException} from './errors/business-exception';
import * as express from 'express';
import {NextFunction, Request, Response} from 'express';
import {Logger} from './lib/logger';
import * as http from 'http';
import {Server} from 'http';
import {format} from 'util';
import morgan = require('morgan');

const app: any = module.exports = express();

const enrouten = require('express-enrouten');

const logger = new Logger();

app.use(morgan(':remote-addr :remote-user :method :url :status :res[content-length] - :response-time ms [:user-agent]'))
    .use(express.json({
        strict: true
    }))
    .use(express.urlencoded({
        extended: true
    })).use((req: Request, res: Response, next: NextFunction) => {

    // Website you wish to allow to connect
    res.header('Access-Control-Allow-Origin', 'http://localhost:4200');

    // Request methods you wish to allow
    res.header('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');

    // Request headers you wish to allow
    res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept, Authorization');

    // Pass to next layer of middleware
    next();

}).use(enrouten({
    directory: 'controllers'
}));

//Cusom ErrorHandler
app.use((error: any, req: Request, res: Response, next: NextFunction) => {

    res.status(400)
        .header('Content-Message', 'Could not process request')
        .header('Content-Version', 'v1');

    //logger.log('error', format('Could not process request: %j', error));

    if (error instanceof BusinessException) {
        res.status((<BusinessException>error).status || 400).send({
            status: (<BusinessException>error).status || 400,
            message: error.message
        });
    } else {
        res.status(500).send({
            error: error.message,
            message: 'Could not process request'
        });
    }

});

const port: string = process.env.PORT || '8000';

const server: Server = http.createServer(app);

if (!module.parent) {
    server.listen(port, () => {
        console.log(format('Server listening on http://localhost:%d', port));
    });
}

server.on('listening', function () {
    console.log('Application ready to serve requests.');
    console.log(format('Environment: %s', process.env.NODE_ENV || 'development'));
});

process.on('uncaughtException', function (error: any) {
    console.log(format('uncaughtException: %s', error.message));
});

module.exports = server;
