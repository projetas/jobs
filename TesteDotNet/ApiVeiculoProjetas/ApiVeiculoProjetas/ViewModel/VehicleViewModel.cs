using System;

namespace ApiVeiculoProjetas.ViewModel
{
    public class VehicleViewModel
    {
        public string Mark { get; set; }
        public string Model { get; set; }
        public string Color { get; set; }
        public int Year { get; set; }
        public decimal Price { get; set; }
        public string Description { get; set; }
        public bool IsNew { get; set; }
    }
}
