using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.Common;
using Test.Database.Entities;
using Test.Database.Context;
using Microsoft.EntityFrameworkCore;

namespace Test.Database.DAL
{
    public class VehicleDAL
    {
        public static List<Vehicle> FindAll()
        {
            IEnumerable<Vehicle> _vehicle = null;
            using (var context = new ProjetasContext())
            {
                _vehicle = context.Vehicle;
                return _vehicle != null ? _vehicle.ToList() : null;
            }
        }

        public static Vehicle FindById(long id)
        {
            Vehicle _vehicle = null;
            using (var context = new ProjetasContext())
            {
                _vehicle = context.Vehicle.Where(c => c.Id == id).FirstOrDefault();
            }
            return _vehicle;
        }

        public static IList<Vehicle> FindByBrand(string brand)
        {
            IList<Vehicle> _vehicle = null;
            using (var context = new ProjetasContext())
            {
                _vehicle = context.Vehicle.Where(c => c.Brand.Contains(brand)).ToList();
            }
            return _vehicle;
        }

        public static IList<Vehicle> FindByColor(string color)
        {
            IList<Vehicle> _vehicle = null;
            using (var context = new ProjetasContext())
            {
                _vehicle = context.Vehicle.Where(c => c.Color.Contains(color)).ToList();
            }
            return _vehicle;
        }

        public static int Add(Vehicle _vehicle)
        {
            try
            {
                using (var context = new ProjetasContext())
                {
                    context.Vehicle.Add(_vehicle);
                    context.SaveChanges();
                    return _vehicle.Id;
                }
            }
            catch (Exception dbEx)
            {
                string message = string.Format("{0}", dbEx.Message);
                return 0;
            }
           
        }

        public static int Update(Vehicle _vehicle)
        {
            using (var context = new ProjetasContext())
            {
                Vehicle _dbEntity = context.Vehicle.Find(_vehicle.Id);
                context.Entry(_dbEntity).CurrentValues.SetValues(_vehicle);

                context.SaveChanges();
                return _vehicle.Id;
            }
        }

        public static void Delete(IList<Vehicle> _vehicle)
        {
            try
            {
                using (var context = new ProjetasContext())
                {
                    foreach (Vehicle c in _vehicle)
                    {
                        context.Vehicle.Attach(c);
                        context.Vehicle.Remove(c);
                        context.Entry(c).State = EntityState.Deleted;
                    }
                    context.SaveChanges();
                }
            }
            catch (Exception dbEx)
            {               
                string message = string.Format("{0}", dbEx.Message);                  
            }
        }

    }
}
