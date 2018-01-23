using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Teste.App.Services;
using Teste.App.viewModel;
using Teste.Domain.Entities;
using Teste.Domain.Repositories;

namespace Test.TesteUnit
{

    [TestClass]
    public class VehiclesTest
    {
        IRepository<Vehicle> _rep;

        VehiclesTest(IRepository<Vehicle> rep)
        {
            _rep = rep;
        }

        [TestMethod]
        public void GetAllVehicles()
        {
            IEnumerable<Vehicle> pessoas = null;

            pessoas = _rep.GetAll();


            Assert.IsNotNull(pessoas);

            Assert.IsTrue(pessoas.ToList().Count > 0);
        }


        [TestMethod]
        public void InsertVehicle()
        {
            Vehicle vehicle = null;

            var v = new VehicleViewModel
            {
                Id = 1,
                Brand = "bmw",
                Model = "X1 bmw",
                Price = 11111,
                ModifiedDate = new DateTime(2018, 11, 25),
                Description = "",
                IsNew = true,
                Color = "verde",
                Year = 1999
            };

            var service = new ContractVehicleApp(_rep);

            var response = service.SaveVehicle(v);

            vehicle = response;

            Assert.IsNotNull(vehicle);
            //Assert.IsFalse(person);

        }


        [TestMethod]
        public void UpdateVehicle()
        {
            Vehicle vehicle = null;

            var at = new Vehicle
            {
                Id = 1,
                Brand = "bmw",
                Model = "X1 bmw",
                Price = 11111,
                AddedDate = new DateTime(2018, 11, 25),
                Description = "",
                IsNew = true,
                Color = "verde",
                Year = 1999
            };
            var atvm = new VehicleViewModel
            {
                Id = 1,
                Brand = "bmw",
                Model = "X1 bmw",
                Price = 11111,
                AddedDate = new DateTime(2018, 11, 25),
                Description = "",
                IsNew = true,
                Color = "verde",
                Year = 1999
            };

            var service = new ContractVehicleApp(_rep);

            var response = service.EditVehicle(atvm);

            vehicle = response;

            Assert.IsNotNull(vehicle);

            Assert.AreEqual(at.Id, atvm.Id);

            Assert.AreEqual(at.IsNew, atvm.IsNew);
        }

    }
}
