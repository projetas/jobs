using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;

namespace Vehicle.Projetas.Dal
{
    public abstract class GenericCrud<T> : IGenericCrud<T> where T : class
    {
        Context ctx = new Context();

        public void create(T oEntity)
        {
            ctx.Set<T>().Add(oEntity);
            ctx.SaveChanges();
        }

        public void delete(Func<T, bool> predicate)
        {
            ctx.Set<T>()
           .Where(predicate).ToList()
           .ForEach(del => ctx.Set<T>().Remove(del));
            ctx.SaveChanges();
        }

        public void update(T oEntity)
        {         
            ctx.Entry(oEntity).State = EntityState.Modified;
            ctx.SaveChanges();
        }

        public IEnumerable<T> readAll()
        {
            return ctx.Set<T>();
        }

        public IEnumerable<T> read(Func<T, bool> predicate)
        {
            return ctx.Set<T>().Where(predicate);
        }

    }
}
