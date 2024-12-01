package com.crustdelivery.backend.controller;

import com.crustdelivery.backend.model.Order;
import com.crustdelivery.backend.model.Route;
import com.crustdelivery.backend.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("routes")
public class RotaController {

    @Autowired
    private RouteService otimizador;

    @PostMapping("/otimizar")
    public List<Route> otimizarRotas(@RequestBody List<Order> pedidos) {

        return otimizador.gerarRotasOtimizadas(pedidos);
    }

    @GetMapping
    public List<Route> findAll() {
        return otimizador.findAll();
    }

    @DeleteMapping("/{id}")
    public void deletarRota(@PathVariable Long id) {
        otimizador.deletarRota(id);
    }
}
