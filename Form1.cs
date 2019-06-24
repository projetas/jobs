using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CadCar
{
    public partial class Form1 : Form
    {
        SqlConnection sqlConexao = null;
        private string strConexao = "Server=localhost\\SQLEXPRESS;Database=CADCARDB;Trusted_Connection=True;";
        private string strSQL = string.Empty;
        private int idCar;

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            ResetaCampos();
        }

        private void ResetaCampos()
        {
            btnCadastrar.Enabled = true;
            btnAlterar.Enabled = false;
            btnExcluir.Enabled = false;
            btnConsultar.Enabled = true;
            btnConsulta.Visible = false;
            btnConsulta.Enabled = false;
            btnFechar.Visible = false;
            btnFechar.Enabled = false;

            txtMarca.Clear();
            txtModelo.Clear();
            txtCor.Clear();
            txtAno.Clear();
            txtPreco.Clear();
            txtDesc.Clear();
            txtRegistro.Clear();
            txtAtualizacao.Clear();

            txtRegistro.Enabled = false;
            txtAtualizacao.Enabled = false;

        }

        private void BtnSair_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void BtnCadastrar_Click(object sender, EventArgs e)
        {
            strSQL = "insert into VEICULO( MARCA , MODELO , COR , ANO , PRECO , DESCR , NOVO , DTREGISTRO , DTATUALIZACAO ) values( @MARCA , @MODELO , @COR , @ANO , @PRECO , @DESCR , @NOVO , CURRENT_TIMESTAMP , CURRENT_TIMESTAMP )";
            sqlConexao = new SqlConnection(strConexao);
            SqlCommand eftvComando = new SqlCommand(strSQL, sqlConexao);

            eftvComando.Parameters.Add("@MARCA", SqlDbType.VarChar).Value = txtMarca.Text;
            eftvComando.Parameters.Add("@MODELO", SqlDbType.VarChar).Value = txtModelo.Text;
            eftvComando.Parameters.Add("@COR", SqlDbType.VarChar).Value = txtCor.Text;
            eftvComando.Parameters.Add("@ANO", SqlDbType.VarChar).Value = txtAno.Text;
            eftvComando.Parameters.Add("@PRECO", SqlDbType.Decimal).Value = txtPreco.Text;
            eftvComando.Parameters.Add("@DESCR", SqlDbType.VarChar).Value = txtDesc.Text;

            if (cmbNovo.Text == "Sim")
                eftvComando.Parameters.Add("@NOVO", SqlDbType.Bit).Value = 1;
            else
                eftvComando.Parameters.Add("@NOVO", SqlDbType.Bit).Value = 0;

            try
            {
                sqlConexao.Open();
                eftvComando.ExecuteNonQuery();
                MessageBox.Show("CADASTRO EFETUADO COM SUCESSO!");

                ResetaCampos();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
            finally
            {
                sqlConexao.Close();
            }
        }

        private void BtnConsultar_Click(object sender, EventArgs e)
        {
            ResetaCampos();
            btnCadastrar.Enabled = false;
            btnAlterar.Enabled = false;
            btnExcluir.Enabled = false;
            btnConsultar.Enabled = false;
            btnConsulta.Visible = true;
            btnConsulta.Enabled = true;
            btnFechar.Visible = true;
            btnFechar.Enabled = true;
        }

        private void BtnConsulta_Click(object sender, EventArgs e)
        {
            btnCadastrar.Enabled = false;
            btnAlterar.Enabled = true;
            btnExcluir.Enabled = true;
            btnConsultar.Enabled = true;
            btnConsulta.Visible = false;
            btnConsulta.Enabled = false;
            btnFechar.Visible = true;
            btnFechar.Enabled = true;

            strSQL = "select * from VEICULO where MODELO LIKE @txtPesquisa";
            sqlConexao = new SqlConnection(strConexao);
            SqlCommand eftvComando = new SqlCommand(strSQL, sqlConexao);

            eftvComando.Parameters.Add("@txtPesquisa", SqlDbType.VarChar).Value = "%"+txtModelo.Text+"%";

            try
            {
                sqlConexao.Open();
                SqlDataReader regRecuperado = eftvComando.ExecuteReader();
                regRecuperado.Read();

                idCar = Convert.ToInt32(regRecuperado["ID"]);
                txtMarca.Text = Convert.ToString(regRecuperado["MARCA"]);
                txtModelo.Text = Convert.ToString(regRecuperado["MODELO"]);
                txtCor.Text = Convert.ToString(regRecuperado["COR"]);
                txtAno.Text = Convert.ToString(regRecuperado["ANO"]);
                txtPreco.Text = Convert.ToString(regRecuperado["PRECO"]);
                txtDesc.Text = Convert.ToString(regRecuperado["DESCR"]);
                txtRegistro.Text = Convert.ToString(regRecuperado["DTREGISTRO"]);
                txtAtualizacao.Text = Convert.ToString(regRecuperado["DTATUALIZACAO"]);

                if (Convert.ToInt32(regRecuperado["NOVO"]) == 1)
                    cmbNovo.Text = "Sim";
                else
                    cmbNovo.Text = "Não";
            }

            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
                ResetaCampos();
            }
            finally
            {
                sqlConexao.Close();
            }

        }

        private void BtnFechar_Click(object sender, EventArgs e)
        {
            ResetaCampos();
        }

        private void BtnAlterar_Click(object sender, EventArgs e)
        {

            strSQL = "update VEICULO set MARCA = @MARCA , MODELO = @MODELO , COR = @COR , ANO = @ANO , PRECO = @PRECO , DESCR = @DESCR , NOVO = @NOVO , DTATUALIZACAO = CURRENT_TIMESTAMP WHERE ID = @IDCAR ";
            sqlConexao = new SqlConnection(strConexao);
            SqlCommand eftvComando = new SqlCommand(strSQL, sqlConexao);

            eftvComando.Parameters.Add("@IDCAR", SqlDbType.Int).Value = idCar;
            eftvComando.Parameters.Add("@MARCA", SqlDbType.VarChar).Value = txtMarca.Text;
            eftvComando.Parameters.Add("@MODELO", SqlDbType.VarChar).Value = txtModelo.Text;
            eftvComando.Parameters.Add("@COR", SqlDbType.VarChar).Value = txtCor.Text;
            eftvComando.Parameters.Add("@ANO", SqlDbType.VarChar).Value = txtAno.Text;
            eftvComando.Parameters.Add("@PRECO", SqlDbType.Decimal).Value = txtPreco.Text;
            eftvComando.Parameters.Add("@DESCR", SqlDbType.VarChar).Value = txtDesc.Text;

            if (cmbNovo.Text == "Sim")
                eftvComando.Parameters.Add("@NOVO", SqlDbType.Int).Value = 1;
            else
                eftvComando.Parameters.Add("@NOVO", SqlDbType.Int).Value = 0;

            try
            {
                sqlConexao.Open();
                eftvComando.ExecuteNonQuery();
                MessageBox.Show("ALTERAÇÃO REALIZADA COM SUCESSO!");
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
            finally
            {
                sqlConexao.Close();
            }
        }

        private void BtnExcluir_Click(object sender, EventArgs e)
        {

            strSQL = "delete from VEICULO where id = @idCar";
            sqlConexao = new SqlConnection(strConexao);
            SqlCommand eftvComando = new SqlCommand(strSQL, sqlConexao);

            eftvComando.Parameters.Add("@idCar", SqlDbType.Int).Value = idCar;

            try
            {
                sqlConexao.Open();
                eftvComando.ExecuteNonQuery();
                MessageBox.Show("REGISTRO EXCLUÍDO COM SUCESSO!");
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
            finally
            {
                sqlConexao.Close();
            }

            ResetaCampos();

        }
    }
}
