using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Projetas.Web.Models
{
    public class VehicleModel
    {
        [Required]
        [DisplayName("Code")]
        public int id { get; set; }

        [Required]
        [MaxLength(40)]
        [DisplayName("Brand")]
        public string brand { get; set; }

        [Required]
        [MaxLength(50)]
        [DisplayName("Model")]
        public string model { get; set; }

        [Required]
        [MaxLength(30)]
        [DisplayName("Color")]
        public string color { get; set; }

        [Required]
        [DisplayName("Year")]
        public int year { get; set; }

        [Required]
        [DisplayName("Price")]
        public decimal price { get; set; }

        [DisplayName("Description")]
        public string description { get; set; }

        [Required]
        [DisplayName("Is New ?")]
        public bool isNew { get; set; }

        [Required]
        [DisplayName("Create Date")]
        public DateTime createdDate { get; set; }

        [Required]
        [DisplayName("Update Date")]
        public DateTime updatedDate { get; set; }
    }
}
