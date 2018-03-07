using System.Data.Entity.ModelConfiguration;
using VeiculosCRUD.Domain.Entities;

namespace VeiculosCRUD.Infra.Mappings
{
    public class VehicleMap : EntityTypeConfiguration<Vehicle>
    {
        public VehicleMap()
        {

            //            Marca, texto, não nulo, 40 caracteres;
            //            Modelo, texto, não nulo, 50 caracteres,
            //            Cor, texto, não nulo, 30 caracteres;
            //            Ano, inteiro positivo, não nulo;
            //            Preço, decimal positivo, não nulo;
            //            Descrição, texto;
            //            É novo?, boleano, não nulo;
            //            Data de cadastro, data e hora, não nulo;
            //            Data de atualização, data e hora, nulo.

            ToTable("Vehicle");
            HasKey(x => x.Id);
            Property(x => x.Manufacturer).IsRequired().HasMaxLength(40);
            Property(x => x.Model).IsRequired().HasMaxLength(50);
            Property(x => x.Color).IsRequired().HasMaxLength(30);
            Property(x => x.Year).IsRequired();
            Property(x => x.Price).IsRequired();
            Property(x => x.Description).HasMaxLength(200);
            Property(x => x.isNew).IsRequired();
            Property(x => x.inputDate).IsRequired();
            Property(x => x.changeDate).IsRequired();

        }
    }
}
