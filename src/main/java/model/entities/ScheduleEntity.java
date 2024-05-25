package model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.mapping.Array;

@Entity
@Table (name = "tb_schedule")
public class ScheduleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "consult_list")
	private Array consult_list;

	@Column(name = "disponibility")
	private Array disponibility;
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	private UserEntity user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Array getConsult_list() {
		return consult_list;
	}

	public void setConsult_list(Array consult_list) {
		this.consult_list = consult_list;
	}

	public Array getDisponibility() {
		return disponibility;
	}

	public void setDisponibility(Array disponibility) {
		this.disponibility = disponibility;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public ScheduleEntity() {}
	public ScheduleEntity(long id, Array consult_list, Array disponibility, UserEntity user) {
		super();
		this.id = id;
		this.consult_list = consult_list;
		this.disponibility = disponibility;
		this.user = user;
	}
	
}
