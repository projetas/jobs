using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Reflection;
using System.Text;
using TestProjetas.Repository.Interface;
using System.Linq;
using System.Linq.Expressions;
using TestProjetas.Repository.Configuration;

namespace TestProjetas.Repository
{
    public class RepositoryBase<T> : IDisposable, IRepositoryBase<T> where T : class
    {
        internal PersistContext _context;
        internal DbSet<T> _dbSet;

        public RepositoryBase(PersistContext context)
        {
            this._context = context;
            this._dbSet = context.Set<T>();
        }

        public IList<T> Get(Expression<Func<T, bool>> filter = null)
        {
            IQueryable<T> query = _dbSet;

            if (filter != null)
            {
                query = query.Where(filter);
            }

            return query.ToList();
        }

        public void Add(T entity)
        {
            _dbSet.Add(entity);
            _context.SaveChanges();
        }

        public void Update(T entity)
        {
            var entry = _context.Entry<T>(entity);
            var pkey = entity.GetType().GetProperty("Id").GetValue(entity);

            if (entry.State == EntityState.Detached)
            {
                var set = _context.Set<T>();
                if (set.Find(pkey) != null)
                {
                    var attachedEntry = _context.Entry(set.Find(pkey));
                    attachedEntry.CurrentValues.SetValues(entity);
                }
                else
                {
                    entry.State = EntityState.Modified; 
                }
            }

            _context.SaveChanges();
        }

        public void Delete(T entity)
        {
            if (_context.Entry(entity).State == EntityState.Detached)
            {
                _dbSet.Attach(entity);
            }
            _dbSet.Remove(entity);
            _context.SaveChanges();
        }

        public void Dispose()
        {
            _dbSet = null;
            _context.Dispose();
            GC.SuppressFinalize(this);
        }
    }
}
