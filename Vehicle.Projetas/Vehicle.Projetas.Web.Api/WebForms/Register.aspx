<%@ Page Title="" Language="C#" MasterPageFile="~/WebForms/Site.Master" AutoEventWireup="true" CodeBehind="Register.aspx.cs" Inherits="Vehicle.Projetas.Web.Api.WebForms.Register" %>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <script src="../Scripts/jquery-1.10.2.min.js"></script>
    <script src="../Scripts/Vehicle.js"></script>
    <div class="form-horizontal">
        <h4>Cadastro de Veiculos</h4>
        <hr />

        <p class="text-success">
            <asp:Literal runat="server" ID="successMessage" />
        </p>
        <%--       <asp:UpdatePanel ID="upSave" runat="server" UpdateMode="Conditional">
            <ContentTemplate>--%>
        <div class="form-group">
            <asp:Label runat="server" AssociatedControlID="ddlBrand" CssClass="col-md-2 control-label">Marca</asp:Label>
            <div class="col-md-5">
                <asp:DropDownList ID="ddlBrand" runat="server" CssClass="form-control">
                    <asp:ListItem Value="0">Selecione</asp:ListItem>
                    <asp:ListItem Value="Chevrolet">Chevrolet</asp:ListItem>
                    <asp:ListItem Value="Fiat">Citroën</asp:ListItem>
                    <asp:ListItem Value="Fiat">Fiat</asp:ListItem>
                    <asp:ListItem Value="Ford">Ford</asp:ListItem>
                    <asp:ListItem Value="Honda">Honda</asp:ListItem>
                    <asp:ListItem Value="Hyundai">Hyundai</asp:ListItem>
                    <asp:ListItem Value="JAC Motors">JAC Motors</asp:ListItem>
                    <asp:ListItem Value="Nissan">Nissan</asp:ListItem>
                    <asp:ListItem Value="Peugeot">Peugeot</asp:ListItem>
                    <asp:ListItem Value="Renault">Renault</asp:ListItem>
                    <asp:ListItem Value="Suzuki">Suzuki</asp:ListItem>
                    <asp:ListItem Value="Toyota">Toyota</asp:ListItem>
                    <asp:ListItem Value="Volkswagen">Volkswagen</asp:ListItem>
                    <asp:ListItem Value="Volvo">Volvo</asp:ListItem>
                </asp:DropDownList>
                <asp:CompareValidator ID="cvddlBrand" runat="server" ControlToValidate="ddlBrand"
                    ErrorMessage="Selecione o Marca" Operator="NotEqual" CssClass="text-danger" Display="Dynamic"
                    ValueToCompare="0">Selecione o Marca</asp:CompareValidator>
            </div>
        </div>

        <div class="form-group">
            <asp:Label runat="server" AssociatedControlID="txtModel" CssClass="col-md-2 control-label">Modelo</asp:Label>
            <div class="col-md-10">
                <asp:TextBox runat="server" ID="txtModel" CssClass="form-control" />
                <asp:RequiredFieldValidator runat="server" ControlToValidate="txtModel" Display="Dynamic"
                    CssClass="text-danger" ErrorMessage="Informe o modelo" />
            </div>
        </div>

        <div class="form-group">
            <asp:Label runat="server" AssociatedControlID="txtColor" CssClass="col-md-2 control-label">Cor</asp:Label>
            <div class="col-md-10">
                <asp:TextBox runat="server" ID="txtColor" CssClass="form-control" />
                <asp:RequiredFieldValidator runat="server" ControlToValidate="txtColor"
                    CssClass="text-danger" Display="Dynamic" ErrorMessage="Informe a cor" />
            </div>
        </div>

        <div class="form-group">
            <asp:Label runat="server" AssociatedControlID="rbNew" CssClass="col-md-2 control-label">Novo?</asp:Label>
            <div class="col-md-3">
                <asp:RadioButtonList ID="rbNew" runat="server" ClientIDMode="Static" RepeatDirection="Horizontal">
                    <asp:ListItem Selected="True" Value="False">Não</asp:ListItem>
                    <asp:ListItem Value="True">Sim</asp:ListItem>
                </asp:RadioButtonList>

            </div>
        </div>

        <div class="form-group">
            <asp:Label runat="server" AssociatedControlID="ddlYear" CssClass="col-md-2 control-label">Ano</asp:Label>
            <div class="col-md-10">
                <asp:DropDownList ID="ddlYear" runat="server" ClientIDMode="Static" CssClass="form-control">
                    <asp:ListItem Value="0">Selecione</asp:ListItem>
                    <asp:ListItem Value="2019">2019</asp:ListItem>
                    <asp:ListItem Value="2018">2018</asp:ListItem>
                    <asp:ListItem Value="2017">2017</asp:ListItem>
                    <asp:ListItem Value="2016">2016</asp:ListItem>
                    <asp:ListItem Value="2015">2015</asp:ListItem>
                    <asp:ListItem Value="2014">2014</asp:ListItem>
                    <asp:ListItem Value="2013">2013</asp:ListItem>
                    <asp:ListItem Value="2012">2012</asp:ListItem>
                    <asp:ListItem Value="2011">2011</asp:ListItem>
                    <asp:ListItem Value="2010">2010</asp:ListItem>
                    <asp:ListItem Value="2009">2009</asp:ListItem>
                    <asp:ListItem Value="2008">2008</asp:ListItem>
                    <asp:ListItem Value="2007">2007</asp:ListItem>
                    <asp:ListItem Value="2006">2006</asp:ListItem>
                    <asp:ListItem Value="2005">2005</asp:ListItem>
                    <asp:ListItem Value="2004">2004</asp:ListItem>
                    <asp:ListItem Value="2003">2003</asp:ListItem>
                    <asp:ListItem Value="2002">2002</asp:ListItem>
                    <asp:ListItem Value="2001">2001</asp:ListItem>
                    <asp:ListItem Value="2000">2000</asp:ListItem>
                    <asp:ListItem Value="1999">1999</asp:ListItem>
                    <asp:ListItem Value="1998">1998</asp:ListItem>
                    <asp:ListItem Value="1997">1997</asp:ListItem>
                    <asp:ListItem Value="1996">1996</asp:ListItem>
                    <asp:ListItem Value="1995">1995</asp:ListItem>
                    <asp:ListItem Value="1994">1994</asp:ListItem>
                    <asp:ListItem Value="1993">1993</asp:ListItem>
                    <asp:ListItem Value="1992">1992</asp:ListItem>
                    <asp:ListItem Value="1991">1991</asp:ListItem>
                    <asp:ListItem Value="1990">1990</asp:ListItem>
                    <asp:ListItem Value="1989">1989</asp:ListItem>
                    <asp:ListItem Value="1988">1988</asp:ListItem>
                    <asp:ListItem Value="1987">1987</asp:ListItem>
                    <asp:ListItem Value="1986">1986</asp:ListItem>
                    <asp:ListItem Value="1985">1985</asp:ListItem>
                    <asp:ListItem Value="1984">1984</asp:ListItem>
                    <asp:ListItem Value="1983">1983</asp:ListItem>
                    <asp:ListItem Value="1982">1982</asp:ListItem>
                    <asp:ListItem Value="1981">1981</asp:ListItem>
                    <asp:ListItem Value="1980">1980</asp:ListItem>
                    <asp:ListItem Value="1979">1979</asp:ListItem>
                    <asp:ListItem Value="1978">1978</asp:ListItem>
                    <asp:ListItem Value="1977">1977</asp:ListItem>
                    <asp:ListItem Value="1976">1976</asp:ListItem>
                    <asp:ListItem Value="1975">1975</asp:ListItem>
                    <asp:ListItem Value="1974">1974</asp:ListItem>
                    <asp:ListItem Value="1973">1973</asp:ListItem>
                    <asp:ListItem Value="1972">1972</asp:ListItem>
                    <asp:ListItem Value="1971">1971</asp:ListItem>
                    <asp:ListItem Value="1970">1970</asp:ListItem>
                    <asp:ListItem Value="1969">1969</asp:ListItem>
                    <asp:ListItem Value="1968">1968</asp:ListItem>
                    <asp:ListItem Value="1967">1967</asp:ListItem>
                    <asp:ListItem Value="1966">1966</asp:ListItem>
                    <asp:ListItem Value="1965">1965</asp:ListItem>
                    <asp:ListItem Value="1964">1964</asp:ListItem>
                    <asp:ListItem Value="1963">1963</asp:ListItem>
                    <asp:ListItem Value="1962">1962</asp:ListItem>
                    <asp:ListItem Value="1961">1961</asp:ListItem>
                    <asp:ListItem Value="1959">1959</asp:ListItem>
                    <asp:ListItem Value="1958">1958</asp:ListItem>
                    <asp:ListItem Value="1957">1957</asp:ListItem>
                </asp:DropDownList>
                <%--<asp:CompareValidator ID="cvYear" runat="server" ControlToValidate="ddlYear" Display="Dynamic" ClientIDMode="Static"
                    ErrorMessage="Selecione o Ano" Operator="NotEqual" CssClass="text-danger"
                    ValueToCompare="0">Selecione o Ano</asp:CompareValidator>--%>
            </div>
        </div>

        <div class="form-group">
            <asp:Label runat="server" AssociatedControlID="txtPrice" CssClass="col-md-2 control-label">Preço</asp:Label>
            <div class="col-md-10">
                <asp:TextBox runat="server" ID="txtPrice" CssClass="form-control" />
                <asp:RequiredFieldValidator runat="server" ControlToValidate="txtPrice"
                    CssClass="text-danger" Display="Dynamic" ErrorMessage="Informe o Preço" />
            </div>
        </div>

        <div class="form-group">
            <asp:Label runat="server" AssociatedControlID="txtDescription" CssClass="col-md-2 control-label">Descrição</asp:Label>
            <div class="col-md-10">
                <asp:TextBox runat="server" ID="txtDescription" TextMode="MultiLine" CssClass="form-control" />
            </div>
        </div>

        <div class="form-group">
            <asp:Label runat="server" AssociatedControlID="txtRegistrationDate" CssClass="col-md-2 control-label">Data Cadastro</asp:Label>
            <div class="col-md-10">
                <asp:TextBox runat="server" ID="txtRegistrationDate" ReadOnly="true" CssClass="form-control" />
            </div>
        </div>

        <div class="form-group">
            <asp:Label runat="server" AssociatedControlID="txtUpdateDate" CssClass="col-md-2 control-label">Data Atualização</asp:Label>
            <div class="col-md-10">
                <asp:TextBox runat="server" ID="txtUpdateDate" ReadOnly="true" CssClass="form-control" />
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-offset-2 col-md-10">
                <asp:Button runat="server" Text="Salvar" CssClass="btn btn-default" ID="btnSave" OnClick="btnSave_Click" />
            </div>
        </div>
        <%--      </ContentTemplate>
        </asp:UpdatePanel>--%>

        <hr />

        <%--<asp:UpdatePanel ID="upGridView" runat="server" UpdateMode="Conditional">
            <ContentTemplate>--%>

        <div class="form-group">
            <asp:Label runat="server" AssociatedControlID="txtFindId" CssClass="col-md-2 control-label">Código</asp:Label>
            <div class="col-md-10">
                <div class='row'>

                    <div class="col-sm-3">
                        <div class="form-group">
                            <asp:TextBox runat="server" ID="txtFindId" CssClass="form-control" />
                        </div>
                    </div>

                    <div class="col-sm-3">
                        <div class="form-group">
                            <asp:Button runat="server" Text="Pesquisar" CssClass="btn btn-default" ID="btnFind" CausesValidation="false" OnClick="btnFind_Click" />
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="table-responsive">
            <asp:GridView ID="gvVehicle" ClientIDMode="Static" runat="server" CssClass="table table-bordered table-hover" EmptyDataText="Sem Informações" AllowPaging="True" AutoGenerateColumns="False" PageSize="200" OnRowCommand="gvVehicle_RowCommand" DataKeyNames="Id">
                <Columns>
                    <asp:ButtonField CommandName="edt" Text="Editar" />
                    <asp:ButtonField CommandName="del" Text="Excluir" />
                    <asp:BoundField DataField="Id" HeaderText="Código" />
                    <asp:BoundField DataField="brand" HeaderText="Marca" />
                    <asp:BoundField DataField="model" HeaderText="Modelo" />
                    <asp:BoundField DataField="color" HeaderText="Cor" />
                    <asp:BoundField DataField="year" HeaderText="Ano" />
                    <asp:BoundField DataField="price" HeaderText="Preço" DataFormatString="{0:c}" />
                    <asp:BoundField DataField="description" HeaderText="Descrição" />
                    <asp:BoundField DataField="isnew" HeaderText="Novo" />
                    <asp:BoundField DataField="registrationDate" HeaderText="Dt. Cadastro" DataFormatString="{0:g}" />
                    <asp:BoundField DataField="updateDate" HeaderText="Dt. Atualização" DataFormatString="{0:g}" />
                </Columns>
                <EmptyDataRowStyle ForeColor="Red" />
                <HeaderStyle CssClass="thead" />
                <PagerStyle CssClass="pagination-ys" />
            </asp:GridView>
        </div>
        <%--    </ContentTemplate>
        </asp:UpdatePanel>--%>
    </div>

</asp:Content>
