using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Migrations;
using System;
using System.Collections.Generic;

namespace Teste.Impl.Migrations
{
    public partial class initialCreate : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "VEHICLE",
                columns: table => new
                {
                    ID = table.Column<int>(nullable: false)
                        .Annotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn),
                    AADDED_DATE = table.Column<DateTime>(nullable: false),
                    BRAND = table.Column<string>(maxLength: 50, nullable: false),
                    COLOR = table.Column<string>(maxLength: 30, nullable: false),
                    DESCRIPTION = table.Column<string>(nullable: true),
                    IS_NEW = table.Column<bool>(nullable: false),
                    MODEL = table.Column<string>(maxLength: 50, nullable: false),
                    MODIFIED_DATE = table.Column<DateTime>(nullable: true),
                    DECIMAL = table.Column<decimal>(nullable: false),
                    YEAR = table.Column<int>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_VEHICLE", x => x.ID);
                });
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "VEHICLE");
        }
    }
}
