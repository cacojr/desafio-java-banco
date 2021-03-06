package com.api.southsystem.sistema.banco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class ServicesAbstract <R extends JpaRepository<T, ID>, T, ID>{

    @Autowired
    protected R repository;

    public T salvar(T object){
        return repository.save(object);
    }

    public List<T> salvarTodos(List<T> object){
        return repository.saveAll(object);
    }

    public List<T> buscarTodos(){
        return repository.findAll();
    }

    public Optional<T> buscarPorId(ID id){
        return repository.findById(id);
    }

    public R getRepository(){
        return repository;
    }
}
