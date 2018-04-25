using Microsoft.EntityFrameworkCore;
using Projetas.Domain;
using System;
using System.Collections.Generic;
using System.Text;

namespace Projetas.Infra
{
    public class ProjetasDbContext : DbContext
    {
        public ProjetasDbContext(DbContextOptions<ProjetasDbContext> options) : base(options)
        {

        }

        public DbSet<Vehicle> Vehicles { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Vehicle>().ToTable("VEHICLE");
            modelBuilder.Entity<Vehicle>().HasKey(v => v.id);
            modelBuilder.Entity<Vehicle>()
               .Property(v => v.brand)
                .HasColumnName("BRAND")
                .HasMaxLength(40)
                .HasColumnType("VARCHAR(40)");

            modelBuilder.Entity<Vehicle>()
               .Property(v => v.model)
                .HasColumnName("MODEL")
                .HasMaxLength(50)
                .HasColumnType("VARCHAR(50)");

            modelBuilder.Entity<Vehicle>()
               .Property(v => v.color)
                .HasColumnName("COLOR")
                .HasMaxLength(30)
                .HasColumnType("VARCHAR(30)");

            modelBuilder.Entity<Vehicle>()
               .Property(v => v.price)
                .HasColumnName("PRICE")
                .HasColumnType("DECIMAL(15,5)");

            modelBuilder.Entity<Vehicle>()
               .Property(v => v.description)
                .HasColumnName("DESCRIPTION")
                .HasColumnType("TEXT");

            modelBuilder.Entity<Vehicle>()
               .Property(v => v.isNew)
                .HasColumnName("ISNEW")
                .HasColumnType("boolean");

            modelBuilder.Entity<Vehicle>()
               .Property(v => v.createdDate)
                .HasColumnName("CREATED_DATE")
                .HasColumnType("DATETIME")
                .HasDefaultValueSql("GetDate()");

            modelBuilder.Entity<Vehicle>()
               .Property(v => v.updatedDate)
                .HasColumnName("UPDATED_DATE")
                .HasColumnType("DATETIME")
                .HasDefaultValueSql("GetDate()");

        }

    }
}
