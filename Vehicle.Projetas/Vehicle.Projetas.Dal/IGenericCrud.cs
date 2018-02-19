using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;

namespace Vehicle.Projetas.Dal
{
    public interface IGenericCrud<T> where T : class
    {
        void create(T oEntity);
        void delete(Func<T, bool> predicate);
        void update(T oEntity);
        IEnumerable<T> readAll();
        IEnumerable<T> read(Func<T, bool> predicate);
    }
}
