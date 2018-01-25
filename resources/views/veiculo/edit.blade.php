@extends('layout.template')

@section('content')

<div class="col-md-10 col-md-offset-1 container">
    <h1>Edit Vehicle: {{ $veiculo->modelo }} </h1>

    <hr>

    {!! Form::model($veiculo, ['method' => 'PUT','route' => ['veiculo.update', $veiculo->id]]) !!}

    @if($errors->any())
    <div class="alert alert-danger">
        @foreach($errors->all() as $error)
        <p>{{ $error }}</p>
        @endforeach
    </div>
    @endif

    <div class="form-group">
        {!! Form::label('marca', 'Brand:', ['class' => 'control-label']) !!}
        {!! Form::text('marca', null, ['class' => 'form-control']) !!}
    </div>    

    <div class="form-group">
        {!! Form::label('modelo', 'Model:', ['class' => 'control-label']) !!}
        {!! Form::text('modelo', null, ['class' => 'form-control']) !!}
    </div>
        
    <div class="form-group">    
        {!! Form::label('cor', 'Color:', ['class' => 'control-label']) !!}        
        <select name="cor" class="form-control">                     
            <option value="Black">Black</option>            
            <option value="Grey">Grey</option>
            <option value="Red">Red</option>
            <option value="Blue">Blue</option>
        </select>
    </div>
    
     <div class="form-group">
        {!! Form::label('ano', 'Year:', ['class' => 'control-label']) !!}
        {!! Form::text('ano', null, ['class' => 'form-control']) !!}
    </div>
    
    <div class="form-group">
        {!! Form::label('preco', 'Price:', ['class' => 'control-label']) !!}
        {!! Form::text('preco', null, ['class' => 'form-control']) !!}
    </div>    
    
    <div class="form-group">
        {!! Form::label('descricao', 'Description:', ['class' => 'control-label']) !!}
        {!! Form::text('descricao', null, ['class' => 'form-control']) !!}
    </div>
        
    <div class="form-group">   
        {!! Form::label('tipo', 'Type:', ['class' => 'control-label']) !!}
        <select name="tipo" class="form-control">                   
            <option value="0">New</option>            
            <option value="1">Used</option>
        </select>
    </div>       
    
    <div class="form-group">
        <a href="{{ route('veiculo.index') }}" class="btn btn-warning">Back</a>
        {!! Form::submit('Update Veiculo    ', ['class' => 'btn btn-primary']) !!}    
    </div>
    
    {!! Form::close() !!}
</div>
@stop