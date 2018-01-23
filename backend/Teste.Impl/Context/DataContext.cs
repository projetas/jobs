using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Text;
using Teste.Impl.Configuration;

namespace Teste.Impl.Context
{
    public class DataContext : DbContext
    {
        public DataContext(DbContextOptions<DataContext> options) :  base(options) { }


        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
#if DEBUG
           
            optionsBuilder.UseSqlServer("Data Source=azimut-dev-db.cpxpkaromimb.us-east-1.rds.amazonaws.com;Initial Catalog=TesteDb;User id=Usr_test;Password=Usr_test;");
#endif

            base.OnConfiguring(optionsBuilder);
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.ApplyConfiguration(new VehicleConfig());
        }
    }
}
