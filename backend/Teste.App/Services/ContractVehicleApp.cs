using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Net;
using System.Text;
using Teste.App.viewModel;
using Teste.Domain.Entities;
using Teste.Domain.Repositories;


namespace Teste.App.Services
{
    public class ContractVehicleApp
    {
        private IRepository<Vehicle> _rep;


        public ContractVehicleApp(IRepository<Vehicle> rep)
        {
            _rep = rep;
        }

        public virtual VehicleViewModel GetById(int id)
        {
            try
            {
                var entity = _rep.Get(id);

                if (entity == null)
                    throw new KeyNotFoundException("Objecto não encontrado.");

                VehicleViewModel vehicle = new VehicleViewModel
                {
                    Id              = entity.Id,
                    Brand           = entity.Brand,
                    Model           = entity.Model,
                    AddedDate       = entity.AddedDate,
                    Color           = entity.Color,
                    IsNew           = entity.IsNew,
                    Price           = entity.Price,
                    Description     = entity.Description,
                    Year            = entity.Year,
                    ModifiedDate    = entity.ModifiedDate
                };
                return vehicle;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        public virtual Vehicle SaveVehicle(VehicleViewModel modelView)
        {
            try
            {

                Vehicle entity = new Vehicle
                {
                    Brand       = modelView.Brand,
                    Model       = modelView.Model,
                    AddedDate   = DateTime.Now,
                    Color       = modelView.Color,
                    IsNew       = modelView.IsNew,
                    Price       = modelView.Price,
                    Description = modelView.Description,
                    Year        = modelView.Year
                };
                _rep.Insert(entity);

                return entity;
            }
            catch
            {
                throw new ValidationException();
            }
        }

        public virtual Vehicle EditVehicle(VehicleViewModel modelView)
        {
            try
            {
                Vehicle vehicle = _rep.Get(modelView.Id.Value);

                if (vehicle != null)
                {
                    vehicle.Brand = modelView.Brand;
                    vehicle.Model = modelView.Model;
                    vehicle.ModifiedDate = DateTime.Now;
                    vehicle.Color = modelView.Color;
                    vehicle.IsNew = modelView.IsNew;
                    vehicle.Price = modelView.Price;
                    vehicle.Description = modelView.Description;
                    vehicle.Year = modelView.Year;

                    _rep.Update(vehicle);
                }

                return vehicle;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        public void DeleteVehicle(int id)
        {
            Vehicle vehicle = _rep.Get(id);
            if (vehicle != null)
            {
                _rep.Delete(vehicle);
            }
        }

        public virtual List<VehicleViewModel> GetAll()
        {
            //IEnumerable<Pessoa> pessoa = _rep.GetAll();
            List<VehicleViewModel> listVehicle = new List<VehicleViewModel>();
            _rep.GetAll().ToList().ForEach(v =>
            {
                VehicleViewModel vehicle = new VehicleViewModel
                {
                    Id = v.Id,
                    Brand = v.Brand,
                    Model = v.Model,
                    AddedDate = v.AddedDate,
                    Color = v.Color,
                    IsNew = v.IsNew,
                    Price = v.Price,
                    Description = v.Description,
                    Year = v.Year,
                    ModifiedDate = v.ModifiedDate
                };

                listVehicle.Add(vehicle);
            });

            return listVehicle;
        }
    }
}
