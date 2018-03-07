using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using VeiculosCRUD.Domain.Entities;
using VeiculosCRUD.Infra.Mappings;

namespace VeiculosCRUD.Infra.Context
{
    public class VeiculosCRUDDataContext : DbContext
    {
       

        public VeiculosCRUDDataContext() : base("name=context")
        {
            Configuration.LazyLoadingEnabled = false;
            Configuration.ProxyCreationEnabled = false;
        }

        public DbSet<Vehicle> Vehicles { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Configurations.Add(new VehicleMap());
         
        }
    }
}
