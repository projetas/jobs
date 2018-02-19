using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Vehicle.Projetas.Web.Api.Controllers
{
    public class VehicleController : ApiController
    {
        Bll.Vehicle vehicleBll = new Bll.Vehicle();

        public IEnumerable<Model.Vehicle> Get()
        {
            try
            {
                return vehicleBll.readAll();
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message, ex.InnerException);
            }
        }

        public IEnumerable<Model.Vehicle> Get(int id)
        {
            try
            {
                return vehicleBll.read(v => v.Id == id);
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message, ex.InnerException);
            }
        }

        public void Post([FromBody]Model.Vehicle vehicle)
        {
            try
            {             
                vehicleBll.create(vehicle);
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message, ex.InnerException);
            }
        }

        public void Put(int id, [FromBody]Model.Vehicle vehicle)
        {
            try
            {                
                vehicleBll.update(vehicle);
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message, ex.InnerException);
            }
        }

        public void Delete(int id)
        {
            try
            {
                vehicleBll.delete(v => v.Id == id);
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message, ex.InnerException);
            }
        }
    }
}
