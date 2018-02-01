using System;
using System.Collections.Generic;
using System.Text;
using TestProjetas.Domain.Entities;
using TestProjetas.Repository.Configuration;

namespace TestProjetas.Repository
{
    public class VehicleRepository : IDisposable
    {
        private PersistContext _context = new PersistContext();

        public RepositoryBase<Vehicle> Repository;

        private bool _disposed = false;

        public VehicleRepository()
        {
            if (this.Repository == null)
            {
                this.Repository = new RepositoryBase<Vehicle>(_context);
            }
        }

        public void Dispose()
        {
            if (!this._disposed)
            {
                _context.Dispose();
            }
            _disposed = true;
        }

        ~VehicleRepository()
        {
            _disposed = true;
        }
    }
}
