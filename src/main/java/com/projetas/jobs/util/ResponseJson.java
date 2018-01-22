package com.projetas.jobs.util;

import java.util.ArrayList;
import java.util.List;

public class ResponseJson<T> {
    private T data;
    private List<String> erros;

    public ResponseJson(){}

    public ResponseJson(T data, List<String> erros) {
        this.data = data;
        this.erros = erros;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getErros() {
        if(this.erros == null)
            this.erros = new ArrayList<String>();
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }
}
