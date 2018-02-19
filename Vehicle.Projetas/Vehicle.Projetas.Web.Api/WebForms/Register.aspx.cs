using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Vehicle.Projetas.Web.Api.WebForms
{
    public partial class Register : System.Web.UI.Page
    {
        private string url = ConfigurationManager.AppSettings["apiUrl"];

        private void fillGrid()
        {
            try
            {
                HttpClient client = new HttpClient();
                client.BaseAddress = new Uri(url);
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                HttpResponseMessage response = client.GetAsync("Vehicle").Result;

                gvVehicle.DataSource = response.Content.ReadAsAsync<IEnumerable<Model.Vehicle>>().Result;
                gvVehicle.DataBind();

            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message, ex.InnerException);
            }
        }

        private void fillGridById(int id)
        {
            try
            {
                HttpClient client = new HttpClient();
                client.BaseAddress = new Uri(url);
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                HttpResponseMessage response = client.GetAsync("Vehicle/" + id).Result;

                gvVehicle.DataSource = response.Content.ReadAsAsync<IEnumerable<Model.Vehicle>>().Result;
                gvVehicle.DataBind();

            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message, ex.InnerException);
            }
        }

        private void selectRow(int index)
        {
            ViewState["id"] = gvVehicle.Rows[index].Cells[2].Text;
            ddlBrand.SelectedValue = gvVehicle.Rows[index].Cells[3].Text;
            txtModel.Text = gvVehicle.Rows[index].Cells[4].Text;
            txtColor.Text = gvVehicle.Rows[index].Cells[5].Text;
            ddlYear.SelectedValue = gvVehicle.Rows[index].Cells[6].Text;
            txtPrice.Text = gvVehicle.Rows[index].Cells[7].Text;
            txtDescription.Text = Server.HtmlDecode(gvVehicle.Rows[index].Cells[8].Text);
            rbNew.SelectedValue = gvVehicle.Rows[index].Cells[9].Text;
            if (gvVehicle.Rows[index].Cells[9].Text.Equals("True"))
            {
                ddlYear.Enabled = false;
            }
            else
            {
                ddlYear.Enabled = true;
            }
            txtRegistrationDate.Text = gvVehicle.Rows[index].Cells[10].Text;
            txtUpdateDate.Text = Server.HtmlDecode(gvVehicle.Rows[index].Cells[11].Text);
            btnSave.Text = "Editar";
        }

        private void clearFields()
        {
            ddlBrand.Focus();
            ddlBrand.SelectedValue = "0";
            txtModel.Text = string.Empty;
            txtColor.Text = string.Empty;
            ddlYear.SelectedValue = "0";
            txtPrice.Text = string.Empty;
            txtDescription.Text = string.Empty;
            rbNew.SelectedValue = "False";
            btnSave.Text = "Salvar";
        }

        private void delete(int index)
        {
            try
            {
                HttpClient client = new HttpClient();
                client.BaseAddress = new Uri(url);
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                HttpResponseMessage response = client.DeleteAsync("Vehicle/" + index).Result;
                successMessage.Text = "Veículo excluido com sucesso";
                fillGrid();
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message, ex.InnerException);
            }
        }

        private void save(Model.Vehicle vehicle)
        {
            try
            {
                HttpClient client = new HttpClient();
                client.BaseAddress = new Uri(url);
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                HttpResponseMessage response = client.PostAsJsonAsync("Vehicle", vehicle).Result;
                //response.IsSuccessStatusCode;
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message, ex.InnerException);
            }
        }

        private void update(Model.Vehicle vehicle)
        {
            try
            {
                HttpClient client = new HttpClient();
                client.BaseAddress = new Uri(url);
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                HttpResponseMessage response = client.PutAsJsonAsync("Vehicle/" + vehicle.Id, vehicle).Result;
                //response.IsSuccessStatusCode;  
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message, ex.InnerException);
            }
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!Page.IsPostBack)
            {
                fillGrid();
                txtRegistrationDate.Text = DateTime.Now.ToString();
            }
        }

        protected void btnSave_Click(object sender, EventArgs e)
        {
            try
            {
                Model.Vehicle vehicle = new Model.Vehicle();
                vehicle.brand = ddlBrand.SelectedValue;
                vehicle.model = txtModel.Text;
                vehicle.color = txtColor.Text;
                vehicle.year = rbNew.SelectedValue.Equals("True") ? DateTime.Now.Year : Convert.ToInt32(ddlYear.SelectedValue);
                vehicle.price = Convert.ToDecimal(txtPrice.Text.Replace("R$", ""));
                vehicle.description = txtDescription.Text;
                vehicle.isnew = Convert.ToBoolean(rbNew.SelectedValue);
                vehicle.registrationDate = Convert.ToDateTime(txtRegistrationDate.Text);

                if (ViewState["id"] == null)
                {
                    save(vehicle);
                    fillGrid();
                    successMessage.Text = "Veículo cadastrado com sucesso";
                }
                else
                {
                    vehicle.Id = Convert.ToInt32(ViewState["id"]);
                    vehicle.updateDate = DateTime.Now;
                    update(vehicle);
                    fillGrid();
                    successMessage.Text = "Veículo atualizado com sucesso";
                }
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message, ex.InnerException);
            }
            finally
            {
                clearFields();
            }

        }

        protected void gvVehicle_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            try
            {
                if (e.CommandName.Equals("edt"))
                    selectRow(Convert.ToInt32(e.CommandArgument));

                if (e.CommandName.Equals("del"))
                    delete(Convert.ToInt32(gvVehicle.DataKeys[Convert.ToInt32(e.CommandArgument)].Value));
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message, ex.InnerException);
            }
        }

        protected void btnFind_Click(object sender, EventArgs e)
        {
            try
            {
                fillGridById(Convert.ToInt32(txtFindId.Text));
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message, ex.InnerException);
            }
        }

    }
}