package filippotimo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rivenditori")
public class Rivenditore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_venditore", nullable = false)
	private TipoVenditore tipoVenditore;

	public Rivenditore() {
	}

	public Rivenditore(String nome, TipoVenditore tipoVenditore) {
		this.nome = nome;
		this.tipoVenditore = tipoVenditore;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoVenditore getTipoVenditore() {
		return tipoVenditore;
	}

	public void setTipoVenditore(TipoVenditore tipoVenditore) {
		this.tipoVenditore = tipoVenditore;
	}
}
