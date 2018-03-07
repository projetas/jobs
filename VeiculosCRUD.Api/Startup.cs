using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(VeiculosCRUD.Api.Startup))]
namespace VeiculosCRUD.Api
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
