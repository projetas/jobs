using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Data.Entity.Validation;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;

namespace CarProg.DAL.Base
{
    public class BaseAccess<T> where T : class
    {
        #region[ Properties ]

        private CarProgContext _context;

        #endregion

        #region [ Constructors ]

        public BaseAccess()
        {
            _context = new CarProgContext();
        }

        public BaseAccess(CarProgContext context)
        {
            _context = context;
        }

        #endregion

        #region [ Methods ]

        public virtual async Task<int> InsertAsync(params T[] items)
        {
            try
            {

                foreach (T item in items)
                {
                    _context.Entry(item).State = EntityState.Added;
                }

                return await _context.SaveChangesAsync();

            }
            catch (DbEntityValidationException e)
            {
                var stringBuilder = new StringBuilder();

                foreach (var eve in e.EntityValidationErrors)
                {
                    stringBuilder.AppendFormat(
                        "Entidade do tipo \"{0}\" no estado \"{1}\" segue os seguintes erros de validação:",
                        eve.Entry.Entity.GetType().Name, eve.Entry.State);
                    foreach (var ve in eve.ValidationErrors)
                    {
                        stringBuilder.AppendFormat("- Propriedade: \"{0}\", Erro: \"{1}\"", ve.PropertyName,
                            ve.ErrorMessage);
                    }
                }

                throw new Exception(stringBuilder.ToString(), e);
            }
            catch (Exception ex)
            {
                var myException = ex;

                while (myException.InnerException != null)
                {
                    myException = myException.InnerException;
                }

                throw new Exception(myException.Message, ex);
            }
        }

        public virtual async Task<int> UpdateAsync(params T[] items)
        {
            try
            {

                foreach (T item in items)
                {
                    _context.Entry(item).State = EntityState.Modified;
                }

                return await _context.SaveChangesAsync();
            }
            catch (DbEntityValidationException e)
            {
                var stringBuilder = new StringBuilder();

                foreach (var eve in e.EntityValidationErrors)
                {
                    stringBuilder.AppendFormat("Entidade do tipo \"{0}\" no estado \"{1}\" segue os seguintes erros de validação:", eve.Entry.Entity.GetType().Name, eve.Entry.State);
                    foreach (var ve in eve.ValidationErrors)
                    {
                        stringBuilder.AppendFormat("- Propriedade: \"{0}\", Erro: \"{1}\"", ve.PropertyName, ve.ErrorMessage);
                    }
                }

                throw new Exception(stringBuilder.ToString(), e);
            }
            catch (Exception ex)
            {
                var myException = ex;

                while (myException.InnerException != null)
                {
                    myException = myException.InnerException;
                }

                throw new Exception(myException.Message, ex);
            }
        }

        public virtual async Task<int> DeleteAsync(params T[] items)
        {
            try
            {

                foreach (T item in items)
                {
                    _context.Entry(item).State = EntityState.Deleted;
                }

                return await _context.SaveChangesAsync();
            }
            catch (DbEntityValidationException e)
            {
                var stringBuilder = new StringBuilder();

                foreach (var eve in e.EntityValidationErrors)
                {
                    stringBuilder.AppendFormat("Entidade do tipo \"{0}\" no estado \"{1}\" segue os seguintes erros de validação:", eve.Entry.Entity.GetType().Name, eve.Entry.State);
                    foreach (var ve in eve.ValidationErrors)
                    {
                        stringBuilder.AppendFormat("- Propriedade: \"{0}\", Erro: \"{1}\"", ve.PropertyName, ve.ErrorMessage);
                    }
                }

                throw new Exception(stringBuilder.ToString(), e);
            }
            catch (Exception ex)
            {
                var myException = ex;

                while (myException.InnerException != null)
                {
                    myException = myException.InnerException;
                }

                throw new Exception(myException.Message, ex);
            }
        }

        public virtual async Task<List<T>> GetAllAsync(params Expression<Func<T, object>>[] @navigationProperties)
        {

            IQueryable<T> dbQuery = GetQueryable(_context);


            //Apply eager loading
            foreach (Expression<Func<T, object>> navigationProperty in navigationProperties)
                dbQuery = dbQuery.Include<T, object>(navigationProperty);

            return await dbQuery
                .AsNoTracking()
                .ToListAsync<T>();

        }

        public virtual async Task<List<T>> GetAsync(Expression<Func<T, bool>> @where, params Expression<Func<T, object>>[] navigationProperties)
        {
            List<T> list;

            IQueryable<T> dbQuery = GetQueryable(_context);

            //Apply eager loading
            foreach (Expression<Func<T, object>> navigationProperty in navigationProperties)
                dbQuery = dbQuery.Include<T, object>(navigationProperty);


            if (where != null)
                list = await dbQuery
                    .AsNoTracking()
                    .Where(where).ToListAsync();
            else
                list = await dbQuery
                    .ToListAsync();


            return list;
        }

        public virtual async Task<T> GetFirstOrDefaultAsync(Expression<Func<T, bool>> @where, params Expression<Func<T, object>>[] @navigationProperties)
        {
            T item = null;

            IQueryable<T> dbQuery = GetQueryable(_context);

            //Apply eager loading
            foreach (Expression<Func<T, object>> navigationProperty in navigationProperties)
                dbQuery = dbQuery.Include<T, object>(navigationProperty);

            item = await dbQuery
                .AsNoTracking() //Don't track any changes for the selected item
                .Where(where)
                .FirstOrDefaultAsync<T>(); //Apply where clause

            return item;
        }

        public virtual async Task<bool> AnyAsync(Expression<Func<T, bool>> @where)
        {
            bool result;

            IQueryable<T> dbQuery = GetQueryable(_context);

            if (where != null)
                result = await dbQuery
                    .AsNoTracking()
                    .Where(where)
                    .AnyAsync();
            else
                result = await dbQuery
                    .AnyAsync();


            return result;
        }

        public virtual IQueryable<T> GetQueryable(CarProgContext context)
        {
            IQueryable<T> dbQuery = context.Set<T>();

            return dbQuery
                .AsNoTracking(); // Don't track any changes for the selected item
        }

        #endregion
    }
}
