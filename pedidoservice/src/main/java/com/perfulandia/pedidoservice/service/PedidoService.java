package com.perfulandia.pedidoservice.service;

import com.perfulandia.pedidoservice.model.Pedido;
import com.perfulandia.pedidoservice.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository repo;

    public PedidoService(PedidoRepository repo) {
        this.repo = repo;
    }

    public List<Pedido> listar() {
        return repo.findAll();
    }

    public Pedido guardar(Pedido pedido) {
        return repo.save(pedido);
    }

    public Pedido buscarPorId(long id) {
        return repo.findById(id).orElse(null);
    }

    public void eliminar(long id) {
        repo.deleteById(id);
    }
}
