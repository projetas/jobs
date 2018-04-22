using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Test.Business;
using Test.Database.Entities;

namespace Test.API.Controllers
{
    /// <summary>
    /// Controller responsible for manipulating the data of a vehicle
    /// </summary>
    public class VehicleController : Controller
    {

        /// <summary>
        /// Return all the vehicles in database
        /// </summary>
        /// <returns>Return all the vehicles in database</returns>
        [HttpGet("vehicle")]
        public ActionResult GetAll()
        {
            try
            {
                return Json(VehicleBLL.FindAll());
            }
            catch (Exception appException)
            {
                throw appException;
            }
        }

        /// <summary>
        /// Return the vehicle in database by the Id
        /// </summary>
        /// <param name="id">Vehicle ID</param>
        /// <returns>Return the vehicle in database by the Id</returns>
        [HttpGet("vehicle/{id}")]
        public ActionResult GetVehicletById([FromRoute] int id)
        {
            try
            {
                return Json(VehicleBLL.FindById(id));
            }
            catch (Exception appException)
            {
                throw appException;
            }
        }

        /// <summary>
        /// Returns a list of vehicles of a certain brand
        /// </summary>
        /// <param name="brand">Vehicle brand</param>
        /// <returns>Returns a list of vehicles of a certain brand</returns>
        [HttpGet("vehicle/{brand}")]
        public ActionResult GetVehicletByBrand([FromRoute] string brand)
        {
            try
            {
                return Json(VehicleBLL.FindByBrand(brand));
            }
            catch (Exception appException)
            {
                throw appException;
            }
        }

        /// <summary>
        /// Add or update a vehicle in database
        /// </summary>
        /// <param name="_vehicle"></param>
        /// <returns></returns>
        [HttpPost("vehicle")]
        public ActionResult Save([FromBody] Vehicle _vehicle)
        {
            try
            {
                return Json(VehicleBLL.Save(_vehicle));
            }
            catch (Exception appException)
            {
                throw appException;
            }
        }

        /// <summary>
        /// Delete a vehicle from database
        /// </summary>
        /// <param name="_ids"></param>
        /// <returns></returns>
        [HttpDelete("vehicle/delete")]
        public ActionResult Delete([FromBody] IList<Int32> _ids)
        {
            try
            {
                return Json(VehicleBLL.Delete(_ids));
            }
            catch (Exception appException)
            {
                throw appException;
            }
        }
    }
}
