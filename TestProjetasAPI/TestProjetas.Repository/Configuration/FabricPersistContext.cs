using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Design;
using Microsoft.EntityFrameworkCore.Infrastructure;
using System;
using System.Collections.Generic;
using System.Text;

namespace TestProjetas.Repository.Configuration
{
    public class FabricPersistContext : IDesignTimeDbContextFactory<PersistContext>
    {
        private const string CONNECTIONSTRING = Constants.CONNECTIONSTRING;

        public FabricPersistContext()
        {
        }

        public PersistContext CreateDbContext(string[] args)
        {
            var constructor = new DbContextOptionsBuilder<PersistContext>();
            constructor.UseSqlServer(CONNECTIONSTRING);
            return new PersistContext(constructor.Options);
        }
    }
}
