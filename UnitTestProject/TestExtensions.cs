using Microsoft.EntityFrameworkCore;
using System;
using Microsoft.EntityFrameworkCore.Diagnostics;


namespace Projetas.Unit.Test
{
    public static class TestExtensions
    {
        public static DbContextOptions GetDBOptions(this VehicleTest vehicleTest)
        {
            DbContextOptionsBuilder optionsBuilder = new DbContextOptionsBuilder();
            optionsBuilder.UseInMemoryDatabase(Guid.NewGuid().ToString()).ConfigureWarnings(x => x.Ignore(InMemoryEventId.TransactionIgnoredWarning));
            return optionsBuilder.Options;
        }
    }
}