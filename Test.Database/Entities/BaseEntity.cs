using System;
using System.Collections.Generic;
using System.Text;

namespace Test.Database.Entities
{
    public abstract class BaseEntity
    {
        public abstract object[] GetKey();
    }
}
