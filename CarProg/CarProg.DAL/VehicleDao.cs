using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;
using CarProg.DAL.Base;
using CarProg.OBJ;

namespace CarProg.DAL
{
    public class VehicleDao : BaseDao<Vehicle, int>
    {
        #region [ Contrutor ]

        public VehicleDao() : base()
        {

        }

        public VehicleDao(CarProgContext context) : base(context)
        {

        }

        #endregion

        #region [ Methods ]

        public override async Task<int> Insert(params Vehicle[] obj)
        {
            try
            {
                return await Access.InsertAsync(obj);
            }
            catch (Exception ex)
            {
                throw new Exception(string.Format("Erro ao inserir {0} no banco de dados. [{1}]", obj[0].GetType().Name, ex.Message));
            }
        }

        public override async Task<int> Update(params Vehicle[] obj)
        {
            try
            {
                return await Access.UpdateAsync(obj);
            }
            catch (Exception ex)
            {
                throw new Exception(string.Format("Erro ao alterar {0} no banco de dados. [{1}]", obj[0].GetType().Name, ex.Message));
            }
        }

        public override async Task<int> Delete(params Vehicle[] obj)
        {
            try
            {
                return await Access.DeleteAsync(obj);
            }
            catch (Exception ex)
            {
                throw new Exception(string.Format("Erro ao deletar {0} no banco de dados. [{1}]", obj[0].GetType().Name, ex.Message));
            }
        }

        public override async Task<int> Delete(int id)
        {
            var obj = await Get(e => e.Id == id);
            return await Delete(obj);
        }

        public override async Task<Vehicle> Get(int id)
        {
            return await Get(e => e.Id == id);
        }

        public override async Task<Vehicle> Get(Expression<Func<Vehicle, bool>> @where)
        {
            return await Access.GetFirstOrDefaultAsync(where);
        }

        public override async Task<List<Vehicle>> GetList(Expression<Func<Vehicle, bool>> @where)
        {
            return await Access.GetAsync(where);
        }

        public override async Task<List<Vehicle>> GetListAll()
        {
            return await GetList(null);
        }

        #endregion
    }
}
