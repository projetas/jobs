using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using TestProjetas.Domain.Entities;
using TestProjetas.Repository;

namespace TestProjetas.WebApi.Controllers
{
    [Produces("application/json")]
    [Route("api/Vehicles")]
    public class VehiclesController : Controller
    {
        // GET: api/Vehicles
        [HttpGet]
        public IEnumerable<Vehicle> Get()
        {
            return new VehicleRepository().Repository.Get();
        }

        // GET: api/Vehicles/5
        [HttpGet("{id}", Name = "Get")]
        public Vehicle Get(int id)
        {
            return new VehicleRepository().Repository.Get(x => x.Id == id).First();
        }

        // POST: api/Vehicles
        [HttpPost]
        public void Post([FromBody]Vehicle value)
        {
            new VehicleRepository().Repository.Add(value);
        }

        // PUT: api/Vehicles
        [HttpPut]
        public void Put([FromBody]Vehicle value)
        {
            new VehicleRepository().Repository.Update(value);
        }

        // DELETE: api/ApiWithActions/5
        [HttpDelete("{id}")]
        public void Delete(int id)
        {
            new VehicleRepository().Repository.Delete(new Vehicle { Id = id });
        }
    }
}
