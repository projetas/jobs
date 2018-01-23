using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using CarProg.DAL.Base;

namespace CarProg.BLL.Base
{
    public class BaseBo<T, TKey> where T : class
    {
        public BaseBo()
        {
        }
    }
}
