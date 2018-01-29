import {VehicleModel} from '../../../../../model/vehicle.model';
import * as request from 'supertest';
import {SuperTest, Test} from 'supertest';
import {isNullOrUndefined} from 'util';
import {Response} from 'superagent';
import v4 = require('uuid/v4');

const server = require('../../../../../server');

describe('Vehicles', function () {

    let app: any;

    let agent: SuperTest<Test>;

    let vehicle: VehicleModel;

    before((done) => {
        app = server.listen(done);
        agent = request.agent(app);
    });

    after((done) => {
        app.close(done);
    });

    it('/POST: register a new vehicle without information', (done) => {
        agent.post('/api/v1/vehicles')
            .set('Content-Type', 'application/json')
            .set('Accept-Language', 'pt-BR;en-US')
            .set('Accept', 'application/json')
            .expect(412)
            .end((error: any) => {
                done(error);
            });

    });

    it('/POST: register a new unbranded vehicle', (done) => {
        agent.post('/api/v1/vehicles')
            .set('Content-Type', 'application/json')
            .set('Accept-Language', 'pt-BR;en-US')
            .set('Accept', 'application/json')
            .send(
                {
                    'model': 'Sentra'
                }
            )
            .expect(412)
            .end((error: any) => {
                done(error);
            });

    });

    it('/POST: register a new vehicle without a model', (done) => {
        agent.post('/api/v1/vehicles')
            .set('Content-Type', 'application/json')
            .set('Accept-Language', 'pt-BR;en-US')
            .set('Accept', 'application/json')
            .send(
                {
                    'brand': 'Nissan'
                }
            )
            .expect(412)
            .end((error: any) => {
                done(error);
            });

    });

    it('/POST: register a new vehicle without a price', (done) => {
        agent.post('/api/v1/vehicles')
            .set('Content-Type', 'application/json')
            .set('Accept-Language', 'pt-BR;en-US')
            .set('Accept', 'application/json')
            .send(
                {
                    'brand': 'Nissan',
                    'model': 'Sentra'
                }
            )
            .expect(412)
            .end((error: any) => {
                done(error);
            });

    });

    it('/POST: register a new vehicle without a year', (done) => {
        agent.post('/api/v1/vehicles')
            .set('Content-Type', 'application/json')
            .set('Accept-Language', 'pt-BR;en-US')
            .set('Accept', 'application/json')
            .send(
                {
                    'brand': 'Nissan',
                    'model': 'Sentra',
                    'price': 100.95
                }
            )
            .expect(412)
            .end((error: any) => {
                done(error);
            });

    });

    it('/POST: register a new vehicle without a situation', (done) => {
        agent.post('/api/v1/vehicles')
            .set('Content-Type', 'application/json')
            .set('Accept-Language', 'pt-BR;en-US')
            .set('Accept', 'application/json')
            .send(
                {
                    'brand': 'Nissan',
                    'model': 'Sentra',
                    'price': 100.95,
                    'year': 2010
                }
            )
            .expect(412)
            .end((error: any) => {
                done(error);
            });

    });

    it('/POST: register a new vehicle', (done) => {
        agent.post('/api/v1/vehicles')
            .set('Content-Type', 'application/json')
            .set('Accept-Language', 'pt-BR;en-US')
            .set('Accept', 'application/json')
            .send(
                {
                    'brand': 'Nissan',
                    'model': 'Sentra',
                    'price': 100.95,
                    'year': 2010,
                    'new': true,
                    'cor': 'Malbec Red',
                    'description': 'Malbec Red'
                }
            )
            .expect(200)
            .expect('Content-Type', /json/)
            .end((error: any, res: Response) => {
                if (!isNullOrUndefined(res.body)) {
                    vehicle = res.body;
                }
                done(error);
            });
    });

    it('/GET: search for vehicles without filter', (done) => {
        agent.get('/api/v1/vehicles')
            .set('Content-Type', 'application/x-www-form-urlencoded')
            .set('Accept-Language', 'pt-BR;en-US')
            .set('Accept', 'application/json')
            .expect(400)
            .end((error: any) => {
                done(error);
            });
    });

    it('/GET: search vehicles per year, not content', (done) => {
        agent.get('/api/v1/vehicles')
            .set('Content-Type', 'application/x-www-form-urlencoded')
            .set('Accept-Language', 'pt-BR;en-US')
            .set('Accept', 'application/json')
            .query({
                fulltext: '2018',
                page: 0
            })
            .expect(204)
            .end((error: any) => {
                done(error);
            });
    });

    it('/GET: Will get vehicles by model', (done) => {
        agent.get('/api/v1/vehicles')
            .set('Content-Type', 'application/x-www-form-urlencoded')
            .set('Accept-Language', 'pt-BR;en-US')
            .set('Accept', 'application/json')
            .query({
                fulltext: 'sentra'
            })
            .expect(200)
            .expect('Content-Type', /json/)
            .expect('Content-Version', /v1/)
            .end((error: any) => {
                done(error);
            });
    });

    it('/GET: Will get vehicles by year', (done) => {
        agent.get('/api/v1/vehicles')
            .set('Content-Type', 'application/x-www-form-urlencoded')
            .set('Accept-Language', 'pt-BR;en-US')
            .set('Accept', 'application/json')
            .query({
                fulltext: '2010',
                page: 0
            })
            .expect(200)
            .expect('Content-Type', /json/)
            .expect('Content-Version', /v1/)
            .end((error: any) => {
                done(error);
            });
    });

    it('/GET: Vehicle by id', (done) => {
        agent.get('/api/v1/vehicles/' + vehicle.id)
            .set('Content-Type', 'application/x-www-form-urlencoded')
            .set('Accept-Language', 'pt-BR;en-US')
            .set('Accept', 'application/json')
            .expect(200)
            .expect('Content-Type', /json/)
            .expect('Content-Version', /v1/)
            .end((error: any) => {
                done(error);
            });
    });

    it('/GET: Vehicle by id, not found', (done) => {
        agent.get('/api/v1/vehicles/' + v4())
            .set('Content-Type', 'application/x-www-form-urlencoded')
            .set('Accept-Language', 'pt-BR;en-US')
            .set('Accept', 'application/json')
            .expect(204)
            .end((error: any) => {
                done(error);
            });
    });

    it('/PUT: change vehicle with id', (done) => {
        agent.put('/api/v1/vehicles')
            .set('Content-Type', 'application/json')
            .set('Accept-Language', 'pt-BR;en-US')
            .set('Accept', 'application/json')
            .send(
                Object.assign(vehicle, {
                    cor: 'Malbec'
                })
            )
            .expect(200)
            .expect('Content-Type', /json/)
            .end((error: any) => {
                done(error);
            });

    });

    it('/PUT:change vehicle without id', (done) => {
        agent.put('/api/v1/vehicles')
            .set('Content-Type', 'application/json')
            .set('Accept-Language', 'pt-BR;en-US')
            .set('Accept', 'application/json')
            .send(
                {
                    cor: 'Malbec'
                }
            )
            .expect(412)
            .expect('Content-Type', /json/)
            .end((error: any) => {
                done(error);
            });

    });

    it('/PUT:change vehicle without informations', (done) => {
        agent.put('/api/v1/vehicles')
            .set('Content-Type', 'application/json')
            .set('Accept-Language', 'pt-BR;en-US')
            .set('Accept', 'application/json')
            .expect(500)
            .expect('Content-Type', /json/)
            .end((error: any) => {
                done(error);
            });

    });

    it('/DELETE: delete vehicle', (done) => {
        agent.delete('/api/v1/vehicles/' + vehicle.id)
            .set('Content-Type', 'application/x-www-form-urlencoded')
            .set('Accept-Language', 'pt-BR;en-US')
            .set('Accept', 'application/json')
            .expect(200)
            .expect('Content-Type', /json/)
            .end((error: any) => {
                done(error);
            });

    });

    it('/DELETE: delete vehicle, not found', (done) => {
        agent.delete('/api/v1/vehicles/' + v4())
            .set('Content-Type', 'application/x-www-form-urlencoded')
            .set('Accept-Language', 'pt-BR;en-US')
            .set('Accept', 'application/json')
            .expect(204)
            .end((error: any) => {
                done(error);
            });

    });

});
