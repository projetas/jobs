using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using CarProg.OBJ;

namespace CarProg.DAL
{
    public class CarProgContext : DbContext
    {
        #region [ Constructor ]

        public CarProgContext() : base()
        {

        }

        #endregion

        #region [ Properties ]

        public DbSet<Vehicle> Vehicles { get; set; }

        #endregion

        #region [ Methods ]

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            //Configure default schema
            modelBuilder.HasDefaultSchema("Admin");
        }

        #endregion
    }
}
