package com.example.prueba.controller

import com.example.prueba.model.Usuario
import com.example.prueba.repository.UsuarioRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/usuarios")
class UsuarioController(private val usuarioRepository: UsuarioRepository) {

    @GetMapping
    fun obtenerTodos(): List<Usuario> = usuarioRepository.findAll()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun crear(@RequestBody usuario: Usuario): Usuario = usuarioRepository.save(usuario)

    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: Long): Usuario =
        usuarioRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado") }
}
