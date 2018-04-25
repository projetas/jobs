using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Projetas.Domain;
using Projetas.Infra;

namespace Projetas.API.Controllers
{
    [Produces("application/json")]
    [Route("api/Vehicles")]
    public class VehiclesController : Controller
    {
        private IRepository<Vehicle, int> _repo;

        public VehiclesController(IRepository<Vehicle, int> repo)
        {
            _repo = repo;
        }

        // GET: api/<controller>
        [HttpGet]
        public IEnumerable<Vehicle> Get()
        {
            return _repo.GetAll();
        }

        // GET api/<controller>/5
        [HttpGet("{id}")]
        public Vehicle Get(int id)
        {
            return _repo.Get(id);
        }

        // POST api/<controller>
        [HttpPost]
        public void Post([FromBody]Vehicle item)
        {
            _repo.Add(item);
        }

        // PUT api/<controller>/5
        [HttpPut("{id}")]
        public void Put(int id, [FromBody]Vehicle item)
        {
            _repo.Update(id, item);
        }

        // DELETE api/<controller>/5
        [HttpDelete("{id}")]
        public void Delete(int id)
        {
            _repo.Delete(id);
        }
    }
}
