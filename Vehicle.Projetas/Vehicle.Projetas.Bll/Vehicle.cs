using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;

namespace Vehicle.Projetas.Bll
{
    public class Vehicle
    {
        Dal.IVehicle vehicleDal = new Dal.Vehicle();

        public void create(Model.Vehicle vehicle)
        {
            vehicleDal.create(vehicle);
        }

        public void update(Model.Vehicle vehicle)
        {
            vehicleDal.update(vehicle);
        }

        public IEnumerable<Model.Vehicle> readAll()
        {
            return vehicleDal.readAll();
        }

        public IEnumerable<Model.Vehicle> read(Func<Model.Vehicle, bool> predicate)
        {
            return vehicleDal.read(predicate);
        }

        public void delete(Func<Model.Vehicle, bool> predicate)
        {
            vehicleDal.delete(predicate);
        }
    }
}
