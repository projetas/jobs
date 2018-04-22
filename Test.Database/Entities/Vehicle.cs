using System;
using System.Collections.Generic;
using System.Text;

namespace Test.Database.Entities
{
    public class Vehicle
    {
        public int Id { get; set; }

        public string Brand { get; set; }

        public string Model { get; set; }

        public string Color { get; set; }

        public Int32 Year { get; set; }

        public Decimal Price { get; set; }

        public string Description { get; set; }

        public bool ItsNew { get; set; }

        public DateTime CreateDate { get; set; }

        public DateTime LastUpdate { get; set; }
        
        public Vehicle()
        {
            this.CreateDate = DateTime.Now;            
        }

        public Vehicle(Int32 id)
        {
            this.LastUpdate = DateTime.Now;
        }


    }
}
