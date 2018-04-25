using System;
using System.Collections.Generic;

namespace Projetas.Infra
{
    public interface IRepository<TEntity, U> where TEntity : class
    {
        IEnumerable<TEntity> GetAll();
        TEntity Get(U id);
        int Add(TEntity b);
        int Update(U id, TEntity b);
        int Delete(U id);
    }
}
