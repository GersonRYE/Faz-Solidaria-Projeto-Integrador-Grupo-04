package com.fazSolidaria.fazSolidaria.model;



	import java.util.List;

	import javax.persistence.CascadeType;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.OneToMany;
	import javax.persistence.Table;
	import javax.validation.constraints.Email;
	import javax.validation.constraints.NotNull;
	import javax.validation.constraints.Size;
	import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

	
	@Entity
	@Table(name = "tb_usuario")
	public class Usuario<PostagemModel> {

		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		
		@NotNull
		@Size(min=2,max=100)
		private String nome;
		
		
		@NotNull (message = "O atributo usuário é obrigatório")
		@Email (message = "O atributo usuaério deve ser um  email válido")
		private String usuario;
		
		@NotNull
		@Size(min=8)
		private String senha;
		
		private String foto;
		
		
		@OneToMany(mappedBy = "endereco", cascade = CascadeType.REMOVE)
		@JsonIgnoreProperties("endereco")
		private List<EnderecoModel> usuarios ;
		
		public Usuario(long id,String nome,String usuario,  String senha,String foto, List<EnderecoModel> usuarios) {
			this.id = id;
			this.nome = nome;
			this.usuario = usuario;
			this.senha = senha;
			this.foto = foto;
			this.usuarios = usuarios;
			
			
		}
		
		public Usuario()
		{
			
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getUsuario() {
			return usuario;
		}

		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}

		public String getFoto() {
			return foto;
		}

		public void setFoto(String foto) {
			this.foto = foto;
		}

		public List<EnderecoModel> getUsuarios() {
			return usuarios;
		}

		public void setUsuarios(List<EnderecoModel> usuarios) {
			this.usuarios = usuarios;
		}

		
		

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
