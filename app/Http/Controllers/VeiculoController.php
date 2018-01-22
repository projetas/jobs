<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Veiculo;
use Carbon\Carbon;

class VeiculoController extends Controller
{
    public function index() {
        $veiculos = Veiculo::all();
        
        return view('veiculo.index')->withVeiculo($veiculos);
    }

    public function create() {
        return view('veiculo.create');
    }

    public function store(Request $request) {
        $input = $request->all();
        
        $create_at = Carbon::now()->toDateTimeString();

        Veiculo::create($input, $create_at);            
        
        return redirect()->route('veiculo.index');
    }

    public function edit($id) {
        $veiculo = Veiculo::find($id);
        
        return view('veiculo.edit')->with('veiculo', $veiculo);
    }

    public function update($id, Request $request) {
        $veiculo = Veiculo::findOrFail($id);
        
        /*
            $this->validate($request, [
                'nome' => 'required',
            ]);        
        */

        $input = $request->all();   
        $veiculo->fill($input)->save();
        return redirect()->route('veiculo.index');
    }

    public function destroy(Request $request, $id) {
        $veiculo = Veiculo::findOrFail($id);
        
        $veiculo->delete();
        
        return redirect()->route('veiculo.index');
    }

    public function show($id) {
        $veiculo = Veiculo::findOrFail($id);
        
        return view('veiculo.show')->withVeiculo($veiculo);
    }
}
