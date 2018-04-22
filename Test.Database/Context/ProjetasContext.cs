using System;
using System.Collections.Generic;
using System.Text;
using Microsoft.EntityFrameworkCore;
using Test.Database.Entities;


namespace Test.Database.Context
{
    public class ProjetasContext : DbContext
    {

        public static string ConnectionString { get; set; }

        public ProjetasContext() { }

        public ProjetasContext(DbContextOptions options)
        : base(options)
        {            
        }        

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlServer(ConnectionString);
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Vehicle>(entityTypeBuilder => {
                entityTypeBuilder.ToTable("TBL_VEHICLE");
                entityTypeBuilder.HasKey(c => c.Id);
                entityTypeBuilder.Property(c => c.Id).HasColumnName("ID").UseSqlServerIdentityColumn().IsRequired();
                entityTypeBuilder.Property(c => c.Brand).HasColumnName("BRAND").IsRequired();
                entityTypeBuilder.Property(c => c.Model).HasColumnName("MODEL").IsRequired();
                entityTypeBuilder.Property(c => c.Color).HasColumnName("COLOR").IsRequired();
                entityTypeBuilder.Property(c => c.Year).HasColumnName("YEAR").IsRequired();
                entityTypeBuilder.Property(c => c.Price).HasColumnName("PRICE").IsRequired();
                entityTypeBuilder.Property(c => c.Description).HasColumnName("DESCRIPTION").IsRequired();
                entityTypeBuilder.Property(c => c.ItsNew).HasColumnName("ITS_NEW").IsRequired();
                entityTypeBuilder.Property(c => c.CreateDate).HasColumnName("CREATE_DATE").IsRequired();
                entityTypeBuilder.Property(c => c.LastUpdate).HasColumnName("LAST_UPDATE");

            });            
        }

        public virtual DbSet<Vehicle> Vehicle { get; set; }       

    }
    
}
