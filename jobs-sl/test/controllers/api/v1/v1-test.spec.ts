import * as server from '../../../../server';
import * as request from 'supertest';

describe('Jobs', function () {

    it('/GET', (done) => {
        request.agent(server).get('/api/v1')
            .set('Content-Type', 'text/html')
            .set('Accept-Language', 'pt-BR;en-US')
            .expect(200)
            .end((error: any) => {
                done(error);
            })
    });

});
