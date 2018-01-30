import {Request, Response, Router} from 'express';

module.exports = function (router: Router) {

    router.get('/', (req: Request | any, res: Response) => {

        res.status(200)
            .header('Content-Type', 'text/html')
            .header('Content-Version', 'v1')
            .header('Content-Message', 'Jobs for works')
            .send('Jobs for works');

    });

};
