using System;
using System.Linq;
using System.Threading.Tasks;
using CarProg.API.Controllers;
using CarProg.OBJ;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace CarProg.API.Tests.Controllers
{
    [TestClass]
    public class VehicleControllerTest
    {
        [TestMethod]
        public async Task PostAsync()
        {
            try
            {
                // Arrange
                VehicleController controller = new VehicleController();

                var vehicle = new Vehicle
                {
                    Brand = "Toyota",
                    Model = "Corolla",
                    Color = "Branco",
                    Description = "Carro impecável!",
                    IsNew = false,
                    Price = 98750.1M,
                    RegistrationDate = DateTime.Now,
                    Year = 2017,
                };

                // Act
                await controller.Post(vehicle);

                // Act
                var result = await controller.Get();

                vehicle = result.FirstOrDefault(e => e.Brand.Equals("Toyota") &&
                e.Model.Equals("Corolla") &&
                e.Price == 98750.1M);

                // Assert
                Assert.IsNotNull(vehicle);
            }
            catch (Exception)
            {
                Assert.Fail();
            }
        }

        [TestMethod]
        public async Task PutAsync()
        {
            try
            {
                // Arrange
                VehicleController controller = new VehicleController();

                var result = await controller.Get();

                var vehicle = result.FirstOrDefault(e => e.Brand.Equals("Toyota") &&
                e.Model.Equals("Corolla") &&
                e.Price == 98750.1M);

                vehicle.Price = 98750.7M;

                // Act
                await controller.Put(vehicle);

                result = await controller.Get();

                vehicle = result.FirstOrDefault(e => e.Brand.Equals("Toyota") &&
                e.Model.Equals("Corolla") &&
                e.Price == 98750.7M);

                // Assert
                Assert.IsNotNull(vehicle);

            }
            catch (Exception)
            {
                Assert.Fail();
            }
        }

        [TestMethod]
        public async Task DeleteAsync()
        {
            try
            {
                // Arrange
                VehicleController controller = new VehicleController();

                var result = await controller.Get();

                var vehicle = result.FirstOrDefault(e => e.Brand.Equals("Toyota") &&
                e.Model.Equals("Corolla") &&
                e.Price == 98750.7M);
                
                // Act
                await controller.Delete(vehicle.Id);

                result = await controller.Get();

                vehicle = result.FirstOrDefault(e => e.Brand.Equals("Toyota") &&
                e.Model.Equals("Corolla") &&
                e.Price == 98750.7M);

                // Assert
                Assert.IsNull(vehicle);

            }
            catch (Exception)
            {
                Assert.Fail();
            }
        }
    }
}
