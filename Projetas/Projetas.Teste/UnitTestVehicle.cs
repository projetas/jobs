using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using Projetas.Domain;
using Projetas.Infra;
using System;
using System.Collections.Generic;
using System.Linq;

namespace Projetas.Teste
{
    [TestClass]
    public class UnitTestVehicle
    {
        public readonly IRepository<Vehicle, int> _mockVehicleRepository;

        public UnitTestVehicle()
        {
            IList<Vehicle> vehicles = new List<Vehicle>
            {
                new Vehicle()
                {
                    id = 1,
                    model = "Beetle",
                    brand = "VW",
                    color = "White",
                    description = "New Beetle",
                    isNew = true,
                    year = 2017,
                    price = 111,
                    createdDate = DateTime.Now,
                    updatedDate = DateTime.Now
                },
                new Vehicle()
                {
                    id = 2,
                    model = "Passat",
                    brand = "VW",
                    color = "White",
                    description = "Passat Variant",
                    isNew = false,
                    year = 2015,
                    price = 111,
                    createdDate = DateTime.Now,
                    updatedDate = DateTime.Now
                },
                new Vehicle()
                {
                    id = 3,
                    model = "Voyagem",
                    brand = "VW",
                    color = "Black",
                    description = "Voyagem",
                    isNew = true,
                    year = 2017,
                    price = 111,
                    createdDate = DateTime.Now,
                    updatedDate = DateTime.Now
                }
            };

            var mockRepo = new Mock<IRepository<Vehicle, int>>();

            mockRepo.Setup(mr => mr.GetAll()).Returns(vehicles);

            mockRepo.Setup(mr => mr.Get(
                It.IsAny<int>())).Returns((int i) => vehicles.Where(
                x => x.id == i).Single());

            mockRepo.Setup(mr => mr.Delete(
                It.IsAny<int>())).Returns(
                (int idVehicle) =>
                {
                    var vehicle = vehicles.Where(
                    q => q.id == idVehicle).Single();

                    vehicles.Remove(vehicle);

                    return idVehicle;
                });

            mockRepo.Setup(mr => mr.Add(It.IsAny<Vehicle>())).Returns(
                (Vehicle target) =>
                {
                    DateTime now = DateTime.Now;

                    int vehicleId = 0;

                    if (target.id.Equals(default(int)))
                    {   
                        target.createdDate = now;
                        target.updatedDate = now;
                        target.id = vehicles.Count() + 1;
                        vehicles.Add(target);
                    }
                    else
                    {
                        var original = vehicles.Where(
                            q => q.id == target.id).Single();

                        if (original == null)
                        {
                            return vehicleId;
                        }

                        original.model = target.model;
                        original.price = target.price;
                        original.description = target.description;
                        original.updatedDate = now;
                        original.model = target.model;
                        original.year = target.year;
                        original.isNew = target.isNew;
                        original.color = target.color;
                        vehicleId = target.id;
                    }

                    return vehicleId;
                });

            this._mockVehicleRepository = mockRepo.Object;
        }

        [TestMethod]
        public void CanReturnVehicleById()
        {
            Vehicle testVehicle = this._mockVehicleRepository.Get(2);

            Assert.IsNotNull(testVehicle);
            Assert.IsInstanceOfType(testVehicle, typeof(Vehicle));
            Assert.AreEqual("Passat", testVehicle.model);

        }

        [TestMethod]
        public void CanReturnAllVehicle()
        {
            IList<Vehicle> testVehicles = this._mockVehicleRepository.GetAll().ToList();

            Assert.IsNotNull(testVehicles);
            Assert.AreEqual(3, testVehicles.Count);
        }

        [TestMethod]
        public void CanInsertVehicle()
        {
            Vehicle newVehicle = new Vehicle
            {
                model = "Gol",
                brand = "VW",
                color = "Blue",
                description = "Gol Copa",
                isNew = true,
                year = 2017,
                price = 113,
                createdDate = DateTime.Now,
                updatedDate = DateTime.Now
            };

            int vehicleCount = this._mockVehicleRepository.GetAll().Count();
            Assert.AreEqual(3, vehicleCount); 

            this._mockVehicleRepository.Add(newVehicle);

            vehicleCount = this._mockVehicleRepository.GetAll().Count();
            Assert.AreEqual(4, vehicleCount); 

            Vehicle testVehicle = this._mockVehicleRepository.Get(4);

            Assert.IsNotNull(testVehicle);
            Assert.IsInstanceOfType(testVehicle, typeof(Vehicle));
            Assert.AreEqual(4, testVehicle.id);
        }

        [TestMethod]
        public void CanUpdateVehicle()
        {
            Vehicle testVehicle = this._mockVehicleRepository.Get(1);

            testVehicle.color = "Red";

            this._mockVehicleRepository.Update(1, testVehicle);

            Assert.AreEqual("Red", this._mockVehicleRepository.Get(1).color);
        }

        [TestMethod]
        public void CanDeleteVehicle()
        {
            int vehicleCount = this._mockVehicleRepository.GetAll().Count();
            Assert.AreEqual(3, vehicleCount);

            Vehicle testVehicle = this._mockVehicleRepository.Get(1);

            this._mockVehicleRepository.Delete(1);

            vehicleCount = this._mockVehicleRepository.GetAll().Count();
            Assert.AreEqual(2, vehicleCount);
        }
    }
}
