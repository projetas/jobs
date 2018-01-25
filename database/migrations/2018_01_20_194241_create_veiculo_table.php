<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateVeiculoTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
       Schema::create('veiculos', function (Blueprint $table) {
            $table->increments('id');
            $table->string('marca', 40);
            $table->string('modelo', 50);
            $table->string('cor', 30);
            $table->integer('ano');
            $table->decimal('preco'); 
            $table->text('descricao')->nullable();             
            $table->boolean('tipo');
            $table->timestamps();
        });
    }
   
    public function down()
    {
        Schema::dropIfExists('veiculo');
    }
}
