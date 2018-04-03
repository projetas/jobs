using System;
using System.Collections.Generic;
using System.Linq;
using ApiVeiculoProjetas.Response;
using ApiVeiculoProjetas.ViewModel;
using Entities;
using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Mvc;
using Service;

namespace ApiVeiculoProjetas.Controllers
{
    [Produces("application/json")]
    [EnableCors("Policy")]
    public class VehiclesController : Controller
    {
        private readonly Services _services;

        public VehiclesController(Services services)
        {
            _services = services;
        }

        /// <summary>
        /// Consultar lista de todos os Veículos
        /// </summary>
        /// <param name="search"></param>
        /// <returns></returns>
        [HttpGet("vehicles")]
        [ProducesResponseType(typeof(Response<Vehicle>), 200)]
        [ProducesResponseType(typeof(Response<Vehicle>), 404)]
        [ProducesResponseType(typeof(Response<Vehicle>), 500)]
        public Response<Vehicle> GetAll([FromQuery] string search)
        {
            try
            {
                var result = _services.GetAll(search);

                return new Response<Vehicle>()
                {
                    Erro = false,
                    DataLista = result
                };
            }
            catch (Exception ex)
            {
                return new Response<Vehicle>()
                {
                    Erro = true,
                    Mensagem = ex.Message
                };
            }
        }

        /// <summary>
        /// Consultar veículo por id
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpGet("vehicles/{id}")]
        [ProducesResponseType(typeof(Response<Vehicle>), 200)]
        [ProducesResponseType(typeof(Response<Vehicle>), 404)]
        [ProducesResponseType(typeof(Response<Vehicle>), 500)]
        public Response<Vehicle> GetById(int id)
        {
            try
            {
                var result = _services.GetById(id);

                return new Response<Vehicle>()
                {
                    Erro = false,
                    Data = result
                };
            }
            catch (Exception ex)
            {
                return new Response<Vehicle>()
                {
                    Erro = true,
                    Mensagem = ex.Message
                };
            }
        }

        /// <summary>
        /// Inserir veículo
        /// </summary>
        /// <param name="viewModel"></param>
        [HttpPost("vehicles")]
        [ProducesResponseType(typeof(Response<Vehicle>), 200)]
        [ProducesResponseType(typeof(Response<Vehicle>), 500)]
        public Response<Vehicle> Post([FromBody]VehicleViewModel viewModel)
        {
            try
            {
                var entity = new Vehicle
                {
                    Id = (_services.GetAll(null).Max(x => x.Id) + 1),
                    Color = viewModel.Color,
                    CreateDate = DateTime.Now,
                    Description = viewModel.Description,
                    IsNew = viewModel.IsNew,
                    Mark = viewModel.Mark,
                    Model = viewModel.Model,
                    Price = viewModel.Price,
                    Year = viewModel.Year
                };

                _services.Save(entity);

                return new Response<Vehicle>()
                {
                    Erro = false,
                    Data = entity
                };
            }
            catch (Exception ex)
            {
                return new Response<Vehicle>()
                {
                    Erro = true,
                    Mensagem = ex.Message
                };
            }
        }

        /// <summary>
        /// Editar veículo
        /// </summary>
        /// <param name="id"></param>
        /// <param name="viewModel"></param>
        /// <returns></returns>
        [HttpPut("vehicles/{id}")]
        [ProducesResponseType(typeof(Response<Vehicle>), 200)]
        [ProducesResponseType(typeof(Response<Vehicle>), 404)]
        [ProducesResponseType(typeof(Response<Vehicle>), 500)]
        public Response<Vehicle> Put(int id, [FromBody]VehicleViewModel viewModel)
        {
            try
            {
                var entity = _services.GetById(id);
                if (entity != null)
                {
                    entity.Color = viewModel.Color;
                    entity.Description = viewModel.Description;
                    entity.IsNew = viewModel.IsNew;
                    entity.Mark = viewModel.Mark;
                    entity.Model = viewModel.Model;
                    entity.Price = viewModel.Price;
                    entity.UpdateDate = DateTime.Now;
                    entity.Year = viewModel.Year;

                    _services.Update(entity);

                    return new Response<Vehicle>()
                    {
                        Erro = false,
                        Data = entity
                    };
                }
                else
                {
                    throw new Exception("Veículo não encontrado!");
                }
            }
            catch (Exception ex)
            {
                return new Response<Vehicle>()
                {
                    Erro = true,
                    Mensagem = ex.Message
                };
            }
        }

        /// <summary>
        /// Deletar veículo
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpDelete("vehicles/{id}")]
        [ProducesResponseType(typeof(Response<bool>), 200)]
        [ProducesResponseType(typeof(Response<bool>), 404)]
        [ProducesResponseType(typeof(Response<bool>), 500)]
        public Response<bool> Delete(int id)
        {
            try
            {
                var entity = _services.GetById(id);
                if (entity == null)
                {
                    throw new Exception("Veículo não encontrado!");
                }

                _services.Delete(entity);

                return new Response<bool>()
                {
                    Erro = false,
                    Data = true
                };
            }
            catch (Exception ex)
            {
                return new Response<bool>()
                {
                    Erro = true,
                    Mensagem = ex.Message
                };
            }
        }
    }
}
