package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteVO> addClienteVO(
            @RequestParam String cedula,
            @RequestParam String nombre,
            @RequestParam String direccion,
            @RequestParam String email,
            @RequestParam String telefono) {
        
        ClienteVO nuevoCliente = new ClienteVO(cedula, nombre, direccion, email, telefono);
        ClienteVO guardado = clienteService.createClienteVO(nuevoCliente);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @GetMapping
    public ResponseEntity<List<ClienteVO>> getAllClienteVOs() {
        List<ClienteVO> clienteVOs = clienteService.getAll();
        if (clienteVOs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(clienteVOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteVO> getClienteVOById(@PathVariable Integer id) {
        return clienteService.getClienteVOById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteVO> updateClienteVO(
            @PathVariable Integer id,
            @RequestParam String cedula,
            @RequestParam String nombre,
            @RequestParam String direccion,
            @RequestParam String email,
            @RequestParam String telefono) {

        if (!clienteService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        ClienteVO clienteActualizado = new ClienteVO(cedula, nombre, direccion, email, telefono);
        clienteActualizado.setClienteId(id);
        ClienteVO actualizado = clienteService.updateClienteVO(clienteActualizado);
        
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClienteVO(@PathVariable Integer id) {
        if (!clienteService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
        }
        clienteService.deleteClienteVO(id);
        return ResponseEntity.ok("Cliente eliminado exitosamente");
    }
}
