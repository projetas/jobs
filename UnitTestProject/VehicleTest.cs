using Microsoft.VisualStudio.TestTools.UnitTesting;
using Test.Database.Entities;
using System.Collections.Generic;
using Test.Database.Context;
using System.Net.Http;
using Microsoft.EntityFrameworkCore;
using System.Linq;

namespace Projetas.Unit.Test
{
    [TestClass]
    public class VehicleTest 
    {
        private HttpClient _client;

        public VehicleTest()
        {              
        }

        [TestMethod]
        public void GetAllVehicle()
        {
            IEnumerable<Vehicle> _vehicles = null;
            ProjetasContext _contextInMemory = new ProjetasContext(this.GetDBOptions());
            using (_contextInMemory)
            {
                int id = 0;
                _contextInMemory.Vehicle.Add(new Vehicle
                {
                    Id = id++,
                    Brand = "Toyota",
                    Model = "Supra MK IV",
                    Color = "Orange",
                    Year = 1994,
                    Description = "I can do 400m in less than 10 seconds",
                    ItsNew = false
                });
                _contextInMemory.Vehicle.Add(new Vehicle
                {
                    Id = id++,
                    Brand = "Nissan",
                    Model = "Skyline GTR R34",
                    Color = "Silver",
                    Year = 1999,
                    Description = "Make movies better",
                    ItsNew = false
                });
                _contextInMemory.Vehicle.Add(new Vehicle
                {
                    Id = id++,
                    Brand = "Dodge",
                    Model = "Charger R/T",
                    Color = "Black",
                    Year = 1970,
                    Description = "I can fly!",
                    ItsNew = false
                });

                _contextInMemory.SaveChanges();

                _vehicles = _contextInMemory.Vehicle.ToList();
            }

            Assert.IsNotNull(_vehicles);
            Assert.IsTrue(_vehicles.ToList().Count > 0);
        }

        [TestMethod]
        public void GetVehicleById(int id)
        {
            Vehicle _vehicle = null;
            ProjetasContext _contextInMemory = new ProjetasContext(this.GetDBOptions());
            using (_contextInMemory)
            {                
                _contextInMemory.Vehicle.Add(new Vehicle
                {
                    Id = id,
                    Brand = "Toyota",
                    Model = "Supra MK IV",
                    Color = "Orange",
                    Year = 1994,
                    Description = "I can do 400m in less than 10 seconds",
                    ItsNew = false
                });

                _vehicle = _contextInMemory.Vehicle.Where(c => c.Id == id).FirstOrDefault();
                Assert.IsTrue(_vehicle != null);
            }
        }

        [TestMethod]
        public void AddVehicle(Vehicle vehicle)
        {
            ProjetasContext _contextInMemory = new ProjetasContext(this.GetDBOptions());
            Vehicle _v = new Vehicle();           

            _v.Id = vehicle.Id + 1;
            _v.Brand = vehicle.Brand;
            _v.Model = vehicle.Model;
            _v.Color = vehicle.Color;
            _v.Year = vehicle.Year;
            _v.Description = vehicle.Description;
            _v.ItsNew = vehicle.ItsNew;

            _contextInMemory.AddRange(_v);
            _contextInMemory.SaveChanges();

        }

        [TestMethod]
        public void Update(Vehicle v)
        {
            ProjetasContext _contextInMemory = new ProjetasContext(this.GetDBOptions());

            v = new Vehicle {
                Id = 1,
                Brand = "Dodge",
                Model = "Charger R/T",
                Color = "Black",
                Year = 1970,
                Description = "I can fly!",
                ItsNew = false
            };

            using (_contextInMemory)
            {
                _contextInMemory.Vehicle.Add(v);

                Vehicle _dbEntity = _contextInMemory.Vehicle.Find(1);
                _contextInMemory.Entry(_dbEntity).CurrentValues.SetValues(v);

                _contextInMemory.SaveChanges();

                Assert.IsTrue(v.Id != -1);
            }
        }

        [TestMethod]
        public void Delete(int Id)
        {
            Vehicle v = null;
            ProjetasContext _contextInMemory = new ProjetasContext(this.GetDBOptions());
            using (_contextInMemory)
            {
                v = new Vehicle
                {
                   Id = 2,
                   Brand = "Nissan",
                   Model = "Skyline GTR R34",
                   Color = "Silver",
                   Year = 1999,
                   Description = "Make movies better",
                   ItsNew = false
               };

                _contextInMemory.Vehicle.Attach(v);
                _contextInMemory.Vehicle.Remove(v);
                _contextInMemory.Entry(v).State = EntityState.Deleted;
                _contextInMemory.SaveChanges();                
            }
            Assert.IsNotNull(v);
        }


    }   
}
