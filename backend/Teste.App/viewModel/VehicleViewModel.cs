using System;
using System.Collections.Generic;
using System.Runtime.Serialization;
using System.Text;

namespace Teste.App.viewModel
{
    [DataContract]
    public class VehicleViewModel
    {

        [DataMember(Name = "id")]
        public int? Id { get; set; }

        [DataMember(Name = "brand")]
        public string Brand { get; set; }

        [DataMember(Name = "model")]
        public string Model { get; set; }

        [DataMember(Name = "color")]
        public string Color { get; set; }

        [DataMember(Name = "year")]
        public int Year { get; set; }

        [DataMember(Name = "price")]
        public Decimal Price { get; set; }

        [DataMember(Name = "description")]
        public string Description { get; set; }

        [DataMember(Name = "is_new")]
        public Boolean IsNew { get; set; }

        [IgnoreDataMember]
        public DateTime? AddedDate { get; set; }

        [DataMember(Name = "modified_date")]
        public DateTime? ModifiedDate { get; set; }
    }
}
