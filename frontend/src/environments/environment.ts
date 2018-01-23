
/* Create URL Default APIs */
const URL_DEFAULT_API = 'localhost';
const PROTOCOL_HTTPS = false;
// const PORT_DEFAULT_API = PROTOCOL_HTTPS ? '8243' : '8280';
const HTTP_API = PROTOCOL_HTTPS ? 'https://' : 'http://';
const URL_API = HTTP_API + URL_DEFAULT_API;
//+ ':'  + PORT_DEFAULT_API + '/';

/* Control name APIs */
const TESTE_API = ':1080';


/*Services endpoints control names*/
const VEHICLES_ENDPOINT = '/vehicle';

export const environment = {  
  production: false,
  envName: 'dev',
  version: '0.0.1',
  

  TESTE_API: URL_API + TESTE_API,

  
  VEHICLES_ENDPOINT: VEHICLES_ENDPOINT
};


