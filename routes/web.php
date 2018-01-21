<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/
Route::get('/', function () {
    return view('home');
});

Route::resource('veiculo', 'VeiculoController');

Route::get("/autocomplete", array('as' => 'autocomplete', 'uses' => 'TarefasController@autocomplete'));


Route::get('/search', 'TarefasController@busca');


Route::get('/home', 'VeiculoController@home');

