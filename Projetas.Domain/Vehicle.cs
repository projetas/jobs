using System;

namespace Projetas.Domain
{
    public class Vehicle
    {
        public int id { get; set; }
        public string brand { get; set; }
        public string model { get; set; }
        public string color { get; set; }
        public int year { get; set; }
        public decimal price { get; set; }
        public string description { get; set; }
        public bool isNew { get; set; }
        public DateTime createdDate { get; set; }
        public DateTime updatedDate { get; set; }
    }
}
