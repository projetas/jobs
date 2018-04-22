using System;
using System.Collections.Generic;
using System.Text;
using Test.Database.DAL;
using Test.Database.Entities;

namespace Test.Business
{
    public class VehicleBLL
    {
        public static IList<Vehicle> FindAll()
        {
            return VehicleDAL.FindAll();
        }

        public static Vehicle FindById(long id)
        {
            return VehicleDAL.FindById(id);
        }

        public static IList<Vehicle> FindByBrand(string brand)
        {
            return VehicleDAL.FindByBrand(brand);
        }

        public static int Save(Vehicle _vehicle)
        {
            try
            {
                Vehicle existing = VehicleDAL.FindById(_vehicle.Id);
                //Duplicado
                if (existing != null && _vehicle.Id != existing.Id)
                {
                    return -2;
                }

                if (_vehicle.Id <= 0)
                {
                    return VehicleDAL.Add(_vehicle);
                }
                else
                {
                    return VehicleDAL.Update(_vehicle);
                }

            }
            catch (Exception ex)
            {
                string message = string.Format("{0}", ex.Message);
                return -1;
            }
        }

        public static Boolean Delete(IList<int> _ids)
        {
            IList<Vehicle> _exist = null;
            Vehicle v = null;
            try
            {
                foreach (Int32 id in _ids)
                {
                    v = VehicleDAL.FindById(id);
                    if (v != null)
                    {
                        _exist.Add(v);
                    }                    
                }

                if (_exist != null)
                {
                    VehicleDAL.Delete(_exist);
                }

                return true;           
            }
            catch (Exception ex)
            {
                string message = string.Format("{0}", ex.Message);
                return false;
            }
        }

    }
}
