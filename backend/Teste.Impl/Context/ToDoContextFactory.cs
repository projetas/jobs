using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Design;
using System;
using System.Collections.Generic;
using System.Text;

namespace Teste.Impl.Context
{
    public class ToDoContextFactory : IDesignTimeDbContextFactory<DataContext>
    {
   
        DataContext IDesignTimeDbContextFactory<DataContext>.CreateDbContext(string[] args)
        {
            var builder = new DbContextOptionsBuilder<DataContext>();
            builder.UseSqlServer("Data Source=azimut-dev-db.cpxpkaromimb.us-east-1.rds.amazonaws.com;Initial Catalog=TesteDb;User id=Usr_test;Password=Usr_test;");
            return new DataContext(builder.Options);
        }
    }
}
