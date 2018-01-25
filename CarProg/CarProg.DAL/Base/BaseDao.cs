using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;

namespace CarProg.DAL.Base
{
    public abstract class BaseDao<T, TKey> where T : class
    {
        #region [ Properties ]

        protected BaseAccess<T> Access { get; private set; }
        protected CarProgContext Context { get; private set; }

        #endregion

        #region [ Constructors ]

        protected BaseDao(CarProgContext context)
        {
            Context = context;
            Initialize();
        }

        protected BaseDao()
        {
            Initialize();
        }

        #endregion

        #region [ Private Methods ]

        private void Initialize()
        {
            try
            {
                if (Context == null)
                {
                    Context = new CarProgContext();
                }
            }
            catch (Exception)
            {
                Context = new CarProgContext();
            }

            Access = new BaseAccess<T>(Context);

        }

        #endregion

        #region [ Abstract Methods ]

        public abstract Task<int> Insert(params T[] obj);
        public abstract Task<int> Update(params T[] obj);
        public abstract Task<int> Delete(params T[] obj);
        public abstract Task<int> Delete(TKey id);

        public abstract Task<T> Get(TKey id);
        public abstract Task<T> Get(Expression<Func<T, bool>> @where);
        public abstract Task<List<T>> GetList(Expression<Func<T, bool>> @where);
        public abstract Task<List<T>> GetListAll();

        #endregion
    }
}
