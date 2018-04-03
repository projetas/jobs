using Entities;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Collections.Generic;

namespace UnitTestApi
{
    [TestClass]
    public class UnitTest
    {
        private List<Vehicle> GetTestVehicles()
        {
            var testProducts = new List<Vehicle>();
            testProducts.Add(new Vehicle { Id = 1, Mark = "Demo1", Price = 1 });
            testProducts.Add(new Vehicle { Id = 2, Mark = "Demo2", Price = 3.75M });
            testProducts.Add(new Vehicle { Id = 3, Mark = "Demo3", Price = 16.99M });
            testProducts.Add(new Vehicle { Id = 4, Mark = "Demo4", Price = 11.00M });

            return testProducts;
        }
    }
}
