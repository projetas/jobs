//using Microsoft.VisualStudio.TestTools.UnitTesting;
//using System;
//using System.Collections.Generic;
//using System.Linq;
//using System.Text;
//using Teste.Domain.Entities;
//using Teste.Domain.Repositories;
//using Teste.Impl.Context;


//namespace TesteProject
//{
//    [TestClass]
//    public class PessoaTest
//    {
//        IRepository<Pessoa> _rep;

//        PessoaTest(IRepository<Pessoa> rep)
//        {
//            _rep = rep;
//        }

//        [TestMethod]
//        public void GeAllPessoas()
//        {
//            IEnumerable<Pessoa> pessoas = null;

//            pessoas = _rep.GetAll();


//            Assert.IsNotNull(pessoas);

//            Assert.IsTrue(pessoas.ToList().Count > 0);
//        }



//        [TestMethod]
//        public void InsertPessoa()
//        {
//            Pessoa person = null;

//            var pessoa = new PessoaViewModel
//            {
//                Id = 1,
//                Nome = "Pedro henrique Cândido Ferreira",
//                SexoId = 1,
//                Cpf = "1231232131",
//                DataNascimento = new DateTime(2018, 11, 25)
//            };

//            var service = new ContractVehicleApp(_rep);

//            var response = service.SavePessoa(pessoa);

//            person = response;

//            Assert.IsNotNull(person);
//            //Assert.IsFalse(person);

//        }

//        [TestMethod]
//        public void UpdatePessoa()
//        {
//            Pessoa person = null;

//            var at = new Pessoa
//            {
//                Id = 1,
//                Nome = "Pedro henrique Cândido Ferreira",
//                SexoId = 1,
//                Cpf = "1231232131",
//                DataNascimento = new DateTime(2018, 11, 25)
//            };
//            var atvm = new PessoaViewModel
//            {
//                Id = 1,
//                Nome = "Pedro henrique Cândido Ferreira",
//                SexoId = 1,
//                Cpf = "1231232131",
//                DataNascimento = new DateTime(2018, 11, 25)
//            };

//            var service = new ContractVehicleApp(_rep);

//            var response = service.EditPessoa(atvm);

//            person = response;

//            Assert.IsNotNull(person);

//            Assert.AreEqual(at.Id, atvm.Id);

//            Assert.AreEqual(at.SexoId, atvm.SexoId);
//        }


//    }
//}
