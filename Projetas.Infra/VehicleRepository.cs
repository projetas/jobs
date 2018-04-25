using Projetas.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Projetas.Infra
{
    public class VehicleRepository : IRepository<Vehicle, int>
    {

        private ProjetasDbContext _dbContext;

        public VehicleRepository(ProjetasDbContext projetasDbContext)
        {
            _dbContext = projetasDbContext;
        }

        public int Add(Vehicle vehicle)
        {
            if (checkExists(vehicle.id))
            {
                return Update(vehicle.id, vehicle);
            }

            _dbContext.Vehicles.Add(vehicle);
            int vehicleId = _dbContext.SaveChanges();
            return vehicleId;
        }

        public int Delete(int id)
        {
            int vehicleId = 0;

            var item = _dbContext.Vehicles.FirstOrDefault(i => i.id == id);
            if (item != null)
            {
                _dbContext.Vehicles.Remove(item);
                vehicleId = _dbContext.SaveChanges();
            }

            return vehicleId;
        }

        public Vehicle Get(int id)
        {
            var vehicle = _dbContext.Vehicles.FirstOrDefault(i => i.id == id);
            return vehicle;
        }

        public IEnumerable<Vehicle> GetAll()
        {
            var itens = _dbContext.Vehicles.ToList();
            return itens;
        }

        public int Update(int id, Vehicle v)
        {
            int vehicleId = 0;
            var item = _dbContext.Vehicles.Find(id);
            if (item != null)
            {
                item.brand = v.brand;
                item.color = v.color;
                item.description = v.description;
                item.isNew = v.isNew;
                item.model = v.model;
                item.year = v.year;
                item.updatedDate = v.updatedDate;

                vehicleId = _dbContext.SaveChanges();
            }

            return vehicleId;
        }

        private bool checkExists(int id)
        {
            var vehicle = Get(id);

            return vehicle == null ? false : true;
        }
    }
}
