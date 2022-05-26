package com.ProjetoIntegrador.FazSolidaria.services;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ProjetoIntegrador.FazSolidaria.exception.NegocioException;
import com.ProjetoIntegrador.FazSolidaria.exception.UsuarioNaoEncontradoException;
import com.ProjetoIntegrador.FazSolidaria.input.UsuarioLoginInput;
import com.ProjetoIntegrador.FazSolidaria.model.Usuario;
import com.ProjetoIntegrador.FazSolidaria.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private static final String SENHA_PERSONALIZADA = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,16}$";

	@Autowired
	UsuarioRepository usuarioRepository;

	public List<Usuario> mostrarTodosUsuarios() {
		return usuarioRepository.findAll();
	}

	public Usuario codigoUsuario(Long idProduto) {
		return usuarioRepository.findById(idProduto).orElseThrow(() -> new UsuarioNaoEncontradoException(idProduto));
	}

	public Usuario buscarNome(String nome) {
		return usuarioRepository.findAllByNomeIgnoreCase(nome).orElseThrow(() -> new UsuarioNaoEncontradoException(
				String.format("Nome do usuario '%s' não encontrado no banco de dados FazSolidaria", nome)));
	}

	@Transactional
	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {
		Optional<Usuario> emailExistente = usuarioRepository.findByUsuario(usuario.getUsuario());
		Optional<Usuario> cpfExistente = usuarioRepository.findAllByCpf(usuario.getCpf());

		if (!usuario.getSenha().matches(SENHA_PERSONALIZADA)) {
			throw new NegocioException(String.format("Senha invalida '%s' A senha deve conter pelo menos"
					+ " 1 caracter minusculo, " + " 1 caracter maiúsculo, " + " 1 número e," + " 1 caracter especial e,"
					+ " deve conter o mínimo de 8 e máximo de 15 caracteres.", usuario.getSenha()));
		}
		if (emailExistente.isPresent() && !emailExistente.equals(usuario)) {
			throw new NegocioException(
					String.format("Usuário '%s' duplicado no banco de dados FazSolidaria, digite um novo EMAIL",
							usuario.getUsuario()));
		}
		if (cpfExistente.isPresent() && !cpfExistente.equals(usuario)) {
			throw new NegocioException(String
					.format("CPF '%s' duplicado no banco de dados FazSolidaria, digite um novo CPF", usuario.getCpf()));
		}
		usuario.setSenha(criptografarSenha(usuario.getSenha()));
		return Optional.of(usuarioRepository.save(usuario));

	}

	public Optional<UsuarioLoginInput> validarUsuario(Optional<UsuarioLoginInput> usuarioLogin) {
		Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());
		if (usuario.isPresent()) {
			if (compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {
				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get()
						.setToken(gerarTokenBasico(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha()));
				usuarioLogin.get().setFoto(usuario.get().getFoto());
				usuarioLogin.get().setTipo(usuario.get().getTipo());
				usuarioLogin.get().setSenha(usuario.get().getSenha());

				return usuarioLogin;
			}
		}
		return Optional.empty();
	}

	// origem-destino do objeto
	public void copiaInfoNovas(Usuario proOrigem, Usuario proDestino) {
		BeanUtils.copyProperties(proOrigem, proDestino);
	}

	@Transactional
	public Optional<Usuario> atualizarCadastroUsuario(Usuario usuario) {
		if (usuarioRepository.findById(usuario.getId()).isPresent()) {
			Optional<Usuario> buscarUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
			if (buscarUsuario.isPresent()) {
				if (buscarUsuario.get().getId() != usuario.getId()) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Usuário já existe!", null);
				}
				if (!usuario.getSenha().matches(SENHA_PERSONALIZADA)) {
					throw new NegocioException(
							String.format(
									"Senha invalida '%s' A senha deve conter pelo menos" + " 1 caracter minusculo, "
											+ " 1 caracter maiúsculo, " + " 1 número e," + " 1 caracter especial e,"
											+ " deve conter o mínimo de 8 e máximo de 15 caracteres.",
									usuario.getSenha()));
				}
				usuario.setSenha(criptografarSenha(usuario.getSenha()));
				return Optional.of(usuarioRepository.save(usuario));
			}
		}
		return Optional.empty();
	}

	private String criptografarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);
	}

	private boolean compararSenhas(String senhaDigitada, String senhaDoBanco) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(senhaDigitada, senhaDoBanco);
	}

	private String gerarTokenBasico(String email, String senha) {
		String tokenBase = email + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(tokenBase.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);
	}

}
