package org.sysreg.sia.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//import org.sysreg.sia.shared.CampoDTO;
//import org.sysreg.sia.shared.ComarcaDTO;
//import org.sysreg.sia.shared.MunicipioDTO;
//import org.sysreg.sia.shared.ParcelaDTO;
//import org.sysreg.sia.shared.ParcelaDTO.ParcelaDTOId;
//import org.sysreg.sia.shared.ProvinciaDTO;
//import org.sysreg.sia.shared.RecintoDTO;
//import org.sysreg.sia.shared.RecintoDTO.RecintoDTOId;
//import org.sysreg.sia.shared.UsuarioDTO;

@Entity
@Table(name = "USERS")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	
	@Column (unique = true, nullable = false)
	private String username;
	@Column (nullable = false)
	private String password;
    @Column
    private boolean active = true;
	@Column
	private String name;
	@Column
	private String surname;
	@Column
	private String mobile;
	@Column
	private String address;
	@Column
	private String dni;

	@ManyToOne
	@JoinColumn(name = "TOWN_ID", nullable = true)
	private Town town;

	@OneToMany(mappedBy = "user")
	private Set<Field> fields = new HashSet<Field>();

    @ManyToOne
    @JoinColumn(name = "AUTHORITY_ID", nullable = true)
    private Authority authority;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Town getTown() {
		return town;
	}

	public void setTown(Town town) {
		this.town = town;
	}

	public Set<Field> getFields() {
		return fields;
	}

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public void setFields(Set<Field> fields) {
		this.fields = fields;
	}
	//
	// @Override
	// public String toString() {
	// String res = "User [usuario=" + usuario + ", password=" + password +
	// ", nombre=" + nombre + ", apellidos="
	// + apellidos + ", telefono=" + telefono + ", direccion=" + direccion +
	// ", dni=" + dni + ", town="
	// + town.toString() + "]" + '\n';
	// for (Field campo : fields)
	// for (Parcel parcela : campo.getParcelas())
	// for (Enclosure recinto : parcela.getRecintos())
	// res += "Field= " + campo.getNombre() + ", Parcel=" +
	// parcela.getId().getTown().getCodigo()
	// + " " + parcela.getPoligono() + " " + parcela.getParcel() + ", Enclosure"
	// + recinto.toString() + '\n';
	// return res;
	// }

	// public UsuarioDTO toUsuarioDTO() {
	// UsuarioDTO user = new UsuarioDTO(usuario, password, nombre, apellidos,
	// telefono, direccion, dni);
	//
	// ProvinciaDTO prov = new ProvinciaDTO(town.getRegion()
	// .getProvincia().getCodigo(), town.getRegion()
	// .getProvincia().getNombre());
	// ComarcaDTO com = new ComarcaDTO(town.getRegion().getCodigo(),
	// town.getRegion().getNombre(), prov);
	// MunicipioDTO mun = new MunicipioDTO(town.getCodigo(),
	// town.getNombre(), com);
	// user.setTown(mun);
	//
	// // Revisar en el futuro
	// // Carga Eager de fields-parcelas-recintos
	// for (Field campo : this.fields) {
	// CampoDTO campoDTO = new CampoDTO(campo.getCodigo(),
	// campo.getNombre(), user);
	//
	// // Transformamos las parcelas
	// for (Parcel parcela : campo.getParcelas()) {
	// ParcelaDTOId parcelaDTOId = new ParcelaDTOId(mun,
	// parcela.getAgregado(), parcela.getZona(),
	// parcela.getPoligono(), parcela.getParcel());
	//
	// ParcelaDTO parcelaDTO = new ParcelaDTO(parcelaDTOId,
	// parcela.getSuperficie(), parcela.getCoordinates()
	// .toCoordenadasDTO(), campoDTO);
	//
	// // Transformamos los recintos
	// for (Enclosure recinto : parcela.getRecintos()) {
	// RecintoDTOId recintoDTOId = new RecintoDTOId(parcelaDTO,
	// recinto.getRecinto());
	//
	// RecintoDTO recintoDTO = new RecintoDTO(recintoDTOId,
	// recinto.getSuperficie(), recinto.getPendiente(),
	// recinto.getCoefRegadio(), recinto.getCoordinates()
	// .toCoordenadasDTO(), recinto.getUse()
	// .toUsoDTO());
	// // lo añadimos a la parcela
	// parcelaDTO.getRecintos().add(recintoDTO);
	// }
	//
	// // añadimos la parcela vcon los recintos al campo
	// campoDTO.getParcelas().add(parcelaDTO);
	// }
	//
	// // añadimos los fields al usuario
	// user.getFields().add(campoDTO);
	// }
	//
	// return user;
	// }
}