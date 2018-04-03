using System;
using System.IO;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Repository;
using Service;
using Swashbuckle.AspNetCore.Swagger;

namespace ApiVeiculoProjetas
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        public void ConfigureServices(IServiceCollection services)
        {
            RegisterServices(services);
            RegisterContext(services);

            services.AddMvc();

            services.AddCors(o => o.AddPolicy("Policy", builder =>
            {
                builder.AllowAnyOrigin()
                       .AllowAnyMethod()
                       .AllowAnyHeader();
            }));

            services.AddSwaggerGen(c =>
            {
                c.SwaggerDoc("v1", new Info
                {
                    Title = "API Veículo Projetas",
                    Version = "v1"
                });

                c.IncludeXmlComments(Path.Combine(AppContext.BaseDirectory, "ApiVeiculoProjetas.xml"));
            });
        }

        private void RegisterServices(IServiceCollection services)
        {
            services.AddTransient<Services>();
        }

        private void RegisterContext(IServiceCollection services)
        {
            services.AddDbContext<VehicleContext>(opt => opt.UseInMemoryDatabase("Vehicle"));
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IHostingEnvironment env)
        {
            app.UseSwagger();

            app.UseSwaggerUI(c =>
            {
                c.SwaggerEndpoint("/swagger/v1/swagger.json", "API Veículo Projetas");
            });

            app.UseCors("Policy");

            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseMvc();
        }
    }
}
