using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;
using CarProg.BLL;
using CarProg.OBJ;

namespace CarProg.API.Controllers
{
    public class VehicleController : ApiController
    {
        private VehicleBo vehicleBo = new VehicleBo();

        // GET api/vehicle
        [HttpGet]
        public async Task<IEnumerable<Vehicle>> Get()
        {
            return await vehicleBo.GetListAsync();
        }

        // POST api/vehicle
        [HttpPost]
        public async Task Post([FromBody]Vehicle vehicle)
        {
            await vehicleBo.AddAsync(vehicle);
        }

        // PUT api/vehicle
        [HttpPut]
        public async Task Put([FromBody]Vehicle vehicle)
        {
            await vehicleBo.AlterAsync(vehicle);
        }

        // DELETE api/vehicle
        [HttpDelete]
        public async Task Delete([FromUri]int id)
        {
            await vehicleBo.RemoveAsync(id);
        }
    }
}
