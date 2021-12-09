package com.example.aprochegue.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>
 * - id: Represents order id column. This column is automatically generated
 * and AUTO_INCREMET;
 * </p>
 * <p>
 * - descricao: Represents the description of the table "tema". String NOT NULL;
 * </p>
 * <p>
 * - titulo: Represents the title of the table "tema". String NOT NULL;
 * </p>
 * <p>
 * - assunto: Represents the Subject of the table "tema". String NOT NULL;
 * </p>
 * @author Adan
 * @author Matheus
 * @author Lucas
 * @author Júlia
 * @author Mayara
 * @author Guilherme
 * @since 1.0
 * 
 */
@Entity
@Table(name ="tb_tema")
public class Tema {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String descricao;
	
	@OneToMany(mappedBy = "tema", cascade =  CascadeType.REMOVE)
	@JsonIgnoreProperties("tema")
	private List<Postagem> postagem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}


}
