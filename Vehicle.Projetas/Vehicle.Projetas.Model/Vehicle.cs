using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Vehicle.Projetas.Model
{
    [Table("tb_Vehicle", Schema = "dbo")]
    public class Vehicle
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        [Column("Id")]
        public int Id { get; set; }

        [MaxLength(40), Required()]
        [Column("brand")]
        public string brand { get; set; }

        [MaxLength(50), Required()]
        [Column("model")]
        public string model { get; set; }

        [MaxLength(30), Required()]
        [Column("color")]
        public string color { get; set; }

        [Required()]
        [Column("year")]
        public int year { get; set; }

        [Required()]
        [Column("price")]
        public decimal price { get; set; }

        [Column("description")]
        public string description { get; set; }

        [Required()]
        [Column("isnew")]
        public bool isnew { get; set; }

        [Required()]
        [Column("registrationDate")]
        public DateTime registrationDate { get; set; }
        
        [Column("updateDate")]
        public DateTime? updateDate { get; set; }

    }
}
