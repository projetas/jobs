import * as server from '../../../server';
import * as request from 'supertest';

describe('Jobs API', function () {

    it('/GET', (done) => {
        request.agent(server).get('/api')
            .set('Content-Type', 'text/html')
            .set('Accept-Language', 'pt-BR;en-US')
            .expect(404)
            .end((error: any) => {
                done(error);
            })
    });

});
