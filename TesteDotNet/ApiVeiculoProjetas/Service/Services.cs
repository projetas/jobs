using Entities;
using Repository;
using System;
using System.Collections.Generic;
using System.Linq;

namespace Service
{
    public class Services
    {
        private readonly VehicleContext _context;

        public Services(VehicleContext context)
        {
            _context = context;
            /*
            if (_context.Vehicles.Count() == 0)
            {
                _context.Vehicles.Add(new Vehicle
                {
                    Id = 1,
                    Color = "Vermelho",
                    CreateDate = DateTime.Now,
                    Description = "Importado",
                    IsNew = true,
                    Mark = "Ferrari",
                    Model = "458",
                    Price = Convert.ToDecimal("180.678,50"),
                    Year = 2019
                });

                _context.Vehicles.Add(new Vehicle
                {
                    Id = 2,
                    Color = "Branco",
                    CreateDate = DateTime.Now,
                    Description = "Nacional",
                    IsNew = true,
                    Mark = "Fiat",
                    Model = "Uno Fire",
                    Price = Convert.ToDecimal("40.000"),
                    Year = 2018
                });

                _context.SaveChanges();
            }
            */
        }

        public List<Vehicle> GetAll(string search)
        {
            var result = new List<Vehicle>();

            if (string.IsNullOrWhiteSpace(search))
            {
                result = _context.Vehicles.ToList();
            }
            else
            {
                result = _context.Vehicles.Where(x => x.Mark.ToUpper().Contains(search.ToUpper())
                                                || x.Model.ToUpper().Contains(search.ToUpper())
                                                || x.Color.ToUpper().Contains(search.ToUpper())
                                                || x.Description.ToUpper().Contains(search.ToUpper())).ToList();
            }

            return result;
        }

        public Vehicle GetById(int id)
        {
            return _context.Vehicles.Where(x => x.Id == id).FirstOrDefault();
        }

        public bool Save(Vehicle entity)
        {
            try
            {
                _context.Vehicles.Add(entity);

                _context.SaveChanges();

                return true;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        public bool Update(Vehicle entity)
        {
            try
            { 
                _context.Vehicles.Update(entity);

                _context.SaveChanges();

                return true;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        public bool Delete(Vehicle entity)
        {
            try
            { 
                _context.Vehicles.Remove(entity);

                _context.SaveChanges();

                return true;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
    }
}
