using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Text;
using TestProjetas.Domain.Entities;

namespace TestProjetas.Repository.Configuration
{
    public class PersistContext : DbContext
    {
        public PersistContext()
        { }

        public PersistContext(DbContextOptions<PersistContext> options)
            : base(options)
        { }

        private void VehicleConfiguration(ModelBuilder modelConstructor)
        {
            modelConstructor.Entity<Vehicle>(etd =>
            {
                etd.ToTable("TB_VEHICLE");
                etd.HasKey(c => c.Id).HasName("PK_Id_Vehicle");
                etd.Property(c => c.Id).HasColumnName("Id").ValueGeneratedOnAdd();
                etd.Property(c => c.Brand).HasColumnName("NM_BRAND").HasMaxLength(40).IsRequired(true);
                etd.Property(c => c.Model).HasColumnName("NM_MODEL").HasMaxLength(50).IsRequired(true);
                etd.Property(c => c.Color).HasColumnName("NM_COLOR").HasMaxLength(50).IsRequired(true);
                etd.Property(c => c.Year).HasColumnName("NR_YEAR").IsRequired(true);
                etd.Property(c => c.Price).HasColumnName("DC_PRICE").IsRequired(true);
                etd.Property(c => c.Description).HasColumnName("DS_DESCRIPTION").IsRequired(false).HasColumnType("TEXT");
                etd.Property(c => c.IsZero).HasColumnName("FL_NEW").IsRequired(true).HasColumnType("BIT");
                etd.Property(c => c.RegistrationDate).HasColumnName("DT_REGISTRATION").IsRequired(true);
                etd.Property(c => c.UpdateDate).HasColumnName("DT_UPDATE").IsRequired(false);
            });
        }

        protected override void OnModelCreating(ModelBuilder modelConstructor)
        {
            modelConstructor.ForSqlServerUseIdentityColumns();
            modelConstructor.HasDefaultSchema("dbo");

            VehicleConfiguration(modelConstructor);
        }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
                optionsBuilder.UseSqlServer(Constants.CONNECTIONSTRING);
            }
        }
    }
}
