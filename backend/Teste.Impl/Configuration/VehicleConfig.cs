using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Text;
using Teste.Domain.Entities;

namespace Teste.Impl.Configuration
{
    public class VehicleConfig : IEntityTypeConfiguration<Vehicle>
    {
        public void Configure(EntityTypeBuilder<Vehicle> entity)
        {
            entity.ToTable("VEHICLE");

            entity.HasKey(v => v.Id);
            entity.Property(v => v.Id).HasColumnName("ID").UseSqlServerIdentityColumn().IsRequired();
            entity.Property(v => v.Model).HasColumnName("MODEL").HasMaxLength(50).IsRequired();
            entity.Property(v => v.Brand).HasColumnName("BRAND").HasMaxLength(50).IsRequired();
            entity.Property(v => v.Color).HasColumnName("COLOR").HasMaxLength(30).IsRequired();
            entity.Property(v => v.Price).HasColumnName("DECIMAL").IsRequired();
            entity.Property(v => v.AddedDate).HasColumnName("AADDED_DATE").IsRequired();
            entity.Property(v => v.ModifiedDate).HasColumnName("MODIFIED_DATE").IsRequired(false);
            entity.Property(v => v.Year).HasColumnName("YEAR").IsRequired();
            entity.Property(v => v.IsNew).HasColumnName("IS_NEW").IsRequired();
            entity.Property(v => v.Description).HasColumnName("DESCRIPTION").IsRequired(false);

        }
    }
}
