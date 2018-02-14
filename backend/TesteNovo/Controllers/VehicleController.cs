using System;
using System.Collections.Generic;
using System.Net;
using Microsoft.AspNetCore.Mvc;
using Teste.App.Services;
using Teste.App.viewModel;
using Teste.Domain.Entities;

namespace TesteNovo.Controllers
{


    public class VehicleController : Controller
    {

        [HttpGet]
        [Produces(typeof(Vehicle))]
        [Route("/vehicle/{id}")]
        public IActionResult Get(int id, [FromServices] ContractVehicleApp contractPersonApp)
        {
            try
            {
                return Ok(contractPersonApp.GetById(id));
            }
            catch(Exception ex)
            {
                throw ex;
            }
        }


        [HttpGet]
        [Produces(typeof(List<Vehicle>))]
        [Route("/vehicle")]
        public IActionResult Get([FromServices] ContractVehicleApp contractPersonApp)
        {
            try
            {
                return Ok(contractPersonApp.GetAll());
            }
            catch
            {
                return NotFound();
            }

        }

 

        [HttpPost]
        [Route("vehicle")]
        public IActionResult Post([FromBody]VehicleViewModel body, [FromServices] ContractVehicleApp contractPersonApp)
        {
            try
            {
                contractPersonApp.SaveVehicle(body);
                return Ok(HttpStatusCode.OK);
            }
            catch
            {
                return BadRequest();
            }
        }
 
  

        [HttpPut]
        [Route("vehicle")]
        public IActionResult Put([FromBody]VehicleViewModel body, [FromServices] ContractVehicleApp contractPersonApp)
        {
            try
            {
                contractPersonApp.EditVehicle(body);
                return Ok(HttpStatusCode.OK);
            }
            catch
            {
                return BadRequest();
            }
        }

        [HttpDelete]
        [Route("vehicle/{id}")]
        public IActionResult Delete(int id, [FromServices] ContractVehicleApp contractPersonApp)
        {
            if (id > 0)
                contractPersonApp.DeleteVehicle(id);

            else
                BadRequest();

            return Ok(HttpStatusCode.OK);
        }
    }
}