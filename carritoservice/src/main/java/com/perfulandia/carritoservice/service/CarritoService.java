package com.perfulandia.carritoservice.service;

import com.perfulandia.carritoservice.model.Carrito;
import com.perfulandia.carritoservice.repository.CarritoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoService {
    private final CarritoRepository repo;

    //Constructor para poder consumir la interfaz
    public CarritoService(CarritoRepository repo){
        this.repo=repo;
    }

    //Listar
    public List<Carrito> listar(){
        return repo.findAll();
    }
    //Guardar
    public Carrito guardar(Carrito carrito){
        return repo.save(carrito);
    }

    //Buscar por id
    public Carrito buscar(long id){
        return repo.findById(id).orElse(null);
    }
    //Eliminar id
    public void eliminar(long id){
        repo.deleteById(id);
    }
}
