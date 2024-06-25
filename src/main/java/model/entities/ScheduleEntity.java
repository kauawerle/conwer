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
	
	@Column(name = "hour")
	private String hour;
	
	@Column(name = "date")
	private String date;

	@ManyToOne
	@JoinColumn(name = "id_user")
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "id_client")
	private UserEntity client;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public UserEntity getClient() {
		return client;
	}

	public void setClient(UserEntity client) {
		this.client = client;
	}
	
	
	public ScheduleEntity() {}
	public ScheduleEntity(String hour, String date, UserEntity user, UserEntity client) {
		super();
		this.date = date;
		this.hour = hour;
		this.user = user;
		this.client = client;
	}
	
}
