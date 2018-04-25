using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using Projetas.Web.Helper;
using Projetas.Web.Models;

namespace Projetas.Web.Controllers
{
    public class HomeController : Controller
    {
        VehicleApi _vehicleApi = new VehicleApi();

        public async Task<IActionResult> Index()
        {
            List<VehicleModel> vm = new List<VehicleModel>();

            HttpClient client = _vehicleApi.InitializeClient();

            HttpResponseMessage res = await client.GetAsync("api/vehicles");

            if (res.IsSuccessStatusCode)
            {
                var result = res.Content.ReadAsStringAsync().Result;
                vm = JsonConvert.DeserializeObject<List<VehicleModel>>(result);
            }

            return View(vm);
        }

        public async Task<IActionResult> Cadastro(int? id)
        {
            if (id == null)
            {
                return View();
            }
            else
            {
                List<VehicleModel> vm = new List<VehicleModel>();
                HttpClient client = _vehicleApi.InitializeClient();
                HttpResponseMessage res = await client.GetAsync("api/vehicles");

                if (res.IsSuccessStatusCode)
                {
                    var result = res.Content.ReadAsStringAsync().Result;
                    vm = JsonConvert.DeserializeObject<List<VehicleModel>>(result);
                }

                var item = vm.SingleOrDefault(m => m.id == id);
                if (item == null)
                {
                    return NotFound();
                }
                else
                {
                    return View(item);
                }
            }
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public IActionResult Cadastro(
            [Bind("id,brand,model,color,year,price,description,isNew,createdDate,updatedDate")] VehicleModel item)
        {
            if (ModelState.IsValid)
            {
                HttpClient client = _vehicleApi.InitializeClient();

                var content = new StringContent(JsonConvert.SerializeObject(item), Encoding.UTF8, "application/json");
                HttpResponseMessage res = client.PostAsync("api/vehicles", content).Result;
                if (res.IsSuccessStatusCode)
                {
                    return RedirectToAction("Index");
                }
            }
            return View(item);
        }

        public async Task<IActionResult> Detalhe(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            List<VehicleModel> dto = new List<VehicleModel>();
            HttpClient client = _vehicleApi.InitializeClient();
            HttpResponseMessage res = await client.GetAsync("api/vehicles");

            if (res.IsSuccessStatusCode)
            {
                var result = res.Content.ReadAsStringAsync().Result;
                dto = JsonConvert.DeserializeObject<List<VehicleModel>>(result);
            }

            var item = dto.SingleOrDefault(m => m.id == id);
            if (item == null)
            {
                return NotFound();
            }

            return View(item);
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public IActionResult Detalhe(int id, [Bind("id,brand,model,color,year,price,description,isNew,createdDate,updatedDate")] VehicleModel item)
        {
            if (id != item.id)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                HttpClient client = _vehicleApi.InitializeClient();

                var content = new StringContent(JsonConvert.SerializeObject(item), Encoding.UTF8, "application/json");
                HttpResponseMessage res = client.PutAsync("api/vehicles", content).Result;
                if (res.IsSuccessStatusCode)
                {
                    return RedirectToAction("Index");
                }
            }
            return View(item);
        }


        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}
