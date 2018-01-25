<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Veiculo extends Model
{
    protected $fillable = ['id','marca','modelo','cor', 'ano', 'preco', 'descricao', 'tipo', 'created_at', 'update_at'];
}
