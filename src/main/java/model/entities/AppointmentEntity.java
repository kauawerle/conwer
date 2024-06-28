package model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.mapping.Array;


@Entity
@Table (name = "tb_appointment")
public class AppointmentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "date_appointment")
	private String date;
	
	@Column(name = "hour_appointment")
	private String hour;
	
	@Column(name = "name_client")
	private String name_client;
	
	@Column(name = "name_professional")
	private String name_professional;
	
	@Column(name = "status")
	private int status;
	
	@Column(name = "id_drug")
	private String[] id_drug;

    private long id_service;
	
    private long id_user;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getName_client() {
		return name_client;
	}

	public void setName_client(String name_client) {
		this.name_client = name_client;
	}

	public String getName_professional() {
		return name_professional;
	}

	public void setName_professional(String name_professional) {
		this.name_professional = name_professional;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String[] getId_drug() {
		return id_drug;
	}

	public void setId_drug(String[] id_drug) {
		this.id_drug = id_drug;
	}

	public long getId_service() {
		return id_service;
	}

	public void setId_service(long id_service) {
		this.id_service = id_service;
	}

	public long getId_user() {
		return id_user;
	}

	public void setId_user(long id_user) {
		this.id_user = id_user;
	}

	public AppointmentEntity() {}
	public AppointmentEntity(String name, String date, String hour, String name_client,
			String name_professional, int status, long id_service, long id_user) {
		super();
		this.name = name;
		this.date = date;
		this.hour = hour;
		this.name_client = name_client;
		this.name_professional = name_professional;
		this.status = status;
//		this.id_drug = id_drug;
		this.id_service = id_service;
		this.id_user = id_user;
	}
	
	
}
