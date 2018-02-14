using System;
using System.Collections.Generic;
using System.Linq.Expressions;

namespace TestProjetas.Repository.Interface
{
    public interface IRepositoryBase<T> where T : class
    {
        IList<T> Get(Expression<Func<T, bool>> filter = null);
        void Add(T entity);
        void Update(T entity);
        void Delete(T entity);
    }
}
