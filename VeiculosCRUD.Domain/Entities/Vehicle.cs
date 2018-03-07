using System;


namespace VeiculosCRUD.Domain.Entities
{
    public class Vehicle
    {
        public Guid Id { get; set; }
        public string Manufacturer { get; set; }
        public string Model { get; set; }
        public int Year { get; set; }
        public string Color { get; set; }
        public decimal Price { get; set; }
        public string Description { get; set; }
        public bool isNew { get; set; }
        public DateTime inputDate { get; set; }
        public DateTime changeDate { get; set; }
    }
}
