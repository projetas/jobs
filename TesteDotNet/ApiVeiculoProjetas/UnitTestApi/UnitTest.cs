using Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Repository;
using Service;
using System;
using System.Linq;

namespace UnitTestApi
{
    [TestClass]
    public class UnitTest
    {
        private DbContextOptions<VehicleContext> options = null;
        private VehicleContext context = null;

        public UnitTest()
        {
           options = new DbContextOptionsBuilder<VehicleContext>()
                .UseInMemoryDatabase(databaseName: "Vehicle")
                .Options;

            context = new VehicleContext(options);
        }

        [TestMethod]
        public void GetAll()
        {
            using (var context = new VehicleContext(options))
            {
                var id = 0;
                if (context.Vehicles.Count() > 0)
                {
                    id = context.Vehicles.Max(x => x.Id);
                }

                context.Vehicles.Add(new Vehicle { Id = (id + 1), Mark = "Fiat", Model = "teste1", Color = "color1", Description = "desc1" });
                context.Vehicles.Add(new Vehicle { Id = (id + 2), Mark = "Honda", Model = "teste1", Color = "color1", Description = "desc1" });
                context.Vehicles.Add(new Vehicle { Id = (id + 3), Mark = "Ferrari", Model = "teste1", Color = "color1", Description = "desc1" });
                context.SaveChanges();
            }

            using (var context = new VehicleContext(options))
            {
                var service = new Services(context);
                var result = service.GetAll(null);
                Assert.AreEqual(3, result.Count());
            }
        }

        [TestMethod]
        public void GetById()
        {
            using (var context = new VehicleContext(options))
            {
                var service = new Services(context);
                var result = service.GetById(2);
                Assert.IsTrue(result != null);
            }
        }

        [TestMethod]
        public void Save()
        {
            using (var context = new VehicleContext(options))
            {
                var id = 0;
                if (context.Vehicles.Count() > 0)
                {
                    id = context.Vehicles.Max(x => x.Id);
                }

                var service = new Services(context);

                var entity = new Vehicle() { Id = (id + 1), Mark = "Fiat", Model = "teste1", Color = "color1", Description = "desc1" };

                var result = service.Save(entity);
                Assert.IsTrue(result);
            }
        }

        [TestMethod]
        public void Update()
        {
            using (var context = new VehicleContext(options))
            {
                var service = new Services(context);

                var entity = new Vehicle() { Id = 1, Mark = "Fiat", Model = "teste1", Color = "color1", Description = "desc1", CreateDate = DateTime.Now, IsNew = false, Price = 1000, UpdateDate = DateTime.Now, Year = 1990 };

                var result = service.Update(entity);
                Assert.IsTrue(result);
            }
        }

        [TestMethod]
        public void Delete()
        {
            using (var context = new VehicleContext(options))
            {
                var service = new Services(context);

                var entity = new Vehicle { Id = 1, Mark = "Fiat", Model = "teste1", Color = "color1", Description = "desc1" };

                var result = service.Delete(entity);
                Assert.IsTrue(result);
            }
        }
    }
}
