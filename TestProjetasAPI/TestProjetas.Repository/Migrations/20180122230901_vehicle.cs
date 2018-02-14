using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Migrations;
using System;
using System.Collections.Generic;

namespace TestProjetas.Repository.Migrations
{
    public partial class vehicle : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.EnsureSchema(
                name: "dbo");

            migrationBuilder.CreateTable(
                name: "TB_VEHICLE",
                schema: "dbo",
                columns: table => new
                {
                    Id = table.Column<int>(nullable: false)
                        .Annotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn),
                    NM_BRAND = table.Column<string>(maxLength: 40, nullable: false),
                    NM_COLOR = table.Column<string>(maxLength: 50, nullable: false),
                    DS_DESCRIPTION = table.Column<string>(type: "TEXT", nullable: true),
                    FL_NEW = table.Column<bool>(type: "BIT", nullable: false),
                    NM_MODEL = table.Column<string>(maxLength: 50, nullable: false),
                    DC_PRICE = table.Column<decimal>(nullable: false),
                    DT_REGISTRATION = table.Column<DateTime>(nullable: false),
                    DT_UPDATE = table.Column<DateTime>(nullable: true),
                    NR_YEAR = table.Column<int>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Id_Vehicle", x => x.Id);
                });
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "TB_VEHICLE",
                schema: "dbo");
        }
    }
}
