@extends('layout.template')

@section('content')
<div class="col-md-10 col-md-offset-1 container">

    <h1 class="text-primary">Create new Vehicle</h1>
    <hr>
    
    {!! Form::open(array('route' => 'veiculo.store', 'class' => 'form')) !!}
    @if($errors->any())
    <div class="alert alert-danger">
        @foreach($errors->all() as $error)
        <p>{{ $error }}</p>
        @endforeach
    </div>
    @endif
    
    <div class="form-group">			
        {!! Form::text('marca', null, 
        array('required', 
        'class'=>'form-control', 
        'placeholder'=>'Brand')) !!}
    </div>
    
    <div class="form-group">			
        {!! Form::text('modelo', null, 
        array('required', 
        'class'=>'form-control', 
        'placeholder'=>'Model')) !!}
    </div>
    
    
    <div class="form-group">                
        <select name="cor" class="form-control">               
            <option value="Preto">Preto</option>            
            <option value="Prata">Prata</option>
            <option value="Vermelho">Vermelho</option>
            <option value="Azul">Azul</option>
        </select>
    </div>
    
    
    <div class="form-group">			
        {!! Form::text('ano', null, 
        array('required', 
        'class'=>'form-control', 
        'placeholder'=>'Year')) !!}
    </div>
    
    
    <div class="form-group">			
        {!! Form::text('preco', null, 
        array('required', 
        'class'=>'form-control', 
        'placeholder'=>'Price')) !!}
    </div>
    
    <div class="form-group">			
        {!! Form::text('descicao', null, 
        array('required', 
        'class'=>'form-control', 
        'placeholder'=>'Description')) !!}
    </div>
    
     <div class="form-group">                
        <select name="tipo" class="form-control">                  
            <option value="0">Novo</option>            
            <option value="1">Usado</option>
        </select>
    </div>
    
    
    <hr>

    <div class="form-group">
        <a href="{{ route('veiculo.index') }}" class="btn btn-warning">Back</a>
        {!! Form::submit('Save', 
        array('class'=>'btn btn-primary')) !!}

    </div>


    {!! Form::close() !!}






</div>


@stop