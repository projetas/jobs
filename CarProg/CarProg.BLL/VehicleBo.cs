using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;
using CarProg.BLL.Base;
using CarProg.DAL;
using CarProg.DAL.Base;
using CarProg.OBJ;

namespace CarProg.BLL
{
    public class VehicleBo : BaseBo<Vehicle, int>
    {
        private VehicleDao _dao;

        public VehicleBo() : base()
        {
            _dao = new VehicleDao();
        }

        public async Task<int> AddAsync(params Vehicle[] obj)
        {
            try
            {
                foreach (var item in obj)
                {
                    item.RegistrationDate = DateTime.Now;
                }

                return await _dao.Insert(obj);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
        
        public async Task AlterAsync(params Vehicle[] obj)
        {
            try
            {
                foreach (var item in obj)
                {
                    item.UpdateDate = DateTime.Now;
                }

                await _dao.Update(obj);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        public async Task<List<Vehicle>> GetListAsync()
        {
            return await _dao.GetListAll();
        }

        public async Task RemoveAsync(int id)
        {
            try
            {
                await _dao.Delete(id);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        public async Task<Vehicle> GetAsync(Expression<Func<Vehicle, bool>> @where)
        {
            return await _dao.Get(where);
        }
    }
}
