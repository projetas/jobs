using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Vehicle.Projetas.Dal
{
    public class Context : DbContext
    {        
        public Context()
            : base(ConfigurationManager.AppSettings["connectionString"])
        {
        }

        public DbSet<Model.Vehicle> vehicle { get; set; }

        public class MyConfiguration : DbConfiguration
        {
            public MyConfiguration()
            {
                this.SetProviderServices(System.Data.Entity.SqlServer.SqlProviderServices.ProviderInvariantName, System.Data.Entity.SqlServer.SqlProviderServices.Instance);
            }
        }

    }

}
